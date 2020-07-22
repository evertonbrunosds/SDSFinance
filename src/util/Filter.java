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
package util;

import exceptions.DateInvalidException;
import exceptions.DoubleValueInvalidException;
import exceptions.IntegerValueInvalidException;
import exceptions.NullObjectException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        if (object == null) {
            throw new NullObjectException();
        } else if (object.toString().equals("")) {
            throw new NullObjectException();
        }
    }

    /**
     * Método responsável por realizar a filtragem de valores decimais válidos.
     * @param value Refere-se ao valor possívelmente válido.
     * @throws NullObjectException         Exceção lançada em caso de string nula.
     * @throws DoubleValueInvalidException Exceção lançada em caso de valor decimal inválido.
     */
    public static void invalidValueDouble(final String value)
            throws NullObjectException, DoubleValueInvalidException {
        nullObject(value);
        try {
            Double.parseDouble(value.replace(',', '.'));
        } catch (final NumberFormatException ex) {
            throw new DoubleValueInvalidException(value);
        }
    }

    /**
     * Método responsável por realizar a filtragem de valores inteiros válidos.
     * @param value Refere-se ao valor possívelmente válido.
     * @throws NullObjectException          Exceção lançada em caso de string nula.
     * @throws IntegerValueInvalidException Exceção lançada em caso de valor inteiro inválido.
     */
    public static void invalidValueInteger(final String value)
            throws NullObjectException, IntegerValueInvalidException {
        nullObject(value);
        try {
            Integer.parseInt(value);
        } catch (final NumberFormatException ex) {
            throw new IntegerValueInvalidException(value);
        }
    }

    /**
     * Método responsável por realizar a filtragem de datas válidas.
     * @param date Refere-se a data possívelmente válida.
     * @throws NullObjectException  Exceção lançada em caso de data nula.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    public static void invalidDate(final String date) throws NullObjectException, DateInvalidException {
        nullObject(date);
        verifyDate(date);
    }

    /**
     * Método responsável por realizar a filtragem de datas válidas.
     * @param day   Refere-se ao dia.
     * @param month Refere-se ao mês.
     * @param year  Refere-se ao ano.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    public static void invalidDate(final int day, final int month, final int year) throws DateInvalidException {
        verifyDate(Converter.toPositive(day) + "/" + Converter.toPositive(month) + "/" + Converter.toPositive(year));
    }

    /**
     * Método responsável por verificar a validade de uma data.
     * @param date Refere-se a data.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    private static void verifyDate(String date) throws DateInvalidException {
        date = date.replace('-', '0');
        if(date.split("/")[2].length() != 4) {
            throw new DateInvalidException(date);
        }
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (final ParseException ex) {
            throw new DateInvalidException(date);
        }
    }

}