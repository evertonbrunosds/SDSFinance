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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por testar a pilha simples.
 * @author Everton Bruno Silva dos Santos.
 */
public class SimpleStackTest {
    private SimpleStack<Integer> simpleStack;
    
    public SimpleStackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.simpleStack = new SimpleStack<>();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Este teste verifica a efitividade da inserção e remoção de elementos da pilha simples.
     */
    @Test
    public void pushTest() {
        simpleStack.push(3);
        simpleStack.push(5);
        simpleStack.push(7);
        simpleStack.push(11);
        assertSame(11, simpleStack.pop());
        assertSame(7, simpleStack.pop());
        assertSame(5, simpleStack.pop());
        assertSame(3, simpleStack.pop());
        assertNull(simpleStack.pop());
    }
    
    /**
     * Este teste verifica a evetividade do método isEmpty que junto ao pop pode ser usado como laço.
     */
    @Test
    public void isEmptyTest() {
        final int[] nVector = new int[]{11, 7, 5, 3};
        simpleStack.push(3);
        simpleStack.push(5);
        simpleStack.push(7);
        simpleStack.push(11);
        int counter = 0;
        while(!simpleStack.isEmpty()) {
            assertSame(nVector[counter], simpleStack.pop());
            counter++;
        }
    }
    
}
