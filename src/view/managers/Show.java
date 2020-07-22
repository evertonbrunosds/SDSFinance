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

import javax.swing.JOptionPane;

/**
 * Classe responsável por exibir telas de diálogo.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Show {

    /**
     * Método responsável por exibir mensagens de erro.
     * @param msg Refere-se ao conteúdo da mensagem.
     */
    public static void errorMessage(final String msg) {
        JOptionPane.showMessageDialog(null, msg, "Mensagem de Falha", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método responsável por exibir mensagens de aviso.
     * @param msg Refere-se ao conteúdo da mensagem.
     */
    public static void warningMessage(final String msg) {
        JOptionPane.showMessageDialog(null, msg, "Mensagem de Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Método responsável por exibir mensagens de questionamento.
     * @param msg Refere-se ao conteúdo da mensagem.
     * @param optionLeft Refere-se a opção da esquerda.
     * @param optionRight Refere-se a opção da direita.
     * @return Retorna escolha booleana do usuário.
     */
    public static boolean questionMessage(final String msg, final String optionLeft, final String optionRight) {
        final String[] options = {optionLeft, optionRight};
        return 0 != JOptionPane.showOptionDialog(null, msg, "Mensagem de Confirmação", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

}