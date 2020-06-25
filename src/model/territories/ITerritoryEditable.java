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
package model.territories;

import exceptions.NullObjectException;
import util.IElement;

/**
 * Interface responsável por fornecer os métodos de um território editável.
 * @author Everton Bruo Silva dos Santos.
 */
public interface ITerritoryEditable extends ITerritoryVisible, IElement<String> {

    /**
     * Método responsável por alterar chave do território.
     * @param key Refere-se ao novo nome do território.
     * @throws NullObjectException Exceção lançada em caso de nome de terrirório nulo.
     */
    @Override
    public void setKey(String key) throws NullObjectException;
    
    /**
     * Método responsável por pré-visualizar chave do território pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de chave nome.
     * @throws NullObjectException Exceção lançada em caso de nome de território nulo.
     */
    @Override
    public Comparable<String> previewKey(String key) throws NullObjectException;
    
}
