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
import model.sets.SimpleStack;
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
            private static final long serialVersionUID = 8883668983947526195L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
        ViewControl.alignTo(instance.table, SwingConstants.CENTER);
    }

    /**
     * Método responsável por atualizar as opções.
     */
    private void updateOptions() {
        if (table.getRowCount() == 0 || table.getSelectedRow() == -1) {
            optRemove.setEnabled(false);
        } else {
            optRemove.setEnabled(true);
        }
        if (table.getRowCount() == 0 || table.getSelectedRow() == -1 || table.getSelectedRows().length > 1) {
            optAccess.setEnabled(false);
            optEdit.setEnabled(false);
        } else {
            optAccess.setEnabled(true);
            optEdit.setEnabled(true);
        }
    }

    /**
     * Método responsável por excluir uma lista de aquisições ligadas aos fornecedores removidos.
     * @param provider   Refere-se ao fornecedor removido.
     * @param wasChanged Refere-se a informação de que alterações foram feitas.
     * @return Retorna informação de que alterações foram feitas.
     * @throws ElementNotFoundException Exceção lançada no caso das aquisições não terem sido encontradas.
     */
    private boolean removeAcquisitions(final IProvider provider, boolean wasChanged) throws ElementNotFoundException {
        final SimpleStack<IAcquisition> simpleStack = new SimpleStack<>();
        Controller.getInstance().getAcquisitionCollection().forEach(true, element -> {
            if (element.getProvider().equals(provider)) {
                simpleStack.push(element);
            }
        });
        if(!wasChanged) {
            wasChanged = !simpleStack.isEmpty();
        }
        while (!simpleStack.isEmpty()) {
            Controller.getInstance().getAcquisitionCollection().remove(simpleStack.pop().getKey());
        }
        return wasChanged;
    }

    /**
     * Método responsável por excluir uma lista de fornecedores.
     * @throws ElementNotFoundException Exceção lançada no caso dos fornecedores não terem sido encontrados.
     */
    private void removeProviders() throws ElementNotFoundException {
        final int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            if (Show.questionMessage("Essa ação excluirá permanentemente não só os fornecedores selecionadas, mas\n"
                    + "também todas as suas ofertas e aquisições atribuídas a eles, deseja prosseguir?")) {
                IProvider provider;
                boolean wasChanged = false;
                for (final int row : selectedRows) {
                    provider = (IProvider) table.getModel().getValueAt(row, 0);
                    Controller.getInstance().getProviderCollection().remove(provider.getKey());
                    wasChanged = removeAcquisitions(provider, wasChanged);
                }
                ViewControl.saveRecord();
                if(wasChanged) {
                    MainForm.updateWindow();
                }
                updateWindow();
            }
        }
    }

    /**
     * Método responsável por atualizar a janela de fornecedores.
     */
    public static void updateWindow() {
        if (instance != null) {
            ViewControl.clear(instance.table);
            final DefaultTableModel model = (DefaultTableModel) instance.table.getModel();
            Controller.getInstance().getProviderCollection().forEach(false, provider -> {
                model.addRow(Converter.toVector(provider));
            });
        }
    }

    /**
     * Método responsável por exibir a janela de fornecedores.
     */
    public static void showModal() {
        createInstance();
        updateWindow();
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de fornecedores.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private ProviderWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        optAccess = new javax.swing.JMenuItem();
        optAdd = new javax.swing.JMenuItem();
        optRemove = new javax.swing.JMenuItem();
        optEdit = new javax.swing.JMenuItem();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        optAccess.setText("Acessar");
        optAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optAccessActionPerformed(evt);
            }
        });
        popupMenu.add(optAccess);

        optAdd.setText("Adicionar");
        optAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optAddActionPerformed(evt);
            }
        });
        popupMenu.add(optAdd);

        optRemove.setText("Excluir");
        optRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optRemoveActionPerformed(evt);
            }
        });
        popupMenu.add(optRemove);

        optEdit.setText("Editar");
        optEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optEditActionPerformed(evt);
            }
        });
        popupMenu.add(optEdit);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fornecedores");

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                scrollPaneMouseReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "Fornecedor", "Rua", "Bairro", "Cidade" }) {
            /**
             * Refere-se ao número de série da classe.
             */
            private static final long serialVersionUID = -2288420985406491358L;
            boolean[] canEdit = new boolean[] { false, false, false, false };

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

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400,
                Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optAddActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAddActionPerformed
        ProviderManager.showModal();
    }//GEN-LAST:event_optAddActionPerformed

    private void optEditActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditActionPerformed
        ProviderManager.showModal((IProvider) table.getModel().getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_optEditActionPerformed

    private void tableMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        if (evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void scrollPaneMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseReleased
        if (evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneMouseReleased

    private void optRemoveActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveActionPerformed
        try {
            removeProviders();
        } catch (final ElementNotFoundException ex) {
            Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
        }
    }//GEN-LAST:event_optRemoveActionPerformed

    private void optAccessActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAccessActionPerformed
        OfferWindow.showModal((IProvider) table.getModel().getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_optAccessActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem optAccess;
    private javax.swing.JMenuItem optAdd;
    private javax.swing.JMenuItem optEdit;
    private javax.swing.JMenuItem optRemove;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
