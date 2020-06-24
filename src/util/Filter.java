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
package util;

import exceptions.NullStringException;

/**
 * Classe responsável por comportar-se como um filtro.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Filter {
    
    /**
     * Método responsável por realizar a filtragem de strings nulas.
     * @param string Refere-se a string possívelmente nula.
     * @throws NullStringException Exceção lançada em caso de string nula.
     */
    public static void nullString(final String string) throws NullStringException {
        if(string == null) {
            throw new NullStringException();
        } else if(string.equals("")) {
            throw new NullStringException();
        }
    }
    
}
