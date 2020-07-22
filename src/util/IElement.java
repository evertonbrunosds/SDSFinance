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
package util;

import exceptions.NullObjectException;
import java.io.Serializable;

/**
 * Interface responsável por fornecer os métodos de um elemento.
 * @author Everton Bruno Silva dos Santos.
 * @param <K> Refere-se ao tipo de chave do elemento.
 */
public interface IElement<K> extends Serializable {

    /**
     * Método responsável por retornar o nome do elemento.
     * @return Retorna nome do elemento.
     */
    @Override
    public String toString();

    /**
     * Método responsável por alterar a chave do elemento.
     * @param key Refere-se a chave do elemento.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     */
    public void setKey(K key) throws NullObjectException;

    /**
     * Método responsável por retornar a chave comparável do elemento.
     * @return Retorna chave comparável do elemento.
     */
    public Comparable<K> getKey();

    /**
     * Método responsável por pré-visualizar chave de elemento pós-alterações.
     * @param key Refere-se a nova chave.
     * @return Retorna pré-visualização de nova chave.
     * @throws NullObjectException Exceção lançada em caso de chave nula.
     */
    public Comparable<K> previewKey(K key) throws NullObjectException;

}