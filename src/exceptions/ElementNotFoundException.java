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
package exceptions;

/**
 * Classe responsável por comportar-se como exceção de elemento não encontrado.
 * @author Everton Bruno Silva dos Santos.
 */
public class ElementNotFoundException extends Exception {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -6509042509524524415L;

    /**
     * Construtor responsável pelo instanciamento da exceção de elemento não encontrado.
     */
    public ElementNotFoundException() {
    }

}
