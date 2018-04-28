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
import javax.swing.*;
import com.manolodominguez.opensimmpls.scenario.TTrafficSinkNode;
import com.manolodominguez.opensimmpls.scenario.TTopology;
import com.manolodominguez.opensimmpls.ui.simulator.JDesignPanel;
import com.manolodominguez.opensimmpls.ui.utils.TImageBroker;

/**
 * Esta clase implementa una ventana que permite al usuario configurar todos los
 * aspectos de un nodo receptor.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class JTrafficSinkWindow extends javax.swing.JDialog {

    /**
     * Crea una nueva instancia de JVentanaReceptor
     * @since 2.0
     * @param t Topolog�a dentro de la cual se encuentra el nodo receptor que se va a configurar.
     * @param pad Panel de dise�o en el cual se est� dise�ando el nodo receptor.
     * @param di Dispensador de im�genes com�n a todo el programa.
     * @param parent Ventana padre dentro de la cual se va a abrir la ventana de configuraci�n del
     * receptor.
     * @param modal TRUE indica que la interfaz quedar� deshabilitada hasta que se cierre la ventana
     * de configuraci�n del nodo receptor. FALSE indica que no es as�.
     */
    public JTrafficSinkWindow(TTopology t, JDesignPanel pad, TImageBroker di, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ventanaPadre = parent;
        dispensadorDeImagenes = di;
        pd = pad;
        topo = t;
        initComponents();
        initComponents2();
    }

    /**
     * Este m�todo configura los componentes que no hayan quedado del todo configurados
     * en el constructor.
     * @since 2.0
     */    
    public void initComponents2() {
        panelCoordenadas.setDesignPanel(pd);
        java.awt.Dimension tamFrame=this.getSize();
        java.awt.Dimension tamPadre=ventanaPadre.getSize();
        setLocation((tamPadre.width-tamFrame.width)/2, (tamPadre.height-tamFrame.height)/2);
        configReceptor = null;
        coordenadaX.setText(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaEmisor.X=_") + panelCoordenadas.getRealX());
        coordenadaY.setText(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaEmisor.Y=_") + panelCoordenadas.getRealY());
        BKUPMostrarNombre = true;
        BKUPNombre = "";
        reconfigurando = false;
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
        iconoReceptor = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        nombreNodo = new javax.swing.JTextField();
        panelPosicion = new javax.swing.JPanel();
        coordenadaX = new javax.swing.JLabel();
        coordenadaY = new javax.swing.JLabel();
        panelCoordenadas = new com.manolodominguez.opensimmpls.ui.dialogs.JCoordinatesPanel();
        verNombre = new javax.swing.JCheckBox();
        panelRapido = new javax.swing.JPanel();
        iconoEnlace1 = new javax.swing.JLabel();
        selectorGenerarEstadisticaFacil = new javax.swing.JCheckBox();
        panelAvanzado = new javax.swing.JPanel();
        iconoEnlace2 = new javax.swing.JLabel();
        selectorGenerarEstadisticaAvanzada = new javax.swing.JCheckBox();
        panelBotones = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations"); // NOI18N
        setTitle(bundle.getString("VentanaReceptor.titulo")); // NOI18N
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

        iconoReceptor.setIcon(dispensadorDeImagenes.getIcon(TImageBroker.RECEPTOR));
        iconoReceptor.setText(bundle.getString("VentanaReceptor.descripcion")); // NOI18N
        panelGeneral.add(iconoReceptor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        etiquetaNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        etiquetaNombre.setText(bundle.getString("VentanaReceptor.etiquetaNombre")); // NOI18N
        panelGeneral.add(etiquetaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 80, 120, -1));
        panelGeneral.add(nombreNodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 105, 125, -1));

        panelPosicion.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("VentanaReceptor.titulogrupo"))); // NOI18N
        panelPosicion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        coordenadaX.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        coordenadaX.setText(bundle.getString("VentanaReceptor.X=_")); // NOI18N
        panelPosicion.add(coordenadaX, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        coordenadaY.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        coordenadaY.setText(bundle.getString("VentanaReceptor.Y=_")); // NOI18N
        panelPosicion.add(coordenadaY, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        panelCoordenadas.setBackground(new java.awt.Color(255, 255, 255));
        panelCoordenadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clicEnPanelCoordenadas(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ratonEntraEnPanelCoordenadas(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ratonSaleDePanelCoordenadas(evt);
            }
        });
        panelPosicion.add(panelCoordenadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 130, 70));

        panelGeneral.add(panelPosicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 75, 180, 125));

        verNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        verNombre.setSelected(true);
        verNombre.setText(bundle.getString("VentanaReceptor.verNombre")); // NOI18N
        panelGeneral.add(verNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 135, -1, -1));

        panelPestanias.addTab(bundle.getString("VentanaReceptor.tab.General"), panelGeneral); // NOI18N

        panelRapido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace1.setIcon(dispensadorDeImagenes.getIcon(TImageBroker.ASISTENTE));
        iconoEnlace1.setText(bundle.getString("JVentanaReceptor.configuracionSencilla")); // NOI18N
        panelRapido.add(iconoEnlace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        selectorGenerarEstadisticaFacil.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorGenerarEstadisticaFacil.setText(bundle.getString("JVentanaReceptor.generarEstadisticas")); // NOI18N
        selectorGenerarEstadisticaFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnGenerarEstadisticaFacil(evt);
            }
        });
        panelRapido.add(selectorGenerarEstadisticaFacil, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 125, -1, -1));

        panelPestanias.addTab(bundle.getString("VentanaReceptor.tab.Fast"), panelRapido); // NOI18N

        panelAvanzado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace2.setIcon(dispensadorDeImagenes.getIcon(TImageBroker.AVANZADA));
        iconoEnlace2.setText(bundle.getString("JVentanaReceptor.configuracionAvanzada")); // NOI18N
        panelAvanzado.add(iconoEnlace2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        selectorGenerarEstadisticaAvanzada.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        selectorGenerarEstadisticaAvanzada.setText(bundle.getString("JVentanaReceptor.GenerEstadisticas")); // NOI18N
        selectorGenerarEstadisticaAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnGenerarEstadisticaAvanzada(evt);
            }
        });
        panelAvanzado.add(selectorGenerarEstadisticaAvanzada, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 125, -1, -1));

        panelPestanias.addTab(bundle.getString("VentanaReceptor.tab.Advanced"), panelAvanzado); // NOI18N

        panelPrincipal.add(panelPestanias, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 370, 240));

        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton2.setIcon(dispensadorDeImagenes.getIcon(TImageBroker.ACEPTAR));
        jButton2.setMnemonic(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaReceptor.botones.mne.Aceptar").charAt(0));
        jButton2.setText(bundle.getString("VentanaReceptor.boton.Ok")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnAceptar(evt);
            }
        });
        panelBotones.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 105, -1));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton3.setIcon(dispensadorDeImagenes.getIcon(TImageBroker.CANCELAR));
        jButton3.setMnemonic(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaReceptor.botones.mne.Cancelar").charAt(0));
        jButton3.setText(bundle.getString("VentanaReceptor.boton.Cancel")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCancelar(evt);
            }
        });
        panelBotones.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 15, 105, -1));

        panelPrincipal.add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 255, 400, 55));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void clicEnGenerarEstadisticaAvanzada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnGenerarEstadisticaAvanzada
    this.selectorGenerarEstadisticaFacil.setSelected(this.selectorGenerarEstadisticaAvanzada.isSelected());
}//GEN-LAST:event_clicEnGenerarEstadisticaAvanzada

private void clicEnGenerarEstadisticaFacil(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnGenerarEstadisticaFacil
    this.selectorGenerarEstadisticaAvanzada.setSelected(this.selectorGenerarEstadisticaFacil.isSelected());
}//GEN-LAST:event_clicEnGenerarEstadisticaFacil

private void clicEnCancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCancelar
    if (reconfigurando) {
        configReceptor.setShowName(BKUPMostrarNombre);
        configReceptor.setName(BKUPNombre);
        configReceptor.setWellConfigured(true);
        reconfigurando = false;
    } else {
        configReceptor.setWellConfigured(false);
    }
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_clicEnCancelar

private void clicEnAceptar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnAceptar
    configReceptor.setWellConfigured(true);
    if (!this.reconfigurando){
        configReceptor.setScreenPosition(new Point(panelCoordenadas.getRealX(),panelCoordenadas.getRealY()));
    }
    configReceptor.setName(nombreNodo.getText());
    configReceptor.setShowName(verNombre.isSelected());
    configReceptor.setGenerateStats(this.selectorGenerarEstadisticaAvanzada.isSelected());
    int error = configReceptor.validateConfig(topo, this.reconfigurando);
    if (error != TTrafficSinkNode.OK) {
        JWarningWindow va = new JWarningWindow(ventanaPadre, true, dispensadorDeImagenes);
        va.setWarningMessage(configReceptor.getErrorMessage(error));
        va.show();
    } else {
        this.setVisible(false);
        this.dispose();
    }
}//GEN-LAST:event_clicEnAceptar

private void clicEnPanelCoordenadas(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clicEnPanelCoordenadas
    if (evt.getButton() == MouseEvent.BUTTON1) {
        panelCoordenadas.setCoordinates(evt.getPoint());
        coordenadaX.setText(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaEmisor.X=_") + panelCoordenadas.getRealX());
        coordenadaY.setText(java.util.ResourceBundle.getBundle("com/manolodominguez/opensimmpls/resources/translations/translations").getString("VentanaEmisor.Y=_") + panelCoordenadas.getRealY());
        panelCoordenadas.repaint();
    }
}//GEN-LAST:event_clicEnPanelCoordenadas

private void ratonSaleDePanelCoordenadas(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratonSaleDePanelCoordenadas
    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_ratonSaleDePanelCoordenadas

private void ratonEntraEnPanelCoordenadas(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratonEntraEnPanelCoordenadas
    this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
}//GEN-LAST:event_ratonEntraEnPanelCoordenadas

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        configReceptor.setWellConfigured(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * Este m�todo configura la ventana de configuraci�n del receptor con los valores
     * que el receptor tenga ya activos.
     * @since 2.0
     * @param tnr Nodo receptor que se desea configurar.
     * @param recfg TRUE indica que es una reconfiguraci�n del nodo receptor. FALSE indica que el
     * nodo est� siendo insertado por primera vez.
     */    
    public void ponerConfiguracion(TTrafficSinkNode tnr, boolean recfg) {
        configReceptor = tnr;
        reconfigurando = recfg;
        if (reconfigurando) {
            this.panelCoordenadas.setEnabled(false);
            this.panelCoordenadas.setToolTipText(null);

            BKUPGenerarEstadisticas = tnr.isGeneratingStats();
            BKUPMostrarNombre = tnr.nameMustBeDisplayed();
            BKUPNombre = tnr.getName();

            this.nombreNodo.setText(BKUPNombre);
            this.verNombre.setSelected(BKUPMostrarNombre);
            this.selectorGenerarEstadisticaAvanzada.setSelected(BKUPGenerarEstadisticas);
            this.selectorGenerarEstadisticaFacil.setSelected(BKUPGenerarEstadisticas);
        }
    }

    private TImageBroker dispensadorDeImagenes;
    private Frame ventanaPadre;
    private JDesignPanel pd;
    private TTrafficSinkNode configReceptor;
    private TTopology topo;
    
    private boolean BKUPMostrarNombre;
    private String BKUPNombre;
    private boolean BKUPGenerarEstadisticas;

    private boolean reconfigurando;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel coordenadaX;
    private javax.swing.JLabel coordenadaY;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel iconoEnlace1;
    private javax.swing.JLabel iconoEnlace2;
    private javax.swing.JLabel iconoReceptor;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField nombreNodo;
    private javax.swing.JPanel panelAvanzado;
    private javax.swing.JPanel panelBotones;
    private com.manolodominguez.opensimmpls.ui.dialogs.JCoordinatesPanel panelCoordenadas;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTabbedPane panelPestanias;
    private javax.swing.JPanel panelPosicion;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRapido;
    private javax.swing.JCheckBox selectorGenerarEstadisticaAvanzada;
    private javax.swing.JCheckBox selectorGenerarEstadisticaFacil;
    private javax.swing.JCheckBox verNombre;
    // End of variables declaration//GEN-END:variables

}