/*
 * file is part of the SDSFinance Open Source Project.
 * SDSFinance is licensed under the GNU GPLv3.
 *
 * Copyright (c) 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
 *
 * program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with program.  If not, see <https://www.gnu.org/licenses/>.
 */
package util;

import model.sets.Tree;
import exceptions.ElementNotFoundException;
import exceptions.KeyUsedException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por testar árvore.
 * @author Everton Bruno Silva dos Santos.
 */
public class TreeTest {
    /**
     * Refere-se a árvore.
     */
    private Tree<Integer, String> tree;
    /**
     * Refere-se ao vetor de strings.
     */
    private String[] vector;
    /**
     * Refere-se ao contador.
     */
    private int counter;
    
    /**
     * Construtor responsável pelo instanciamento da classe de teste.
     */
    public TreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tree = new Tree<>();
        vector = new String[] {"A", "B", "C", "D", "E"};
        counter = 0;
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Testa a crieção da árvore vazia.
     */
    @Test
    public void correctCreationAndIsEmpty() {
        assertTrue("A estrutura não foi criada vazia.", tree.isEmpty());
    }
    
    /**
     * Testa a criação da árvore com tamanho zero.
     */
    @Test
    public void correctCreationAndSize() {
        assertEquals("A estrutura não foi criada com zero elementos.", 0, tree.size());
    }
    
    /**
     * Testa método responsável por verificar se dado elemento está contido na árvore contido em árvore vazia.
     */
    @Test
    public void correctCreateTreeIsContains() {
        assertFalse(tree.isContains((Integer) 20));
    }
    
    /**
     * Testa método de remoção com árvore vazia.
     */
    @Test
    public void correctCreateTreeRemoveElement() {
        try {
            tree.remove((Integer) 3);
            fail("A remoção de elementos em árvore vazia seguiu o fluxo.");
        } catch (ElementNotFoundException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Testa método de busca com árvore vazia.
     */
    @Test
    public void correctCreateTreeSearchElement() {
        try {
            tree.search((Integer) 3);
            fail("A busca de elementos em árvore vazia seguiu o fluxo.");
        } catch (ElementNotFoundException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Testa a busca e tamanho da árvore após inserções.
     */
    @Test
    public void correctInsertionAndIsContainsAndSearchAndIsEmptyAndSize() {
        try {
            Integer one = 1;
            Integer two = 2;
            Integer three = 3;
            tree.insert(one, "one");
            tree.insert(two, "two");
            tree.insert(three, "three");
            assertTrue("O elemento 'one' não foi inserido", tree.isContains(one));
            assertTrue("O elemento 'two' não foi inserido", tree.isContains(two));
            assertTrue("O elemento 'three' não foi inserido", tree.isContains(three));
            assertEquals("Busca incorreta", "one", tree.search(one));
            assertEquals("Busca incorreta", "two", tree.search(two));
            assertEquals("Busca incorreta", "three", tree.search(three));
            assertFalse("A estrutura permanece vazia.", tree.isEmpty());
            assertEquals("A estrutura não três elementos.", 3, tree.size());
        } catch (KeyUsedException ex) {
            fail("Exceção de chave usada inesperada");
        } catch (ElementNotFoundException ex) {
            fail("Exceção de elemento não encontrado inesperada");
        }
    }
    
    /**
     * Testa efetividade do método de remoção.
     */
    @Test
    public void correctRemoveAndIsContainsAndIsEmptyAndSize() {
        try {
            Integer one = 1;
            Integer two = 2;
            Integer three = 3;
            tree.insert(one, "one");
            tree.insert(two, "two");
            tree.insert(three, "three");
            tree.remove(one);
            tree.remove(three);
            assertFalse("O elemento 'one' não foi removido", tree.isContains(one));
            assertTrue("O elemento 'two' foi removido", tree.isContains(two));
            assertFalse("O elemento 'three' não foi removido", tree.isContains(three));
            assertFalse("A estrutura permanece vazia.", tree.isEmpty());
            assertEquals("A estrutura não um elemento.", 1, tree.size());
        } catch (KeyUsedException ex) {
            fail("Exceção de chave usada inesperada");
        } catch (ElementNotFoundException ex) {
            fail("Exceção de elemento não encontrado inesperada");
        }
    }
    
    /**
     * Testa lançamento de exceção de chave usada.
     */
    @Test 
    public void usedKeyInsertion() {
        try {
            Integer one = 1;
            Integer two = 2;
            Integer three = 3;
            tree.insert(one, "one");
            tree.insert(two, "two");
            tree.insert(three, "three");
            tree.insert(three, "three");
            fail("A inserção de chave usada seguiu o fluxo.");
        } catch (KeyUsedException ex) {
            assertEquals("three", ex.getElement());
        }
        assertEquals(3, tree.size());
    }
    
    /**
     * Testa lançamento de exceção de elemento não encontrado ao efetuar buscas.
     */
    @Test
    public void elementNotFoundSearch() {
        try {
            Integer one = 1;
            Integer two = 2;
            Integer three = 3;
            tree.insert(one, "one");
            tree.insert(two, "two");
            tree.insert(three, "three");
            tree.search((Integer) 5);   
            fail("A busca por chave inexistente seguiu o fluxo.");
        } catch (KeyUsedException ex) {
            assertEquals("three", ex.getElement());
        } catch (ElementNotFoundException ex) {
            assertTrue(true);
        }
        assertEquals(3, tree.size());
    }
    
    /**
     * Testa lançamento de exceção de elemento não encontraod ao efetuar remoções.
     */
    @Test
    public void elementNotFoundRemove() {
        try {
            Integer one = 1;
            Integer two = 2;
            Integer three = 3;
            tree.insert(one, "one");
            tree.insert(two, "two");
            tree.insert(three, "three");
            tree.remove((Integer) 5);
            fail("A remoção de chave inexistente seguiu o fluxo.");
        } catch (KeyUsedException ex) {
            assertEquals("three", ex.getElement());
        } catch (ElementNotFoundException ex) {
            assertTrue(true);
        }
        assertEquals(3, tree.size());
    }
    
    /**
     * Testa eficiêcia do forEach com árvore vazia.
     */
    @Test
    public void ForEachInNullTree() {
        tree.forEach((element) -> {
            fail("O forEach foi executado com a árvore vazia.");
        });
    }
    
    /**
     * Testa eficiência do forEach para percorrer todos os elementos da árvore.
     */
    @Test
    public void FroEachInNotNullTree() {
        for(Integer i = 0; i < vector.length; i++) {
            try {
                tree.insert(i, vector[i]);
            } catch (KeyUsedException ex) {
                fail("Exceção de chave usada inesperada");
            }
        }
        tree.forEach((element) -> {
            assertEquals("Os elementos divergem", vector[vector.length-1-counter], element);
            counter++;
        });
    }
    
}
