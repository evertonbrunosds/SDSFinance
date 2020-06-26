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

import exceptions.DateInvalidException;
import exceptions.NullObjectException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe responsável por comportar-se como um data.
 * @author Everton Bruno Silva dos Santos.
 */
public class Date implements IDateVisible, Serializable {
    /**
     * Refere-se ao número de série do objeto.
     */
    private static final long serialVersionUID = -803308152338599608L;
    /**
     * Refere-se ao dia da data.
     */
    private int day;
    /**
     * Refere-se ao mês da data.
     */
    private int month;
    /**
     * Refere-se ao ano da data.
     */
    private int year;
    
    /**
     * Construtor responsável pelo instanciamento da data atual.
     */
    public Date() {
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }
    
    /**
     * Construtor responsável pelo instanciamento da data.
     * @param date Refere-se ao dia, mês e ano.
     * @throws NullObjectException Exceção lançada em caso de data nula.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    public Date(final String date) throws NullObjectException, DateInvalidException {
        Filter.invalidDate(date);
        day = Converter.toPositive(Integer.parseInt(date.split("/")[0]));
        month = Converter.toPositive(Integer.parseInt(date.split("/")[1]));
        year = Converter.toPositive(Integer.parseInt(date.split("/")[2]));
    }

    /**
     * Construtor responsável pelo instanciamento da data.
     * @param day Refere-se ao dia da data.
     * @param month Refere-se ao mês da data.
     * @param year Refere-se ao ano da data.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    public Date(final int day, final int month, final int year) throws DateInvalidException {
        Filter.invalidDate(day, month, year);
        this.day = Converter.toPositive(day);
        this.month = Converter.toPositive(month);
        this.year = Converter.toPositive(year);
    }
    
    /**
     * Método responsável por alterar a data por inteiro.
     * @param day Refere-se ao dia da data.
     * @param month Refere-se ao mês da data.
     * @param year Refere-se ao ano da data.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    public void setDate(final int day, final int month, final int year) throws DateInvalidException {
        Filter.invalidDate(day, month, year);
        this.day = Converter.toPositive(day);
        this.month = Converter.toPositive(month);
        this.year = Converter.toPositive(year);
    }
    
    /**
     * Método responsável por alterar a data por inteiro.
     * @param date Refere-se ao dia, mês e ano.
     * @throws NullObjectException Exceção lançada em caso de string nula.
     * @throws DateInvalidException Exceção lançada em caso de data inválida.
     */
    public void setDate(final String date) throws NullObjectException, DateInvalidException {
        Filter.invalidDate(date);
        day = Converter.toPositive(Integer.parseInt(date.split("/")[0]));
        month = Converter.toPositive(Integer.parseInt(date.split("/")[1]));
        year = Converter.toPositive(Integer.parseInt(date.split("/")[2]));
    }

    /**
     * Método responsável por retornar o dia da data.
     * @return Retorna dia da data.
     */
    @Override
    public int getDay() {
        return day;
    }

    /**
     * Método responsável por retornar o mês da data.
     * @return Retorna mês da data.
     */
    @Override
    public int getMonth() {
        return month;
    }

    /**
     * Método responsável por retornar o ano da data.
     * @return Retorna ano da data.
     */
    @Override
    public int getYear() {
        return year;
    }
    
    /**
     * Método responsável por retornar data.
     * @return Retorna data.
     */
    @Override
    public String toString() {
        return twoChar(day)+"/"+twoChar(month)+"/"+Converter.toString(year);
    }
    
    /**
     * Método responsável por retornar garantir que um dia ou mês tenha dois caracteres.
     * @param number Refere-se ao número que corresponde ao dia ou mês.
     * @return Retorna dia ou mês com dois caracteres.
     */
    private String twoChar(final int number) {
        final String numberStr = Converter.toString(number);
        if(numberStr.length() < 2) {
            return "0" + numberStr;
        } else {
            return numberStr;
        }
    }
    
}
