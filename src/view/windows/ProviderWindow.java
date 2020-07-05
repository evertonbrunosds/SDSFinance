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
import exceptions.ElementNotFoundException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.organizations.IProvider;
import util.Converter;
import view.managers.Show;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de fornecedores.
 * @author Everton Bruno Silva dos Santos.
 */
public class ProviderWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -1909188430346199723L;
    /**
     * Refere-se a instância da janela de fornecedores.
     */
    private static ProviderWindow instance;
    
    /**
     * Método responsável por gerar instância da janela.
     */
    private static void createInstance() {
        instance = new ProviderWindow(null, true) {
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }

    /**
     * Método responsável por atualizar as opções.
     */
    private void updateOptions() {
        if(table.getRowCount() == 0 || table.getSelectedRow() == -1) {
            optRemove.setEnabled(false);
        } else {
            optRemove.setEnabled(true);
        }
        if(table.getRowCount() == 0 || table.getSelectedRow() == -1 || table.getSelectedRows().length > 1) {
            optAcess.setEnabled(false);
            optEdit.setEnabled(false);
        } else {
            optAcess.setEnabled(true);
            optEdit.setEnabled(true);
        }
    }
    
    /**
     * Método responsável por excluir uma lista de fornecedores.
     * @throws ElementNotFoundException Exceção lançada no caso dos fornecedores não terem sido encontrados.
     */
    private void removeProviders() throws ElementNotFoundException {
        final int[] selectedRows = table.getSelectedRows();
        if(selectedRows.length > 0) {
            if(Show.questionMessage("Essa ação excluirá permanentemente não só os fornecedores selecionadas, mas\n"
                    + "também todas as suas ofertas e aquisições atribuidas a eles, deseja prosseguir?")) {
                IProvider provider;
                for(int row : selectedRows) {
                    provider = (IProvider)table.getModel().getValueAt(row,0);
                    Controller.getInstance().getProviderCollection().remove(provider.getKey());
                }
                ViewControl.saveRecord();
                updateWindow();
            }
        }
    }
    
    /**
     * Método responsável por atualizar a janela de fornecedores.
     */
    public static void updateWindow() {
        if(instance != null) {
            ViewControl.clear(instance.table);
            final DefaultTableModel model = (DefaultTableModel) instance.table.getModel();
            Controller.getInstance().getProviderCollection().forEachInOrder((provider) -> {
                model.addRow(Converter.toVector(provider));
            });
        }
    }
    
    /**
     * Método responsável por exibir a janela de fornecedores.
     */
    public static void showModal() {
        createInstance();
        ViewControl.alignTo(instance.table, SwingConstants.CENTER);
        updateWindow();
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de fornecedores.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private ProviderWindow(java.awt.Frame parent, boolean modal) {
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
        optAcess = new javax.swing.JMenuItem();
        optAdd = new javax.swing.JMenuItem();
        optRemove = new javax.swing.JMenuItem();
        optEdit = new javax.swing.JMenuItem();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        optAcess.setText("Acessar");
        optAcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAcessActionPerformed(evt);
            }
        });
        popupMenu.add(optAcess);

        optAdd.setText("Adicionar");
        optAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAddActionPerformed(evt);
            }
        });
        popupMenu.add(optAdd);

        optRemove.setText("Excluir");
        optRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRemoveActionPerformed(evt);
            }
        });
        popupMenu.add(optRemove);

        optEdit.setText("Editar");
        optEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optEditActionPerformed(evt);
            }
        });
        popupMenu.add(optEdit);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fornecedores");

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                scrollPaneMouseReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        scrollPane.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAddActionPerformed
        ProviderManager.showModal();
    }//GEN-LAST:event_optAddActionPerformed

    private void optEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditActionPerformed
        ProviderManager.showModal((IProvider) table.getModel().getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_optEditActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        if(evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void scrollPaneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseReleased
        if(evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneMouseReleased

    private void optRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveActionPerformed
        try {
            removeProviders();
        } catch (ElementNotFoundException ex) {
            Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
        }
    }//GEN-LAST:event_optRemoveActionPerformed

    private void optAcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAcessActionPerformed
        OfferWindow.showModal((IProvider) table.getModel().getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_optAcessActionPerformed

    /**
     * @param args the command line arguments
     */
    private static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ProviderWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ProviderWindow dialog = new ProviderWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem optAcess;
    private javax.swing.JMenuItem optAdd;
    private javax.swing.JMenuItem optEdit;
    private javax.swing.JMenuItem optRemove;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
