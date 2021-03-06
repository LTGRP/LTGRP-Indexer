
package com.billooms.indexwheeleditor;

import com.billooms.indexwheel.api.IndexChildren;
import com.billooms.indexwheel.api.IndexWheel;
import com.billooms.indexwheel.api.IndexWheelMgr;
import com.billooms.indexwheel.api.RootNode;
import com.billooms.indexwheeleditor.drawables.Grid;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import javax.swing.JPanel;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.ListView;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 * Top component for editing and displaying an IndexWheel.
 * @author Bill Ooms. Copyright 2011 Studio of Bill Ooms. All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
@ConvertAsProperties(dtd = "-//com.billooms.indexwheeleditor//IndexWheelEditor//EN",
autostore = false)
@TopComponent.Description(preferredID = "IndexWheelEditorTopComponent",
iconBase="com/billooms/indexwheeleditor/icon16.png", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.billooms.indexwheeleditor.IndexWheelEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_IndexWheelEditorAction",
preferredID = "IndexWheelEditorTopComponent")
public final class IndexWheelEditorTopComponent extends TopComponent implements PropertyChangeListener, LookupListener, ExplorerManager.Provider {
		
	private DisplayPanel display;			// panel for displaying the graphics (nested class below)
	private IndexWheel selected = null;		// currently selected IndexWheel
	private IndexWheelMgr idxMgr = Lookup.getDefault().lookup(IndexWheelMgr.class);	// IndexWheel Manager
	
	private final ExplorerManager exMgr = new ExplorerManager();	// for the ListView down in the EditPanel
    private Lookup.Result result = null;	// global selection of IndexWheel
	private Node root;						// root for the list of wheels
	
	/** Creates a new IndexWheelEditorTopComponent */
	public IndexWheelEditorTopComponent() {
		initComponents();
		setName(NbBundle.getMessage(IndexWheelEditorTopComponent.class, "CTL_IndexWheelEditorTopComponent"));
		setToolTipText(NbBundle.getMessage(IndexWheelEditorTopComponent.class, "HINT_IndexWheelEditorTopComponent"));
		
		phaseSlider.setLabelTable(new SliderLabels(0, 100, 25, 0.0, 0.25));	// labels for slider
		numSpinner.setValue(idxMgr.size());
		
		display = new DisplayPanel();		// DisplayPanel is a nested class below
		this.add(display, BorderLayout.CENTER);
		
		putClientProperty("print.printable", Boolean.TRUE);	// this can be printed

		root = new RootNode(new IndexChildren());
		exMgr.setRootContext(root);			// root for the list of wheels
				
		this.associateLookup(ExplorerUtils.createLookup(exMgr, getActionMap()));
	}

	/**
	 * Update all editor controls with values from the currently selected wheel 
	 * and update the display panel.
	 */
	private void updateAll() {
		numSpinner.setValue(idxMgr.size());
		if (selected != null) {
			holesField.setValue(selected.getNumHoles());
			phaseField.setValue(selected.getPhase());
			phaseSlider.setValue((int) (100.0 * selected.getPhase()));
			nameField.setText(selected.getName());
		}
		display.repaint();
	}
	
	/**
	 * The given indexWheel is now the new selection. 
	 * Remove propertyChangeListeners from the old selection and 
	 * add propertyChangeListeners to the new selection.
	 * @param w New selected IndexWheel
	 */
	private void setSelected(IndexWheel w) {
		if (selected != null) {		// remove PropertyChangeListener for the old wheel
			selected.removePropertyChangeListener(this);
		}
		this.selected = w;
		if (selected != null) {		// listen for changes in the new wheel
			selected.addPropertyChangeListener(this);
		}
		updateAll();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editor = new javax.swing.JPanel();
        selectPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numSpinner = new javax.swing.JSpinner();
        jScrollPane1 = new ListView();
        jLabel3 = new javax.swing.JLabel();
        holesField = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        phasePanel = new javax.swing.JPanel();
        phaseSlider = new javax.swing.JSlider();
        phaseField = new javax.swing.JFormattedTextField();
        fillPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        hole1Spinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        deltaSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        fillButton = new javax.swing.JButton();
        fillAllButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        selectPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.selectPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jLabel1.text")); // NOI18N

        numSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        numSpinner.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.numSpinner.toolTipText")); // NOI18N
        numSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeNum(evt);
            }
        });

        jScrollPane1.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jScrollPane1.toolTipText")); // NOI18N

        javax.swing.GroupLayout selectPanelLayout = new javax.swing.GroupLayout(selectPanel);
        selectPanel.setLayout(selectPanelLayout);
        selectPanelLayout.setHorizontalGroup(
            selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        selectPanelLayout.setVerticalGroup(
            selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPanelLayout.createSequentialGroup()
                .addGroup(selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(numSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jLabel3.text")); // NOI18N

        holesField.setColumns(4);
        holesField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        holesField.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.holesField.toolTipText")); // NOI18N
        holesField.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollHoles(evt);
            }
        });
        holesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeHoles(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jLabel7.text")); // NOI18N

        nameField.setColumns(10);
        nameField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        nameField.setText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.nameField.text")); // NOI18N
        nameField.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.nameField.toolTipText")); // NOI18N
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeName(evt);
            }
        });

        phasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.phasePanel.border.title"))); // NOI18N

        phaseSlider.setMajorTickSpacing(10);
        phaseSlider.setMinorTickSpacing(5);
        phaseSlider.setPaintLabels(true);
        phaseSlider.setPaintTicks(true);
        phaseSlider.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.phaseSlider.toolTipText")); // NOI18N
        phaseSlider.setValue(0);
        phaseSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changePhaseSlider(evt);
            }
        });

        phaseField.setColumns(5);
        phaseField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        phaseField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phaseField.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.phaseField.toolTipText")); // NOI18N
        phaseField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePhase(evt);
            }
        });

        javax.swing.GroupLayout phasePanelLayout = new javax.swing.GroupLayout(phasePanel);
        phasePanel.setLayout(phasePanelLayout);
        phasePanelLayout.setHorizontalGroup(
            phasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phasePanelLayout.createSequentialGroup()
                .addComponent(phaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phaseSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        phasePanelLayout.setVerticalGroup(
            phasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(phaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(phaseSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        fillPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.fillPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jLabel4.text")); // NOI18N

        hole1Spinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        hole1Spinner.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.hole1Spinner.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jLabel5.text")); // NOI18N

        deltaSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(1), null, Integer.valueOf(1)));
        deltaSpinner.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.deltaSpinner.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(fillButton, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.fillButton.text")); // NOI18N
        fillButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.fillButton.toolTipText")); // NOI18N
        fillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillHoles(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(fillAllButton, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.fillAllButton.text")); // NOI18N
        fillAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillAll(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(clearButton, org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.clearButton.text")); // NOI18N
        clearButton.setToolTipText(org.openide.util.NbBundle.getMessage(IndexWheelEditorTopComponent.class, "IndexWheelEditorTopComponent.clearButton.toolTipText")); // NOI18N
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAll(evt);
            }
        });

        javax.swing.GroupLayout fillPanelLayout = new javax.swing.GroupLayout(fillPanel);
        fillPanel.setLayout(fillPanelLayout);
        fillPanelLayout.setHorizontalGroup(
            fillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fillPanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hole1Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fillPanelLayout.createSequentialGroup()
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fillAllButton))
                    .addGroup(fillPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deltaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fillButton))))
        );
        fillPanelLayout.setVerticalGroup(
            fillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fillPanelLayout.createSequentialGroup()
                .addGroup(fillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(fillButton))
                    .addComponent(jLabel4)
                    .addComponent(hole1Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deltaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fillAllButton)
                    .addComponent(clearButton)))
        );

        javax.swing.GroupLayout editorLayout = new javax.swing.GroupLayout(editor);
        editor.setLayout(editorLayout);
        editorLayout.setHorizontalGroup(
            editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editorLayout.createSequentialGroup()
                .addComponent(selectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editorLayout.createSequentialGroup()
                        .addGroup(editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editorLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(holesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editorLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fillPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        editorLayout.setVerticalGroup(
            editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(editorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(holesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addComponent(fillPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(phasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(editor, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

private void changeNum(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeNum
		if (selected == null) {
			return;
		}
		int n = (Integer)numSpinner.getValue();
		if (n > idxMgr.size()) {
			for (int i = idxMgr.size(); i < n; i++) {		// add some more at the end of the list
				idxMgr.addWheel();
			}
		} else if (n < idxMgr.size()) {
			for (int i = idxMgr.size()-1; i >= n; i--) {	// remove the last ones from the list
				idxMgr.remove(i);
			}
		}
}//GEN-LAST:event_changeNum

private void scrollHoles(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollHoles
		if (selected == null) {
			return;
		}
		if (holesField.isFocusOwner()) {
			int n = ((Number)holesField.getValue()).intValue();
			n = n - evt.getWheelRotation();
			n = Math.max(n, 1);		// don't go less than one
			holesField.setValue(n);
			selected.setNumHoles(n);
		}
}//GEN-LAST:event_scrollHoles

private void changeHoles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeHoles
		if (selected == null) {
			return;
		}
		selected.setNumHoles(((Number)holesField.getValue()).intValue());
}//GEN-LAST:event_changeHoles

private void changeName(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeName
		if (selected == null) {
			return;
		}
		selected.setName(nameField.getText());
}//GEN-LAST:event_changeName

private void changePhaseSlider(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changePhaseSlider
		if (selected == null) {
			return;
		}
		double ph = phaseSlider.getValue() / 100.0;
		phaseField.setValue(ph);
		selected.setPhase(ph);
}//GEN-LAST:event_changePhaseSlider

private void changePhase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePhase
		if (selected == null) {
			return;
		}
		double n = ((Number)phaseField.getValue()).doubleValue();
		phaseSlider.setValue((int)(100*n));
		selected.setPhase(n);
}//GEN-LAST:event_changePhase

private void fillHoles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillHoles
		if (selected == null) {
			return;
		}
		int n = ((Number)hole1Spinner.getValue()).intValue();
		int delta = ((Number)deltaSpinner.getValue()).intValue();
		while (n < selected.getNumHoles()) {
			selected.fillHole(n, true);
			n += delta;
		}
}//GEN-LAST:event_fillHoles

private void fillAll(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillAll
		if (selected == null) {
			return;
		}
		selected.fillAll();
}//GEN-LAST:event_fillAll

private void clearAll(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAll
		if (selected == null) {
			return;
		}
		selected.clearAll();
}//GEN-LAST:event_clearAll

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JSpinner deltaSpinner;
    private javax.swing.JPanel editor;
    private javax.swing.JButton fillAllButton;
    private javax.swing.JButton fillButton;
    private javax.swing.JPanel fillPanel;
    private javax.swing.JSpinner hole1Spinner;
    private javax.swing.JFormattedTextField holesField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JSpinner numSpinner;
    private javax.swing.JFormattedTextField phaseField;
    private javax.swing.JPanel phasePanel;
    private javax.swing.JSlider phaseSlider;
    private javax.swing.JPanel selectPanel;
    // End of variables declaration//GEN-END:variables
	
	@Override
	public void componentOpened() {
		idxMgr = Lookup.getDefault().lookup(IndexWheelMgr.class);
		idxMgr.addPropertyChangeListener(this);	// listen to IndexWheelMgr
        result = Utilities.actionsGlobalContext().lookupResult(IndexWheel.class);
        result.addLookupListener(this);		// listen for changes in the selection
		if (selected != null) {
			selected.addPropertyChangeListener(this);
		}
	}

	@Override
	public void componentClosed() {
		idxMgr.removePropertyChangeListener(this);	// remove the listeners when the window closes
		idxMgr = null;
        result.removeLookupListener(this);
        result = null;
		if (selected != null) {
			selected.removePropertyChangeListener(this);
		}
	}

	void writeProperties(java.util.Properties p) {
		// better to version settings since initial version as advocated at
		// http://wiki.apidesign.org/wiki/PropertyFiles
		p.setProperty("version", "1.0");
		// TODO store your settings
	}

	void readProperties(java.util.Properties p) {
		String version = p.getProperty("version");
		// TODO read your settings according to their version
	}

	@Override
	public ExplorerManager getExplorerManager() {
		return exMgr;
	}

	@Override
	public void resultChanged(LookupEvent evt) {
        Lookup.Result r = (Lookup.Result) evt.getSource();	// should be same as result
        Collection c = r.allInstances();
        if (!c.isEmpty()) {
            IndexWheel wh = (IndexWheel) c.iterator().next();
			setSelected(wh);
        }
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof IndexWheel) {	// when the wheel changes, update everything
			updateAll();		// when the wheel changes, update everything
		} else if (evt.getSource() instanceof IndexWheelMgr) {
			if (evt.getPropertyName().equals(IndexWheelMgr.PROP_READXML)) {
				this.setDisplayName("IndexWheel Editor: " + evt.getNewValue());	// update display name
				setSelected(idxMgr.get(0));	// after a file read, look at the first wheel
			}
			if ((evt.getPropertyName().equals(IndexWheelMgr.PROP_ADD)) && (idxMgr.size() == 1)) {
				setSelected(idxMgr.get(0));	// after the first one is added, select it
			}
			if (evt.getPropertyName().equals(IndexWheelMgr.PROP_REMOVE)) {
				numSpinner.setValue(idxMgr.size());
				if (idxMgr.size() == 0) {		// shouldn't happen
					setSelected(null);
				}
				if (evt.getOldValue() == selected) {	// show the first one on the list
					setSelected((IndexWheel)evt.getNewValue());	
				}
			}
		}
	}
	
	/**
	 * Nested Class -- Panel for displaying an IndexWheel
	 * @author Bill Ooms. Copyright 2011 Studio of Bill Ooms. All rights reserved.
	 */
	public class DisplayPanel extends JPanel implements MouseListener {

		private final double INITIAL_DPI = 100.0;				// for the first time the window comes up
		private final double WINDOW_PERCENT = 0.95;				// use 95% of the window for the index wheel
		private final Point INITIAL_ZPIX = new Point(150, 200);	// artibrary
		private double dpi = INITIAL_DPI;		// Dots per inch for zooming in/out
		private Point zeroPix = INITIAL_ZPIX;	// Location of 0.0, 0.0 in pixels
		private final int CLOSEST = 10;			// select a hole if within 10 pixels (for clicking)
		private final Font MESSAGE_FONT = new Font("SansSerif", Font.BOLD, 16);
		private final static String OPEN_MSG = "Open an existing file or create a new file";

		/** Creates new DisplayPanel
		 */
		public DisplayPanel() {
            this.setBackground(Color.WHITE);
			addMouseListener(this);
		}

		/**
		 * Paint the selected IndexWheel
		 * @param g Graphics g
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			if (selected == null) {		// show opening messate
				FontMetrics fm = g2d.getFontMetrics(MESSAGE_FONT);
				g2d.setFont(MESSAGE_FONT);
				g2d.drawString(OPEN_MSG, getWidth() / 2 - fm.stringWidth(OPEN_MSG) / 2, getHeight() / 2);
			} else {
				// scale to fit the window
				dpi = (int) Math.min(WINDOW_PERCENT * this.getWidth() / (2 * IndexWheel.WHEEL_RADIUS), 
						WINDOW_PERCENT * this.getHeight() / (2 * IndexWheel.WHEEL_RADIUS));
				zeroPix = new Point(getWidth() / 2, getHeight() / 2);	// zero is always in the center
				g2d.translate(zeroPix.x, zeroPix.y);
				g2d.scale(dpi, -dpi);	// positive y is up

				// Paint the grid
				new Grid(-(double) zeroPix.x / dpi, -(double) (getHeight() - zeroPix.y) / dpi,
						(double) getWidth() / dpi, (double) getHeight() / dpi).paint(g2d);
				selected.paint(g2d);		// paint the wheel
			}
		}

		/**
		 * Do this when the mouse is clicked
		 * @param evt event
		 */
		@Override
		public void mouseClicked(MouseEvent evt) {	// click on a hole to toggle the fill
			if (selected == null) {
				return;
			}
			if (evt.getClickCount() == 1) {
				Point2D.Double pt = scalePixToInch(new Point(evt.getX(), evt.getY()));
				selected.toggleHoleNearest(pt, CLOSEST / dpi);
			}
		}

		/**
		 * Convert a point in pixels to inches
		 * @param p point in pixels
		 * @return point in inches
		 */
		private Point2D.Double scalePixToInch(Point p) {
			return new Point2D.Double((double) (p.x - zeroPix.x) / dpi, 
					(double) (zeroPix.y - p.y) / dpi);
		}

		/**
		 * (not currently used)
		 * @param evt
		 */
		@Override
		public void mousePressed(MouseEvent evt) {};

		/**
		 * (not currently used)
		 * @param evt
		 */
		@Override
		public void mouseReleased(MouseEvent evt) {};

		/**
		 * (not currently used)
		 * @param evt
		 */
		@Override
		public void mouseEntered(MouseEvent evt) {};

		/**
		 * (not currently used)
		 * @param evt
		 */
		@Override
		public void mouseExited(MouseEvent evt) {};
	}
}
