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
import exceptions.DoubleValueInvalidException;
import exceptions.IntegerValueInvalidException;
import exceptions.NullObjectException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes do filtro.
 * @author Everton Bruno Silva dos Santos.
 */
public class FilterTest {
    
    public FilterTest() {
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
     * Este teste verifica a não ocorrência de exceções em caso de um objeto não nulo.
     */
    @Test
    public void notNull() {
        try {
            Filter.nullObject("string não nula");
            assertTrue(true);
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        }
    }

    /**
     * Este teste verifica a ocorrência de exceções em caso de uma string nula.
     */
    @Test
    public void nullObject() {
        try {
            Filter.nullObject(null);
            fail("o desvio de fluxo não ocorreu ao filtrar uma string nula");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a não ocorrência de exceções em caso de um valor decimal
     * contido em string ser válido.
     */
    @Test
    public void validDoubleValue() {
        try {
            Filter.invalidValueDouble("2,50");
            assertTrue(true);
        } catch (final DoubleValueInvalidException ex) {
            fail("exceção de valor decimal inválido inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }

    /**
     * Este teste verifica a ocorrência de exceções em caso de valor decimal contido
     * em string ser inválido.
     */
    @Test
    public void invalidDoubleValue() {
        try {
            Filter.invalidValueDouble(null);
            fail("o desvio de fluxo não ocorreu ao filtrar um valor decimal"
                    + "criado por meio de string");
        } catch (final DoubleValueInvalidException ex) {
            fail("exceção de valor decimal inválido inesperada");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a não ocorrência de exceções em caso de um valor inteiro
     * contido em string ser válido.
     */
    @Test
    public void validIntegerValue() {
        try {
            Filter.invalidValueInteger("50");
            assertTrue(true);
        } catch (final IntegerValueInvalidException ex) {
            fail("exceção de valor inteiro inválido inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }

    /**
     * Este teste verifica a ocorrência de exceções em caso de valor inteiro contido
     * em string ser inválido.
     */
    @Test
    public void invalidIntegerValue() {
        try {
            Filter.invalidValueInteger(null);
            fail("o desvio de fluxo não ocorreu ao filtrar um valor inteiro" + "criado por meio de string");
        } catch (final IntegerValueInvalidException ex) {
            fail("exceção de valor inteiro inválido inesperada");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a eficácia do filtro de datas válidas por meio de valores
     * inteiros positivos.
     */
    @Test
    public void validDateIntegerPositive() {
        try {
            Filter.invalidDate(2, 2, 2020);
            assertTrue(true);
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida iesperada");
        }
    }

    /**
     * Este teste verifica a eficácia do filtro de datas válidas por meio de valores
     * inteiros negativos.
     */
    @Test
    public void validDateIntegerNegative() {
        try {
            Filter.invalidDate(-2, -2, -2020);
            assertTrue(true);
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida iesperada");
        }
    }

    /**
     * Este teste verifica o desparo de exceção de data inválida no caso de inserir
     * uma data inexistente ao criar uma nova data.
     */
    @Test
    public void invalidDateInteger() {
        try {
            Filter.invalidDate(31, 2, 2020);
            fail("o desvio de fluxo não ocorreu ao criar uma data inválida");
        } catch (final DateInvalidException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a eficácia do filtro de datas válidas por meio de string
     * não nula contendo em si uma data válida positiva.
     */
    @Test
    public void validDateStringPositive() {
        try {
            Filter.invalidDate("2/2/2020");
            assertTrue(true);
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

    /**
     * Este teste verifica a eficácia do filtro de datas válidas por meio de string
     * não nula contendo em si uma data válida positiva.
     */
    @Test
    public void validDateStringNegative() {
        try {
            Filter.invalidDate("-2/-2/-2020");
            fail("não houve desvio de fluxo ao filtrar um data inválida");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        } catch (final DateInvalidException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica o desparo de exceção de data inválida criada por meio de
     * uma string com uma data inválida.
     */
    @Test
    public void invalidDateString() {
        try {
            Filter.invalidDate("31/2/2020");
            fail("o desvio de fluxo não ocorreu ao criar uma data com uma string inválida");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inesperada");
        } catch (final DateInvalidException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica o desparo de exceção de data inválida criada por meio de
     * uma string nula.
     */
    @Test
    public void nullDateString() {
        try {
            Filter.invalidDate(null);
            fail("o desvio de fluxo não ocorreu ao criar uma data com uma string inválida");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        } catch (final DateInvalidException ex) {
            fail("exceção de data inválida inesperada");
        }
    }

}
