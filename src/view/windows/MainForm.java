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
import model.business.IAcquisition;
import model.organizations.IProvider;
import util.Converter;
import view.managers.Show;
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
     * Refere-se a instância da janela principal.
     */
    private static MainForm instance;

    /**
     * Construtor responsável por inicializar a janela principal da aplicação.
     */
    private MainForm() {
        instance = this;
        initComponents();
        ViewControl.loadRecord();
        ViewControl.alignTo(table, SwingConstants.CENTER);
        updateWindow();
    }

    /**
     * Método responsável por atualizar a jenala principal.
     */
    public static void updateWindow() {
        if (instance != null) {
            ViewControl.clear(instance.table);
            final DefaultTableModel model = (DefaultTableModel) instance.table.getModel();
            Controller.getInstance().getAcquisitionCollection().forEach(true, acquisition -> {
                model.addRow(Converter.toVector(acquisition));
            });
        }
    }

    /**
     * Método responsável por excluir uma lista de aquisições.
     * @throws ElementNotFoundException Exceção lançada no caso das aquisições não terem sido encontradas.
     */
    private void removeAcquisitions() throws ElementNotFoundException {
        final int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            if (Show.questionMessage("Essa ação excluirá permanentemente todas as\n"
                    + "aquisições selecionadas, deseja prosseguir?")) {
                IAcquisition acquisition;
                for (final int row : selectedRows) {
                    acquisition = (IAcquisition) table.getModel().getValueAt(row, 0);
                    Controller.getInstance().getAcquisitionCollection().remove(acquisition.getKey());
                }
                ViewControl.saveRecord();
                updateWindow();
            }
        }
    }

    /**
     * Método responsável por atualizar as opções.
     */
    private void updateOptions() {
        if (table.getRowCount() == 0 || table.getSelectedRow() == -1) {
            optRemoveAcquisition.setEnabled(false);
        } else {
            optRemoveAcquisition.setEnabled(true);
        }
        if (table.getRowCount() == 0 || table.getSelectedRow() == -1 || table.getSelectedRows().length > 1) {
            optAcessProvider.setEnabled(false);
            optEditAcquisition.setEnabled(false);
        } else {
            optAcessProvider.setEnabled(true);
            optEditAcquisition.setEnabled(true);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        optAcessProvider = new javax.swing.JMenuItem();
        optEditAcquisition = new javax.swing.JMenuItem();
        optRemoveAcquisition = new javax.swing.JMenuItem();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        toolBar = new javax.swing.JMenuBar();
        optRecorder = new javax.swing.JMenu();
        optImport = new javax.swing.JMenuItem();
        optExport = new javax.swing.JMenuItem();
        optRestart = new javax.swing.JMenuItem();
        optShow = new javax.swing.JMenu();
        optProvider = new javax.swing.JMenuItem();
        optBudgeting = new javax.swing.JMenuItem();
        optExtracts = new javax.swing.JMenuItem();
        optAbout = new javax.swing.JMenu();
        optAuthor = new javax.swing.JMenuItem();

        optAcessProvider.setText("Acessar Fornecedor");
        optAcessProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optAcessProviderActionPerformed(evt);
            }
        });
        popupMenu.add(optAcessProvider);

        optEditAcquisition.setText("Editar Aquisição");
        optEditAcquisition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optEditAcquisitionActionPerformed(evt);
            }
        });
        popupMenu.add(optEditAcquisition);

        optRemoveAcquisition.setText("Excluir Aquisições");
        optRemoveAcquisition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optRemoveAcquisitionActionPerformed(evt);
            }
        });
        popupMenu.add(optRemoveAcquisition);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SDS Finance");
        setIconImage(
                java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48/SDSFinance.png")));

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                scrollPaneMouseReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "Aquisição", "Fornecedor", "Quantidade", "Valor Unitário", "Valor Total", "Data" }) {
            /**
             * Refere-se ao número de série da classe.
             */
            private static final long serialVersionUID = 1239724359305259903L;
            boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(2).setMinWidth(90);
            table.getColumnModel().getColumn(2).setPreferredWidth(90);
            table.getColumnModel().getColumn(2).setMaxWidth(90);
            table.getColumnModel().getColumn(3).setMinWidth(110);
            table.getColumnModel().getColumn(3).setPreferredWidth(110);
            table.getColumnModel().getColumn(3).setMaxWidth(110);
            table.getColumnModel().getColumn(4).setMinWidth(110);
            table.getColumnModel().getColumn(4).setPreferredWidth(110);
            table.getColumnModel().getColumn(4).setMaxWidth(110);
            table.getColumnModel().getColumn(5).setMinWidth(90);
            table.getColumnModel().getColumn(5).setPreferredWidth(90);
            table.getColumnModel().getColumn(5).setMaxWidth(90);
        }

        optRecorder.setText("Registro");

        optImport.setText("Importar");
        optImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optImportActionPerformed(evt);
            }
        });
        optRecorder.add(optImport);

        optExport.setText("Exportar");
        optExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optExportActionPerformed(evt);
            }
        });
        optRecorder.add(optExport);

        optRestart.setText("Reiniciar");
        optRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optRestartActionPerformed(evt);
            }
        });
        optRecorder.add(optRestart);

        toolBar.add(optRecorder);

        optShow.setText("Exibir");
        optShow.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(final java.awt.event.ItemEvent evt) {
                optShowItemStateChanged(evt);
            }
        });

        optProvider.setText("Fornecedores");
        optProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optProviderActionPerformed(evt);
            }
        });
        optShow.add(optProvider);

        optBudgeting.setText("Orçamentos");
        optShow.add(optBudgeting);

        optExtracts.setText("Extratos");
        optExtracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optExtractsActionPerformed(evt);
            }
        });
        optShow.add(optExtracts);

        toolBar.add(optShow);

        optAbout.setText("Sobre");

        optAuthor.setText("Autoria");
        optAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optAuthorActionPerformed(evt);
            }
        });
        optAbout.add(optAuthor);

        toolBar.add(optAbout);

        setJMenuBar(toolBar);

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optAuthorActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAuthorActionPerformed
        AuthorAbout.showModal();
    }//GEN-LAST:event_optAuthorActionPerformed

    private void tableMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        if (evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void optProviderActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optProviderActionPerformed
        ProviderWindow.showModal();
    }//GEN-LAST:event_optProviderActionPerformed

    private void optShowItemStateChanged(final java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optShowItemStateChanged
        if (table.getRowCount() == 0) {
            optBudgeting.setEnabled(false);
            optExtracts.setEnabled(false);
        } else {
            optBudgeting.setEnabled(true);
            optExtracts.setEnabled(true);
        }
    }//GEN-LAST:event_optShowItemStateChanged

    private void optExportActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optExportActionPerformed
        ViewControl.exportRecord();
    }//GEN-LAST:event_optExportActionPerformed

    private void optImportActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optImportActionPerformed
        ViewControl.importRecord();
    }//GEN-LAST:event_optImportActionPerformed

    private void optExtractsActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optExtractsActionPerformed
        ExtractsWindow.showModal();
    }//GEN-LAST:event_optExtractsActionPerformed

    private void optRestartActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRestartActionPerformed
        ViewControl.restartRecord();
    }//GEN-LAST:event_optRestartActionPerformed

    private void optRemoveAcquisitionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveAcquisitionActionPerformed
        try {
            removeAcquisitions();
        } catch (final ElementNotFoundException ex) {
            Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
        }
    }//GEN-LAST:event_optRemoveAcquisitionActionPerformed

    private void scrollPaneMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseReleased
        if (evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneMouseReleased

    private void optEditAcquisitionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditAcquisitionActionPerformed
        AcquisitionManager.showModal((IAcquisition) table.getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_optEditAcquisitionActionPerformed

    private void optAcessProviderActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAcessProviderActionPerformed
        OfferWindow.showModal((IProvider) table.getValueAt(table.getSelectedRow(), 1));
    }//GEN-LAST:event_optAcessProviderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        try {
            for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu optAbout;
    private javax.swing.JMenuItem optAcessProvider;
    private javax.swing.JMenuItem optAuthor;
    private javax.swing.JMenuItem optBudgeting;
    private javax.swing.JMenuItem optEditAcquisition;
    private javax.swing.JMenuItem optExport;
    private javax.swing.JMenuItem optExtracts;
    private javax.swing.JMenuItem optImport;
    private javax.swing.JMenuItem optProvider;
    private javax.swing.JMenu optRecorder;
    private javax.swing.JMenuItem optRemoveAcquisition;
    private javax.swing.JMenuItem optRestart;
    private javax.swing.JMenu optShow;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration//GEN-END:variables
}