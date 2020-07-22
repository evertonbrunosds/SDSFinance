/*
 * This file is part of the SDSFinance Open Source Project.
 * SDSFinance is licensed under the GNU GPLv3.
 *
 * Copyright © 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
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

import control.Record;
import exceptions.ElementNotFoundException;
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import model.business.IAcquisition;
import model.organizations.IProvider;
import model.sets.SimpleStack;
import model.territories.City;
import model.territories.Neighborhood;
import model.territories.Street;
import util.Factory;
import view.managers.Show;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de adição ou edição de fornecedores.
 * @author Everton Bruno Silva dos Santos.
 */
public class ProviderManager extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 461334098237371860L;
    /**
     * Refere-se a instância da janela de adição ou edição de fornecedores.
     */
    private static ProviderManager instance;
    /**
     * Refere-se ao fornecedor contido na classe.
     */
    private IProvider provider;

    /**
     * Método responsável por gerar instância da janela.
     */
    private static void createInstance() {
        instance = new ProviderManager(null, true) {
            private static final long serialVersionUID = 1722644153201452996L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }

    /**
     * Método responsável por adicionar um fornecedor.
     * @throws NullObjectException Exceção lançada no caso de haver uma string nula.
     * @throws KeyUsedException    Exceção lançada em caso de haver um outro fornecedor com mesmo nome no mesmo local.
     */
    private void addProvider() throws NullObjectException, KeyUsedException {
        final City city = Factory.city(textCity.getText());
        final Neighborhood neighborhood = Factory.neighborhood(textNeighborhood.getText());
        final Street street = Factory.street(textStreet.getText());
        final IProvider tmpProvider = Factory.provider(textName.getText(), street, neighborhood, city);
        Record.getInstance().getProviderCollection().insert(tmpProvider);
        ViewControl.setWasChanged(false);
        ProviderWindow.updateWindow();
        dispose();
    }

    /**
     * Método responsável por coletar alterações.
     * @param keysStackChanged         Refere-se a pilha de chaves alteradas
     * @param acquisitionsStackChanged Refere-se apilha de aquisições alteradas.
     */
    private void collectChanges(final SimpleStack<Comparable<String>> keysStackChanged,
            final SimpleStack<IAcquisition> acquisitionsStackChanged) {
        if (keysStackChanged.isEmpty() && acquisitionsStackChanged.isEmpty()) {
            Record.getInstance().getAcquisitionCollection().forEach(true, element -> {
                if (element.getProvider().equals(provider)) {
                    keysStackChanged.push(element.getKey());
                    acquisitionsStackChanged.push(element);
                }
            });
        }
    }

    /**
     * Método responsável por alterar a cidade de um fornecedor.
     * @param keysStackChanged         Refere-se a pilha de chaves alteradas
     * @param acquisitionsStackChanged Refere-se apilha de aquisições alteradas.
     * @param wasChanged               Refere-se a informação de que o fornecedor foi modificado.
     * @return Retorna informação de que o fornecedor foi modificado.
     * @throws NullObjectException      Exceção lançada no caso de haver uma string nula.
     * @throws ElementNotFoundException Exceção lançada no caso do fornecedor não ser encontrado.
     * @throws KeyUsedException         Exceção lançada em caso de haver um outro fornecedor com mesmo nome no mesmo local.
     */
    private boolean setCity(final SimpleStack<Comparable<String>> keysStackChanged,
            final SimpleStack<IAcquisition> acquisitionsStackChanged, final boolean wasChanged)
            throws NullObjectException, ElementNotFoundException, KeyUsedException {
        if (!textCity.getText().equals(provider.getCity().toString())) {
            collectChanges(keysStackChanged, acquisitionsStackChanged);
            final City city = Factory.city(textCity.getText());
            Record.getInstance().getProviderCollection().setCity(provider.getKey().toString(), city);
            return true;
        } else {
            return wasChanged == true;
        }
    }

    /**
     * Método responsável por alterar o bairro de um fornecedor.
     * @param keysStackChanged         Refere-se a pilha de chaves alteradas
     * @param acquisitionsStackChanged Refere-se apilha de aquisições alteradas.
     * @param wasChanged               Refere-se a informação de que o fornecedor foi modificado.
     * @return Retorna informação de que o fornecedor foi modificado.
     * @throws NullObjectException      Exceção lançada no caso de haver uma string nula.
     * @throws ElementNotFoundException Exceção lançada no caso do fornecedor não ser encontrado.
     * @throws KeyUsedException         Exceção lançada em caso de haver um outro fornecedor com mesmo nome no mesmo local.
     */
    private boolean setNeighborhood(final SimpleStack<Comparable<String>> keysStackChanged,
            final SimpleStack<IAcquisition> acquisitionsStackChanged, final boolean wasChanged)
            throws NullObjectException, ElementNotFoundException, KeyUsedException {
        if (!textNeighborhood.getText().equals(provider.getNeighborhood().toString())) {
            collectChanges(keysStackChanged, acquisitionsStackChanged);
            final Neighborhood neighborhood = Factory.neighborhood(textNeighborhood.getText());
            Record.getInstance().getProviderCollection().setNeighborhood(provider.getKey().toString(), neighborhood);
            return true;
        } else {
            return wasChanged == true;
        }
    }

    /**
     * Método responsável por alterar a rua de um fornecedor.
     * @param keysStackChanged         Refere-se a pilha de chaves alteradas
     * @param acquisitionsStackChanged Refere-se apilha de aquisições alteradas.
     * @param wasChanged               Refere-se a informação de que o fornecedor foi modificado.
     * @return Retorna informação de que o fornecedor foi modificado.
     * @throws NullObjectException      Exceção lançada no caso de haver uma string nula.
     * @throws ElementNotFoundException Exceção lançada no caso do fornecedor não ser encontrado.
     * @throws KeyUsedException         Exceção lançada em caso de haver um outro fornecedor com mesmo nome no mesmo local.
     */
    private boolean setStreet(final SimpleStack<Comparable<String>> keysStackChanged,
            final SimpleStack<IAcquisition> acquisitionsStackChanged, final boolean wasChanged)
            throws NullObjectException, ElementNotFoundException, KeyUsedException {
        if (!textStreet.getText().equals(provider.getStreet().toString())) {
            collectChanges(keysStackChanged, acquisitionsStackChanged);
            final Street street = Factory.street(textStreet.getText());
            Record.getInstance().getProviderCollection().setStreet(provider.getKey().toString(), street);
            return true;
        } else {
            return wasChanged == true;
        }
    }

    /**
     * Método responsável por alterar o nome de um fornecedor.
     * @param keysStackChanged         Refere-se a pilha de chaves alteradas
     * @param acquisitionsStackChanged Refere-se apilha de aquisições alteradas.
     * @param wasChanged               Refere-se a informação de que o fornecedor foi modificado.
     * @return Retorna informação de que o fornecedor foi modificado.
     * @throws NullObjectException      Exceção lançada no caso de haver uma string nula.
     * @throws ElementNotFoundException Exceção lançada no caso do fornecedor não ser encontrado.
     * @throws KeyUsedException         Exceção lançada em caso de haver um outro fornecedor com mesmo nome no mesmo local.
     */
    private boolean setName(final SimpleStack<Comparable<String>> keysStackChanged,
            final SimpleStack<IAcquisition> acquisitionsStackChanged, final boolean wasChanged)
            throws NullObjectException, ElementNotFoundException, KeyUsedException {
        if (!textName.getText().equals(provider.toString())) {
            collectChanges(keysStackChanged, acquisitionsStackChanged);
            Record.getInstance().getProviderCollection().redefineKey(provider.getKey().toString(), textName.getText());
            return true;
        } else {
            return wasChanged == true;
        }
    }

    /**
     * Método responsável por editar um fornecedor.
     * @throws NullObjectException      Exceção lançada no caso de haver uma string nula.
     * @throws ElementNotFoundException Exceção lançada no caso do fornecedor não ser encontrado.
     * @throws KeyUsedException         Exceção lançada em caso de haver um outro fornecedor com mesmo nome no mesmo local.
     */
    private void editProvider() throws NullObjectException, ElementNotFoundException, KeyUsedException {
        boolean wasChanged = false;
        final SimpleStack<Comparable<String>> keysStackChanged = new SimpleStack<>();
        final SimpleStack<IAcquisition> acquisitionsStackChanged = new SimpleStack<>();
        wasChanged = setCity(keysStackChanged, acquisitionsStackChanged, wasChanged);
        wasChanged = setNeighborhood(keysStackChanged, acquisitionsStackChanged, wasChanged);
        wasChanged = setStreet(keysStackChanged, acquisitionsStackChanged, wasChanged);
        wasChanged = setName(keysStackChanged, acquisitionsStackChanged, wasChanged);
        if (!keysStackChanged.isEmpty()) {
            while (!keysStackChanged.isEmpty()) {
                Record.getInstance().getAcquisitionCollection().remove(keysStackChanged.pop());
                Record.getInstance().getAcquisitionCollection().insert(acquisitionsStackChanged.pop());
            }
            MainForm.updateWindow();
        }
        if (wasChanged) {
            ViewControl.setWasChanged(false);
            ProviderWindow.updateWindow();
        }
        dispose();
    }

    /**
     * Método responsável por exibir a janela de adição de fornecedores.
     */
    public static void showModal() {
        createInstance();
        instance.setVisible(true);
    }

    /**
     * Método responsável por exibir a janela de edição de fornecedores.
     * @param provider Refere-se ao fornecedor editável.
     */
    public static void showModal(final IProvider provider) {
        createInstance();
        instance.btnConfirm.setText("Aplicar");
        instance.setTitle("Editar Fornecedor");
        instance.provider = provider;
        instance.textCity.setText(provider.getCity().toString());
        instance.textNeighborhood.setText(provider.getNeighborhood().toString());
        instance.textStreet.setText(provider.getStreet().toString());
        instance.textName.setText(provider.toString());
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de adição ou edição de fornecedores.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private ProviderManager(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCity = new javax.swing.JLabel();
        textCity = new javax.swing.JTextField();
        labelNeighborhood = new javax.swing.JLabel();
        textNeighborhood = new javax.swing.JTextField();
        labelStreet = new javax.swing.JLabel();
        textStreet = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Fornecedor");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48/SDSFinance.png")));
        setResizable(false);

        labelCity.setText("Cidade:");

        textCity.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelNeighborhood.setText("Bairro:");

        textNeighborhood.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelStreet.setText("Rua:");

        textStreet.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelName.setText("Nome:");

        textName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnConfirm.setText("Adicionar");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(labelCity)
                        .addComponent(labelNeighborhood).addComponent(labelStreet).addComponent(labelName))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textCity, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textNeighborhood, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textStreet, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textName, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                        .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 96,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(textCity, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelCity))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(textNeighborhood, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelNeighborhood))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(textStreet, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelStreet))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelName))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancel).addComponent(btnConfirm))
                        .addGap(20, 20, 20)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if (provider == null) {
            try {
                addProvider();
            } catch (final NullObjectException ex) {
                Show.warningMessage("Todos os campos devem ser preenchidos.");
            } catch (final KeyUsedException ex) {
                Show.warningMessage("Outro fornecedor já faz uso do nome \"" + ex.getElement().toString()
                        + "\" na mesma rua, bairro e cidade indicada.");
            }
        } else {
            try {
                editProvider();
            } catch (final NullObjectException ex) {
                Show.warningMessage("Todos os campos devem estar preenchidos.");
            } catch (final ElementNotFoundException ex) {
                Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
            } catch (final KeyUsedException ex) {
                Show.warningMessage("Outro fornecedor já faz uso do nome \"" + ex.getElement().toString()
                        + "\" na mesma rua, bairro e cidade indicada.");
            }
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel labelCity;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNeighborhood;
    private javax.swing.JLabel labelStreet;
    private javax.swing.JTextField textCity;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textNeighborhood;
    private javax.swing.JTextField textStreet;
    // End of variables declaration//GEN-END:variables
}