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
package com.manolodominguez.opensimmpls.ui.dialogs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.manolodominguez.opensimmpls.scenario.TLinkConfig;
import com.manolodominguez.opensimmpls.scenario.TTopology;
import com.manolodominguez.opensimmpls.scenario.TNode;
import com.manolodominguez.opensimmpls.ui.utils.TImagesBroker;

/**
 * Esta clase implementa una ventana que permite configurar un enlace de la
 * topolog�a.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class JVentanaEnlace extends javax.swing.JDialog {

    /**
     * Crea una nueva instancia de JVentanaEmisor
     * @param t Topolog�a dentro de la cual est� insertado el enlace.
     * @param di Dispensador de im�genes global de la aplicaci�n.
     * @param parent Ventana padre donde se ubicar� esta ventana de tipo JVentanaEnlace.
     * @param modal TRUE indica que la ventana impedir� que se pueda seleccionar nada de la interfza
     * hasta que se cierre. FALSE indica que esto no es asi.
     * @since 2.0
     */
    public JVentanaEnlace(TTopology t, TImagesBroker di, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ventanaPadre = parent;
        dispensadorDeImagenes = di;
        topo = t;
        configEnlace = null;
        initComponents();
        initComponents2();
    }

    /**
     * Este m�todo configura aspectos de la ventana que no han podido ser configurados
     * en el constructor.
     * @since 2.0
     */    
    public void initComponents2() {
        java.awt.Dimension tamFrame=this.getSize();
        java.awt.Dimension tamPadre=ventanaPadre.getSize();
        setLocation((tamPadre.width-tamFrame.width)/2, (tamPadre.height-tamFrame.height)/2);
        BCKUPNombre = null;
        BCKUPMostrarNombre = false;
        BCKUPCrearEstadisticas = false;
        BCKUPDelay = 1000;
        this.delayFacil.removeAllItems();
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaHija.Personalized"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaHija.Too_fast"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaHija.Fast"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaHija.Normal"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaHija.Low"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaHija.Too_low"));
        this.delayFacil.setSelectedIndex(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelPestanias = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        iconoEnlace = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        nombreEnlace = new javax.swing.JTextField();
        verNombre = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectorExtremoDerecho = new javax.swing.JComboBox();
        selectorExtremoIzquierdo = new javax.swing.JComboBox();
        selectorPuertoDerecho = new javax.swing.JComboBox();
        selectorPuertoIzquierdo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelRapido = new javax.swing.JPanel();
        iconoEnlace1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        delayFacil = new javax.swing.JComboBox();
        panelAvanzado = new javax.swing.JPanel();
        iconoEnlace2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        delayAvanzado = new javax.swing.JSlider();
        etiquetaDelay = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations"); // NOI18N
        setTitle(bundle.getString("VentanaEnlace.titulo")); // NOI18N
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPestanias.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace.setIcon(dispensadorDeImagenes.obtenerIcono(TImagesBroker.ENLACE));
        iconoEnlace.setText(bundle.getString("VentanaEnlace.descripcion")); // NOI18N
        panelGeneral.add(iconoEnlace, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        etiquetaNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        etiquetaNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre.setText(bundle.getString("VentanaEnlace.etiquetaNombre")); // NOI18N
        panelGeneral.add(etiquetaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));

        nombreEnlace.setToolTipText(bundle.getString("JVentanaEnlace.tooltip.NombreEnlace")); // NOI18N
        panelGeneral.add(nombreEnlace, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 80, 110, -1));

        verNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        verNombre.setText(bundle.getString("VentanaEnlace.verNombre")); // NOI18N
        verNombre.setToolTipText(bundle.getString("JVentanaEnlace.tooltip.VerNombre")); // NOI18N
        panelGeneral.add(verNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 80, -1, -1));

        jLabel1.setIcon(dispensadorDeImagenes.obtenerIcono(TImagesBroker.ENLACE));
        panelGeneral.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 120, 50, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("VentanaEnlace.etiquetaExtremoIzquierdo")); // NOI18N
        panelGeneral.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 135, 130, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(bundle.getString("VentanaEnlace.etiquetaExtremoDerecho")); // NOI18N
        panelGeneral.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 135, 130, -1));

        selectorExtremoDerecho.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorExtremoDerecho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        selectorExtremoDerecho.setToolTipText(bundle.getString("JVentanaEnlace.tooltip.ExtremoIzquierdo")); // NOI18N
        selectorExtremoDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCambioNodoDerecho(evt);
            }
        });
        panelGeneral.add(selectorExtremoDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 90, -1));

        selectorExtremoIzquierdo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorExtremoIzquierdo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        selectorExtremoIzquierdo.setToolTipText(bundle.getString("JVentanaEnlace.tooltip.extremoDerecho")); // NOI18N
        selectorExtremoIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCambioNodoIzquierdo(evt);
            }
        });
        panelGeneral.add(selectorExtremoIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 160, 90, -1));

        selectorPuertoDerecho.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorPuertoDerecho.setToolTipText(bundle.getString("JVentanaEnlace.tooltip.puertoEntrada")); // NOI18N
        panelGeneral.add(selectorPuertoDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        selectorPuertoIzquierdo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorPuertoIzquierdo.setToolTipText(bundle.getString("JVentanaEnlace.tooltip.puertosalida")); // NOI18N
        panelGeneral.add(selectorPuertoIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 160, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(bundle.getString("JVentanaEnlace.:")); // NOI18N
        panelGeneral.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 165, 10, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(bundle.getString("JVentanaEnlace.:")); // NOI18N
        panelGeneral.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 165, 10, -1));

        panelPestanias.addTab(bundle.getString("VentanaEnlace.tabs.General"), panelGeneral); // NOI18N

        panelRapido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace1.setIcon(dispensadorDeImagenes.obtenerIcono(TImagesBroker.ASISTENTE));
        iconoEnlace1.setText(bundle.getString("JVentanaEnlace.Rapida.Descripcion")); // NOI18N
        panelRapido.add(iconoEnlace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText(bundle.getString("JVentanaEnlace.Link_speed")); // NOI18N
        panelRapido.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 100, -1));

        delayFacil.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        delayFacil.setMaximumRowCount(6);
        delayFacil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Personalizado", "Very high", "High", "Normal", "Low", "Very low" }));
        delayFacil.setSelectedIndex(3);
        delayFacil.setToolTipText(bundle.getString("JVentanaEnlace.Select_the_link_speed")); // NOI18N
        delayFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnDelayFacil(evt);
            }
        });
        panelRapido.add(delayFacil, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 180, -1));

        panelPestanias.addTab(bundle.getString("VentanaEnlace.tabs.Fast"), panelRapido); // NOI18N

        panelAvanzado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace2.setIcon(dispensadorDeImagenes.obtenerIcono(TImagesBroker.AVANZADA));
        iconoEnlace2.setText(bundle.getString("JVentanaEnlace.Advanced_and_complete_link_configuration.")); // NOI18N
        panelAvanzado.add(iconoEnlace2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText(bundle.getString("JVentanaEnlace.Link_delay")); // NOI18N
        panelAvanzado.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 100, -1));

        delayAvanzado.setMajorTickSpacing(1000);
        delayAvanzado.setMaximum(500000);
        delayAvanzado.setMinimum(1);
        delayAvanzado.setMinorTickSpacing(1000);
        delayAvanzado.setToolTipText(bundle.getString("JVentanaEnlace.Slide_it_to_set_the_link_delay.")); // NOI18N
        delayAvanzado.setValue(125000);
        delayAvanzado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                clicEnDelayAvanzado(evt);
            }
        });
        panelAvanzado.add(delayAvanzado, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 105, 150, -1));

        etiquetaDelay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        etiquetaDelay.setForeground(new java.awt.Color(102, 102, 102));
        etiquetaDelay.setText(bundle.getString("JVentanaEnlace.500_ns.")); // NOI18N
        panelAvanzado.add(etiquetaDelay, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 105, 70, -1));

        panelPestanias.addTab(bundle.getString("VentanaEnlace.tabs.Advanced"), panelAvanzado); // NOI18N

        panelPrincipal.add(panelPestanias, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 370, 240));

        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton2.setIcon(dispensadorDeImagenes.obtenerIcono(TImagesBroker.ACEPTAR));
        jButton2.setMnemonic(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaEnlace.botones.mne.Aceptar").charAt(0));
        jButton2.setText(bundle.getString("VentanaEnlace.boton.Ok")); // NOI18N
        jButton2.setToolTipText(bundle.getString("JVentanaEnlace.Adds_the_link_to_the_topology.")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnAceptar(evt);
            }
        });
        panelBotones.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 115, -1));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton3.setIcon(dispensadorDeImagenes.obtenerIcono(TImagesBroker.CANCELAR));
        jButton3.setMnemonic(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaEnlace.botones.mne.Cancelar").charAt(0));
        jButton3.setText(bundle.getString("VentanaEnlace.boton.Cancel")); // NOI18N
        jButton3.setToolTipText(bundle.getString("JVentanaEnlace.Cancel_the_operation")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCancelar(evt);
            }
        });
        panelBotones.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 15, 115, -1));

        panelPrincipal.add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 255, 400, 55));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void clicEnCambioNodoDerecho(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCambioNodoDerecho
    this.selectorPuertoDerecho.removeAllItems();
    this.selectorPuertoDerecho.addItem("");
    this.selectorPuertoDerecho.setSelectedIndex(0);
    if (this.selectorExtremoDerecho.getSelectedIndex() != 0) {
        TNode seleccionado = topo.getFirstNodeNamed((String) selectorExtremoDerecho.getSelectedItem());
        Iterator it = topo.getNodesIterator();
        TNode nt;
        if (seleccionado != null) {
//          Actualizar los ports de dicho nodo
            int i=0;
            for (i=0; i<seleccionado.getPorts().getNumberOfPorts(); i++) {
                if (seleccionado.getPorts().getPort(i).isAvailable())
                    this.selectorPuertoDerecho.addItem(""+i);
            }
//          Actualizar los ports de dicho nodo
        }
    }
}//GEN-LAST:event_clicEnCambioNodoDerecho

private void clicEnCambioNodoIzquierdo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCambioNodoIzquierdo
    this.selectorExtremoDerecho.removeAllItems();
    this.selectorExtremoDerecho.addItem("");
    this.selectorExtremoDerecho.setSelectedIndex(0);
    this.selectorPuertoIzquierdo.removeAllItems();
    this.selectorPuertoIzquierdo.addItem("");
    this.selectorPuertoIzquierdo.setSelectedIndex(0);
    this.selectorPuertoDerecho.removeAllItems();
    this.selectorPuertoDerecho.addItem("");
    this.selectorPuertoDerecho.setSelectedIndex(0);
    if (this.selectorExtremoIzquierdo.getSelectedIndex() != 0) {
        TNode seleccionado = topo.getFirstNodeNamed((String) selectorExtremoIzquierdo.getSelectedItem());
        Iterator it = topo.getNodesIterator();
        TNode nt;
        if (seleccionado != null) {
//          Actualizar los ports de dicho nodo
            int i=0;
            for (i=0; i<seleccionado.getPorts().getNumberOfPorts(); i++) {
                if (seleccionado.getPorts().getPort(i).isAvailable())
                    this.selectorPuertoIzquierdo.addItem(""+i);
            }
//          Actualizar los ports de dicho nodo
            while (it.hasNext()) {
                nt = (TNode) it.next();
                if (!nt.getName().equals(seleccionado.getName())) {
                    if (nt.hasAvailablePorts()) {
                        if (!topo.isThereAnyLinkThatJoins(nt.getNodeID(), seleccionado.getNodeID())) {
                            switch (seleccionado.getNodeType()) {
                                case TNode.SENDER: {
                                    if ((nt.getNodeType() == TNode.LER) || (nt.getNodeType() == TNode.ACTIVE_LER)) {
                                        selectorExtremoDerecho.addItem(nt.getName());
                                    }
                                    break;
                                }
                                case TNode.RECEIVER: {
                                    if ((nt.getNodeType() == TNode.LER) || (nt.getNodeType() == TNode.ACTIVE_LER)) {
                                        selectorExtremoDerecho.addItem(nt.getName());
                                    }
                                    break;
                                }
                                case TNode.LER: {
                                    selectorExtremoDerecho.addItem(nt.getName());
                                    break;
                                }
                                case TNode.ACTIVE_LER: {
                                    selectorExtremoDerecho.addItem(nt.getName());
                                    break;
                                }
                                case TNode.LSR: {
                                    if ((nt.getNodeType() == TNode.LER) || (nt.getNodeType() == TNode.ACTIVE_LER)
                                       || (nt.getNodeType() == TNode.LSR) || (nt.getNodeType() == TNode.ACTIVE_LSR)) {
                                        selectorExtremoDerecho.addItem(nt.getName());
                                    }
                                    break;
                                }
                                case TNode.ACTIVE_LSR: {
                                    if ((nt.getNodeType() == TNode.LER) || (nt.getNodeType() == TNode.ACTIVE_LER)
                                       || (nt.getNodeType() == TNode.LSR) || (nt.getNodeType() == TNode.ACTIVE_LSR)) {
                                        selectorExtremoDerecho.addItem(nt.getName());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}//GEN-LAST:event_clicEnCambioNodoIzquierdo

/**
 * Este m�todo carga todos los nodos de la topolog�a en una lista para poder
 * seleccionar dentro de ella los dos nodos que va a unir el enlace.
 * @since 2.0
 */
public void cargarNodosPorDefecto() {
    Iterator it = topo.getNodesIterator();
    TNode nt;
    while (it.hasNext()) {
        nt = (TNode) it.next();
        if (nt.hasAvailablePorts()) {
            selectorExtremoIzquierdo.addItem(nt.getName());
            selectorExtremoIzquierdo.setSelectedIndex(0);
        }
    }
}

private void clicEnDelayFacil(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnDelayFacil
    switch (this.delayFacil.getSelectedIndex()) {
        case 0: {
            // dejo como est� el delay en la configuraci�n avanzada
            this.delayFacil.setSelectedIndex(0);
            break;
        }
        case 1: {
            this.delayAvanzado.setValue(1000);
            this.delayFacil.setSelectedIndex(1);
            break;
        }
        case 2: {
            this.delayAvanzado.setValue(62500);
            this.delayFacil.setSelectedIndex(2);
            break;
        }
        case 3: {
            this.delayAvanzado.setValue(125000);
            this.delayFacil.setSelectedIndex(3);
            break;
        }
        case 4: {
            this.delayAvanzado.setValue(187500);
            this.delayFacil.setSelectedIndex(4);
            break;
        }
        case 5: {
            this.delayAvanzado.setValue(250000);
            this.delayFacil.setSelectedIndex(5);
            break;
        }
    }
    this.etiquetaDelay.setText(this.delayAvanzado.getValue() + java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaEnlace._ns."));
}//GEN-LAST:event_clicEnDelayFacil

private void clicEnDelayAvanzado(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_clicEnDelayAvanzado
    this.delayFacil.setSelectedIndex(0);
    this.etiquetaDelay.setText(this.delayAvanzado.getValue() + java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("JVentanaEnlace._ns."));
}//GEN-LAST:event_clicEnDelayAvanzado

private void clicEnCancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCancelar
    if (this.reconfigurando) {
        configEnlace.setName(BCKUPNombre);
        configEnlace.setShowName(BCKUPMostrarNombre);
        configEnlace.setLinkDelay(BCKUPDelay);
        this.reconfigurando = false;
        configEnlace.setWellConfigured(true);
    } else {
        configEnlace.setWellConfigured(false);
    }
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_clicEnCancelar

private void clicEnAceptar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnAceptar
    configEnlace.setWellConfigured(true);
    configEnlace.setName(nombreEnlace.getText());
    configEnlace.setShowName(verNombre.isSelected());
    configEnlace.setLinkDelay(this.delayAvanzado.getValue());
    
    if (!this.reconfigurando) {
        configEnlace.setHeadEndNodeName((String) selectorExtremoIzquierdo.getSelectedItem());
        configEnlace.setTailEndNodeName((String) selectorExtremoDerecho.getSelectedItem());
        configEnlace.discoverLinkType(topo);
        if (((String)this.selectorPuertoIzquierdo.getSelectedItem()).equals("")) {
            configEnlace.setHeadEndNodePortID(-1);
        } else {
            String aux = (String) this.selectorPuertoIzquierdo.getSelectedItem();
            int aux2 = Integer.valueOf(aux).intValue();
            configEnlace.setHeadEndNodePortID(aux2);
        }
        if (((String)this.selectorPuertoDerecho.getSelectedItem()).equals("")) {
            configEnlace.setTailEndNodePortID(-1);
        } else {
            String aux = (String) this.selectorPuertoDerecho.getSelectedItem();
            int aux2 = Integer.valueOf(aux).intValue();
            configEnlace.setTailEndNodePortID(aux2);
        }
    }
    int error = configEnlace.validateConfig(topo, this.reconfigurando);
    if (error != TLinkConfig.OK) {
        JVentanaAdvertencia va = new JVentanaAdvertencia(ventanaPadre, true, dispensadorDeImagenes);
        va.mostrarMensaje(configEnlace.getErrorMessage(error));
        va.show();
    } else {
        this.setVisible(false);
        this.dispose();
    }
}//GEN-LAST:event_clicEnAceptar

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        configEnlace.setWellConfigured(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * Este m�todo permite cargar en la ventana la configuraci�n actual del enlace que estamos
     * configurando.
     * @since 2.0
     * @param recfg TRUE indica que se est� reconfigurando el enlace. FALSE indica que el enlace se
     * est� insertando nuevo.
     * @param tcenlace El enlace que estamos configurando.
     */    
    public void ponerConfiguracion(TLinkConfig tcenlace, boolean recfg) {
        configEnlace = tcenlace;
        if (recfg) {
            this.reconfigurando = recfg;
            BCKUPNombre = tcenlace.getName();
            BCKUPMostrarNombre = tcenlace.nameMustBeDisplayed();
            BCKUPDelay = tcenlace.getLinkDelay();

            this.nombreEnlace.setText(tcenlace.getName());
            this.verNombre.setSelected(tcenlace.nameMustBeDisplayed());
            this.delayFacil.setSelectedIndex(0);
            this.delayAvanzado.setValue(tcenlace.getLinkDelay());
            
            this.selectorExtremoIzquierdo.setEnabled(false);
            this.selectorPuertoIzquierdo.setEnabled(false);
            this.selectorExtremoDerecho.setEnabled(false);
            this.selectorPuertoDerecho.setEnabled(false);
            
            this.selectorExtremoIzquierdo.removeAllItems();
            this.selectorExtremoIzquierdo.addItem(tcenlace.getHeadEndNodeName());
            this.selectorExtremoIzquierdo.setSelectedIndex(0);

            this.selectorPuertoIzquierdo.removeAllItems();
            this.selectorPuertoIzquierdo.addItem(""+tcenlace.getHeadEndNodePortID());
            this.selectorPuertoIzquierdo.setSelectedIndex(0);
            
            this.selectorExtremoDerecho.removeAllItems();
            this.selectorExtremoDerecho.addItem(tcenlace.getTailEndNodeName());
            this.selectorExtremoDerecho.setSelectedIndex(0);

            this.selectorPuertoDerecho.removeAllItems();
            this.selectorPuertoDerecho.addItem(""+tcenlace.getTailEndNodePortID());
            this.selectorPuertoDerecho.setSelectedIndex(0);
        }
    }

    private boolean reconfigurando;
    
    private String BCKUPNombre;
    private boolean BCKUPMostrarNombre;
    private boolean BCKUPCrearEstadisticas;
    private int BCKUPDelay;
    
    private TImagesBroker dispensadorDeImagenes;
    private Frame ventanaPadre;
    private TTopology topo;
    private TLinkConfig configEnlace;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider delayAvanzado;
    private javax.swing.JComboBox delayFacil;
    private javax.swing.JLabel etiquetaDelay;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel iconoEnlace;
    private javax.swing.JLabel iconoEnlace1;
    private javax.swing.JLabel iconoEnlace2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField nombreEnlace;
    private javax.swing.JPanel panelAvanzado;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTabbedPane panelPestanias;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRapido;
    private javax.swing.JComboBox selectorExtremoDerecho;
    private javax.swing.JComboBox selectorExtremoIzquierdo;
    private javax.swing.JComboBox selectorPuertoDerecho;
    private javax.swing.JComboBox selectorPuertoIzquierdo;
    private javax.swing.JCheckBox verNombre;
    // End of variables declaration//GEN-END:variables

}
