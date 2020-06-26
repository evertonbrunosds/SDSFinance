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
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por comportar-se como uma data.
 * @author Everton Bruno Silva dos Santos.
 */
public class DateTest {
    private final String currentDate;
    
    public DateTest() {
        final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        currentDate = twoChar(day) + "/" + twoChar(month) + "/" + year;
    }
    
    private static String twoChar(final int number) {
        final String numberStr = Converter.toString(number);
        if(numberStr.length() < 2) {
            return "0" + numberStr;
        } else {
            return numberStr;
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Este teste verifica a efetividade de criação de uma data sem o uso de
     * parâmetros, nesse caso a estrutura deve conter em si a data atual .
     */
    @Test
    public void newDate() {
        final Date date = new Date();
        assertEquals("a data não é a atual", currentDate, date.toString());
    }

    /**
     * Este teste verifica a efetividade de criação de uma data com o uso do
     * parâmetro de string, nesse caso a estrutura deve conter em si a data
     * designada.
     */
    @Test
    public void newDateString() {
        try {
            final Date date = new Date("5/2/2020");
            assertEquals("data incorreta", "05/02/2020", date.toString());
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

    /**
     * Este teste verifica o desparo de exceção de string nula no caso de criar uma
     * data fazendo o uso de uma string nula como parâmetro.
     */
    @Test
    public void newDateNullString() {
        try {
            final Date date = new Date(null);
            fail("não houve desvio de fluxo ao criar uma data com string nula");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

    /**
     * Este teste verifica o desparo de exceção de data inválida no caso de criar
     * uma data fazendo o uso de uma data inexistente como parâmetro de string.
     */
    @Test
    public void newDateInvalidString() {
        try {
            final Date date = new Date("31/02/2020"); // >>FEVEREIRO NÃO TEM 31 DIAS<<//
            fail("não houve desvio de fluxo ao criar uma data com string inválida");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        } catch (final DateInvalidException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a efetividade de criação de uma data com o uso de
     * parâmetros de valor inteiro, nesse caso a estrutura deve conter em si a data
     * designada.
     */
    @Test
    public void newDateInteger() {
        try {
            final Date date = new Date(5, 2, 2020);
            assertEquals("data incorreta", "05/02/2020", date.toString());
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

    /**
     * Este teste verifica a efetividade de criação de uma data com o uso de
     * parâmetros de valor inteiro negativo, nesse caso a estrutura deve conter em
     * si a data designada.
     */
    @Test
    public void newDateNegativeInteger() {
        try {
            final Date date = new Date(-5, -2, -2020);
            assertEquals("data incorreta", "05/02/2020", date.toString());
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

    /**
     * Este teste verifica a efetividade de criação de uma data com o uso de
     * parâmetros de string contendo valores inteiros negativos, nesse caso a
     * estrutura deve conter em si a data designada.
     */
    @Test
    public void newDateNegativeString() {
        try {
            final Date date = new Date("-5/-2/-2020");
            assertEquals("data incorreta", "05/02/2020", date.toString());
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção string nula inesperada");
        }
    }

    /**
     * Este teste verifica o desparo de exceção de data inválida no caso de criar
     * uma data fazendo o uso de uma data inexistente como parâmetro de valor
     * inteiro.
     */
    @Test
    public void newDateInvalidInteger() {
        try {
            final Date date = new Date(31, 2, 2020);
            fail("não houve desvio de fluxo ao criar uma data com valor inteiro inválido");
        } catch (final DateInvalidException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica o desparo de exceção de data inválida, nesse caso ao
     * alterar os valores de dia, mês e ano para valores inválidos deve ocorrer
     * exceção de data inválida.
     */
    @Test
    public void setDayMonthYearMonoInvalidInteger() {
        try {
            final Date date = new Date(5, 2, 2020);
            assertEquals("data incorreta", "05/02/2020", date.toString());
            date.setDate(45, 20, 1997);
            fail("não houve desvio de fluxo ao alterar uma data com valores inteiros inválidos");
        } catch (final DateInvalidException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a efetividade de alteração da data com o uso de
     * parâmetros de string, nesse caso a estrutura deve conter em sí a nova data
     * informada.
     */
    @Test
    public void setDayMonthYearMonoString() {
        try {
            final Date date = new Date(5, 2, 2020);
            assertEquals("data incorreta", "05/02/2020", date.toString());
            date.setDate("1/1/1997");
            assertEquals("alteração do ano incorreta", "01/01/1997", date.toString());
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        }
    }

    /**
     * Este teste verifica a efetividade de alteração da data com o uso de
     * parâmetros de string contendo valores inteiros negativos, nesse caso a
     * estrutura deve conter em sí a nova data informada.
     */
    @Test
    public void setDayMonthYearMonoStringNegative() {
        try {
            final Date date = new Date(5, 2, 2020);
            assertEquals("data incorreta", "05/02/2020", date.toString());
            date.setDate("-1/-1/-1997");
            assertEquals("alteração do ano incorreta", "01/01/1997", date.toString());
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        }
    }

    /**
     * Este teste verifica o desparo de exceção no caso de alterar uma data por meio
     * de uma string nula.
     */
    @Test
    public void setDayMonthYearMonoNullString() {
        try {
            final Date date = new Date(5, 2, 2020);
            assertEquals("data incorreta", "05/02/2020", date.toString());
            date.setDate(null);
            fail("não houve desvio de fluxo ao alterar uma data com string nula");
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a efetividade do retorno da data por meio dos seus gets,
     * nesse caso espera-se verificar cada um deles sequencialmente.
     */
    @Test
    public void getDayMonthYear() {
        try {
            final Date date = new Date(5, 2, 2020);
            assertEquals("retorno do dia incorreto", 5, date.getDay());
            assertEquals("retorno do mês incorreto", 2, date.getMonth());
            assertEquals("retorno do ano incorreto", 2020, date.getYear());
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

    
}