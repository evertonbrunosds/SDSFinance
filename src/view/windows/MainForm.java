/*
 * This file is part of the SDSFinance Open Source Project.
 * SDSFinance is licensed under the GNU GPLv3.
 *
 * Copyright (c) 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package view.windows;

import javax.swing.SwingConstants;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela principal da aplicação.
 * @author Everton Bruno Silva dos Santos.
 */
public class MainForm extends javax.swing.JFrame {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 3549108067875363853L;

    /**
     * Construtor responsável por inicializar a janela principal da aplicação.
     */
    public MainForm() {
        initComponents();
        ViewControl.loadRecord();
        ViewControl.alignTo(acquisitionTable, SwingConstants.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        popRemove = new javax.swing.JMenuItem();
        opoEdit = new javax.swing.JMenuItem();
        popShow = new javax.swing.JMenuItem();
        scrollPane = new javax.swing.JScrollPane();
        acquisitionTable = new javax.swing.JTable();
        toolBar = new javax.swing.JMenuBar();
        optRecorder = new javax.swing.JMenu();
        optRemove = new javax.swing.JMenuItem();
        optImport = new javax.swing.JMenuItem();
        optExport = new javax.swing.JMenuItem();
        optTools = new javax.swing.JMenu();
        optProvider = new javax.swing.JMenuItem();
        optBudgeting = new javax.swing.JMenuItem();
        optShows = new javax.swing.JMenuItem();
        optAbout = new javax.swing.JMenu();
        optAuthor = new javax.swing.JMenuItem();

        popRemove.setText("Excluir");
        popupMenu.add(popRemove);

        opoEdit.setText("Editar");
        popupMenu.add(opoEdit);

        popShow.setText("Exibir");
        popupMenu.add(popShow);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SDS Finance");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48/SDSFinance.png"))
        );

        acquisitionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aquisição", "Fornecedor", "Quantidade", "Valor Unitário", "Valor Total", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        acquisitionTable.getTableHeader().setReorderingAllowed(false);
        acquisitionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                acquisitionTableMouseReleased(evt);
            }
        });
        scrollPane.setViewportView(acquisitionTable);
        if (acquisitionTable.getColumnModel().getColumnCount() > 0) {
            acquisitionTable.getColumnModel().getColumn(2).setMinWidth(90);
            acquisitionTable.getColumnModel().getColumn(2).setPreferredWidth(90);
            acquisitionTable.getColumnModel().getColumn(2).setMaxWidth(90);
            acquisitionTable.getColumnModel().getColumn(3).setMinWidth(110);
            acquisitionTable.getColumnModel().getColumn(3).setPreferredWidth(110);
            acquisitionTable.getColumnModel().getColumn(3).setMaxWidth(110);
            acquisitionTable.getColumnModel().getColumn(4).setMinWidth(110);
            acquisitionTable.getColumnModel().getColumn(4).setPreferredWidth(110);
            acquisitionTable.getColumnModel().getColumn(4).setMaxWidth(110);
            acquisitionTable.getColumnModel().getColumn(5).setMinWidth(90);
            acquisitionTable.getColumnModel().getColumn(5).setPreferredWidth(90);
            acquisitionTable.getColumnModel().getColumn(5).setMaxWidth(90);
        }

        optRecorder.setText("Registro");

        optRemove.setText("Excluir");
        optRecorder.add(optRemove);

        optImport.setText("Importar");
        optImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optImportActionPerformed(evt);
            }
        });
        optRecorder.add(optImport);

        optExport.setText("Exportar");
        optExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optExportActionPerformed(evt);
            }
        });
        optRecorder.add(optExport);

        toolBar.add(optRecorder);

        optTools.setText("Ferramentas");
        optTools.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                optToolsItemStateChanged(evt);
            }
        });

        optProvider.setText("Fornecedores");
        optProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optProviderActionPerformed(evt);
            }
        });
        optTools.add(optProvider);

        optBudgeting.setText("Orçamentos");
        optTools.add(optBudgeting);

        optShows.setText("Exibições");
        optTools.add(optShows);

        toolBar.add(optTools);

        optAbout.setText("Sobre");

        optAuthor.setText("Autoria");
        optAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAuthorActionPerformed(evt);
            }
        });
        optAbout.add(optAuthor);

        toolBar.add(optAbout);

        setJMenuBar(toolBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAuthorActionPerformed
        AuthorAbout.showModal();
    }//GEN-LAST:event_optAuthorActionPerformed

    private void acquisitionTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acquisitionTableMouseReleased
        if(evt.isMetaDown() && acquisitionTable.getSelectedRow() != -1) {
            System.out.println("true");
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        } else {
            System.out.println("false");
        }
    }//GEN-LAST:event_acquisitionTableMouseReleased

    private void optProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optProviderActionPerformed
        ProviderManager.showModal();
    }//GEN-LAST:event_optProviderActionPerformed

    private void optToolsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optToolsItemStateChanged
        if(acquisitionTable.getRowCount() == 0) {
            optBudgeting.setEnabled(false);
            optShows.setEnabled(false);
        } else {
            optBudgeting.setEnabled(true);
            optShows.setEnabled(true);            
        }
    }//GEN-LAST:event_optToolsItemStateChanged

    private void optExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optExportActionPerformed
        ViewControl.exportRecord();
    }//GEN-LAST:event_optExportActionPerformed

    private void optImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optImportActionPerformed
        ViewControl.importRecord();
    }//GEN-LAST:event_optImportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable acquisitionTable;
    private javax.swing.JMenuItem opoEdit;
    private javax.swing.JMenu optAbout;
    private javax.swing.JMenuItem optAuthor;
    private javax.swing.JMenuItem optBudgeting;
    private javax.swing.JMenuItem optExport;
    private javax.swing.JMenuItem optImport;
    private javax.swing.JMenuItem optProvider;
    private javax.swing.JMenu optRecorder;
    private javax.swing.JMenuItem optRemove;
    private javax.swing.JMenuItem optShows;
    private javax.swing.JMenu optTools;
    private javax.swing.JMenuItem popRemove;
    private javax.swing.JMenuItem popShow;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration//GEN-END:variables
}