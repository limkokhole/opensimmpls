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
package com.manolodominguez.opensimmpls.io.osm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.manolodominguez.opensimmpls.scenario.TInternalLink;
import com.manolodominguez.opensimmpls.scenario.TExternalLink;
import com.manolodominguez.opensimmpls.scenario.TScenario;
import com.manolodominguez.opensimmpls.scenario.TLERNode;
import com.manolodominguez.opensimmpls.scenario.TTrafficGeneratorNode;
import com.manolodominguez.opensimmpls.scenario.TTrafficSinkNode;
import com.manolodominguez.opensimmpls.scenario.TActiveLSRNode;
import com.manolodominguez.opensimmpls.scenario.TLSRNode;
import com.manolodominguez.opensimmpls.scenario.TActiveLERNode;

/**
 * This class implements a class that loads a scenario from disk in OSM (Open
 * SimMPLS format).
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 * @version 2.0
 */
public class TOSMLoader {

    /**
     * This method is the constructor of the class. It creates a new instance of
     * TOSMLoader.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     */
    public TOSMLoader() {
        this.scenario = new TScenario();
        this.inputStream = null;
        this.input = null;
        this.configSection = TOSMLoader.DEFAULT_NONE;
    }

    /**
     * This method loads an scenario description from a file formated as OSM.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param inputFile The file where a scenario description is stored.
     * @return true, if the file can be correctly loaded. False on the contrary.
     * @since 2.0
     */
    public boolean load(File inputFile) {
        String stringAux = "";
        this.scenario.setScenarioFile(inputFile);
        try {
            if (inputFile.exists()) {
                this.inputStream = new FileInputStream(inputFile);
                this.input = new BufferedReader(new InputStreamReader(this.inputStream));
                while ((stringAux = this.input.readLine()) != null) { // Read till EOF
                    // This code read lines from the file, sequentially, 
                    // untill it detects tokens that allow to identify 
                    // different sections of the configuration file. 
                    // Do not load blank linkes, comments and lines that
                    // store CRC info (deprecated, but still present in some
                    // scenarios).
                    if ((!stringAux.equals("")) && (!stringAux.startsWith("//")) && (!stringAux.startsWith("@CRC#"))) {
                        switch (this.configSection) {
                            case TOSMLoader.DEFAULT_NONE:
                                if (stringAux.startsWith("@?Escenario")) {
                                    this.configSection = TOSMLoader.SCENARIO;
                                } else if (stringAux.startsWith("@?Topologia")) {
                                    this.configSection = TOSMLoader.TOPOLOGY;
                                } else if (stringAux.startsWith("@?Simulacion")) {
                                    this.configSection = TOSMLoader.SIMULATION;
                                } else if (stringAux.startsWith("@?Analisis")) {
                                    this.configSection = TOSMLoader.ANALISYS;
                                }
                                break;
                            case TOSMLoader.SCENARIO:
                                loadScenario(stringAux);
                                break;
                            case TOSMLoader.TOPOLOGY:
                                loadTopology(stringAux);
                                break;
                            case TOSMLoader.SIMULATION:
                                if (stringAux.startsWith("@!Simulacion")) {
                                    this.configSection = TOSMLoader.DEFAULT_NONE;
                                }
                                break;
                            case TOSMLoader.ANALISYS:
                                if (stringAux.startsWith("@!Analisis")) {
                                    this.configSection = TOSMLoader.DEFAULT_NONE;
                                }
                                break;
                            default:
                                // Another unexpected line has been read, 
                                // and is discarded silently.
                                break;
                        }
                    }
                }
                this.inputStream.close();
                this.input.close();
                this.scenario.setAlreadySaved(true);
                this.scenario.setModified(false);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void loadTopology(String topologyString) {
        if (topologyString.startsWith("@!Topologia")) {
            this.configSection = TOSMLoader.DEFAULT_NONE;
        } else if (topologyString.startsWith("#Receptor#")) {
            TTrafficSinkNode receiver = new TTrafficSinkNode(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, TOSMLoader.DEFAULT_IPV4_ADDRESS, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (receiver.fromOSMString(topologyString)) {
                this.scenario.getTopology().addNode(receiver);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(receiver.getNodeID());
                this.scenario.getTopology().getIPv4AddressGenerator().setIPv4AddressIfGreater(receiver.getIPv4Address());
            }
            receiver = null;
        } else if (topologyString.startsWith("#Emisor#")) {
            TTrafficGeneratorNode sender = new TTrafficGeneratorNode(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, TOSMLoader.DEFAULT_IPV4_ADDRESS, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (sender.fromOSMString(topologyString)) {
                this.scenario.getTopology().addNode(sender);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(sender.getNodeID());
                this.scenario.getTopology().getIPv4AddressGenerator().setIPv4AddressIfGreater(sender.getIPv4Address());
            }
            sender = null;
        } else if (topologyString.startsWith("#LER#")) {
            TLERNode ler = new TLERNode(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, TOSMLoader.DEFAULT_IPV4_ADDRESS, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (ler.fromOSMString(topologyString)) {
                this.scenario.getTopology().addNode(ler);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(ler.getNodeID());
                this.scenario.getTopology().getIPv4AddressGenerator().setIPv4AddressIfGreater(ler.getIPv4Address());
            }
            ler = null;
        } else if (topologyString.startsWith("#LERA#")) {
            TActiveLERNode activeLER = new TActiveLERNode(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, TOSMLoader.DEFAULT_IPV4_ADDRESS, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (activeLER.fromOSMString(topologyString)) {
                this.scenario.getTopology().addNode(activeLER);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(activeLER.getNodeID());
                this.scenario.getTopology().getIPv4AddressGenerator().setIPv4AddressIfGreater(activeLER.getIPv4Address());
            }
            activeLER = null;
        } else if (topologyString.startsWith("#LSR#")) {
            TLSRNode lsr = new TLSRNode(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, TOSMLoader.DEFAULT_IPV4_ADDRESS, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (lsr.fromOSMString(topologyString)) {
                this.scenario.getTopology().addNode(lsr);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(lsr.getNodeID());
                this.scenario.getTopology().getIPv4AddressGenerator().setIPv4AddressIfGreater(lsr.getIPv4Address());
            }
            lsr = null;
        } else if (topologyString.startsWith("#LSRA#")) {
            TActiveLSRNode activeLSR = new TActiveLSRNode(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, TOSMLoader.DEFAULT_IPV4_ADDRESS, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (activeLSR.fromOSMString(topologyString)) {
                this.scenario.getTopology().addNode(activeLSR);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(activeLSR.getNodeID());
                this.scenario.getTopology().getIPv4AddressGenerator().setIPv4AddressIfGreater(activeLSR.getIPv4Address());
            }
            activeLSR = null;
        } else if (topologyString.startsWith("#EnlaceExterno#")) {
            TExternalLink externalLink = new TExternalLink(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (externalLink.fromOSMString(topologyString)) {
                this.scenario.getTopology().addLink(externalLink);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(externalLink.getID());
            }
            externalLink = null;
        } else if (topologyString.startsWith("#EnlaceInterno#")) {
            TInternalLink internalLink = new TInternalLink(TOSMLoader.DEFAULT_TOPOLOGY_ELEMENT_ID, this.scenario.getTopology().getEventIDGenerator(), this.scenario.getTopology());
            if (internalLink.fromOSMString(topologyString)) {
                this.scenario.getTopology().addLink(internalLink);
                this.scenario.getTopology().getElementsIDGenerator().setIdentifierIfGreater(internalLink.getID());
            }
            internalLink = null;
        }
    }

    private void loadScenario(String scenarioString) {
        if (scenarioString.startsWith("@!Escenario")) {
            this.configSection = TOSMLoader.DEFAULT_NONE;
        } else if (scenarioString.startsWith("#Titulo#")) {
            if (!this.scenario.unmarshallTitle(scenarioString)) {
                this.scenario.setTitle(TOSMLoader.DEFAULT_TITLE);
            }
        } else if (scenarioString.startsWith("#Autor#")) {
            if (!this.scenario.unmarshallAuthor(scenarioString)) {
                this.scenario.setAuthor(TOSMLoader.DEFAULT_AUTHOR);
            }
        } else if (scenarioString.startsWith("#Descripcion#")) {
            if (!this.scenario.unmarshallDescription(scenarioString)) {
                this.scenario.setDescription(TOSMLoader.DEFAULT_DESCRIPTION);
            }
        } else if (scenarioString.startsWith("#Temporizacion#")) {
            if (!this.scenario.getSimulation().unmarshallTimeParameters(scenarioString)) {
                this.scenario.getSimulation().setSimulationLengthInNs(TOSMLoader.DEFAULT_SIMULATION_LENGTH_IN_NS);
                this.scenario.getSimulation().setSimulationTickDurationInNs(TOSMLoader.DEFAULT_SIMULATION_TICK_DURATION_IN_NS);
            }
        }
    }

    /**
     * This method gets the scenario that has been loaded from file.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return TScenario, the scenario loaded from a file. Null if no scenario
     * has been loaded.
     * @since 2.0
     */
    public TScenario getScenario() {
        return this.scenario;
    }

    private static final int DEFAULT_NONE = 0;
    private static final int SCENARIO = 1;
    private static final int TOPOLOGY = 2;
    private static final int SIMULATION = 3;
    private static final int ANALISYS = 4;

    private static final int DEFAULT_SIMULATION_LENGTH_IN_NS = 500;
    private static final int DEFAULT_SIMULATION_TICK_DURATION_IN_NS = 1;
    private static final String DEFAULT_IPV4_ADDRESS = "10.0.0.1";
    private static final int DEFAULT_TOPOLOGY_ELEMENT_ID = 0;
    private static final String DEFAULT_TITLE = "";
    private static final String DEFAULT_AUTHOR = "";
    private static final String DEFAULT_DESCRIPTION = "";
    
    
    private int configSection;
    private TScenario scenario;
    private FileInputStream inputStream;
    private BufferedReader input;
}
