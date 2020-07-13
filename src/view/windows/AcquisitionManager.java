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
import exceptions.DateInvalidException;
import exceptions.DoubleValueInvalidException;
import exceptions.ElementNotFoundException;
import exceptions.IntegerValueInvalidException;
import exceptions.NullObjectException;
import model.business.IAcquisition;
import model.offers.IExpense;
import model.offers.IOfferEditable;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.Converter;
import util.Date;
import util.Factory;
import view.managers.Show;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de adição ou edição de aquisições.
 * @author Everton Bruno Silva dos Santos.
 */
public class AcquisitionManager extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 6458904358912143126L;
    /**
     * Refere-se a instância da janela de aquisição.
     */
    private static AcquisitionManager instance;
    /**
     * Refere-se ao fornecedor da oferta.
     */
    private IProvider provider;
    /**
     * Refere-se a oferta;
     */
    private IOfferVisible offer;
    /**
     * Refere-se a aquisição.
     */
    private IAcquisition acquisition;

    /**
     * Método responsável por gerar instância da janela.
     */
    private static void createInstance() {
        instance = new AcquisitionManager(null, true) {
            private static final long serialVersionUID = 3313230953225368382L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }

    /**
     * Método responsável por adicionar uma aquisição.
     * @throws NullObjectException          Exceção lançada no caso de haver uma string nula.
     * @throws DoubleValueInvalidException  Exceção lançada no caso de um valor decimal ser inválido.
     * @throws DateInvalidException         Exceção lançada no caso de uma data inválida.
     * @throws IntegerValueInvalidException Exceção lançada no caso de um valor inteiro ser inválido.
     * @throws ElementNotFoundException     Exceção lançada no caso do fornecedor não ter sido encontrado.
     */
    private void addAcquisition() throws NullObjectException, DoubleValueInvalidException, DateInvalidException,
            IntegerValueInvalidException, ElementNotFoundException {
        final IOfferEditable tmpOffer = (IOfferEditable) offer.duplicate();
        tmpOffer.setValue(Converter.toDouble(textValue.getText()));
        final Date date = Factory.date(textDate.getText());
        Controller.getInstance().getAcquisitionCollection()
                .insert(Factory.acquisition(provider, tmpOffer, textAmount.getText(), date));
        if (!textValue.getText().equals(Double.toString(offer.getValue()))) {
            if (offer instanceof IExpense) {
                provider.getExpenseCollection().setValue(offer.getKey(), Converter.toDouble(textValue.getText()));
            } else {
                provider.getIncomeCollection().setValue(offer.getKey(), Converter.toDouble(textValue.getText()));
            }
            OfferWindow.updateWindow();
        }
        ViewControl.saveRecord();
        MainForm.updateWindow();
        dispose();
    }

    /**
     * Método responsável por editar uma aquisição.
     * @throws NullObjectException          Exceção lançada no caso de haver uma string nula.
     * @throws DoubleValueInvalidException  Exceção lançada no caso de um valor decimal ser inválido.
     * @throws ElementNotFoundException     Exceção lançada no caso do fornecedor não ter sido encontrado.
     * @throws IntegerValueInvalidException Exceção lançada no caso de um valor inteiro ser inválido.
     * @throws DateInvalidException         Exceção lançada no caso de uma data inválida.
     */
    private void editAcquisition() throws NullObjectException, DoubleValueInvalidException, ElementNotFoundException, IntegerValueInvalidException, DateInvalidException {
        boolean wasChanged = false;
        if (!textValue.getText().equals(Double.toString(acquisition.getOffer().getValue()))) {
            final double value = Converter.toDouble(textValue.getText());
            Controller.getInstance().getAcquisitionCollection().setUnitaryValue(acquisition.getKey(), value);
            wasChanged = true;
        }
        if (!textAmount.getText().equals(Integer.toString(acquisition.getAmount()))) {
            final int amount = Converter.toInteger(textAmount.getText());
            Controller.getInstance().getAcquisitionCollection().setAmount(acquisition.getKey(), amount);
            wasChanged = true;
        }
        if (!textDate.getText().equals(acquisition.getDate().toString())) {
            Controller.getInstance().getAcquisitionCollection().setDate(acquisition.getKey(), Factory.date(textDate.getText()));
            wasChanged = true;
        }
        if (wasChanged) {
            ViewControl.saveRecord();
            MainForm.updateWindow();
        }
        dispose();
    }

    /**
     * Método responsável por exibir a janela de adição de aquisições.
     * @param provider Refere-se ao fornecedor.
     * @param offer    Refere-se a oferta adquirida.
     */
    public static void showModal(final IProvider provider, final IOfferVisible offer) {
        createInstance();
        instance.provider = provider;
        instance.offer = offer;
        instance.textDate.setText(Factory.date().toString());
        instance.textValue.setText(Double.toString(offer.getValue()));
        instance.setTitle(offer.toString());
        instance.setVisible(true);
    }

    /**
     * Método responsável por exibir a janela de edição de aquisições.
     * @param acquisition Refere-se a aquisição.
     */
    public static void showModal(final IAcquisition acquisition) {
        createInstance();
        instance.acquisition = acquisition;
        instance.textDate.setText(acquisition.getDate().toString());
        instance.textValue.setText(Double.toString(acquisition.getOffer().getValue()));
        instance.textAmount.setText(Integer.toString(acquisition.getAmount()));
        instance.btnConfirm.setText("Aplicar");
        instance.setTitle("Editar Aquisição");
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de adição ou edição de aquisições.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private AcquisitionManager(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelValue = new javax.swing.JLabel();
        textValue = new javax.swing.JTextField();
        labelAmount = new javax.swing.JLabel();
        textAmount = new javax.swing.JTextField();
        labelDate = new javax.swing.JLabel();
        textDate = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        labelValue.setText("Valor:");

        textValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelAmount.setText("Quantidade:");

        textAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelDate.setText("Data:");

        textDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnConfirm.setText("Adquirir");
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
                        .addComponent(labelAmount).addComponent(labelDate))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textValue, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textAmount, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textDate, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18).addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER).addComponent(labelValue)
                        .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(textAmount, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelAmount))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDate))
                .addGap(20, 20, 20).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancel).addComponent(btnConfirm))
                .addGap(20, 20, 20)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if (acquisition == null) {
            try {
                addAcquisition();
            } catch (final NullObjectException ex) {
                Show.warningMessage("Todos os campos devem estar preenchidos.");
            } catch (final DoubleValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getDoubleValueInvalid() + "\" não é um valor decimal válido.");
            } catch (final DateInvalidException ex) {
                Show.warningMessage("\"" + ex.getInvalidDate() + "\" não é uma data válida de aquisição.");
            } catch (final IntegerValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getIntegerValueInvalid() + "\" não é um valor inteiro válido.");
            } catch (final ElementNotFoundException ex) {
                Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
            }
        } else {
            try {
                editAcquisition();
            } catch (final NullObjectException ex) {
                Show.warningMessage("Todos os campos devem estar preenchidos.");
            } catch (final DoubleValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getDoubleValueInvalid() + "\" não é um valor decimal válido.");
            } catch (final ElementNotFoundException ex) {
                Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
            } catch (final IntegerValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getIntegerValueInvalid() + "\" não é um valor inteiro válido.");
            } catch (final DateInvalidException ex) {
                Show.warningMessage("\"" + ex.getInvalidDate() + "\" não é uma data válida de aquisição.");
            }
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel labelAmount;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelValue;
    private javax.swing.JTextField textAmount;
    private javax.swing.JTextField textDate;
    private javax.swing.JTextField textValue;
    // End of variables declaration//GEN-END:variables
}