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
package model.organizations;

import exceptions.NullObjectException;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;
import util.IElement;

/**
 * Interface responsável por fornecer os métodos de organização editável.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IOrganizationEditable extends IOrganizationVisible, IElement<String> {

    /**
     * Método responsável por alterar chave da organização.
     * @param key Refere-se ao novo nome da organização.
     * @throws NullObjectException Exceção lançada em caso de nome de organização nula.
     */
    @Override
    public void setKey(String key) throws NullObjectException;
    
    /**
     * Método responsável por alterar a rua da organização.
     * @param street Refere-se a nova rua da organização.
     */
    public void setStreet(IStreet street);
    
    /**
     * Método responsável por alterar o bairro da organização.
     * @param neighborhood Refere-se ao novo bairro da organização.
     */
    public void setNeighborhood(INeighborhood neighborhood);
    
    /**
     * Método responsável por alterar a cidade da organização.
     * @param city Refere-se a nova cidade da organização.
     */
    public void setCity(ICity city);
    
    /**
     * Método responsável por pré-visualizar chave da organização pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de chave.
     * @throws NullObjectException Exceção lançada em caso de nome de organização nula.
     */
    @Override
    public Comparable<String> previewKey(String key) throws NullObjectException;
    
    /**
     * Método responsável por pré-visualizar chave da organização pós-alterações.
     * @param street Refere-se a nova cidade.
     * @return Retorna pré-visualização da nova chave.
     */
    public Comparable<String> previewKey(IStreet street);
    
    /**
     * Método responsável por pré-visualizar chave da organização pós-alterações.
     * @param neighborhood Refere-se ao novo bairro.
     * @return Retorna pré-visualização da nova chave.
     */
    public Comparable<String> previewKey(INeighborhood neighborhood);
    
    /**
     * Método responsável por pré-visualizar chave da organização pós-alterações.
     * @param city Refere-se a nova cidade.
     * @return Retorna pré-visualização da nova chave.
     */
    public Comparable<String> previewKey(ICity city);
    
}
