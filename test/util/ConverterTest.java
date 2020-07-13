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
import exceptions.IntegerValueInvalidException;
import exceptions.NullObjectException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes do conversor.
 * @author Everton Bruno Silva dos Santos.
 */
public class ConverterTest {
    
    public ConverterTest() {
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
     * Este teste verifica a armazenagem de valores decimais inválidos.
     */
    @Test
    public void invalidValueToDouble() {
        try {
            Converter.toDouble("valor inválido");
            fail("o desvio de fluxo não ocorreu ao converter para decimal uma string");
        } catch (final DoubleValueInvalidException ex) {
            assertEquals("valor inválido", ex.getDoubleValueInvalid());
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }
    
    /**
     * Este teste verifica o desparo de exceção de string nula ao tentar converter
     * para decimal uma string nula.
     */
    @Test
    public void nullValueToDouble() {
        try {
            Converter.toDouble(null);
            fail("o desvio de fluxo não ocorreu ao converter para decimal uma string");
        } catch (final DoubleValueInvalidException ex) {
            fail("exceção de valor decimal inválido inesperada");
        } catch (final NullObjectException ex) {
            assertTrue(true);
        }
    }

    /**
     * Este teste verifica a armazenagem de valores inteiros inválidos.
     */
    @Test
    public void invalidValueToInteger() {
        try {
            Converter.toInteger("valor inválido");
            fail("o desvio de fluxo não ocorreu ao converter para inteiro uma string");
        } catch (final IntegerValueInvalidException ex) {
            assertEquals("valor inválido", ex.getIntegerValueInvalid());
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }
    
    /**
     * Este teste verifica o desparo de exceção de string nula ao tentar converter
     * para inteiro uma string nula.
     */
    @Test
    public void nullValueToInteger() {
        try {
            Converter.toInteger("valor inválido");
            fail("o desvio de fluxo não ocorreu ao converter para inteiro uma string");
        } catch (final IntegerValueInvalidException ex) {
            assertEquals("valor inválido", ex.getIntegerValueInvalid());
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }

    /**
     * Este teste verifica a conversão de string negativa para decimal negativo.
     */
    @Test
    public void negativeValueToNegativeDouble() {
        try {
            assertEquals(-123.45, Converter.toDouble("-123,45"), 0.00);
        } catch (final DoubleValueInvalidException ex) {
            fail("exceção de valor decimal inválido inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }

    /**
     * Este teste verifica a conversão de string positiva para decimal positivo.
     */
    @Test
    public void positiveValueToPositiveDouble() {
        try {
            assertEquals(123.45, Converter.toDouble("123,45"), 0.00);
        } catch (final DoubleValueInvalidException ex) {
            fail("exceção de valor decimal inválido inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }

    /**
     * Este teste verifica a conversão de string negativa para inteiro negativo.
     */
    @Test
    public void negativeValueToNegativeInteger() {
        try {
            assertEquals(-6, Converter.toInteger("-6"));
        } catch (final IntegerValueInvalidException ex) {
            fail("exceção de valor inteiro inválido inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }

    /**
     * Este teste verifica a conversão de string positiva para inteiro positivo.
     */
    @Test
    public void positiveValueToPositiveInteger() {
        try {
            assertEquals(6, Converter.toInteger("6"));
        } catch (final IntegerValueInvalidException ex) {
            fail("exceção de valor inteiro inválido inesperada");
        } catch (final NullObjectException ex) {
            fail("exceção de string nula inválido inesperada");
        }
    }
    
    /**
     * Este teste verifica a conversão de decimal positivo para string.
     */
    @Test
    public void positiveDoubleToString() {
        assertEquals("2,50", Converter.toString(2.5000001));
    }
    
    /**
     * Este teste verifica a conversão de decimal negativo para string.
     */
    @Test
    public void negativeDoubleToString() {
        assertEquals("-2,50", Converter.toString(-2.5000001));
    }
    
    /**
     * Este teste verifica a conversão de inteiro positivo para string.
     */
    @Test
    public void positiveIntegerToString() {
        assertEquals("2", Converter.toString(2));
    }
    
    /**
     * Este teste verifica a conversão de inteiro negativo para string.
     */
    @Test
    public void negativeIntegerToString() {
        assertEquals("-2", Converter.toString(-2));
    }
    
    /**
     * Este teste verifica a conversão de valores inteiros 
     * positivos para valores inteiros negativos.
     */
    @Test
    public void positiveValueIntegerToNegativeValueInteger() {
        assertEquals(-5, Converter.toNegative(5));
    }
    
    /**
     * Este teste verifica a conversão de valores inteiros 
     * negativos para valores inteiros negativos.
     */
    @Test
    public void negativeValueIntegerToNegativeValueInteger() {
        assertEquals(-5, Converter.toNegative(-5));
    }
    
    /**
     * Este teste verifica a conversão de valores decimais 
     * positivos para valores decimais negativos.
     */
    @Test
    public void positiveValueDoubleToNegativeValueDoble() {
        assertEquals(-5.56, Converter.toNegative(5.56), 0.00);
    }
    
    /**
     * Este teste verifica a conversão de valores decimais  
     * negativos para valores decimais negativos.
     */
    @Test
    public void negativeValueDoubleToNegativeValueDouble() {
        assertEquals(-5.56, Converter.toNegative(-5.56), 0.00);
    }
    
    /**
     * Este teste verifica a conversão de valores inteiros 
     * positivos para valores inteiros positivos.
     */
    @Test
    public void positiveValueIntegerToPositiveValueInteger() {
        assertEquals(5, Converter.toPositive(5));
    }
    
    /**
     * Este teste verifica a conversão de valores inteiros 
     * negativos para valores inteiros positivos.
     */
    @Test
    public void negativeValueIntegerToPositiveValueInteger() {
        assertEquals(5, Converter.toPositive(-5));
    }
    
    /**
     * Este teste verifica a conversão de valores decimais 
     * positivos para valores decimais positivos.
     */
    @Test
    public void positiveValueDoubleToPositiveValueDoble() {
        assertEquals(5.56, Converter.toPositive(5.56), 0.00);
    }
    
    /**
     * Este teste verifica a conversão de valores decimais  
     * negativos para valores decimais positivos.
     */
    @Test
    public void negativeValueDoubleToPositiveValueDouble() {
        assertEquals(5.56, Converter.toPositive(-5.56), 0.00);
    }
    
    /**
     * Este teste verifica se são convertidos para o nome de extenção indicado, 
     * eventuais caminhos de arquivos.
     */
    @Test
    public void toExtensionName() {
        String pathFile = "File Name";
        assertEquals("File Name.wav", Converter.toExtensionName(pathFile, ".wav"));
    }
    
    /**
     * Este teste verifica se não são convertidos para o nome de extenção indicado,
     * caso esse seja nulo.
     */
    @Test
    public void toExtensionNameNullExtension() {
        String pathFile = "File Name";
        assertEquals("File Name", Converter.toExtensionName(pathFile, null));
    }
    
    /**
     * Este teste verifica se não são convertidos para o nome de extenção indicado,
     * caso o caminho do arquivo seja nulo.
     */
    @Test
    public void toExtensionNameNullPathFile() {
        assertNull(Converter.toExtensionName(null, ".wav"));
    }
    
    /**
     * Este teste verifica se não são convertidos para o nome de extenção indicado,
     * caso de tanto o caminho do arquivo, quanto o nome de extenção serem nulos.
     */
    @Test
    public void toExtensionNameNullPathFileAndNullName() {
        assertNull(Converter.toExtensionName(null, null));
    }
    
}
