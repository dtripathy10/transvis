/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.tripassignment;

import edu.iit.tse.transvis.experimentui.Installer;
import edu.iit.tse.transvis.experimentui.ProjectInformation;
import edu.iitb.tse.algo.model.CONVERGENCECRITERIA;
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

public final class UserEquilibriumVisualPanel extends JPanel {

    /**
     * Creates new form RegressionVisualPanel
     */
    private List<String> fileList;
    private FileObject networkFile, odFile;
    private List<String> header = new ArrayList<>();
    private CONVERGENCECRITERIA convergence;
    private double alpha,beta;
    private int numberOfIteration;

    public CONVERGENCECRITERIA getConvergence() {
        return convergence;
    }

    public double getAlpha() {
        return new Double(jTextField3.getText());
    }

    public double getBeta() {
        return new Double(jTextField2.getText());
    }

    public int getNumberOfIteration() {
        return new Integer(jTextField1.getText());
    }

    public UserEquilibriumVisualPanel() {
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

        jButton1 = new javax.swing.JButton();
        odLabel = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        jSeparator1 = new javax.swing.JSeparator();
        networkLabel = new javax.swing.JLabel();
        choice2 = new java.awt.Choice();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        choice3 = new java.awt.Choice();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jButton1.text")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(odLabel, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.odLabel.text")); // NOI18N

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

        org.openide.awt.Mnemonics.setLocalizedText(networkLabel, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.networkLabel.text")); // NOI18N

        for(String item:fileList) {
            choice2.add(item);
        }
        choice2.setBackground(Color.WHITE);
        choice2.setEnabled(true);
        choice2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        choice2.setFocusable(false);
        choice2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice2ItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jButton2.text")); // NOI18N
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

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jLabel1.text")); // NOI18N

        choice3.add("ALPHA");
        choice3.add("TRAVEL TIME");
        choice3.add("FLOW ON LINK");
        choice3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice3ItemStateChanged(evt);
            }
        });

        jSeparator2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jLabel4.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jTextField2.text")); // NOI18N

        jTextField3.setText(org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jTextField3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jLabel5.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(UserEquilibriumVisualPanel.class, "UserEquilibriumVisualPanel.jTextField1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(odLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(networkLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(choice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(networkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(odLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                JFileChooser chooser = new JFileChooser("");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = chooser.showOpenDialog((java.awt.Component) null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileObject temp = FileUtil.toFileObject(chooser.getSelectedFile());
                        networkFile = FileUtil.copyFile(temp, ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input"), temp.getName());
                        choice1.add(networkFile.getPath());
                        fileList.add(networkFile.getPath());
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        });
    }//GEN-LAST:event_jButton1MouseClicked

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged

        String line = null;
        String selectedItem = fileList.get(fileList.indexOf(evt.getItem()));
        System.out.println("Selected Item is\t" + selectedItem);
        networkFile = ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(selectedItem);
        //Read the Header Information
        BufferedReader bufRdr;
        try {
            boolean boo = false;
            bufRdr = new BufferedReader(new InputStreamReader(networkFile.getInputStream()));
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

    private void choice2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice2ItemStateChanged
        // TODO add your handling code here:

        String line = null;
        String selectedItem = fileList.get(fileList.indexOf(evt.getItem()));
        System.out.println("Selected Item is\t" + selectedItem);
        odFile = ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input").getFileObject(selectedItem);
        //Read the Header Information
        BufferedReader bufRdr;
        try {
            boolean boo = false;
            bufRdr = new BufferedReader(new InputStreamReader(odFile.getInputStream()));
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
    }//GEN-LAST:event_choice2ItemStateChanged

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
                        odFile = FileUtil.copyFile(temp, ProjectInformation.getCurrentProject().getProjectDirectory().getFileObject("input"), temp.getName());
                        choice1.add(odFile.getPath());
                        fileList.add(odFile.getPath());
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
        if (choice3.getSelectedItem().equals("ALPHA")) {
            convergence = CONVERGENCECRITERIA.ALPHA;
        }
        if (choice3.getSelectedItem().equals("TRAVEL TIME")) {
            convergence = CONVERGENCECRITERIA.TRAVELTIME;
        }
        if (choice3.getSelectedItem().equals("FLOW ON LINK")) {
            convergence = CONVERGENCECRITERIA.FLOWONLINK;
        }
    }//GEN-LAST:event_choice3ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private java.awt.Choice choice3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel networkLabel;
    private javax.swing.JLabel odLabel;
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

    public FileObject getNetworkFile() {
        return networkFile;
    }

    public FileObject getOdFile() {
        return odFile;
    }
}
