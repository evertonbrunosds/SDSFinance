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
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;

/**
 * Interface responsável por fornecer os métodos de coleção de organizações.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de organização.
 */
public interface IOrganizationCollection<T> extends ICollection<String, T> {

    /**
     * Método responsável por alterar a rua da organização.
     * @param key       Refere-se a chave da organização.
     * @param newStreet Refere-se a nova rua da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    public void setStreet(Comparable<String> key, IStreet newStreet) throws ElementNotFoundException, KeyUsedException;

    /**
     * Método responsável por alterar o bairro da organização.
     * @param key             Refere-se a chave da organização.
     * @param newNeighborhood Refere-se ao novo bairro da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    public void setNeighborhood(Comparable<String> key, INeighborhood newNeighborhood)
            throws ElementNotFoundException, KeyUsedException;

    /**
     * Método responsável por alterar a cidade da organização.
     * @param key     Refere-se a chave da organização.
     * @param newCity Refere-se a nova cidade da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    public void setCity(Comparable<String> key, ICity newCity) throws ElementNotFoundException, KeyUsedException;

}