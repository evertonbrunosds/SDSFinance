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

import control.Record;
import exceptions.DoubleValueInvalidException;
import exceptions.ElementNotFoundException;
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import model.offers.IExpense;
import model.offers.IIncome;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import model.sets.SimpleStack;
import util.Converter;
import util.Factory;
import view.managers.Show;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de adição ou edição de ofertas.
 * @author Everton Bruno Silva dos Santos.
 */
public class OfferManager extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 5156805488678896728L;
    /**
     * Refere-se a instância da janela de adição ou edição de ofertas.
     */
    private static OfferManager instance;
    /**
     * Refere-se ao fornecedor contido na classe.
     */
    private IProvider provider;
    /**
     * Refere-se a oferta contida na classe.
     */
    private IOfferVisible offer;
    /**
     * Refere-se a informação booleana que indica se a oferta é uma despesa.
     */
    private boolean isExpense;

    /**
     * Método responsável por gerar instância da janela.
     */
    private static void createInstance() {
        instance = new OfferManager(null, true) {
            private static final long serialVersionUID = -3908618039438825771L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }

    /**
     * Método responsável por adicionar uma oferta a dado fornecedor.
     * @throws NullObjectException         Exceção lançada no caso de haverem dados de entrada nulos.
     * @throws DoubleValueInvalidException Exceção lançada no caso de um valor decimal ser inválido.
     * @throws KeyUsedException            Exceção lançada no caso de haver uma outra oferta de mesmo nome.
     */
    private void addOffer() throws NullObjectException, DoubleValueInvalidException, KeyUsedException {
        if (isExpense) {
            final IExpense expense = Factory.expense(textName.getText(), Converter.toDouble(textValue.getText()));
            provider.getExpenseCollection().insert(expense);
        } else {
            final IIncome income = Factory.income(textName.getText(), Converter.toDouble(textValue.getText()));
            provider.getIncomeCollection().insert(income);
        }
        ViewControl.setWasChanged(false);
        OfferWindow.updateWindow();
        dispose();
    }

    /**
     * Método responsável por coletar alterações.
     * @param keysStackChanged Refere-se a pilha de chaves alteradas.
     */
    private void collectChanges(final SimpleStack<Comparable<String>> keysStackChanged) {
        if (keysStackChanged.isEmpty()) {
            Record.getInstance().getAcquisitionCollection().forEach(true, element -> {
                if (element.getProvider().equals(provider) && element.getOffer().getKey().equals(offer.getKey())) {
                    keysStackChanged.push(element.getKey());
                }
            });
        }
    }

    /**
     * Método responsável por alterar o nome da oferta.
     * @param keysStackChanged Refere-se a pilha de chaves alteradas.
     * @throws NullObjectException         Exceção lançada no caso de haver uma string nula.
     * @throws DoubleValueInvalidException Exceção lançada no caso de um valor decimal ser inválido.
     * @throws ElementNotFoundException    Exceção lançada no caso da oferta não ser encontrada.
     */
    private void setName(final SimpleStack<Comparable<String>> keysStackChanged)
            throws ElementNotFoundException, NullObjectException, KeyUsedException {
        if (!textName.getText().equals(offer.toString())) {
            collectChanges(keysStackChanged);
            if (isExpense) {
                provider.getExpenseCollection().redefineKey(offer.getKey(), textName.getText());
            } else {
                provider.getIncomeCollection().redefineKey(offer.getKey(), textName.getText());
            }
        }
    }

    /**
     * Método responsável por alterar o valor da oferta.
     * @return Retorna indicativo de que a oferta foi alterada.
     * @throws NullObjectException         Exceção lançada no caso de haver uma string nula.
     * @throws DoubleValueInvalidException Exceção lançada no caso de um valor decimal ser inválido.
     * @throws ElementNotFoundException    Exceção lançada no caso da oferta não ser encontrada.
     */
    private boolean setValue() throws NullObjectException, DoubleValueInvalidException, ElementNotFoundException {
        if (!textValue.getText().equals(Double.toString(offer.getValue()))) {
            if (isExpense) {
                provider.getExpenseCollection().setValue(offer.getKey(), Converter.toDouble(textValue.getText()));
            } else {
                provider.getIncomeCollection().setValue(offer.getKey(), Converter.toDouble(textValue.getText()));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método responsável por editar uma oferta de dado fornecedor.
     * @throws NullObjectException         Exceção lançada no caso de haver uma string nula.
     * @throws DoubleValueInvalidException Exceção lançada no caso de um valor decimal ser inválido.
     * @throws ElementNotFoundException    Exceção lançada no caso da oferta não ser encontrada.
     */
    private void editOffer() throws NullObjectException, DoubleValueInvalidException, ElementNotFoundException, KeyUsedException {
        boolean wasChanged = setValue();
        final SimpleStack<Comparable<String>> keysStackChanged = new SimpleStack<>();
        setName(keysStackChanged);
        if (!keysStackChanged.isEmpty()) {
            while (!keysStackChanged.isEmpty()) {
                Record.getInstance().getAcquisitionCollection().redefineKey(keysStackChanged.pop(), offer.toString());
            }
            MainForm.updateWindow();
            wasChanged = true;
        }
        if (wasChanged) {
            ViewControl.setWasChanged(false);
            OfferWindow.updateWindow();
        }
        dispose();
    }

    /**
     * Método responsável por exibir a janela de adição de ofertas a dado fornecedor.
     * @param provider  Refere-se ao fornecedor.
     * @param isExpense Refere-se a informação booleana que indica se a oferta é uma despesa.
     */
    public static void showModal(final IProvider provider, final boolean isExpense) {
        createInstance();
        instance.provider = provider;
        instance.isExpense = isExpense;
        instance.setVisible(true);
    }

    /**
     * Método responsável por exibir a janela de edição de ofertas de dado fornecedor.
     * @param provider  Refere-se ao fornecedor.
     * @param offer     Refere-se a oferta.
     * @param isExpense Refere-se a informação booleana que indica se a oferta é uma despesa.
     */
    public static void showModal(final IProvider provider, final IOfferVisible offer, final boolean isExpense) {
        createInstance();
        instance.provider = provider;
        instance.offer = offer;
        instance.isExpense = isExpense;
        instance.textName.setText(offer.toString());
        instance.textValue.setText(Double.toString(offer.getValue()));
        instance.btnConfirm.setText("Aplicar");
        instance.setTitle("Editar Oferta");
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de adição ou edição de ofertas.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private OfferManager(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelName = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        labelValue = new javax.swing.JLabel();
        textValue = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Oferta");
        setResizable(false);

        labelName.setText("Nome:");

        textName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelValue.setText("Valor:");

        textValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(labelValue)
                        .addComponent(labelName))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textName, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textValue, javax.swing.GroupLayout.Alignment.TRAILING,
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
                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelName))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelValue))
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
        if (offer == null) {
            try {
                addOffer();
            } catch (final NullObjectException ex) {
                Show.warningMessage("Todos os campos devem ser preenchidos.");
            } catch (final DoubleValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getDoubleValueInvalid() + "\" não é um valor decimal válido.");
            } catch (final KeyUsedException ex) {
                Show.warningMessage("Outra oferta já faz uso do nome \"" + ex.getElement().toString() + "\".");
            }
        } else {
            try {
                editOffer();
            } catch (final NullObjectException ex) {
                Show.warningMessage("Todos os campos devem estar preenchidos.");
            } catch (final DoubleValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getDoubleValueInvalid() + "\" não é um valor decimal válido.");
            } catch (final ElementNotFoundException ex) {
                Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
            } catch (final KeyUsedException ex) {
                Show.warningMessage("Outra oferta já faz uso do nome \"" + ex.getElement().toString() + "\".");
            }
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelValue;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textValue;
    // End of variables declaration//GEN-END:variables
}