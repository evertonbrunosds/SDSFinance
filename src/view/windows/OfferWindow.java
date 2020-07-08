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

import exceptions.ElementNotFoundException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.Converter;
import view.managers.Show;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de ofertas.
 * @author Everton Bruno Silva dos Santos.
 */
public class OfferWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 7541515669080496994L;
    /**
     * Refere-se a instância da janela de ofertas.
     */
    private static OfferWindow instance;
    /**
     * Refere-se ao fornecedor das ofertas.
     */
    private IProvider provider;
    
    /**
     * Método responsável por gerar instância da janela.
     */
    private static void createInstance() {
        instance = new OfferWindow(null, true) {
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
        ViewControl.alignTo(instance.tableExpense, SwingConstants.CENTER);
        ViewControl.alignTo(instance.tableIncome, SwingConstants.CENTER);
    }
    
    /**
     * Método responsável por atualizar a jenala de ofertas.
     */
    public static void updateWindow() {
        if(instance != null) {
            ViewControl.clear(instance.tableExpense);
            final DefaultTableModel modelExpense = (DefaultTableModel) instance.tableExpense.getModel();
            instance.provider.getExpenseCollection().forEach(false, expense -> {
                modelExpense.addRow(Converter.toVector(expense));
            });
            ViewControl.clear(instance.tableIncome);
            final DefaultTableModel modelIncome = (DefaultTableModel) instance.tableIncome.getModel();
            instance.provider.getIncomeCollection().forEach(false, income -> {
                modelIncome.addRow(Converter.toVector(income));
            });
        }
    }
    
    /**
     * Método responsável por atualizar as opções de despesa.
     */
    private void updateExpenseOptions() {
        if(tableExpense.getRowCount() == 0 || tableExpense.getSelectedRow() == -1) {
            optRemoveExpense.setEnabled(false);
        } else {
            optRemoveExpense.setEnabled(true);
        }
        if(tableExpense.getRowCount() == 0 || tableExpense.getSelectedRow() == -1 || tableExpense.getSelectedRows().length > 1) {
            optAcquireExpense.setEnabled(false);
            optEditExpense.setEnabled(false);
        } else {
            optAcquireExpense.setEnabled(true);
            optEditExpense.setEnabled(true);
        }
    }
    
    /**
     * Método responsável por excluir uma lista de despesas de dado fornecedor.
     * @throws ElementNotFoundException Exceção lançada no caso das despesas não terem sido encontradas.
     */
    private void removeExpense() throws ElementNotFoundException {
        final int[] selectedRows = tableExpense.getSelectedRows();
        if(selectedRows.length > 0) {
            if(Show.questionMessage("Essa ação excluirá permanentemente não só as ofertas selecionadas, \n" 
                    + "mas também todas as aquisições atribuidas a elas, deseja prosseguir?")) {
                IOfferVisible expense;
                for(int row : selectedRows) {
                    expense = (IOfferVisible) tableExpense.getModel().getValueAt(row,0);
                    provider.getExpenseCollection().remove(expense.getKey());
                }
                ViewControl.saveRecord();
                updateWindow();
            }
        }
    }
    
    /**
     * Método responsável por excluir uma lista de rendas de dado fornecedor.
     * @throws ElementNotFoundException Exceção lançada no caso das rendas não terem sido encontradas.
     */
    private void removeIncome() throws ElementNotFoundException {
        final int[] selectedRows = tableIncome.getSelectedRows();
        if(selectedRows.length > 0) {
            if(Show.questionMessage("Essa ação excluirá permanentemente não só as ofertas selecionadas, \n" 
                    + "mas também todas as aquisições atribuidas a elas, deseja prosseguir?")) {
                IOfferVisible income;
                for(int row : selectedRows) {
                    income = (IOfferVisible) tableIncome.getModel().getValueAt(row, 0);
                    provider.getIncomeCollection().remove(income.getKey());
                }
                ViewControl.saveRecord();
                updateWindow();
            }
        }
    }
    
    /**
     * Método responsável por atualizar as opções de renda.
     */
    private void updateIncomeOptions() {
        if(tableIncome.getRowCount() == 0 || tableIncome.getSelectedRow() == -1) {
            optRemoveIncome.setEnabled(false);
        } else {
            optRemoveIncome.setEnabled(true);
        }
        if(tableIncome.getRowCount() == 0 || tableIncome.getSelectedRow() == -1 || tableIncome.getSelectedRows().length > 1) {
            optAcquireIncome.setEnabled(false);
            optEditIncome.setEnabled(false);
        } else {
            optAcquireIncome.setEnabled(true);
            optEditIncome.setEnabled(true);
        }
    }
    
    /**
     * Método responsável por exibir a janela de ofertas.
     * @param provider Refere-se ao fornecedor das ofertas.
     */
    public static void showModal(final IProvider provider) {
        createInstance();
        instance.provider = provider;
        instance.setTitle(provider.toString());
        updateWindow();
        instance.setVisible(true);
    }
    
    /**
     * Construtor responsável pelo instanciamento da janela de ofertas.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private OfferWindow(java.awt.Frame parent, boolean modal) {
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

        popupMenuExpense = new javax.swing.JPopupMenu();
        optAcquireExpense = new javax.swing.JMenuItem();
        optAddExpense = new javax.swing.JMenuItem();
        optRemoveExpense = new javax.swing.JMenuItem();
        optEditExpense = new javax.swing.JMenuItem();
        popupMenuIncome = new javax.swing.JPopupMenu();
        optAcquireIncome = new javax.swing.JMenuItem();
        optAddIncome = new javax.swing.JMenuItem();
        optRemoveIncome = new javax.swing.JMenuItem();
        optEditIncome = new javax.swing.JMenuItem();
        tabbedPane = new javax.swing.JTabbedPane();
        scrollPaneExpense = new javax.swing.JScrollPane();
        tableExpense = new javax.swing.JTable();
        scrollPaneIncome = new javax.swing.JScrollPane();
        tableIncome = new javax.swing.JTable();

        optAcquireExpense.setText("Adiquirir");
        optAcquireExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAcquireExpenseActionPerformed(evt);
            }
        });
        popupMenuExpense.add(optAcquireExpense);

        optAddExpense.setText("Adicionar");
        optAddExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAddExpenseActionPerformed(evt);
            }
        });
        popupMenuExpense.add(optAddExpense);

        optRemoveExpense.setText("Excluir");
        optRemoveExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRemoveExpenseActionPerformed(evt);
            }
        });
        popupMenuExpense.add(optRemoveExpense);

        optEditExpense.setText("Editar");
        optEditExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optEditExpenseActionPerformed(evt);
            }
        });
        popupMenuExpense.add(optEditExpense);

        optAcquireIncome.setText("Adiquirir");
        optAcquireIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAcquireIncomeActionPerformed(evt);
            }
        });
        popupMenuIncome.add(optAcquireIncome);

        optAddIncome.setText("Adicionar");
        optAddIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAddIncomeActionPerformed(evt);
            }
        });
        popupMenuIncome.add(optAddIncome);

        optRemoveIncome.setText("Excluir");
        optRemoveIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRemoveIncomeActionPerformed(evt);
            }
        });
        popupMenuIncome.add(optRemoveIncome);

        optEditIncome.setText("Editar");
        optEditIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optEditIncomeActionPerformed(evt);
            }
        });
        popupMenuIncome.add(optEditIncome);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        scrollPaneExpense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                scrollPaneExpenseMouseReleased(evt);
            }
        });

        tableExpense.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome da Oferta", "Valor da Oferta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableExpense.getTableHeader().setReorderingAllowed(false);
        tableExpense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableExpenseMouseReleased(evt);
            }
        });
        scrollPaneExpense.setViewportView(tableExpense);
        if (tableExpense.getColumnModel().getColumnCount() > 0) {
            tableExpense.getColumnModel().getColumn(0).setMinWidth(120);
            tableExpense.getColumnModel().getColumn(1).setPreferredWidth(0);
        }

        tabbedPane.addTab("Ofertas de Despesa", scrollPaneExpense);

        scrollPaneIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                scrollPaneIncomeMouseReleased(evt);
            }
        });

        tableIncome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome da Oferta", "Valor da Oferta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableIncome.getTableHeader().setReorderingAllowed(false);
        tableIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableIncomeMouseReleased(evt);
            }
        });
        scrollPaneIncome.setViewportView(tableIncome);
        if (tableIncome.getColumnModel().getColumnCount() > 0) {
            tableIncome.getColumnModel().getColumn(0).setMinWidth(120);
            tableIncome.getColumnModel().getColumn(1).setPreferredWidth(0);
        }

        tabbedPane.addTab("Ofertas de Renda", scrollPaneIncome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void scrollPaneExpenseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneExpenseMouseReleased
        if(evt.isMetaDown()) {
            updateExpenseOptions();
            popupMenuExpense.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneExpenseMouseReleased

    private void tableExpenseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableExpenseMouseReleased
        if(evt.isMetaDown()) {
            updateExpenseOptions();
            popupMenuExpense.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableExpenseMouseReleased

    private void scrollPaneIncomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneIncomeMouseReleased
        if(evt.isMetaDown()) {
            updateIncomeOptions();
            popupMenuIncome.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneIncomeMouseReleased

    private void tableIncomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableIncomeMouseReleased
        if(evt.isMetaDown()) {
            updateIncomeOptions();
            popupMenuIncome.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableIncomeMouseReleased

    private void optAddExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAddExpenseActionPerformed
        OfferManager.showModal(provider, true);
    }//GEN-LAST:event_optAddExpenseActionPerformed

    private void optAddIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAddIncomeActionPerformed
        OfferManager.showModal(provider, false);
    }//GEN-LAST:event_optAddIncomeActionPerformed

    private void optRemoveExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveExpenseActionPerformed
        try {
            removeExpense();
        } catch (ElementNotFoundException ex) {
            Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
        }
    }//GEN-LAST:event_optRemoveExpenseActionPerformed

    private void optRemoveIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveIncomeActionPerformed
        try {
            removeIncome();
        } catch (ElementNotFoundException ex) {
            Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
        }
    }//GEN-LAST:event_optRemoveIncomeActionPerformed

    private void optEditExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditExpenseActionPerformed
        OfferManager.showModal(provider, (IOfferVisible) tableExpense.getValueAt(tableExpense.getSelectedRow(), 0), true);
    }//GEN-LAST:event_optEditExpenseActionPerformed

    private void optEditIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditIncomeActionPerformed
        OfferManager.showModal(provider, (IOfferVisible) tableIncome.getValueAt(tableIncome.getSelectedRow(), 0), false);
    }//GEN-LAST:event_optEditIncomeActionPerformed

    private void optAcquireExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAcquireExpenseActionPerformed
        AcquisitionManager.showModal(provider, (IOfferVisible) tableExpense.getValueAt(tableExpense.getSelectedRow(), 0));
    }//GEN-LAST:event_optAcquireExpenseActionPerformed

    private void optAcquireIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAcquireIncomeActionPerformed
        AcquisitionManager.showModal(provider, (IOfferVisible) tableIncome.getValueAt(tableIncome.getSelectedRow(), 0));
    }//GEN-LAST:event_optAcquireIncomeActionPerformed

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
            java.util.logging.Logger.getLogger(OfferWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            OfferWindow dialog = new OfferWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem optAcquireExpense;
    private javax.swing.JMenuItem optAcquireIncome;
    private javax.swing.JMenuItem optAddExpense;
    private javax.swing.JMenuItem optAddIncome;
    private javax.swing.JMenuItem optEditExpense;
    private javax.swing.JMenuItem optEditIncome;
    private javax.swing.JMenuItem optRemoveExpense;
    private javax.swing.JMenuItem optRemoveIncome;
    private javax.swing.JPopupMenu popupMenuExpense;
    private javax.swing.JPopupMenu popupMenuIncome;
    private javax.swing.JScrollPane scrollPaneExpense;
    private javax.swing.JScrollPane scrollPaneIncome;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableExpense;
    private javax.swing.JTable tableIncome;
    // End of variables declaration//GEN-END:variables
}
