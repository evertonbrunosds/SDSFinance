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
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Classe responsável por comportar-se como árvore.
 * @author Everton Bruno Silva dos Santos.
 * @param <K> Refere-se ao tipo de chave usada na árvore.
 * @param <E> Refere-se ao tipo de elemento armazenado na árvore.
 */
public class Tree<K, E> implements ITree<K, E> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -5226384390486923525L;
    /**
     * Refere-se ao nó raiz da árvore.
     */
    private Node root;

    /**
     * Construtor responsável pelo instanciamento da árvore.
     */
    public Tree() {
        this.root = null;
    }

    /**
     * Método responsável por retornar a quantidade de elementos contidos na árvore.
     * @return Retorna quantidade de elementos contidos na árvore.
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * Método responsável por retornar a quantidade de elementos contidos na árvore.
     * @param currentNode Refere-se ao nó atual da contagem.
     * @return Retorna quantidade de elementos contidos na árvore.
     */
    private int size(final Node currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            return size(currentNode.sonOnTheLeft) + size(currentNode.sonOnTheRight) + 1;
        }
    }

    /**
     * Método responsável por esvaziar a árvore.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Método responsável por indicar se a árvore está vazia.
     * @return Retorna indicativo de que a árvore está vazia.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Método responsável por indicar se determinado elemento está contido na árvore.
     * @param key Refere-se a chave do elemento.
     * @return Retorna indicativo de que a árvore contém o eventual elemento.
     */
    @Override
    public boolean isContains(final Comparable<K> key) {
        return search(key, root) != null;
    }

    /**
     * Método responsável por buscar dado elemento que está contido na árvore.
     * @param key Refere-se a chave do elemento.
     * @return Retorna elemento se contido na árvore.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    @Override
    public E search(final Comparable<K> key) throws ElementNotFoundException {
        final E element = search(key, root);
        if (element == null) {
            throw new ElementNotFoundException();
        } else {
            return element;
        }
    }

    /**
     * Método responsável por buscar dado elemento que está contido na árvore.
     * @param key         Refere-se a chave do elemento.
     * @param currentNode Refere-se ao nó atual da busca.
     * @return Retorna elemento se contido na árvore, do contrário retorna referência nula.
     */
    private E search(final Comparable<K> key, final Node currentNode) {
        if (currentNode == null) {
            return null;
        } else {
            final int comparisonResult = currentNode.key.compareTo((K) key);
            if (comparisonResult == 0) {
                return currentNode.element;
            } else if (comparisonResult > 0) {
                return search(key, currentNode.sonOnTheLeft);
            } else {
                return search(key, currentNode.sonOnTheRight);
            }
        }
    }

    /**
     * Método responsável por remover dado elemento que está contido na árvore.
     * @param key Refere-se a chave do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    @Override
    public void remove(final Comparable<K> key) throws ElementNotFoundException {
        if (isContains(key)) {
            root = remove(key, root);
        } else {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Método responsável por remover dado elemento que está contido na árvore.
     * @param key         Refere-se a chave do elemento.
     * @param currentNode Refere-se ao nó atual da remoção.
     * @return Retorna nó raiz da árvore reconstruida sem o elemento removido.
     */
    private Node remove(final Comparable<K> key, final Node currentNode) {
        if (currentNode == null) {
            return null;
        } else {
            final int comparisonResult = currentNode.key.compareTo((K) key);
            if (comparisonResult == 0) {
                if (currentNode.isLeaf()) {
                    return null;
                } else if (currentNode.hasSonJustLeft()) {
                    return currentNode.sonOnTheLeft;
                } else if (currentNode.hasSonJustRight()) {
                    return currentNode.sonOnTheRight;
                } else {
                    Node tmpNode = currentNode.sonOnTheLeft;
                    while (tmpNode.sonOnTheRight != null) {
                        tmpNode = tmpNode.sonOnTheRight;
                    }
                    final Comparable<K> tmpKey = tmpNode.key;
                    final E tmpElement = tmpNode.element;
                    tmpNode.key = currentNode.key;
                    tmpNode.element = currentNode.element;
                    currentNode.key = tmpKey;
                    currentNode.element = tmpElement;
                    currentNode.sonOnTheLeft = remove(key, currentNode.sonOnTheLeft);
                }
            } else if (comparisonResult > 0) {
                currentNode.sonOnTheLeft = remove(key, currentNode.sonOnTheLeft);
            } else {
                currentNode.sonOnTheRight = remove(key, currentNode.sonOnTheRight);
            }
            return adjustHeight(currentNode);
        }
    }

    /**
     * Método responsável por insetir dado elemento na árvore.
     * @param key     Refere-se a chave do elemento.
     * @param element Refere-se ao elemento.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso.
     */
    @Override
    public void insert(final Comparable<K> key, final E element) throws KeyUsedException {
        root = insert(key, element, root);
    }

    /**
     * Método responsável por insetir dado elemento na árvore.
     * @param key         Refere-se a chave do elemento.
     * @param element     Refere-se ao elemento.
     * @param currentNode Refere-se ao nó atual da inserção.
     * @return Retorna nó raiz da árvore reconstruida sem o elemento removido.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso.
     */
    private Node insert(final Comparable<K> key, final E element, final Node currentNode) throws KeyUsedException {
        if (currentNode == null) {
            return new Node(key, element);
        } else {
            final int comparisonResult = currentNode.key.compareTo((K) key);
            if (comparisonResult == 0) {
                throw new KeyUsedException(currentNode.element);
            } else if (comparisonResult > 0) {
                currentNode.sonOnTheLeft = insert(key, element, currentNode.sonOnTheLeft);
            } else {
                currentNode.sonOnTheRight = insert(key, element, currentNode.sonOnTheRight);
            }
            return adjustHeight(currentNode);
        }
    }

    /**
     * Método responsável por percorrer por todos os elementos contidos na árvore.
     * @param element Refere-se aos elementos contidos na árvore.
     */
    @Override
    public void forEachInReverseOrder(final Consumer<? super E> element) {
        forEachInReverseOrder(element, root);
    }

    /**
     * Método responsável por percorrer por todos os elementos contidos na árvore em ordem reversa.
     * @param element     Refere-se aos elementos contidos na árvore.
     * @param currentNode Refere-se ao nó atual da recursão.
     */
    private void forEachInReverseOrder(final Consumer<? super E> element, final Node currentNode) {
        if (currentNode != null) {
            forEachInReverseOrder(element, currentNode.sonOnTheRight);
            element.accept(currentNode.element);
            forEachInReverseOrder(element, currentNode.sonOnTheLeft);
        }
    }

    /**
     * Método responsável por calcular a altura da árvore.
     * @param currentNode Refere-se ao nó atual da contagem.
     * @return Retorna altura da árvore.
     */
    private int calculateHeight(final Node currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            return Math.max(1 + calculateHeight(currentNode.sonOnTheLeft),
                    1 + calculateHeight(currentNode.sonOnTheRight));
        }
    }

    /**
     * Método responsável por calcular o balanceamento da árvore.
     * @param currentNode Refere-se ao nó atual da recursão.
     */
    private void calculateBalance(final Node currentNode) {
        currentNode.balancing = (calculateHeight(currentNode.sonOnTheRight)
                - calculateHeight(currentNode.sonOnTheLeft));
    }

    /**
     * Método responsável por ajustar a altura da árvore.
     * @param currentNode Refere-se ao nó atual do ajuste.
     * @return Retorna nó raiz da árvore reconstruida com altura ajustada.
     */
    private Node adjustHeight(final Node currentNode) {
        calculateBalance(currentNode);
        if (currentNode.balancing <= -2) {
            if (currentNode.balancing * currentNode.sonOnTheLeft.balancing > 0) {
                return simpleRotationLeft(currentNode.sonOnTheLeft, currentNode);
            } else {
                return doubleRotationLeft(currentNode.sonOnTheLeft, currentNode);
            }
        } else if (currentNode.balancing >= 2) {
            if (currentNode.balancing * currentNode.sonOnTheRight.balancing > 0) {
                return simpleRotationRigth(currentNode.sonOnTheRight, currentNode);
            } else {
                return doubleRotationRight(currentNode.sonOnTheRight, currentNode);
            }
        }
        return currentNode;
    }

    /**
     * Método responsável por efetuar rotações simples a esquerda.
     * @param newRoot Refere-se ao novo nó raiz.
     * @param oldRoot Refere-se ao antigo nó raiz.
     * @return Retorna nó raiz de árvore rotacionada a esquerda.
     */
    private Node simpleRotationLeft(final Node newRoot, final Node oldRoot) {
        oldRoot.sonOnTheLeft = newRoot.sonOnTheRight;
        newRoot.sonOnTheRight = oldRoot;
        return newRoot;
    }

    /**
     * Método responsável por efetuar rotações simples a direita.
     * @param newRoot Refere-se ao novo nó raiz.
     * @param oldRoot Refere-se ao antigo nó raiz.
     * @return Retorna nó raiz de árvore rotacionada a direita.
     */
    private Node simpleRotationRigth(final Node newRoot, final Node oldRoot) {
        oldRoot.sonOnTheRight = newRoot.sonOnTheLeft;
        newRoot.sonOnTheLeft = oldRoot;
        return newRoot;
    }

    /**
     * Método responsável por efetuar rotações duplas a esquerda.
     * @param sonOnTheLeft Refere-se ao filho a esquerda.
     * @param oldRoot      Refere-se ao antigo nó raiz.
     * @return Retorna nó raiz de árvore duplamente rotacionada a esquerda.
     */
    private Node doubleRotationLeft(final Node sonOnTheLeft, final Node oldRoot) {
        oldRoot.sonOnTheLeft = sonOnTheLeft.sonOnTheRight;
        sonOnTheLeft.sonOnTheRight = oldRoot.sonOnTheLeft.sonOnTheLeft;
        oldRoot.sonOnTheLeft.sonOnTheLeft = sonOnTheLeft;
        return simpleRotationLeft(oldRoot.sonOnTheLeft, oldRoot);
    }

    /**
     * Método responsável por efetuar rotações duplas a direita.
     * @param sonOnTheLeft Refere-se ao filho a direita.
     * @param oldRoot      Refere-se ao antigo nó raiz.
     * @return Retorna nó raiz de árvore duplamente rotacionada a direita.
     */
    private Node doubleRotationRight(final Node sonOnTheRight, final Node oldRoot) {
        oldRoot.sonOnTheRight = sonOnTheRight.sonOnTheLeft;
        sonOnTheRight.sonOnTheLeft = oldRoot.sonOnTheRight.sonOnTheRight;
        oldRoot.sonOnTheRight.sonOnTheRight = sonOnTheRight;
        return simpleRotationRigth(oldRoot.sonOnTheRight, oldRoot);
    }

    /**
     * Classe responsável por comportar-se como nó de árvore.
     */
    private class Node implements Serializable {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -9185946958686495728L;
        /**
         * Refere-se a chave do nó.
         */
        private Comparable<K> key;
        /**
         * Refere-se ao elemento do nó.
         */
        private E element;
        /**
         * Refere-se ao balanceamento do nó.
         */
        private int balancing;
        /**
         * Refere-se ao filho a esquerda do nó.
         */
        private Node sonOnTheLeft;
        /**
         * Refere-se ao filgo a direita do nó.
         */
        private Node sonOnTheRight;

        /**
         * Construtor responsável pelo instanciamento do nó.
         * @param key     Refere-se a chave do nó.
         * @param element Refere-se ao elemento do nó.
         */
        private Node(final Comparable<K> key, final E element) {
            this.key = key;
            this.element = element;
            this.balancing = 0;
            this.sonOnTheLeft = null;
            this.sonOnTheRight = null;
        }

        /**
         * Método responsável por indicar se o nó é uma folha.
         * @return Retorna indicativo de que o nó é uma folha.
         */
        private boolean isLeaf() {
            return sonOnTheLeft == null && sonOnTheRight == null;
        }

        /**
         * Método responsável por indicar se o nó tem apenas filhos a esquerda.
         * @return Retorna indicativo de que o nó tem apenas filhos a esquerda.
         */
        private boolean hasSonJustLeft() {
            return sonOnTheLeft != null && sonOnTheRight == null;
        }

        /**
         * Método responsável por indicar se o nó tem apenas filhos a direita.
         * @return Retorna indicativo de que o nó tem apenas filhos a direita.
         */
        private boolean hasSonJustRight() {
            return sonOnTheLeft == null && sonOnTheRight != null;
        }

    }

}