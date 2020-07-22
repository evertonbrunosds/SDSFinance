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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe responsável por comportar-se como um diálogo de arquivo.
 * @author Everton Bruno Silva dos Santos.
 */
public class FileDialog {
    /**
     * Refere-se ao componente de seleção de arquivos.
     */
    private final JFileChooser fileChooser;

    /**
     * Construtor responsável pelo instanciamento do diálogo.
     * @param title                   Refere-se ao título do diálogo.
     * @param textAproveButton        Refere-se ao texto do botão de aprovação.
     * @param fileNameExtensionFilter Refere-se ao filtro de extensões.
     */
    public FileDialog(final String title, final String textAproveButton,
            final FileNameExtensionFilter fileNameExtensionFilter) {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setApproveButtonText(textAproveButton);
        fileChooser.setFileFilter(fileNameExtensionFilter);
    }

    /**
     * Método responsável por executar a janela de dialogo.
     * @param parent Refere-se a janela ivocadora do método.
     * @return Retorna resultado da execução.
     */
    public boolean execute(final java.awt.Frame parent) {
        return fileChooser.showDialog(parent, fileChooser.getApproveButtonText()) == JFileChooser.APPROVE_OPTION;
    }

    /**
     * Método responsável por retornar nome do arquivo selecionado.
     * @return Retorna nome do arquivo selecionado.
     */
    public String getFileName() {
        return fileChooser.getSelectedFile().toString();
    }

    /**
     * Método responsável por alterar o título do diálogo.
     * @param title Refere-se ao título do diálogo.
     */
    public void setTitle(final String title) {
        fileChooser.setDialogTitle(title);
    }

    /**
     * Método responsável por alterar o texto do botão de aprovação.
     * @param textAproveButton Refere-se ao texto do botão de aprovação.
     */
    public void setTextAproveButton(final String textAproveButton) {
        fileChooser.setApproveButtonText(textAproveButton);
    }

}