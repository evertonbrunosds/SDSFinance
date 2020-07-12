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

import java.io.Serializable;

/**
 * Classe responsável por comportar-se como pilha simples.
 * @author Everton Bruno Silva dos Santos.
 * @param <E> Refere-se ao tipo de elemento que ficará contido na pilha simples.
 */
public class SimpleStack<E> implements Serializable {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -6410330890507875489L;
    /**
     * Refere-se a raiz da pilha simples.
     */
    private Node root;

    /**
     * Construtor responsável pelo instanciamento da pilha simples.
     */
    public SimpleStack() {
        root = null;
    }

    /**
     * Método responsável por retornar indicativo de que a pilha simples está vazia.
     * @return Retorna indicativo de que a pilha simples está vazia.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Método responsável por empilhar elementos na pilha simples.
     * @param element Refere-se ao elemento a ser empilhado.
     */
    public void push(final E element) {
        root = new Node(element, root);
    }

    /**
     * Método responsável por desempilhar o próximo elemento da pilha simples.
     * @return Retorna elemento da pilha simples.
     */
    public E pop() {
        if(root != null) {
            final E element = root.element;
            root = root.next;
            return element;
        } else {
            return null;
        }
    }

    /**
     * Classe responsável por comportar-se como nó da pilha simples.
     * @author Everton Bruno Silva dos Santos.
     */
    private class Node implements Serializable {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -9033824596430431803L;
        /**
         * Refere-se ao elemento contido no nó.
         */
        private final E element;
        /**
         * Refere-se ao próximo elemento.
         */
        private final Node next;

        /**
         * Construtor responsável pelo instanciamento do nó.
         * @param element Refere-se ao elemento contido no nó.
         * @param next    Refere-se ao próximo elemento.
         */
        private Node(final E element, final Node next) {
            this.element = element;
            this.next = next;
        }
    }

}