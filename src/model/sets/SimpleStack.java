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
import java.io.Serializable;
import java.util.function.Consumer;

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
     * Método responsável por esvaziar a pilha.
     */
    public void clear() {
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
     * Método responsável por pré-visualizar o próximo elemento da pilha simples.
     * @return Retorna elemento da pilha simples.
     * @throws ElementNotFoundException Exceção lançada no caso de não haverem mais elementos.
     */
    public E peek() throws ElementNotFoundException {
        rootNull();
        return root.element;
    }

    /**
     * Método responsável por desempilhar o próximo elemento da pilha simples.
     * @return Retorna elemento da pilha simples.
     * @throws ElementNotFoundException Exceção lançada no caso de não haverem mais elementos.
     */
    public E pop() throws ElementNotFoundException {
        rootNull();
        final E element = root.element;
        root = root.next;
        return element;
    }

    /**
     * Método responsável por percorrer por todos os elementos contidos na pilha simples.
     * @param element Refere-se a cada elemento.
     */
    public void forEach(final Consumer<? super E> element) {
        for (Node tmp = root; tmp != null; tmp = tmp.next) {
            element.accept(tmp.element);
        }
    }

    public java.util.Iterator<E> iterator() {
        return new Iterator(root);
    }

    /**
     * Método responsável por lançar a exceção de elemento não encontrado no caso de não haverem mais elementos.
     * @throws ElementNotFoundException Exceção lançada no caso de não haverem mais elementos.
     */
    private void rootNull() throws ElementNotFoundException {
        if (root == null) {
            throw new ElementNotFoundException();
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

    /**
     * Classe responsável por comportar-se como iterador da pilha simples.
     * @author Everton Bruno Silva dos Santos.
     */
    private class Iterator implements java.util.Iterator<E>, Serializable {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -4046287835605039724L;
        /**
         * Refere-se ao nó temporário do iterador.
         */
        private Node tmp;

        /**
         * Construtor responsável pelo instanciamento do iterador.
         * @param tmp Refere-se ao nó temporário do iterador.
         */
        public Iterator(final Node tmp) {
            this.tmp = tmp;
        }

        /**
         * Método responsável por retornar indicativo de que há um próximo elemento.
         * @return Retorna indicativo de que há um próximo elemento.
         */
        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        /**
         * Método responsável por retornar o próximo elemento.
         * @return Retorna o próximo elemento.
         */
        @Override
        public E next() {
            if (tmp != null) {
                final E element = tmp.element;
                tmp = tmp.next;
                return element;
            } else {
                return null;
            }
        }

    }

}