/*
 * This file is part of the SDSFinance Open Source Project.
 * SDSFinance is licensed under the GNU GPLv3.
 *
 * Copyright © 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
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
import java.util.function.Consumer;
import util.IElement;

/**
 * Classe responsável por comportar-se como coleção.
 * @author Everton Bruno Silva dos Santos.
 * @param <K> Refere-se ao tipo de chave usada na coleção.
 * @param <E> Refere-se ao tipo de elemento armazenado na coleção.
 */
public class Collection<K, E> implements ICollection<K, E> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -8576255233641277803L;
    /**
     * Refere-se a árvore responsável por organizar os elementos.
     */
    private final ITree<K, IElement<K>> tree;

    /**
     * Construtor responsável pelo instanciamento da coleção
     */
    public Collection() {
        this.tree = new Tree<>();
    }

    /**
     * Método responsável por retornar a quantidade de elementos contidos na coleção.
     * @return Retorna quantidade de elementos contidos na coleção.
     */
    @Override
    public int size() {
        return tree.size();
    }

    /**
     * Método responsável por esvaziar a coleção.
     */
    @Override
    public void clear() {
        tree.clear();
    }

    /**
     * Método responsável por indicar se a coleção está vazia.
     * @return Retorna indicativo de que a coleção está vazia.
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * Método responsável por indicar se determinado elemento está contido na coleção.
     * @param key Refere-se a chave do elemento.
     * @return Retorna indicativo de que a coleção contém o eventual elemento.
     */
    @Override
    public boolean isContains(final Comparable<K> key) {
        return tree.isContains(key);
    }

    /**
     * Método responsável por inserir dado elemento na coleção.
     * @param element Refere-se ao elemento.
     * @throws KeyUsedException Exceção lançada no caso da chave do elemento estar em uso.
     */
    @Override
    public void insert(final E element) throws KeyUsedException {
        final IElement<K> e = (IElement<K>) element;
        tree.insert(e.getKey(), e);
    }

    /**
     * Método responsável por percorrer por todos os elementos contidos na coleção.
     * @param reverse Refere-se a indicativo de que deve-se percorrer em ordem reversa.
     * @param element Refere-se aos elementos contidos na coleção.
     */
    @Override
    public void forEach(final boolean reverse, final Consumer<? super E> element) {
        tree.forEach(reverse, subElement -> {
            element.accept((E) subElement);
        });
    }

    /**
     * Método responsável por buscar dado elemento que está contido na coleção.
     * @param key Refere-se a chave do elemento.
     * @return Retorna elemento se contido na coleção.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    @Override
    public E search(final Comparable<K> key) throws ElementNotFoundException {
        return (E) tree.search(key);
    }

    /**
     * Método responsável por remover dado elemento que está contido na coleção.
     * @param key Refere-se a chave do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    @Override
    public void remove(final Comparable<K> key) throws ElementNotFoundException {
        tree.remove(key);
    }

    /**
     * Método responsável por redefinir chave de dado elemento que está contido na coleção.
     * @param currentKey Refere-se a chave atual do elemento.
     * @param newKey     Refere-se a nova chave do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     * @throws NullObjectException      Exceção lançada em caso de string nula.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso.
     */
    @Override
    public void redefineKey(final Comparable<K> currentKey, final K newKey)
            throws ElementNotFoundException, NullObjectException, KeyUsedException {
        final IElement<K> elementInCurrentState = tree.search(currentKey);
        try {
            final IElement<K> elementInNewState = tree.search(elementInCurrentState.previewKey(newKey));
            if (!elementInCurrentState.equals(elementInNewState)) {
                throw new KeyUsedException(elementInNewState);
            } else {
                redefineKey(elementInCurrentState, newKey);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(elementInCurrentState, newKey);
        }
    }

    /**
     * Método responsável por efetuar a redefinição de chave de um elemento.
     * @param elementInCurrentState Refere-se ao elemento em seu atual estado.
     * @param newKey                Refere-se a nova chave do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     * @throws NullObjectException      Exceção lançada em caso de string nula.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso.
     */
    private void redefineKey(final IElement<K> elementInCurrentState, final K newKey)
            throws ElementNotFoundException, NullObjectException, KeyUsedException {
        remove(elementInCurrentState.getKey());
        elementInCurrentState.setKey(newKey);
        tree.insert(elementInCurrentState.getKey(), elementInCurrentState);
    }

}