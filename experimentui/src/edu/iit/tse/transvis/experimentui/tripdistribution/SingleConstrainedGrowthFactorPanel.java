/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.tripdistribution;

import edu.iit.tse.transvis.experimentui.Installer;
import edu.iit.tse.transvis.experimentui.ProjectInformation;
import edu.iitb.tse.algo.core.tripdistribution.growthfactor.ConstrainedType;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.windows.WindowManager;

public final class SingleConstrainedGrowthFactorPanel extends JPanel {

    /**
     * Creates new form RegressionVisualPanel
     */
    private List<String> fileList;
    private List<String> header = new ArrayList<>();
    private ConstrainedType convergence;
    private FileObject baseyearMatrix, horizonArray;

    public SingleConstrainedGrowthFactorPanel() {
        //Get all the file name from the directory
        fileList = getAllFile();
        initComponents();
    }

    @Override
    public String getName() {
        return "Step #1";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        choice1 = new java.awt.Choice();
        jSeparator1 = new javax.swing.JSeparator();
        networkLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        choice2 = new java.awt.Choice();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        choice3 = new java.awt.Choice();
        jButton3 = new javax.swing.JButton();

        for(String item:fileList) {
            choice1.add(item);
        }
        choice1.setBackground(Color.WHITE);
        choice1.setEnabled(true);
        choice1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        choice1.setFocusable(false);
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(networkLabel, org.openide.util.NbBundle.getMessage(SingleConstrainedGrowthFactorPanel.class, "SingleConstrainedGrowthFactorPanel.networkLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(SingleConstrainedGrowthFactorPanel.class, "SingleConstrainedGrowthFactorPanel.jButton2.text")); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SingleConstrainedGrowthFactorPanel.class, "SingleConstrainedGrowthFactorPanel.jLabel1.text")); // NOI18N

        choice2.add("Origin constraint");
        choice2.add("Destination constraint");
        choice2.setBackground(Color.WHITE);
        choice2.setEnabled(true);
        choice2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice2ItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SingleConstrainedGrowthFactorPanel.class, "SingleConstrainedGrowthFactorPanel.jLabel2.text")); // NOI18N

        for(String item:fileList) {
            choice3.add(item);
        }
        choice3.setBackground(Color.WHITE);
        choice3.setEnabled(true);
        choice3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        choice3.setFocusable(false);
        choice3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice3ItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(SingleConstrainedGrowthFactorPanel.class, "SingleConstrainedGrowthFactorPanel.jButton3.text")); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(networkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(21, 21, 21)
                                .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(networkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3))
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged

        String line = null;
        String selectedItem = fileList.get(fileList.indexOf(evt.getItem()));
        System.out.println("Selected Item is\t" + selectedItem);
        baseyearMatrix = ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(selectedItem);
        //Read the Header Information
        BufferedReader bufRdr;
        try {
            boolean boo = false;
            bufRdr = new BufferedReader(new InputStreamReader(baseyearMatrix.getInputStream()));
            while ((line = bufRdr.readLine()) != null && boo != true) {
                if (line.trim().startsWith("#")) {
                    // Don't inject current line into buffer
                } else {
                    header = null;
                    header = fromCommaSeparatedString(line);
                    boo = true;
                    System.out.println("Header\t" + header);
                }
            }


            System.out.println(line);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_choice1ItemStateChanged

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                JFileChooser chooser = new JFileChooser("");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = chooser.showOpenDialog((java.awt.Component) null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileObject temp = FileUtil.toFileObject(chooser.getSelectedFile());
                        baseyearMatrix = FileUtil.copyFile(temp, ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input"), temp.getName());
                        choice1.add(baseyearMatrix.getPath());
                        fileList.add(baseyearMatrix.getPath());
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        });
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void choice3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice3ItemStateChanged
        // TODO add your handling code here:
        String line = null;
        String selectedItem = fileList.get(fileList.indexOf(evt.getItem()));
        System.out.println("Selected Item is\t" + selectedItem);
        horizonArray = ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(selectedItem);
        //Read the Header Information
        BufferedReader bufRdr;
        try {
            boolean boo = false;
            bufRdr = new BufferedReader(new InputStreamReader(baseyearMatrix.getInputStream()));
            while ((line = bufRdr.readLine()) != null && boo != true) {
                if (line.trim().startsWith("#")) {
                    // Don't inject current line into buffer
                } else {
                    header = null;
                    header = fromCommaSeparatedString(line);
                    boo = true;
                    System.out.println("Header\t" + header);
                }
            }


            System.out.println(line);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_choice3ItemStateChanged

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void choice2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice2ItemStateChanged
        // TODO add your handling code here:
        if (choice2.getSelectedItem().equals("Origin constraint")) {
            convergence = ConstrainedType.ORIGIN;
        }
        if (choice2.getSelectedItem().equals("Destination constraint")) {
            convergence = ConstrainedType.DESTINATION;
        }
    }//GEN-LAST:event_choice2ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private java.awt.Choice choice3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel networkLabel;
    // End of variables declaration//GEN-END:variables

    private List<String> getAllFile() {
        List<String> temp = new ArrayList<>();
        System.out.println(Installer.projects);
        FileObject projectDirectory = ProjectInformation.getCurrentProject().getProjectDirectory();
        FileObject te = projectDirectory.getFileObject("input");
        for (FileObject children : te.getChildren()) {
            if (children.isData()) {
                temp.add(children.getName() + "." + children.getExt());
            }
        }
        return temp;
    }

    public static List<String> fromCommaSeparatedString(String string) {
        List<String> strings = new ArrayList<>();
        String[] splitted = string.split(",");
        for (int i = 0; i < splitted.length; i++) {
            String element = splitted[ i].trim();
            strings.add(element);
        }
        System.out.println("splitted.length\t" + splitted.length);
        return strings;
    }

    public FileObject getBaseyearMatrix() {
        return baseyearMatrix;
    }

    public FileObject getHorizonArray() {
        return horizonArray;
    }

    public ConstrainedType getConvergence() {
        return convergence;
    }
}
