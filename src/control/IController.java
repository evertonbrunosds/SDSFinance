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
package control;

import model.sets.ITerritoryCollection;
import model.sets.IOrganizationCollection;
import model.organizations.IProvider;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;

/**
 * Interface responsável por fornecer os métodos de controlador.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IController {

    /**
     * Método responsável por retornar coleção de ruas.
     * @return Retorna coleção de ruas.
     */
    public ITerritoryCollection<IStreet> getStreetCollection();

    /**
     * Método responsável por retornar coleção de bairros.
     * @return Retorna coleção de bairros.
     */
    public ITerritoryCollection<INeighborhood> getNeighborhoodCollection();

    /**
     * Método responsável por retornar coleção de cidades.
     * @return Retorna coleção de cidades.
     */
    public ITerritoryCollection<ICity> getCityCollection();

    /**
     * Método responsável por retornar coleção de fornecedores.
     * @return Retorna coleção de fornecedores.
     */
    public IOrganizationCollection<IProvider> getProviderCollection();

}