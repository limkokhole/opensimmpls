/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manolodominguez.opensimmpls.hardware.ports;

import com.manolodominguez.opensimmpls.commons.EIDGeneratorOverflow;
import java.util.Iterator;
import java.util.LinkedList;
import com.manolodominguez.opensimmpls.scenario.simulationevents.TSimulationEventPacketReceived;
import com.manolodominguez.opensimmpls.scenario.TStats;
import com.manolodominguez.opensimmpls.scenario.TNode;
import com.manolodominguez.opensimmpls.protocols.TAbstractPDU;
import static com.manolodominguez.opensimmpls.commons.UnitsTranslations.OCTETS_PER_MEGABYTE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a I/O port that follow a FIFO scheme to dispatch
 * packets.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 * @version 2.0
 */
public class TFIFOPort extends TPort {

    /**
     * This method is the constructor of the class. It creates a new instance of
     * TFIFOPort.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     * @param portID the identifier of the port. This is the unique identifier
     * ti distinguish the port within the parent port set.
     * @param parentPortSet A reference to the parent port set this port belongs
     * to.
     */
    public TFIFOPort(TPortSet parentPortSet, int portID) {
        super(parentPortSet, portID);
        this.buffer = new LinkedList();
        this.packetRead = null;
        this.isUnlimitedBuffer = false;
    }

    /**
     * This method allow to skip size limitation of the buffer and, hence,
     * configure the port as an ideal port, with unlimited space.
     *
     * @param unlimitedBuffer TRUE if the port is going to be defined as an
     * ideal one (unlimited space on iterator). FALSE, on the contrary.
     * @since 2.0
     */
    @Override
    public void setUnlimitedBuffer(boolean unlimitedBuffer) {
        this.isUnlimitedBuffer = unlimitedBuffer;
    }

    /**
     * This method discard the packet passed as an argument from the buffer.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param packet The packet to be discarded from the buffer.
     * @since 2.0
     */
    @Override
    public void discardPacket(TAbstractPDU packet) {
        this.getPortSet().getParentNode().discardPacket(packet);
    }

    /**
     * This method put a new packet in the buffer of the port.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param packet Packet to be inserted in the buffer of the port.
     * @since 2.0
     */
    @Override
    public void addPacket(TAbstractPDU packet) {
        TFIFOPortSet parentPortSetAux = (TFIFOPortSet) this.parentPortSet;
        parentPortSetAux.portSetSemaphore.setRed();
        this.semaphore.setRed();
        TNode parentNode = this.parentPortSet.getParentNode();
        long eventID = ZERO;
        try {
            eventID = parentNode.eventIdentifierGenerator.getNextIdentifier();
        } catch (EIDGeneratorOverflow ex) {
            this.logger.error(ex.getMessage(), ex);
        }
        int packetSubtype = packet.getSubtype();
        if (this.isUnlimitedBuffer) {
            this.buffer.addLast(packet);
            parentPortSetAux.increasePortSetOccupancy(packet.getSize());
            TSimulationEventPacketReceived packetReceivedEvent = new TSimulationEventPacketReceived(parentNode, eventID, this.getPortSet().getParentNode().getCurrentTimeInstant(), packetSubtype, packet.getSize());
            parentNode.simulationEventsListener.captureSimulationEvents(packetReceivedEvent);
            if (this.getPortSet().getParentNode().getStats() != null) {
                this.getPortSet().getParentNode().getStats().addStatEntry(packet, TStats.INCOMING);
            }
        } else {
            if ((parentPortSetAux.getPortSetOccupancy() + packet.getSize()) <= (parentPortSetAux.getBufferSizeInMBytes() * OCTETS_PER_MEGABYTE.getUnits())) {
                this.buffer.addLast(packet);
                parentPortSetAux.increasePortSetOccupancy(packet.getSize());
                TSimulationEventPacketReceived packetReceivedEvent = new TSimulationEventPacketReceived(parentNode, eventID, this.getPortSet().getParentNode().getCurrentTimeInstant(), packetSubtype, packet.getSize());
                parentNode.simulationEventsListener.captureSimulationEvents(packetReceivedEvent);
                if (this.getPortSet().getParentNode().getStats() != null) {
                    this.getPortSet().getParentNode().getStats().addStatEntry(packet, TStats.INCOMING);
                }
            } else {
                this.discardPacket(packet);
            }
        }
        this.semaphore.setGreen();
        parentPortSetAux.portSetSemaphore.setGreen();
    }

    /**
     * This method put a new packet in the buffer of the port. In fact, this do
     * the same than addPacket(p) method, but does not generates simulation
     * events but do it silently.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param packet The packet to be inserted in the buffer of the port.
     * @since 2.0
     */
    @Override
    public void reEnqueuePacket(TAbstractPDU packet) {
        TFIFOPortSet parentPortSetAux = (TFIFOPortSet) this.parentPortSet;
        parentPortSetAux.portSetSemaphore.setRed();
        this.semaphore.setRed();
        if (this.isUnlimitedBuffer) {
            this.buffer.addLast(packet);
            parentPortSetAux.increasePortSetOccupancy(packet.getSize());
        } else {
            if ((parentPortSetAux.getPortSetOccupancy() + packet.getSize()) <= (parentPortSetAux.getBufferSizeInMBytes() * OCTETS_PER_MEGABYTE.getUnits())) {
                this.buffer.addLast(packet);
                parentPortSetAux.increasePortSetOccupancy(packet.getSize());
            } else {
                this.discardPacket(packet);
            }
        }
        this.semaphore.setGreen();
        parentPortSetAux.portSetSemaphore.setGreen();
    }

    /**
     * This method reads an returns the next packet of the buffer according to
     * FIFO policy.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return The read packet
     * @since 2.0
     */
    @Override
    public TAbstractPDU getPacket() {
        TFIFOPortSet parentPortSetAux = (TFIFOPortSet) this.parentPortSet;
        parentPortSetAux.portSetSemaphore.setRed();
        this.semaphore.setRed();
        this.packetRead = (TAbstractPDU) this.buffer.removeFirst();
        if (!this.isUnlimitedBuffer) {
            parentPortSetAux.decreasePortSetOccupancySize(this.packetRead.getSize());
        }
        this.semaphore.setGreen();
        parentPortSetAux.portSetSemaphore.setGreen();
        return this.packetRead;
    }

    /**
     * This method compute whether it is possible or not to switch the next
     * packet in the buffer having the number of octets (specified as an
     * argument) that the port can switch in the current moment.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param octets The number of octets that the port can switch in this
     * moment.
     * @return TRUE, if we can switch the next packet of the buffer at this
     * moment. Otherwise, FALSE.
     * @since 2.0
     */
    @Override
    public boolean canSwitchPacket(int octets) {
        this.semaphore.setRed();
        this.packetRead = (TAbstractPDU) this.buffer.getFirst();
        this.semaphore.setGreen();
        return this.packetRead.getSize() <= octets;
    }

    /**
     * This method computes the congestion level of the port.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return A number, without decimals, between 0 and 100, which will be the
     * congestion level as a percentage.
     * @since 2.0
     */
    @Override
    public long getCongestionLevel() {
        if (this.isUnlimitedBuffer) {
            return ZERO;
        }
        TFIFOPortSet parentPortSetAux = (TFIFOPortSet) this.parentPortSet;
        long congestion = (parentPortSetAux.getPortSetOccupancy() * ONE_HUNDRED) / (parentPortSetAux.getBufferSizeInMBytes() * OCTETS_PER_MEGABYTE.getUnits());
        return congestion;
    }

    /**
     * This method checks whether there is a packet in the buffer waiting to be
     * switched/routed, or not.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return TRUE, if there is a packet waiting to be switched/routed.
     * Otherwise, FALSE.
     * @since 2.0
     */
    @Override
    public boolean thereIsAPacketWaiting() {
        return this.buffer.size() > ZERO;
    }

    /**
     * This method computes and returns the number of octets that are currently
     * used by packets in the buffer of the port.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return Size, in octects, used by packets in the buffer of the port.
     * @since 2.0
     */
    @Override
    public long getOccupancy() {
        if (this.isUnlimitedBuffer) {
            this.semaphore.setRed();
            int occupancy = ZERO;
            TAbstractPDU packet = null;
            Iterator iterator = this.buffer.iterator();
            while (iterator.hasNext()) {
                packet = (TAbstractPDU) iterator.next();
                if (packet != null) {
                    occupancy += packet.getSize();
                }
            }
            this.semaphore.setGreen();
            return occupancy;
        }
        TFIFOPortSet parentPortSetAux = (TFIFOPortSet) parentPortSet;
        return parentPortSetAux.getPortSetOccupancy();
    }

    /**
     * This method computes and returns the number of packets stored in the
     * buffer of the port.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return The total number of packets stored in the buffer of the port.
     * @since 2.0
     */
    @Override
    public int getNumberOfPackets() {
        return this.buffer.size();
    }

    /**
     * This method reset attributes of the class as when created by the
     * constructor.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     */
    @Override
    public void reset() {
        this.semaphore.setRed();
        Iterator iterator = this.buffer.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.semaphore.setGreen();
    }

    private LinkedList buffer;
    private TAbstractPDU packetRead;
    private boolean isUnlimitedBuffer;
    private final Logger logger = LoggerFactory.getLogger(TFIFOPort.class);
    
    private static final int ZERO = 0;
    private static final int ONE_HUNDRED = 100;
}
