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
 * Classe responsável por comportar-se como uma exceção de data inválida.
 * @author Everton Bruno Silva dos Santos.
 */
public class DateInvalidException extends Exception {
    /**
     * Refere-se ao número de série do objeto.
     */
    private static final long serialVersionUID = -2378639608134988624L;
    /**
     * Refere-se a data inválida.
     */
    private final String invalidDate;

    /**
     * Construtor responsável pelo instanciamento da exceção de data inválida.
     * @param invalidDate Refere-se a data inválida.
     */
    public DateInvalidException(final String invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * Método responsável por retornar a data inválida.
     * @return Retorna data inválida.
     */
    public String getInvalidDate() {
        return invalidDate;
    }

}