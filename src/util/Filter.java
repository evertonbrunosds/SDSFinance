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

import exceptions.DoubleValueInvalidException;
import exceptions.FileStreamInvalidException;
import exceptions.IntegerValueInvalidException;
import exceptions.NullObjectException;
import model.bytes.IFileStream;

/**
 * Classe responsável por comportar-se como um filtro.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Filter {
    
    /**
     * Método responsável por realizar a filtragem de objetos nulos.
     * @param object Refere-se a string possívelmente nula.
     * @throws NullObjectException Exceção lançada em caso de objeto nulo.
     */
    public static void nullObject(final Object object) throws NullObjectException {
        if(object == null) {
            throw new NullObjectException();
        } else if(object.toString().equals("")) {
            throw new NullObjectException();
        }
    }
    
    /**
     * Método responsável por realizar a flitragem de valores decimais válidos.
     * @param value Refere-se ao valor possívelmente válido.
     * @throws NullObjectException Exceção lançada em caso de string nula.
     * @throws DoubleValueInvalidException Exceção lançada em caso de valor decimal inválido.
     */
    public static void invalidValueDouble(final String value) throws NullObjectException, DoubleValueInvalidException {
        nullObject(value);
        try {
            Double.parseDouble(value.replace(',', '.'));
        } catch (final NumberFormatException ex) {
            throw new DoubleValueInvalidException(value);
        }
    }

    /**
     * Método responsável por realizar a flitragem de valores inteiros válidos.
     * @param value Refere-se ao valor possívelmente válido.
     * @throws NullObjectException Exceção lançada em caso de string nula.
     * @throws IntegerValueInvalidException Exceção lançada em caso de valor inteiro inválido.
     */
    public static void invalidValueInteger(final String value) throws NullObjectException, IntegerValueInvalidException {
        nullObject(value);
        try {
            Integer.parseInt(value);
        } catch (final NumberFormatException ex) {
            throw new IntegerValueInvalidException(value);
        }
    }
    
    /**
     * Método responsável por realizar a filtragem de arquivos em fluxo inválidos.
     * @param fileStream Refere-se ao arquivo em fluxo possívelmente nulo.
     * @throws FileStreamInvalidException Exceção lançada em caso de arquivo em fluxo inválido.
     */
    public static void InvalidFileStream(final IFileStream fileStream) throws FileStreamInvalidException {
        if(fileStream == null) {
            throw new FileStreamInvalidException();
        } else if (fileStream.getObject() == null) {
            throw new FileStreamInvalidException();
        }
    }
    
}
