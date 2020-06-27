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
package exceptions;

/**
 * Classe responsável por comportar-se como uma exceção de valor decimal inválido.
 * @author Everton Bruno Silva dos Santos.
 */
public class DoubleValueInvalidException extends Exception {
    /**
     * Refere-se ao número de série do objeto.
     */
    private static final long serialVersionUID = 3923343624731910393L;
    /**
     * Refere-se ao valor decimal inválido.
     */
    private final String doubleValueInvalid;

    /**
     * Construtor responsável pelo instanciamento da exceção de valor decimal inválido.
     * @param doubleValueInvalid Refere-se ao valor decimal inválido.
     */
    public DoubleValueInvalidException(final String doubleValueInvalid) {
        this.doubleValueInvalid = doubleValueInvalid;
    }

    /**
     * Método responsável por retornar o valor decimal inválido.
     * @return Retorna valor decimal inválido.
     */
    public String getDoubleValueInvalid() {
        return doubleValueInvalid;
    }

}