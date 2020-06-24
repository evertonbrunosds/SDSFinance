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
package util;

import exceptions.NullStringException;
import java.io.Serializable;

/**
 * Interface responsável por comportar-se fornecer os métodos de um elemento.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo do elemento.
 */
public interface IElement<T> extends Serializable, Comparable<T> {
    
    /**
     * Método responsável por retornar o nome do elemento.
     * @return Retorna nome do elemento.
     */
    @Override
    public String toString();
    
    /**
     * Método responsável por alterar a string do elemento.
     * @param string Refere-se a string do elemento.
     * @throws NullStringException Exceção lançada em caso de string nula.
     */
    public void setString(String string) throws NullStringException;
    
    /**
     * Método responsável por retornar a identificação comparável do elemento.
     * @return Retorna identificação comparável do elemento.
     */
    public Comparable getId();
    
    /**
     * Método responsável por pré-visualizar identificação de elemento com dada string.
     * @param string Refere-se a dada string.
     * @return Retorna pré-visualização de identificação.
     * @throws NullStringException Exceção lançada em caso de string nula.
     */
    public Comparable previewId(String string) throws NullStringException;
    
}
