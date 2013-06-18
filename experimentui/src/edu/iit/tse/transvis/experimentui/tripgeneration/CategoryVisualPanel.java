/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.tripgeneration;


import edu.iit.tse.transvis.experimentui.Installer;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

public final class CategoryVisualPanel extends JPanel {

    /**
     * Creates new form RegressionVisualPanel
     */
    private List<String> fileList;
    private File regressionFile;

    public CategoryVisualPanel() {
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
        jLabel1 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        jLabel5 = new javax.swing.JLabel();
        choice5 = new java.awt.Choice();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CategoryVisualPanel.class, "CategoryVisualPanel.jButton1.text")); // NOI18N
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

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CategoryVisualPanel.class, "CategoryVisualPanel.jLabel1.text")); // NOI18N

        for(String item:fileList) {
            choice1.add(item);
        }
        choice1.setBackground(Color.WHITE);
        choice1.setEnabled(true);
        choice1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        choice1.setFocusable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CategoryVisualPanel.class, "CategoryVisualPanel.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choice5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
            .addComponent(jSeparator3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(choice5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(229, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        JFileChooser chooser = new JFileChooser("");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog((java.awt.Component) null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            regressionFile = chooser.getSelectedFile();
            //System.out.println(regressionFile.getName());
            //copy the file into the project folder
            if (!Installer.projects.isEmpty()) {
                Project mainProject = Installer.projects.get(0);
                FileObject projectDirectory = mainProject.getProjectDirectory();
                FileObject te = projectDirectory.getFileObject("/utsp/input");
                FileObject downloadedFile = null;
                try {
                    downloadedFile = FileUtil.createData(regressionFile);
                    boolean found = false;
                    //copy this file to project input folder
                    for (FileObject children : te.getChildren()) {
                        if (children.isData()) {
                            String source = children.getName() + "." + children.getExt();
                            String dest = downloadedFile.getName() + "." + downloadedFile.getExt();
                            System.out.println(source);
                            System.out.println(dest);
                            boolean check = source.equalsIgnoreCase(dest);
                            if (check) {
                                found = true;
                                break;
                            }
                        }
                    }
                    if (found == false) {
                        choice1.add(regressionFile.getPath());
                        choice1.select(regressionFile.getPath());
                        downloadedFile.copy(te, downloadedFile.getName(), downloadedFile.getExt());
                    } else {
                        String source = downloadedFile.getName() + "." + downloadedFile.getExt();
                        NotifyDescriptor nd = new NotifyDescriptor.Message("The file\t" + regressionFile.getPath() + "\tAlready exists in project");
                        DialogDisplayer.getDefault().notify(nd);
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

        }
    }//GEN-LAST:event_jButton1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables

    private List<String> getAllFile() {
        List<String> temp = new ArrayList<String>();
        System.out.println(Installer.projects);
        if (!Installer.projects.isEmpty()) {
            Project mainProject = Installer.projects.get(0);
            FileObject projectDirectory = mainProject.getProjectDirectory();
            FileObject te = projectDirectory.getFileObject("/utsp/input");
            for (FileObject children : te.getChildren()) {
                if (children.isData()) {
                    temp.add(children.getName() + "." + children.getExt());
                }
            }
        }
        return temp;
    }
}
