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
package view.managers;

import control.Record;
import exceptions.IncompatibleTypeException;
import exceptions.NullObjectException;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.Converter;
import util.Factory;
import view.windows.MainForm;

/**
 * Classe responsável por comportar-se como centro de controle da interface.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class ViewControl {
    /**
     * Refere-se ao indicativo de que registro foi alterado.
     */
    private static boolean wasChanged = true;

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
     * Método responsável por alterar indicativos de que as alterações foram salvas.
     * @param wasSaved Refere-se a indicativo de que as alterações foram salvas.
     */
    public static void setWasChanged(final boolean wasSaved) {
        if (!wasSaved) {
            MainForm.setTitleWindow(Record.getInstance().getFileName() + " *");
        } else {
            MainForm.setTitleWindow(Record.getInstance().getFileName());
        }
        ViewControl.wasChanged = wasSaved;
    }

    /**
     * Método responsável por retornar indicativos de que as alterações foram salvas.
     * @return Retorna indicativo de que as alterações foram salvas.
     */
    public static boolean getWasChanged() {
        return wasChanged;
    }

    /**
     * Método responsável por abrir registros num dado arquivo.
     * @param parent Refere-se a janela ivocadora do método.
     */
    public static void loadRecordFromFile(final java.awt.Frame parent) {
        final FileDialog fileDialog = Factory.openFileDialog();
        if (fileDialog.execute(parent)) {
            ViewControl.loadRecordFromFile(fileDialog.getFileName());
        }
    }

    /**
     * Método responsável por abrir registros num dado arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     */
    public static void loadRecordFromFile(final String fileName) {
        try {
            Record.getInstance().loadFromFile(fileName);
            setWasChanged(true);
            MainForm.updateWindow();
        } catch (final NullObjectException ex) {
            Show.warningMessage("Você deve especificar um nome de arquivo.");
        } catch (final IOException ex) {
            Show.errorMessage("Não foi possível abrir o arquivo do local especificado.");
        } catch (final ClassNotFoundException ex) {
            Show.errorMessage("O arquivo do local especificado está corrompido.");
        } catch (final IncompatibleTypeException ex) {
            Show.errorMessage("O SDS Finance não é compatível com o arquivo do local especificado.");
        }
    }

    /**
     * Método responsável por salvar registros num dado arquivo.
     * @param parent Refere-se a janela ivocadora do método.
     */
    public static void saveAsRecordToFile(final java.awt.Frame parent) {
        final FileDialog fileDialog = Factory.saveFileDialog();
        if (fileDialog.execute(parent)) {
            try {
                Record.getInstance().saveToFile(Converter.toExtensionName(fileDialog.getFileName(), ".sdsf"));
                setWasChanged(true);
            } catch (final NullObjectException ex) {
                Show.warningMessage("Você deve especificar um nome de arquivo.");
            } catch (final IOException ex) {
                Show.errorMessage("Não foi possível salvar o arquivo no local especificado.");
            }
        }
    }

    /**
     * Método responsável por salvar registros num dado arquivo anteriormente aberto ou salvo.
     * @param parent Refere-se a janela ivocadora do método.
     */
    public static void saveRecordToFile(final java.awt.Frame parent) {
        if (Record.getInstance().neverBeenSavedInFile()) {
            saveAsRecordToFile(parent);
        } else {
            try {
                Record.getInstance().saveToFile();
                setWasChanged(true);
            } catch (final IOException ex) {
                Show.errorMessage("Não foi possível salvar o arquivo no local especificado.");
            }
        }
    }

    /**
     * Método responsável por criar um novo registro.
     * @param parent Refere-se a janela ivocadora do método.
     */
    public static void newRecord(final java.awt.Frame parent) {
        if (getWasChanged()) {
            createNewRecord();
        } else if (!Show.questionMessage("Se você não salvar o registro, todas as alterações serão\n"
                + "perdidas. Deseja salvar antes de criar um novo arquivo?", "Sim", "Não")) {
            ViewControl.saveRecordToFile(parent);
            if (ViewControl.getWasChanged()) {
                createNewRecord();
            }
        } else {
            createNewRecord();
        }
    }

    /**
     * Método responsável por criar um novo registro.
     */
    private static void createNewRecord() {
        Record.getInstance().clear();
        setWasChanged(true);
        MainForm.updateWindow();
    }
    
}