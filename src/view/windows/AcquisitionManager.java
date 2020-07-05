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
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }
    
    /**
     * Método responsável por adicionar uma aquisição.
     * @throws NullObjectException Exceção lançada no caso de haver uma string nula.
     * @throws DoubleValueInvalidException Exceção lançada no caso de um valor decimal ser inválido.
     * @throws DateInvalidException Exceção lançada no caso de uma data inválida.
     * @throws IntegerValueInvalidException Exceção lançada no caso de um valor inteiro ser inválido.
     * @throws ElementNotFoundException Exceção lançada no caso do fornecedor não ter sido encontrado.
     */
    private void addAcquisition() throws NullObjectException, DoubleValueInvalidException, DateInvalidException, IntegerValueInvalidException, ElementNotFoundException {
        IOfferEditable tmpOffer = (IOfferEditable) offer.duplicate();
        tmpOffer.setValue(Converter.toDouble(textValue.getText()));
        Date date = Factory.date(textDate.getText());
        Controller.getInstance().getAcquisitionCollection().insert(Factory.acquisition(provider, tmpOffer, textAmount.getText(), date));
        if(!textValue.getText().equals(Double.toString(offer.getValue()))) {
            if(offer instanceof IExpense) {
                provider.getExpenseCollection().setValue(offer.getKey(), Converter.toDouble(textValue.getText()));
            } else {
                provider.getIncomeCollection().setValue(offer.getKey(), Converter.toDouble(textValue.getText()));
            }
        }
        ViewControl.saveRecord();
        MainForm.updateWindow();
        dispose();
    }
    
    /**
     * Método responsável por exibir a janela de adição de aquisições.
     * @param provider Refere-se ao fornecedor.
     * @param offer Refere-se a oferta adquirida.
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
        instance.setTitle(acquisition.toString());
        instance.setVisible(true);
    }
    
    /**
     * Construtor responsável pelo instanciamento da janela de adição ou edição de aquisições.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private AcquisitionManager(java.awt.Frame parent, boolean modal) {
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

        btnConfirm.setText("Adiquirir");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValue)
                    .addComponent(labelAmount)
                    .addComponent(labelDate))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelValue)
                    .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAmount))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDate))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnConfirm))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if(acquisition == null) {
            try {
                addAcquisition();
            } catch (NullObjectException ex) {
                Show.warningMessage("Todos os campos devem estar preenchidos.");
            } catch (DoubleValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getDoubleValueInvalid() + "\" não é um valor decimal válido.");
            } catch (DateInvalidException ex) {
                Show.questionMessage("\"" + ex.getInvalidDate() + "\" não é uma data válida de aquisição.");
            } catch (IntegerValueInvalidException ex) {
                Show.warningMessage("\"" + ex.getIntegerValueInvalid() + "\" não é um valor inteiro válido.");
            } catch (ElementNotFoundException ex) {
                Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
            }
        } else {
            //CHAMADA DO MÉTODO DE EDIÇÃO
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(AcquisitionManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            AcquisitionManager dialog = new AcquisitionManager(new javax.swing.JFrame(), true);
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
