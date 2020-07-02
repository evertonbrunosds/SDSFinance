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
package model.sets;

import exceptions.ElementNotFoundException;
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import model.offers.Expense;
import model.offers.IExpense;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por testar a coleção.
 * @author Everton Bruno Silva dos Santos.
 */
public class CollectionTest {
    private Collection<String, IExpense> collection;
    
    public CollectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        collection = new Collection<>();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Classe de teste responsável por testar criação, tamando, busca e contência das coleções.
     * @throws NullObjectException Exceção lançada em caso de string nula.
     * @throws KeyUsedException Exceção lançada em caso de chave usada.
     * @throws ElementNotFoundException Exceção lançada em caso de elemento não encontrado.
     */
    @Test
    public void correctInsertionAndSizeAndSearch() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        IExpense expenseOne = new Expense("Bolacha1",3);
        IExpense expenseTwo = new Expense("Bolacha2",5);
        assertTrue("A coleção não foi criada vazia", collection.isEmpty());
        assertEquals("A coleção não foi criada com tamanho zero", 0, collection.size());
        collection.insert(expenseOne);
        assertEquals("Após a inserção o número de elementos não aumentou para um", 1, collection.size());
        collection.insert(expenseTwo);
        assertEquals("Após a inserção o número de elementos não aumentou para dois", 2, collection.size());
        assertEquals("A busca não encontrou o elemento", -3, collection.search(expenseOne.getKey()).getValue(), 0.00);
        assertEquals("A busca não encontrou o elemento", -5, collection.search(expenseTwo.getKey()).getValue(), 0.00);
        assertEquals("A busca não encontrou o elemento", "Bolacha1", collection.search(expenseOne.getKey()).toString());
        assertEquals("A busca não encontrou o elemento", "Bolacha2", collection.search(expenseTwo.getKey()).toString());
        assertTrue("A busca não encontrou o elemento", collection.isContains(expenseOne.getKey()));
        assertTrue("A busca não encontrou o elemento", collection.isContains(expenseTwo.getKey()));
        assertFalse("A coleção permanece vazia", collection.isEmpty());
    }
    
    /**
     * Testa a redefinição de chave ao redefinir um elemento para uma chave que 
     * ainda não foi usada por nenhum elemento.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     * @throws KeyUsedException Exceção lançada em caso de chave usada.
     * @throws ElementNotFoundException Exceção de elemento não encontrado.
     */
    @Test
    public void correctRedefineKey() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        IExpense expenseOne = new Expense("Bolacha1",3);
        IExpense expenseTwo = new Expense("A",4);
        collection.insert(expenseOne);
        collection.insert(expenseTwo);
        collection.redefineKey(expenseOne.getKey(), "AB");
        assertEquals("AB", expenseOne.toString());
    }
    
    /**
     * Testa a redefinição de chave ao redefinir um elemento para uma chave que 
     * já é usada por outro elemento.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     * @throws ElementNotFoundException Exceção de elemento não encontrado.
     */
    @Test
    public void incorrectRedefineKey() throws NullObjectException, ElementNotFoundException {
        try {
            IExpense expenseOne = new Expense("Bolacha",3);
            IExpense expenseTwo = new Expense("Escova",4);
            collection.insert(expenseOne);
            collection.insert(expenseTwo);
            collection.redefineKey(expenseOne.getKey(), "escova");
            fail("seguiu o fluxo de processamento");
        } catch (KeyUsedException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Testa a redefinição de chave ao redefinir um mesmo elemento para uma chave
     * sensitiva ao caso.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     * @throws KeyUsedException Exceção lançada em caso de chave usada.
     * @throws ElementNotFoundException Exceção de elemento não encontrado.
     */
    @Test
    public void correctRedefineKeyLowerCase() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        IExpense expenseOne = new Expense("Bolacha",3);
        IExpense expenseTwo = new Expense("Escova",4);
        collection.insert(expenseOne);
        collection.insert(expenseTwo);
        collection.redefineKey(expenseOne.getKey(), "bolacha");
        collection.redefineKey(expenseTwo.getKey(), "escova");
        assertEquals("bolacha", expenseOne.toString());
        assertEquals("escova", expenseTwo.toString());
    }
    
    /**
     * Testa o uso do forEach sem elemento inserido.
     */
    @Test
    public void incorrectForEach() {
        collection.forEachInReverseOrder((element) -> {
            fail("ForEach usado mesmo com coleção vazia");
        });
        assertTrue(true);
    }
    
    /**
     * Testa o uso do forEach com elemento inserido.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     * @throws KeyUsedException Exceção lançada em caso de chave usada.
     */
    @Test
    public void correctForEach() throws NullObjectException, KeyUsedException {
        collection.insert(new Expense("Algo",50));
        collection.forEachInReverseOrder((element) -> {
            assertEquals("Algo", element.toString());
        });
    }
    
    /**
     * Testa busca por um elemento inserido de modo sensitivo ao caso.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     * @throws KeyUsedException Exceção lançada em caso de chave usada.
     * @throws ElementNotFoundException Exceção de elemento não encontrado.
     */
    @Test
    public void currectSearchSensitiveCase() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        collection.insert(new Expense("Algo",50));
        assertEquals(-50, collection.search("algo").getValue(),0.00);
        assertEquals("Algo", collection.search("algo").toString());
    }
    
    /**
     * Testa remove um elemento inserido de modo sensitivo ao caso.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     * @throws KeyUsedException Exceção lançada em caso de chave usada.
     * @throws ElementNotFoundException Exceção de elemento não encontrado.
     */
    @Test
    public void currectRemoveSensitiveCase() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        assertFalse(collection.isContains("algo"));
        assertTrue(collection.isEmpty());
        assertEquals(0, collection.size());
        collection.insert(new Expense("Algo",50));
        assertTrue(collection.isContains("algo"));
        assertFalse(collection.isEmpty());
        assertEquals(1, collection.size());
        collection.remove("algo");
        assertFalse(collection.isContains("algo"));
        assertTrue(collection.isEmpty());
        assertEquals(0, collection.size());
    }
    
}
