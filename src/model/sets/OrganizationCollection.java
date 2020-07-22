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
import model.organizations.IOrganizationEditable;
import model.territories.City;
import model.territories.Neighborhood;
import model.territories.Street;

/**
 * Classe responsável por comportar-se como coleção de organizações.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de organização.
 */
public class OrganizationCollection<T> extends Collection<String, T> implements IOrganizationCollection<T> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -2995525010502554207L;

    /**
     * Método responsável por alterar a rua da organização.
     * @param key       Refere-se a chave da organização.
     * @param newStreet Refere-se a nova rua da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    @Override
    public void setStreet(final Comparable<String> key, final Street newStreet) throws ElementNotFoundException, KeyUsedException {
        final IOrganizationEditable organizationInCurrentState = (IOrganizationEditable) super.search(key);
        try {
            final IOrganizationEditable organizationInNewState = (IOrganizationEditable) super.search(
                    organizationInCurrentState.previewKey(newStreet));
            if (!organizationInCurrentState.equals(organizationInNewState)) {
                throw new KeyUsedException(organizationInNewState);
            } else {
                redefineKey(organizationInCurrentState, newStreet);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(organizationInCurrentState, newStreet);
        }
    }

    /**
     * Método responsável por efetuar a redefinição de rua de uma organização.
     * @param organizationInCurrentState Refere-se a organização em seu atual estado.
     * @param newStreet                  Refere-se a nova rua da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    private void redefineKey(final IOrganizationEditable organizationInCurrentState, final Street newStreet)
            throws ElementNotFoundException, KeyUsedException {
        super.remove(organizationInCurrentState.getKey());
        organizationInCurrentState.setStreet(newStreet);
        super.insert((T) organizationInCurrentState);
    }

    /**
     * Método responsável por alterar o bairro da organização.
     * @param key             Refere-se a chave da organização.
     * @param newNeighborhood Refere-se ao novo bairro da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    @Override
    public void setNeighborhood(final Comparable<String> key, final Neighborhood newNeighborhood)
            throws ElementNotFoundException, KeyUsedException {
        final IOrganizationEditable organizationInCurrentState = (IOrganizationEditable) super.search(key);
        try {
            final IOrganizationEditable organizationInNewState = (IOrganizationEditable) super.search(
                    organizationInCurrentState.previewKey(newNeighborhood));
            if (!organizationInCurrentState.equals(organizationInNewState)) {
                throw new KeyUsedException(organizationInNewState);
            } else {
                redefineKey(organizationInCurrentState, newNeighborhood);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(organizationInCurrentState, newNeighborhood);
        }
    }

    /**
     * Método responsável por efetuar a redefinição de bairro de uma organização.
     * @param organizationInCurrentState Refere-se a organização em seu atual estado.
     * @param newNeighborhood            Refere-se ao novo bairro da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    private void redefineKey(final IOrganizationEditable organizationInCurrentState,
            final Neighborhood newNeighborhood) throws ElementNotFoundException, KeyUsedException {
        super.remove(organizationInCurrentState.getKey());
        organizationInCurrentState.setNeighborhood(newNeighborhood);
        super.insert((T) organizationInCurrentState);
    }

    /**
     * Método responsável por alterar a cidade da organização.
     * @param key     Refere-se a chave da organização.
     * @param newCity Refere-se a nova cidade da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    @Override
    public void setCity(final Comparable<String> key, final City newCity) throws ElementNotFoundException, KeyUsedException {
        final IOrganizationEditable organizationInCurrentState = (IOrganizationEditable) super.search(key);
        try {
            final IOrganizationEditable organizationInNewState = (IOrganizationEditable) super.search(
                    organizationInCurrentState.previewKey(newCity));
            if (!organizationInCurrentState.equals(organizationInNewState)) {
                throw new KeyUsedException(organizationInNewState);
            } else {
                redefineKey(organizationInCurrentState, newCity);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(organizationInCurrentState, newCity);
        }
    }

    /**
     * Método responsável por efetuar a redefinição de cidade de uma organização.
     * @param organizationInCurrentState Refere-se a organização em seu atual estado.
     * @param newCity                    Refere-se a nova cidade da organização.
     * @throws ElementNotFoundException Exceção lançada no caso da organização não ser encontrada.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outra organização.
     */
    private void redefineKey(final IOrganizationEditable organizationInCurrentState, final City newCity)
            throws ElementNotFoundException, KeyUsedException {
        super.remove(organizationInCurrentState.getKey());
        organizationInCurrentState.setCity(newCity);
        super.insert((T) organizationInCurrentState);
    }

}