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
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.business.IAcquisition;
import model.sets.ITree;
import model.sets.Tree;
import util.Converter;
import util.IElement;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela de extratos.
 * @author Everton Bruno Silva dos Santos.
 */
public class ExtractsWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -7291401217690085300L;
    /**
     * Refere-se a instância da janela de extratos.
     */
    private static ExtractsWindow instance;

    /**
     * Método responsável por criar instância da janela.
     */
    private static void createInstance() {
        instance = new ExtractsWindow(null, true) {
            private static final long serialVersionUID = -4073298386776028161L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
        ViewControl.alignTo(instance.table, SwingConstants.CENTER);
    }

    /**
     * Método responsável por atualizar a janela de extratos com árvore de extratos.
     * @param tree Refere-se a árvore de extratos.
     */
    private void updateWindow(final ITree<String, Extract> tree) {
        ViewControl.clear(instance.table);
        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        tree.forEach(true, extract -> {
            model.addRow(new Object[] { extract.period, extract.getPositiveValue(), extract.getNegativeValue(),
                    extract.getTotalValue() });
        });
    }

    /**
     * Método responsável por preencher árvore de extratos por dia.
     * @param tree Refere-se a árvore.
     * @return Retorna árvore preenchida.
     */
    private ITree<String, Extract> forDay(final ITree<String, Extract> tree) {
        Controller.getInstance().getAcquisitionCollection().forEach(true, acquisition -> {
            final Extract extract = new DailyExtract(acquisition);
            try {
                tree.insert(extract.key, extract);
            } catch (final KeyUsedException ex) {
                ((Extract) ex.getElement()).add(acquisition);
            }
        });
        return tree;
    }

    /**
     * Método responsável por preencher árvore de extratos por mês.
     * @param tree Refere-se a árvore.
     * @return Retorna árvore preenchida.
     */
    private ITree<String, Extract> forMonth(final ITree<String, Extract> tree) {
        Controller.getInstance().getAcquisitionCollection().forEach(true, acquisition -> {
            final Extract extract = new MonthlyExtract(acquisition);
            try {
                tree.insert(extract.key, extract);
            } catch (final KeyUsedException ex) {
                ((Extract) ex.getElement()).add(acquisition);
            }
        });
        return tree;
    }

    /**
     * Método responsável por preencher árvore de extratos por ano.
     * @param tree Refere-se a árvore.
     * @return Retorna árvore preenchida.
     */
    private ITree<String, Extract> forYear(final ITree<String, Extract> tree) {
        Controller.getInstance().getAcquisitionCollection().forEach(true, acquisition -> {
            final Extract extract = new AnnualExtract(acquisition);
            try {
                tree.insert(extract.key, extract);
            } catch (final KeyUsedException ex) {
                ((Extract) ex.getElement()).add(acquisition);
            }
        });
        return tree;
    }

    /**
     * Método responsável por exibir a janela de extratos.
     */
    public static void showModal() {
        createInstance();
        instance.updateWindow(instance.forMonth(new Tree<>()));
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de extratos.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private ExtractsWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        rBtnOptForDay = new javax.swing.JRadioButtonMenuItem();
        rBtnOptForMonth = new javax.swing.JRadioButtonMenuItem();
        rBtnOptForYear = new javax.swing.JRadioButtonMenuItem();
        buttonGroup = new javax.swing.ButtonGroup();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        buttonGroup.add(rBtnOptForDay);
        rBtnOptForDay.setText("Exibir por dia");
        rBtnOptForDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                rBtnOptForDayActionPerformed(evt);
            }
        });
        popupMenu.add(rBtnOptForDay);

        buttonGroup.add(rBtnOptForMonth);
        rBtnOptForMonth.setSelected(true);
        rBtnOptForMonth.setText("Exibir por mês");
        rBtnOptForMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                rBtnOptForMonthActionPerformed(evt);
            }
        });
        popupMenu.add(rBtnOptForMonth);

        buttonGroup.add(rBtnOptForYear);
        rBtnOptForYear.setText("Exibir por ano");
        rBtnOptForYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                rBtnOptForYearActionPerformed(evt);
            }
        });
        popupMenu.add(rBtnOptForYear);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extratos");
        setResizable(false);

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                scrollPaneMouseReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "Período", "Renda", "Despesa", "Saldo" }) {
            /**
             * Refere-se ap número de série da classe.
             */
            private static final long serialVersionUID = -9172262059319079266L;
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
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void scrollPaneMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseReleased
        if (evt.isMetaDown()) {
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneMouseReleased

    private void tableMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        if (evt.isMetaDown()) {
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void rBtnOptForDayActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnOptForDayActionPerformed
        updateWindow(forDay(new Tree<>()));
    }//GEN-LAST:event_rBtnOptForDayActionPerformed

    private void rBtnOptForMonthActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnOptForMonthActionPerformed
        updateWindow(forMonth(new Tree<>()));
    }//GEN-LAST:event_rBtnOptForMonthActionPerformed

    private void rBtnOptForYearActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnOptForYearActionPerformed
        updateWindow(forYear(new Tree<>()));
    }//GEN-LAST:event_rBtnOptForYearActionPerformed

    /**
     * Classe responsável por comportar-se como extrato.
     * @author Everton Bruno Silva dos Santos.
     */
    private abstract class Extract implements IElement<String> {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -5641931943828987451L;
        /**
         * Refere-se ao valor positivo contido no extrato.
         */
        private double positiveValue;
        /**
         * Refere-se ao valor negativo contido no extrato.
         */
        private double positiveNegative;
        /**
         * Refere-se a chave do extrato.
         */
        private final String key;
        /**
         * Refere-se ao período do extrato.
         */
        private final String period;

        /**
         * Construtor responsável pelo instnaciamento do extrato num contexto diário.
         * @param value Refere-se ao valor do extrato.
         * @param day   Refere-se ao dia do extrato.
         * @param month Refere-se ao mês do extrato.
         * @param year  Refere-se ao ano do extrato.
         */
        private Extract(final double value, final int day, final int month, final int year) {
            positiveValue = 0;
            positiveNegative = 0;
            key = toString(year) + toString(month) + toString(day);
            period = toString(day) + "/" + toString(month) + "/" + toString(year);
            add(value);
        }

        /**
         * Construtor responsável pelo instnaciamento do extrato num contexto mensal.
         * @param value Refere-se ao valor do extrato.
         * @param month Refere-se ao mês do extrato.
         * @param year  Refere-se ao ano do extrato.
         */
        private Extract(final double value, final int month, final int year) {
            positiveValue = 0;
            positiveNegative = 0;
            key = toString(year) + toString(month);
            period = toString(month) + "/" + toString(year);
            add(value);
        }

        /**
         * Construtor responsável pelo instnaciamento do extrato num contexto anual.
         * @param value Refere-se ao valor do extrato.
         * @param year  Refere-se ao ano do extrato.
         */
        private Extract(final double value, final int year) {
            positiveValue = 0;
            positiveNegative = 0;
            key = toString(year);
            period = toString(year);
            add(value);
        }

        /**
         * Método responsável por adicionar novos valores ao extrato.
         * @param value Refere-se ao novo valor.
         */
        private void add(final double value) {
            if (value > 0) {
                positiveValue += value;
            } else {
                positiveNegative += value;
            }
        }

        /**
         * Método responsável por adicionar novos valores ao extrato.
         * @param acquisition Refere-se a aquisição que contém em sí o novo valor.
         */
        private void add(final IAcquisition acquisition) {
            add(acquisition.getTotalValue());
        }

        /**
         * Método responsável por retornar o valor positivo contido no extrato.
         * @return Retorna valor positivo contido no extrato.
         */
        private String getPositiveValue() {
            return Converter.toString(positiveValue);
        }

        /**
         * Método responsável por retornar o valor negativo contido no extrato.
         * @return Retorna valor negativo contido no extrato.
         */
        private String getNegativeValue() {
            return Converter.toString(positiveNegative);
        }

        /**
         * Método responsável por retornar o valor total contido no extrato.
         * @return Retorna valor total contido no extrato.
         */
        private String getTotalValue() {
            return Converter.toString(positiveNegative + positiveValue);
        }

        /**
         * Método responsável por retornar a chave do extrato.
         * @return Retorna chave do extrato.
         */
        @Override
        public Comparable<String> getKey() {
            return key;
        }

        /**
         * Método responsável por alterar a chave do extrato.
         * @param key Refere-se a nova chave.
         * @throws NullObjectException Exceção lançada no caso da chave ser nula.
         */
        @Override
        public void setKey(final String key) throws NullObjectException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Método responsável por pré-visualizar a chave do extrato.
         * @param key Refere-se a nova chave.
         * @return Retorna pré-visualização do extrato.
         * @throws NullObjectException Exceção lançada no caso da chave ser nula.
         */
        @Override
        public Comparable<String> previewKey(final String key) throws NullObjectException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Método responsável por converter em string números inteiros contidos em datas.
         * @param n Refere-se ao número inteiro.
         * @return Retorna número inteiro com dois ou mais caracteres em string.
         */
        private String toString(final int n) {
            final String numberStr = Converter.toString(n);
            if (numberStr.length() < 2) {
                return "0" + numberStr;
            } else {
                return numberStr;
            }
        }

    }

    /**
     * Classe responsável por comportar-se como um extrato diário.
     * @author Everton Bruno Silva dos Santos.
     */
    private class DailyExtract extends Extract {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = 3461139864061616895L;

        /**
         * Construtor responsável pelo instanciamento do extrato diário.
         * @param acquisition Refere-se a aquisição.
         */
        public DailyExtract(final IAcquisition acquisition) {
            super(acquisition.getTotalValue(), acquisition.getDate().getDay(), acquisition.getDate().getMonth(),
                    acquisition.getDate().getYear());
        }

    }

    /**
     * Classe responsável por comportar-se como um extrato mensal.
     * @author Everton Bruno Silva dos Santos.
     */
    private class MonthlyExtract extends Extract {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -26880991967078194L;

        /**
         * Construtor responsável pelo instanciamento do extrato mensal.
         * @param acquisition Refere-se a aquisição.
         */
        public MonthlyExtract(final IAcquisition acquisition) {
            super(acquisition.getTotalValue(), acquisition.getDate().getMonth(), acquisition.getDate().getYear());
        }

    }

    /**
     * Classe responsável por comportar-se como um extrato anual.
     * @author Everton Bruno Silva dos Santos.
     */
    private class AnnualExtract extends Extract {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -7838158343627928326L;

        /**
         * Construtor responsável pelo instanciamento do extrato anual.
         * @param acquisition Refere-se a aquisição.
         */
        public AnnualExtract(final IAcquisition acquisition) {
            super(acquisition.getTotalValue(), acquisition.getDate().getYear());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JRadioButtonMenuItem rBtnOptForDay;
    private javax.swing.JRadioButtonMenuItem rBtnOptForMonth;
    private javax.swing.JRadioButtonMenuItem rBtnOptForYear;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
