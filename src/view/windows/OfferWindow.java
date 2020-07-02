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

import javax.swing.table.DefaultTableModel;
import model.organizations.IProvider;
import util.Converter;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de ofertas.
 * @author Everton Bruno Silva dos Santos.
 */
public class OfferWindow extends javax.swing.JDialog {
    /**
     * Refere-se a instância da janela de ofertas.
     */
    private static OfferWindow instance;
    /**
     * Refere-se ao fornecedor das ofertas.
     */
    private IProvider provider;
    
    /**
     * Método responsável por exibir a janela de ofertas.
     * @param provider Refere-se ao fornecedor das ofertas.
     */
    public static void showModal(final IProvider provider) {
        instance = new OfferWindow(null, true);
        instance.provider = provider;
        instance.setVisible(true);
    }
    
    /**
     * Método responsável por atualizar a jenala de ofertas.
     */
    public static void updateWindow() {
        if(instance != null) {
            ViewControl.clear(instance.tableExpense);
            final DefaultTableModel modelExpense = (DefaultTableModel) instance.tableExpense.getModel();
            instance.provider.getExpenseCollection().forEachInOrder((expense) -> {
                modelExpense.addRow(Converter.toVector(expense));
            });
            ViewControl.clear(instance.tableIncome);
            final DefaultTableModel modelIncome = (DefaultTableModel) instance.tableIncome.getModel();
            instance.provider.getIncomeCollection().forEachInOrder((income) -> {
                modelIncome.addRow(Converter.toVector(income));
            });
        }
    }

    /**
     * Construtor responsável pelo instanciamento da janela de ofertas.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    public OfferWindow(java.awt.Frame parent, boolean modal) {
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

        tabbedPane = new javax.swing.JTabbedPane();
        scrollPaneExpense = new javax.swing.JScrollPane();
        tableExpense = new javax.swing.JTable();
        scrollPaneIncome = new javax.swing.JScrollPane();
        tableIncome = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableExpense.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
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
        scrollPaneExpense.setViewportView(tableExpense);
        if (tableExpense.getColumnModel().getColumnCount() > 0) {
            tableExpense.getColumnModel().getColumn(0).setMinWidth(120);
            tableExpense.getColumnModel().getColumn(1).setPreferredWidth(0);
        }

        tabbedPane.addTab("Ofertas de Despesa", scrollPaneExpense);

        tableIncome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
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
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JScrollPane scrollPaneExpense;
    private javax.swing.JScrollPane scrollPaneIncome;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableExpense;
    private javax.swing.JTable tableIncome;
    // End of variables declaration//GEN-END:variables
}