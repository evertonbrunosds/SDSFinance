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
package view.managers;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsável por comportar-se como centro de controle da interface.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class ViewControl {
    
    /**
     * Método responsável por alinhar as linhas e colunas de uma tabela.
     * @param table Refere-se a tabela.
     * @param align Refere-se ao alinhamento.
     */
    public static void alignTo(final JTable table, final int align) {
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(align);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer(align));
        }
    }
    
    /**
     * Método responsável por criar e retornar um modelo de coluna alinhada.
     * @param alignment Refere-se ao alinhamento.
     * @return Retorna modelo de coluna alinhada.
     */
    private static DefaultTableCellRenderer cellRenderer(final int alignment) {
        final DefaultTableCellRenderer align = new DefaultTableCellRenderer();
        align.setHorizontalAlignment(alignment);
        return align;
    }
    
    /**
     * Método responsável por esvaziar uma tabela.
     * @param table Refere-se a tabela.
     */
    public static void clear(final JTable table) {
        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        final int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }        
    }
    
}
