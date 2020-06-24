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
    
    @Test
    public void correctInsertionAndSizeAndSearch() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        IExpense expenseOne = new Expense("Bolacha1",3);
        IExpense expenseTwo = new Expense("Bolacha2",5);
        assertTrue(collection.isEmpty());
        assertEquals(0, collection.size());
        collection.insert(expenseOne);
        assertEquals(1, collection.size());
        collection.insert(expenseTwo);
        assertEquals(2, collection.size());
        assertEquals(-3, collection.search(expenseOne.getKey()).getValue(), 0.00);
        assertEquals(-5, collection.search(expenseTwo.getKey()).getValue(), 0.00);
        assertEquals("Bolacha1", collection.search(expenseOne.getKey()).toString());
        assertEquals("Bolacha2", collection.search(expenseTwo.getKey()).toString());
        assertTrue(collection.isContains(expenseOne.getKey()));
        assertTrue(collection.isContains(expenseTwo.getKey()));
        assertFalse(collection.isEmpty());
    }
    
    @Test
    public void correctRedefineKey() throws NullObjectException, KeyUsedException, ElementNotFoundException {
        IExpense expenseOne = new Expense("Bolacha1",3);
        collection.insert(expenseOne);
        collection.redefineKey(expenseOne.getKey(), "Pão");
        assertEquals("Pão", expenseOne.toString());
    }
    
}
