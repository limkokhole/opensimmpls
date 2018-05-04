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

import com.manolodominguez.opensimmpls.resources.translations.AvailableBundles;
import com.manolodominguez.opensimmpls.scenario.TActiveLSRNode;
import com.manolodominguez.opensimmpls.scenario.TTopology;
import com.manolodominguez.opensimmpls.ui.simulator.JDesignPanel;
import com.manolodominguez.opensimmpls.ui.utils.TImageBroker;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * This class implements a window that is used to configure and reconfigure an
 * active LSR node.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 * @version 2.0
 */
public class JActiveLSRWindow extends JDialog {

    /**
     * This is the constructor of the class and creates a new instance of
     * JActiveLSRWindow.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param designPanel desing panel wich contains the active LER node that is
     * configured via this JActiveLSRWindow
     * @param parent Parent window over wich this JActiveLSRWindow is shown.
     * @param imageBroker An object that supply the needed images to be inserted
     * in the UI.
     * @param modal TRUE, if this dialog has to be modal. Otherwise, FALSE.
     * @param topology Topology the active LSR node belongs to.
     * @since 2.0
     */
    public JActiveLSRWindow(TTopology topology, JDesignPanel designPanel, TImageBroker imageBroker, Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        this.imageBroker = imageBroker;
        this.designPanel = designPanel;
        this.topology = topology;
        initComponents();
        initComponents2();
    }

    /**
     * This method is called from within the constructor to initialize the
     * window components.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     */
    private void initComponents() {
        this.translations = ResourceBundle.getBundle(AvailableBundles.ACTIVE_LSR_WINDOW.getPath());
        this.mainPanel = new JPanel();
        this.panelTabs = new JTabbedPane();
        this.panelGeneralConfiguration = new JPanel();
        this.iconContainerActiveLSR = new JLabel();
        this.labelName = new JLabel();
        this.textFieldName = new JTextField();
        this.panelPosition = new JPanel();
        this.labelCoordinateX = new JLabel();
        this.labelCoordinateY = new JLabel();
        this.coordinatesPanel = new JCoordinatesPanel();
        this.checkBoxShowName = new JCheckBox();
        this.panelQuickConfiguration = new JPanel();
        this.labelQuickConfiguration = new JLabel();
        this.checkBoxQuickGenerateStatistics = new JCheckBox();
        this.labelActiveLSRFeatures = new JLabel();
        this.comboBoxPredefinedOptions = new JComboBox();
        this.panelAdvancedConfiguration = new JPanel();
        this.labelAdvancedConfiguration = new JLabel();
        this.checkBoxAdvancedGenerateStatistics = new JCheckBox();
        this.labelSwitchingPower = new JLabel();
        this.labelBufferSize = new JLabel();
        this.sliderSwitchingPower = new JSlider();
        this.sliderBufferSize = new JSlider();
        this.labelSwitchingPowerMbps = new JLabel();
        this.labelBufferSizeMB = new JLabel();
        this.labelDMGPSize = new JLabel();
        this.sliderDMGPSize = new JSlider();
        this.labelDMGPSizeKB = new JLabel();
        this.panelButtons = new JPanel();
        this.buttonOK = new JButton();
        this.buttonCancel = new JButton();
        setTitle(this.translations.getString("VentanaLSRA.titulo"));
        setModal(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                handleWindowsClosing(evt);
            }
        });
        getContentPane().setLayout(new AbsoluteLayout());
        this.mainPanel.setLayout(new AbsoluteLayout());
        this.panelTabs.setFont(new Font("Dialog", 0, 12));
        this.panelGeneralConfiguration.setLayout(new AbsoluteLayout());
        this.iconContainerActiveLSR.setIcon(this.imageBroker.getIcon(TImageBroker.LSRA));
        this.iconContainerActiveLSR.setText(this.translations.getString("VentanaLSRA.descripcion"));
        this.panelGeneralConfiguration.add(this.iconContainerActiveLSR, new AbsoluteConstraints(15, 20, 335, -1));
        this.labelName.setFont(new Font("Dialog", 0, 12));
        this.labelName.setText(this.translations.getString("VentanaLSRA.etiquetaNombre"));
        this.panelGeneralConfiguration.add(this.labelName, new AbsoluteConstraints(215, 80, 120, -1));
        this.panelGeneralConfiguration.add(this.textFieldName, new AbsoluteConstraints(215, 105, 125, -1));
        this.panelPosition.setBorder(BorderFactory.createTitledBorder(this.translations.getString("VentanaLSR.titulogrupo")));
        this.panelPosition.setLayout(new AbsoluteLayout());
        this.labelCoordinateX.setFont(new Font("Dialog", 0, 12));
        this.labelCoordinateX.setText(this.translations.getString("VentanaLSR.X="));
        this.panelPosition.add(this.labelCoordinateX, new AbsoluteConstraints(100, 100, -1, -1));
        this.labelCoordinateY.setFont(new Font("Dialog", 0, 12));
        this.labelCoordinateY.setText(this.translations.getString("VentanaLSR.Y="));
        this.panelPosition.add(this.labelCoordinateY, new AbsoluteConstraints(40, 100, -1, -1));
        this.coordinatesPanel.setBackground(new Color(255, 255, 255));
        this.coordinatesPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                handleClickOnCoordinatesPanel(evt);
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                handleMouseEnteringInCoordinatesPanel(evt);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                handleMouseLeavingCoordinatesPanel(evt);
            }
        });
        this.panelPosition.add(this.coordinatesPanel, new AbsoluteConstraints(25, 25, 130, 70));
        this.panelGeneralConfiguration.add(this.panelPosition, new AbsoluteConstraints(15, 75, 180, 125));
        this.checkBoxShowName.setFont(new Font("Dialog", 0, 12));
        this.checkBoxShowName.setSelected(true);
        this.checkBoxShowName.setText(this.translations.getString("VentanaLSR.verNombre"));
        this.panelGeneralConfiguration.add(this.checkBoxShowName, new AbsoluteConstraints(215, 135, -1, -1));
        this.panelTabs.addTab(this.translations.getString("VentanaLSR.tabs.General"), this.panelGeneralConfiguration);
        this.panelQuickConfiguration.setLayout(new AbsoluteLayout());
        this.labelQuickConfiguration.setIcon(this.imageBroker.getIcon(TImageBroker.ASISTENTE));
        this.labelQuickConfiguration.setText(this.translations.getString("VentanaLSRA.ConfiguracionSencilla"));
        this.panelQuickConfiguration.add(this.labelQuickConfiguration, new AbsoluteConstraints(15, 20, 335, -1));
        this.checkBoxQuickGenerateStatistics.setFont(new Font("Dialog", 0, 12));
        this.checkBoxQuickGenerateStatistics.setText(this.translations.getString("VentanaLSRA.GenerarEstadisticas"));
        this.checkBoxQuickGenerateStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleClickOnQuickGenerateStatistics(evt);
            }
        });
        this.panelQuickConfiguration.add(this.checkBoxQuickGenerateStatistics, new AbsoluteConstraints(70, 160, -1, -1));
        this.labelActiveLSRFeatures.setFont(new Font("Dialog", 0, 12));
        this.labelActiveLSRFeatures.setHorizontalAlignment(SwingConstants.RIGHT);
        this.labelActiveLSRFeatures.setText(this.translations.getString("VentanaLSRA.Caracteristicas"));
        this.panelQuickConfiguration.add(this.labelActiveLSRFeatures, new AbsoluteConstraints(20, 110, 160, -1));
        this.comboBoxPredefinedOptions.setFont(new Font("Dialog", 0, 12));
        //FIX: Apply I18N in the next line.
        this.comboBoxPredefinedOptions.setModel(new DefaultComboBoxModel(new String[]{"Personalized", "Very low cost LSR", "Low cost LSR", "Medium cost LSR", "Expensive LSR", "Very expensive LSR"}));
        this.comboBoxPredefinedOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleClickOnPredefinedOptions(evt);
            }
        });
        this.panelQuickConfiguration.add(this.comboBoxPredefinedOptions, new AbsoluteConstraints(190, 110, -1, -1));
        this.panelTabs.addTab(this.translations.getString("VentanaLSR.tabs.Fast"), this.panelQuickConfiguration);
        this.panelAdvancedConfiguration.setLayout(new AbsoluteLayout());
        this.labelAdvancedConfiguration.setIcon(this.imageBroker.getIcon(TImageBroker.AVANZADA));
        this.labelAdvancedConfiguration.setText(this.translations.getString("VentanaLSRA.ConfiguracionAvanzada"));
        this.panelAdvancedConfiguration.add(this.labelAdvancedConfiguration, new AbsoluteConstraints(15, 20, 335, -1));
        this.checkBoxAdvancedGenerateStatistics.setFont(new Font("Dialog", 0, 12));
        this.checkBoxAdvancedGenerateStatistics.setText(this.translations.getString("VentanaLSRA.GenerarEstadisticas"));
        this.checkBoxAdvancedGenerateStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleClickOnAdvancedGenerateStatistics(evt);
            }
        });
        this.panelAdvancedConfiguration.add(this.checkBoxAdvancedGenerateStatistics, new AbsoluteConstraints(70, 180, -1, -1));
        this.labelSwitchingPower.setFont(new Font("Dialog", 0, 12));
        this.labelSwitchingPower.setHorizontalAlignment(SwingConstants.RIGHT);
        this.labelSwitchingPower.setText(this.translations.getString("VentanaLSR.PotenciaConmutacion"));
        this.panelAdvancedConfiguration.add(labelSwitchingPower, new AbsoluteConstraints(10, 90, 140, -1));
        this.labelBufferSize.setFont(new Font("Dialog", 0, 12));
        this.labelBufferSize.setHorizontalAlignment(SwingConstants.RIGHT);
        this.labelBufferSize.setText(this.translations.getString("VentanaLSR.TamanioBufferEntrada"));
        this.panelAdvancedConfiguration.add(this.labelBufferSize, new AbsoluteConstraints(10, 120, 180, -1));
        this.sliderSwitchingPower.setMajorTickSpacing(1000);
        this.sliderSwitchingPower.setMaximum(10240);
        this.sliderSwitchingPower.setMinimum(1);
        this.sliderSwitchingPower.setMinorTickSpacing(100);
        this.sliderSwitchingPower.setValue(1);
        this.sliderSwitchingPower.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                handleChangeOnSwitchingPower(evt);
            }
        });
        this.panelAdvancedConfiguration.add(this.sliderSwitchingPower, new AbsoluteConstraints(155, 90, 130, 20));
        this.sliderBufferSize.setMajorTickSpacing(50);
        this.sliderBufferSize.setMaximum(1024);
        this.sliderBufferSize.setMinimum(1);
        this.sliderBufferSize.setMinorTickSpacing(100);
        this.sliderBufferSize.setValue(1);
        this.sliderBufferSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                handleChangeOnBufferSize(evt);
            }
        });
        this.panelAdvancedConfiguration.add(this.sliderBufferSize, new AbsoluteConstraints(200, 120, 100, 20));
        this.labelSwitchingPowerMbps.setFont(new Font("Dialog", 0, 10));
        this.labelSwitchingPowerMbps.setForeground(new Color(102, 102, 102));
        this.labelSwitchingPowerMbps.setHorizontalAlignment(SwingConstants.LEFT);
        this.labelSwitchingPowerMbps.setText(this.translations.getString("VentanaLSR.1_Mbps"));
        this.panelAdvancedConfiguration.add(this.labelSwitchingPowerMbps, new AbsoluteConstraints(290, 90, 70, 20));
        this.labelBufferSizeMB.setFont(new Font("Dialog", 0, 10));
        this.labelBufferSizeMB.setForeground(new Color(102, 102, 102));
        this.labelBufferSizeMB.setHorizontalAlignment(SwingConstants.LEFT);
        this.labelBufferSizeMB.setText(this.translations.getString("VentanaLSR.1_MB"));
        this.panelAdvancedConfiguration.add(this.labelBufferSizeMB, new AbsoluteConstraints(300, 120, 60, 20));
        this.labelDMGPSize.setFont(new Font("Dialog", 0, 12));
        this.labelDMGPSize.setHorizontalAlignment(SwingConstants.RIGHT);
        this.labelDMGPSize.setText(this.translations.getString("JVentanaLSRA.DMGP_size"));
        this.panelAdvancedConfiguration.add(this.labelDMGPSize, new AbsoluteConstraints(10, 150, 160, -1));
        this.sliderDMGPSize.setMajorTickSpacing(50);
        this.sliderDMGPSize.setMaximum(102400);
        this.sliderDMGPSize.setMinimum(1);
        this.sliderDMGPSize.setMinorTickSpacing(100);
        this.sliderDMGPSize.setValue(1);
        this.sliderDMGPSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                handleChangeOnDMGPSize(evt);
            }
        });
        this.panelAdvancedConfiguration.add(this.sliderDMGPSize, new AbsoluteConstraints(180, 150, 120, 20));
        this.labelDMGPSizeKB.setFont(new Font("Dialog", 0, 10));
        this.labelDMGPSizeKB.setForeground(new Color(102, 102, 102));
        this.labelDMGPSizeKB.setHorizontalAlignment(SwingConstants.LEFT);
        this.labelDMGPSizeKB.setText(this.translations.getString("JVentanaLSRA.1_KB"));
        this.panelAdvancedConfiguration.add(this.labelDMGPSizeKB, new AbsoluteConstraints(300, 150, 60, 20));
        this.panelTabs.addTab(this.translations.getString("VentanaLSR.tabs.Advanced"), this.panelAdvancedConfiguration);
        this.mainPanel.add(this.panelTabs, new AbsoluteConstraints(15, 15, 370, 240));
        this.panelButtons.setLayout(new AbsoluteLayout());
        this.buttonOK.setFont(new Font("Dialog", 0, 12));
        this.buttonOK.setIcon(this.imageBroker.getIcon(TImageBroker.ACEPTAR));
        this.buttonOK.setMnemonic(this.translations.getString("VentanaLSR.botones.mne.Aceptar").charAt(0));
        this.buttonOK.setText(this.translations.getString("VentanaLSR.boton.Ok"));
        this.buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleClickOnOKButton(evt);
            }
        });
        this.panelButtons.add(this.buttonOK, new AbsoluteConstraints(20, 10, 105, -1));
        this.buttonCancel.setFont(new Font("Dialog", 0, 12));
        this.buttonCancel.setIcon(this.imageBroker.getIcon(TImageBroker.CANCELAR));
        this.buttonCancel.setMnemonic(this.translations.getString("VentanaLSR.botones.mne.Cancelar").charAt(0));
        this.buttonCancel.setText(this.translations.getString("VentanaLSR.boton.Cancel"));
        this.buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleClickOnCancelButton(evt);
            }
        });
        this.panelButtons.add(this.buttonCancel, new AbsoluteConstraints(140, 10, 105, -1));
        this.mainPanel.add(this.panelButtons, new AbsoluteConstraints(0, 260, 400, 60));
        getContentPane().add(this.mainPanel, new AbsoluteConstraints(0, 0, -1, 310));
        pack();
    }

    /**
     * This method is called from within the constructor to do additional
     * configurations of window components.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     */
    private void initComponents2() {
        this.coordinatesPanel.setDesignPanel(designPanel);
        Dimension frameSize = this.getSize();
        Dimension parentSize = this.parent.getSize();
        setLocation((parentSize.width - frameSize.width) / 2, (parentSize.height - frameSize.height) / 2);
        this.activeLSRNode = null;
        this.labelCoordinateX.setText(this.translations.getString("VentanaEmisor.X=_") + coordinatesPanel.getRealX());
        this.labelCoordinateY.setText(this.translations.getString("VentanaEmisor.Y=_") + coordinatesPanel.getRealY());
        this.currentConfigShowName = true;
        this.currentConfigName = "";
        this.currentConfigSwitchingPower = 0;
        this.currentConfigBufferSize = 0;
        this.currentConfigGenerateStatistics = false;
        this.reconguration = false;
        this.comboBoxPredefinedOptions.removeAllItems();
        this.comboBoxPredefinedOptions.addItem(this.translations.getString("JVentanaLSRA.Personalized_LSRA"));
        this.comboBoxPredefinedOptions.addItem(this.translations.getString("JVentanaLSRA.Very_low_range_LSRA"));
        this.comboBoxPredefinedOptions.addItem(this.translations.getString("JVentanaLSRA.Low_range_LSRA"));
        this.comboBoxPredefinedOptions.addItem(this.translations.getString("JVentanaLSRA.Medium_range_LSRA"));
        this.comboBoxPredefinedOptions.addItem(this.translations.getString("JVentanaLSRA.High_range_LSRA"));
        this.comboBoxPredefinedOptions.addItem(this.translations.getString("JVentanaLSRA.Very_high_range_LSRA"));
        this.comboBoxPredefinedOptions.setSelectedIndex(0);
    }

    /**
     * This method is called when a change is made in the DMGP size (in the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleChangeOnDMGPSize(ChangeEvent evt) {
        this.labelDMGPSizeKB.setText("" + this.sliderDMGPSize.getValue() + " " + this.translations.getString("JVentanaLSRA._MB."));
    }

    /**
     * This method is called when a a predefined option is selected in the UI to
     * configure the Active LSR.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleClickOnPredefinedOptions(ActionEvent evt) {
        int selectedOption = this.comboBoxPredefinedOptions.getSelectedIndex();
        if (selectedOption == 0) {
        } else if (selectedOption == 0) {
            // Do nothing
            this.comboBoxPredefinedOptions.setSelectedIndex(0);
        } else if (selectedOption == 1) {
            this.sliderSwitchingPower.setValue(1);
            this.sliderBufferSize.setValue(1);
            this.sliderDMGPSize.setValue(1);
            this.comboBoxPredefinedOptions.setSelectedIndex(1);
        } else if (selectedOption == 2) {
            this.sliderSwitchingPower.setValue(2560);
            this.sliderBufferSize.setValue(256);
            this.sliderDMGPSize.setValue(2);
            this.comboBoxPredefinedOptions.setSelectedIndex(2);
        } else if (selectedOption == 3) {
            this.sliderSwitchingPower.setValue(5120);
            this.sliderBufferSize.setValue(512);
            this.sliderDMGPSize.setValue(3);
            this.comboBoxPredefinedOptions.setSelectedIndex(3);
        } else if (selectedOption == 4) {
            this.sliderSwitchingPower.setValue(7680);
            this.sliderDMGPSize.setValue(4);
            this.sliderBufferSize.setValue(768);
            this.comboBoxPredefinedOptions.setSelectedIndex(4);
        } else if (selectedOption == 5) {
            this.sliderSwitchingPower.setValue(10240);
            this.sliderBufferSize.setValue(1024);
            this.sliderDMGPSize.setValue(5);
            this.comboBoxPredefinedOptions.setSelectedIndex(5);
        }
    }

    /**
     * This method is called when a change is made in the buffer size (in the
     * UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleChangeOnBufferSize(ChangeEvent evt) {
        this.comboBoxPredefinedOptions.setSelectedIndex(0);
        this.labelBufferSizeMB.setText(this.sliderBufferSize.getValue() + " " + this.translations.getString("VentanaLSR.MB"));
    }

    /**
     * This method is called when a change is made in the switching power (in
     * the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleChangeOnSwitchingPower(ChangeEvent evt) {
        this.comboBoxPredefinedOptions.setSelectedIndex(0);
        this.labelSwitchingPowerMbps.setText(this.sliderSwitchingPower.getValue() + " " + this.translations.getString("VentanaLSR.Mbps"));
    }

    /**
     * This method is called when a change is made in "generate stistics"
     * checkbox located at "Quick configuration" tab (in the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleClickOnQuickGenerateStatistics(ActionEvent evt) {
        this.checkBoxAdvancedGenerateStatistics.setSelected(this.checkBoxQuickGenerateStatistics.isSelected());
    }

    /**
     * This method is called when a change is made in "generate stistics"
     * checkbox located at "Advanced configuration" tab (in the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleClickOnAdvancedGenerateStatistics(ActionEvent evt) {
        this.checkBoxQuickGenerateStatistics.setSelected(this.checkBoxAdvancedGenerateStatistics.isSelected());
    }

    /**
     * This method is called when a click is done "Cancel" button (in the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleClickOnCancelButton(ActionEvent evt) {
        if (this.reconguration) {
            this.activeLSRNode.setShowName(this.currentConfigShowName);
            this.activeLSRNode.setName(this.currentConfigName);
            this.activeLSRNode.setWellConfigured(true);
            this.activeLSRNode.setBufferSizeInMBytes(this.currentConfigBufferSize);
            this.activeLSRNode.setGenerateStats(this.currentConfigGenerateStatistics);
            this.activeLSRNode.setSwitchingPowerInMbps(this.currentConfigSwitchingPower);
            this.activeLSRNode.setDMGPSizeInKB(this.currentConfigDMGPSize);
            this.reconguration = false;
        } else {
            this.activeLSRNode.setWellConfigured(false);
        }
        this.setVisible(false);
        this.dispose();
    }

    /**
     * This method is called when a click is done "Ok" button (in the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleClickOnOKButton(ActionEvent evt) {
        this.activeLSRNode.setWellConfigured(true);
        if (!this.reconguration) {
            this.activeLSRNode.setScreenPosition(new Point(this.coordinatesPanel.getRealX(), this.coordinatesPanel.getRealY()));
        }
        this.activeLSRNode.setDMGPSizeInKB(this.sliderDMGPSize.getValue());
        this.activeLSRNode.setBufferSizeInMBytes(this.sliderBufferSize.getValue());
        this.activeLSRNode.setSwitchingPowerInMbps(this.sliderSwitchingPower.getValue());
        this.activeLSRNode.setName(this.textFieldName.getText());
        this.activeLSRNode.setGenerateStats(this.checkBoxQuickGenerateStatistics.isSelected());
        this.activeLSRNode.setShowName(this.checkBoxShowName.isSelected());
        int error = this.activeLSRNode.validateConfig(this.topology, this.reconguration);
        if (error != TActiveLSRNode.OK) {
            JWarningWindow warningWindow = new JWarningWindow(this.parent, true, this.imageBroker);
            warningWindow.setWarningMessage(this.activeLSRNode.getErrorMessage(error));
            warningWindow.setVisible(true);
        } else {
            this.setVisible(false);
            this.dispose();
        }
    }

    /**
     * This method is called when a click is done over the coordinates panel (in
     * the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleClickOnCoordinatesPanel(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            this.coordinatesPanel.setCoordinates(evt.getPoint());
            this.labelCoordinateX.setText(this.translations.getString("VentanaEmisor.X=_") + coordinatesPanel.getRealX());
            this.labelCoordinateY.setText(this.translations.getString("VentanaEmisor.Y=_") + coordinatesPanel.getRealY());
            this.coordinatesPanel.repaint();
        }
    }

    /**
     * This method is called when the mouse exits the coordinates panel (in the
     * UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleMouseLeavingCoordinatesPanel(MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * This method is called when the mouse enters the coordinates panel (in the
     * UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleMouseEnteringInCoordinatesPanel(MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    /**
     * This method is called when the JActiveLSRWindow is closed (in the UI).
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param evt The event that triggers this method.
     * @since 2.0
     */
    private void handleWindowsClosing(WindowEvent evt) {
        setVisible(false);
        this.activeLSRNode.setWellConfigured(false);
        dispose();
    }

    /**
     * This method configures all components of JActiveLSRWindow with present
     * values retrieved from the Active LSR. It is used to do a reconfiguration.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param activeLSRNode the Active LSR node to be configured through this
     * JActiveLERWindow
     * @param reconfiguration TRUE if the Active LSR is being reconfigured.
     * FALSE if it is the first configuration of the Active LSR after its
     * creation.
     * @since 2.0
     */
    public void setConfiguration(TActiveLSRNode activeLSRNode, boolean reconfiguration) {
        this.activeLSRNode = activeLSRNode;
        this.reconguration = reconfiguration;
        if (this.reconguration) {
            this.coordinatesPanel.setEnabled(false);
            this.coordinatesPanel.setToolTipText(null);
            this.currentConfigGenerateStatistics = activeLSRNode.isGeneratingStats();
            this.currentConfigShowName = activeLSRNode.nameMustBeDisplayed();
            this.currentConfigName = activeLSRNode.getName();
            this.currentConfigSwitchingPower = activeLSRNode.getSwitchingPowerInMbps();
            this.currentConfigBufferSize = activeLSRNode.getBufferSizeInMBytes();
            this.currentConfigDMGPSize = activeLSRNode.getDMGPSizeInKB();
            this.sliderDMGPSize.setValue(this.currentConfigDMGPSize);
            this.checkBoxAdvancedGenerateStatistics.setSelected(this.currentConfigGenerateStatistics);
            this.checkBoxQuickGenerateStatistics.setSelected(this.currentConfigGenerateStatistics);
            this.sliderSwitchingPower.setValue(this.currentConfigSwitchingPower);
            this.sliderBufferSize.setValue(this.currentConfigBufferSize);
            this.textFieldName.setText(this.currentConfigName);
            this.checkBoxShowName.setSelected(this.currentConfigShowName);
        }
    }

    private TImageBroker imageBroker;
    private Frame parent;
    private JDesignPanel designPanel;
    private TActiveLSRNode activeLSRNode;
    private TTopology topology;
    private boolean currentConfigShowName;
    private String currentConfigName;
    private int currentConfigSwitchingPower;
    private int currentConfigBufferSize;
    private boolean currentConfigGenerateStatistics;
    private int currentConfigDMGPSize;
    private boolean reconguration;
    private JLabel labelCoordinateX;
    private JLabel labelCoordinateY;
    private JLabel labelBufferSizeMB;
    private JLabel labelDMGPSizeKB;
    private JLabel labelName;
    private JLabel labelSwitchingPowerMbps;
    private JLabel labelQuickConfiguration;
    private JLabel labelAdvancedConfiguration;
    private JLabel iconContainerActiveLSR;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelActiveLSRFeatures;
    private JLabel labelSwitchingPower;
    private JLabel labelBufferSize;
    private JLabel labelDMGPSize;
    private JTextField textFieldName;
    private JPanel panelAdvancedConfiguration;
    private JPanel panelButtons;
    private JCoordinatesPanel coordinatesPanel;
    private JPanel panelGeneralConfiguration;
    private JTabbedPane panelTabs;
    private JPanel panelPosition;
    private JPanel mainPanel;
    private JPanel panelQuickConfiguration;
    private JCheckBox checkBoxAdvancedGenerateStatistics;
    private JCheckBox checkBoxQuickGenerateStatistics;
    private JSlider sliderSwitchingPower;
    private JSlider sliderBufferSize;
    private JSlider sliderDMGPSize;
    private JComboBox comboBoxPredefinedOptions;
    private JCheckBox checkBoxShowName;
    private ResourceBundle translations;
}
