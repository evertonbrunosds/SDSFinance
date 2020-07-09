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

import control.Controller;
import exceptions.IncompatibleTypeException;
import exceptions.NullObjectException;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.Converter;
import view.windows.MainForm;

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
        for (int i = 0; i < table.getColumnCount(); i++) {
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
        align.setBackground(Color.decode("#d6d9df"));
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

    /**
     * Método responsável por exportar registros.
     */
    public static void exportRecord() {
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Registro do SDS Finance", "sdsf");
        final String textAproveButton = "Exportar";
        final String title = "Exportar Registro";
        final FileDialog fileDialog = new FileDialog(title, textAproveButton, filter);
        if (fileDialog.exceute()) {
            try {
                Controller.getInstance().saveFromFile(Converter.toExtensionName(fileDialog.getFileName(), ".sdsf"));
            } catch (final NullObjectException ex) {
                Show.warningMessage("Você deve especificar um nome de arquivo.");
            } catch (final IOException ex) {
                Show.errorMessage("Não foi possível exportar o arquivo para o local especificado.");
            }
        }
    }

    /**
     * Método responsável por importar registros.
     */
    public static void importRecord() {
        if (Show.questionMessage("Se bem sucedida esta ação irá sobrescrever seus registros atuais, deseja prosseguir?")) {
            final FileNameExtensionFilter filter = new FileNameExtensionFilter("Registro do SDS Finance", "sdsf");
            final String textAproveButton = "Importar";
            final String title = "Importar Registro";
            final FileDialog fileDialog = new FileDialog(title, textAproveButton, filter);
            if (fileDialog.exceute()) {
                try {
                    Controller.getInstance().loadFromFile(Converter.toExtensionName(fileDialog.getFileName(), ".sdsf"));
                    saveRecord();
                    MainForm.updateWindow();
                } catch (final NullObjectException ex) {
                    Show.warningMessage("Você deve especificar um nome de arquivo.");
                } catch (final IOException ex) {
                    Show.errorMessage("Não foi possível importar o arquivo para do local especificado.");
                } catch (final ClassNotFoundException ex) {
                    Show.errorMessage("Não foi possível importar, o arquivo está corrompido.");
                } catch (final IncompatibleTypeException ex) {
                    Show.warningMessage("Este registro pertence a uma versão do SDS Finance diferente da atual.");
                }
            }
        }
    }

    /**
     * Método responsável por gravar registros no dispositivo.
     */
    public static void saveRecord() {
        try {
            Controller.getInstance().saveFromFile();
        } catch (final IOException ex) {
            Show.errorMessage("Não foi possível gravar as alterações.");
        }
    }

    /**
     * Método resmponsável carregar registros do dispositivo.
     */
    public static void loadRecord() {
        try {
            Controller.getInstance().loadFromFile();
        } catch (final IOException ex) {
            Show.errorMessage("Não foi possível carregar o registro.");
        }
    }

}
