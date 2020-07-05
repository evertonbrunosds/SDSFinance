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
 * Interface responsável por fornecer os métodos de árvore.
 * @author Everton Bruno Silva dos Santos.
 * @param <K> Refere-se ao tipo de chave usada na árvore.
 * @param <E> Refere-se ao tipo de elemento armazenado na árvore.
 */
public interface ITree<K, E> extends Serializable {

    /**
     * Método responsável por retornar a quantidade de elementos contidos na árvore.
     * @return Retorna quantidade de elementos contidos na árvore.
     */
    public int size();

    /**
     * Método responsável por esvaziar a árvore.
     */
    public void clear();

    /**
     * Método responsável por indicar se a árvore está vazia.
     * @return Retorna indicativo de que a árvore está vazia.
     */
    public boolean isEmpty();

    /**
     * Método responsável por indicar se determinado elemento está contido na árvore.
     * @param key Refere-se a chave do elemento.
     * @return Retorna indicativo de que a árvore contém o eventual elemento.
     */
    public boolean isContains(Comparable<K> key);

    /**
     * Método responsável por buscar dado elemento que está contido na árvore.
     * @param key Refere-se a chave do elemento.
     * @return Retorna elemento se contido na árvore.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    public E search(Comparable<K> key) throws ElementNotFoundException;

    /**
     * Método responsável por remover dado elemento que está contido na árvore.
     * @param key Refere-se a chave do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    public void remove(Comparable<K> key) throws ElementNotFoundException;

    /**
     * Método responsável por insetir dado elemento na árvore.
     * @param key     Refere-se a chave do elemento.
     * @param element Refere-se ao elemento.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso.
     */
    public void insert(Comparable<K> key, E element) throws KeyUsedException;

    /**
     * Método responsável por percorrer por todos os elementos contidos na árvore em ordem reversa.
     * @param element Refere-se aos elementos contidos na árvore.
     */
    public void forEachInReverseOrder(Consumer<? super E> element);

    /**
     * Método responsável por percorrer por todos os elementos contidos na árvore em ordem.
     * @param element Refere-se aos elementos contidos na árvore.
     */
    public void forEachInOrder(Consumer<? super E> element);

}