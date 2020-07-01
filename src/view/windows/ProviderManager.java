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

import control.Controller;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.organizations.IProvider;
import util.Converter;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de fornecedores.
 * @author Everton Bruno Silva dos Santos.
 */
public class ProviderManager extends javax.swing.JDialog {
    /**
     * Refere-se a instância da janela de fornecedores.
     */
    private static ProviderManager instance;
    
    /**
     * Método responsável por atualizar a janela de fornecedores.
     */
    public static void updateWindow() {
        if(instance != null) {
            ViewControl.clear(instance.providerTable);
            final DefaultTableModel model = (DefaultTableModel) instance.providerTable.getModel();
            Controller.getInstance().getProviderCollection().forEach((provider) -> {
                model.addRow(Converter.toVector(provider));
            });
        }
    }
    
    /**
     * Método responsável por exibir a janela de fornecedores.
     */
    public static void showModal() {
        instance = new ProviderManager(null, true);
        ViewControl.alignTo(instance.providerTable, SwingConstants.CENTER);
        updateWindow();
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de fornecedores.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private ProviderManager(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        optShowOffers = new javax.swing.JMenuItem();
        optEditProvider = new javax.swing.JMenuItem();
        scrollPane = new javax.swing.JScrollPane();
        providerTable = new javax.swing.JTable();
        toolBar = new javax.swing.JMenuBar();
        optTools = new javax.swing.JMenu();
        optAdd = new javax.swing.JMenuItem();
        optSearch = new javax.swing.JMenuItem();
        optRemove = new javax.swing.JMenuItem();

        optShowOffers.setText("Exibir Ofertas");
        popupMenu.add(optShowOffers);

        optEditProvider.setText("Editar Fornecedor");
        optEditProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optEditProviderActionPerformed(evt);
            }
        });
        popupMenu.add(optEditProvider);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fornecedores");

        providerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fornecedor", "Rua", "Bairro", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        providerTable.getTableHeader().setReorderingAllowed(false);
        providerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                providerTableMousePressed(evt);
            }
        });
        scrollPane.setViewportView(providerTable);

        optTools.setText("Ferramentas");
        optTools.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                optToolsItemStateChanged(evt);
            }
        });

        optAdd.setText("Adicionar");
        optAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAddActionPerformed(evt);
            }
        });
        optTools.add(optAdd);

        optSearch.setText("Buscar");
        optTools.add(optSearch);

        optRemove.setText("Excluir");
        optTools.add(optRemove);

        toolBar.add(optTools);

        setJMenuBar(toolBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optToolsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optToolsItemStateChanged
        if(providerTable.getRowCount() == 0) {
            optRemove.setEnabled(false);
            optSearch.setEnabled(false);
        } else {
            optSearch.setEnabled(true);
            if(this.providerTable.getSelectedRow() == -1) {
                optRemove.setEnabled(false);
            } else {
                optRemove.setEnabled(true);
            }
        }
    }//GEN-LAST:event_optToolsItemStateChanged

    private void optAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAddActionPerformed
        AddOrEditProvider.showModal();
    }//GEN-LAST:event_optAddActionPerformed

    private void providerTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_providerTableMousePressed
        if(evt.isMetaDown() && providerTable.getSelectedRow() != -1) {
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_providerTableMousePressed

    private void optEditProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditProviderActionPerformed
        AddOrEditProvider.showModal((IProvider) providerTable.getModel().getValueAt(providerTable.getSelectedRow(), 0));
    }//GEN-LAST:event_optEditProviderActionPerformed

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
            java.util.logging.Logger.getLogger(ProviderManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ProviderManager dialog = new ProviderManager(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem optAdd;
    private javax.swing.JMenuItem optEditProvider;
    private javax.swing.JMenuItem optRemove;
    private javax.swing.JMenuItem optSearch;
    private javax.swing.JMenuItem optShowOffers;
    private javax.swing.JMenu optTools;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JTable providerTable;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration//GEN-END:variables
}
