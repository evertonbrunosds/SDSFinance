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
import exceptions.NullStringException;
import java.util.function.Consumer;
import util.IElement;
import util.ITree;
import util.Tree;

/**
 * Classe responsável por comportar-se como coleção.
 * @author Everton Bruno Silva dos Santos.
 * @param <E> Refere-se ao tipo de elemento armazenado na coleção.
 */
public class Collection<E> implements ICollection<E> {
    /**
     * Refere-se a árvore responsável por organizar os elementos.
     */
    private final ITree<IElement> tree;
    
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
    public boolean isContains(final Comparable key) {
        return tree.isContains(key);
    }

    /**
     * Método responsável por insetir dado elemento na coleção.
     * @param element Refere-se ao elemento.
     * @throws KeyUsedException Exceção lançada no caso da chave do elemento estar em uso.
     */
    @Override
    public void insert(final E element) throws KeyUsedException {
        final IElement e = (IElement) element;
        tree.insert(e.getId(), e);
    }

    /**
     * Método responsável por percorrer por todos os elementos contidos na coleção.
     * @param element Refere-se aos elementos contidos na coleção.
     */
    @Override
    public void forEach(final Consumer<? super E> element) {
        tree.forEach((e) -> {
            element.accept((E) e);
        });
    }

    /**
     * Método responsável por buscar dado elemento que está contido na coleção.
     * @param key Refere-se a chave do elemento.
     * @return Retorna elemento se contido na coleção.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    @Override
    public E search(final Comparable key) throws ElementNotFoundException {
        return (E) tree.search(key);
    }

    /**
     * Método responsável por remover dado elemento que está contido na coleção.
     * @param key Refere-se a chave do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     */
    @Override
    public void remove(final Comparable key) throws ElementNotFoundException {
        tree.remove(key);
    }

    /**
     * Método responsável por reidentificar dado elemento que está contido na coleção.
     * @param key Refere-se a chave do elemento.
     * @param string Refere-se a nova string do elemento.
     * @throws ElementNotFoundException Exceção lançada no caso do elemento não ser encontrado.
     * @throws NullStringException Exceção lançada em caso de string nula.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso.
     */
    @Override
    public void reidentify(final Comparable key, final String string)
            throws ElementNotFoundException, NullStringException, KeyUsedException {
        final IElement e = tree.search(key);
        try {
            final IElement exist = tree.search(e.previewId(string));
            throw new KeyUsedException(exist);
        } catch (final ElementNotFoundException ex) {
            tree.remove(key);
            e.setString(string);
            tree.insert(e.getId(), e);
        }
    }

}
