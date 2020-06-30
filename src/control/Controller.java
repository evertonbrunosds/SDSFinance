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

import model.business.IAcquisition;
import model.sets.ITerritoryCollection;
import model.sets.OrganizationCollection;
import model.sets.IOrganizationCollection;
import model.sets.TerritoryCollection;
import model.organizations.IProvider;
import model.sets.BusinessCollection;
import model.sets.IBusinessCollection;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;

/**
 * Classe responsável por comportar-se como controlador.
 * @author Everton Bruno Silva dos Santos.
 */
public class Controller implements IController {
    /**
     * Refere-se a instância do controlador.
     */
    private static Controller instance;
    /**
     * Refere-se a coleção de ruas.
     */
    private ITerritoryCollection<IStreet> streetCollection;
    /**
     * Refere-se a coleção de bairros.
     */
    private ITerritoryCollection<INeighborhood> neighborhoodCollection;
    /**
     * Refere-se a coleção de cidades.
     */
    private ITerritoryCollection<ICity> cityCollection;
    /**
     * Refere-se a coleção de fornecedores.
     */
    private IOrganizationCollection<IProvider> providerCollection;
    /**
     * Refere-se a coleção de aquisições realisadas.
     */
    private IBusinessCollection<IAcquisition> acquisitionCollection;

    /**
     * Construtor responsável pelo instanciamento do controlador.
     */
    private Controller() {
        streetCollection = new TerritoryCollection<>();
        neighborhoodCollection = new TerritoryCollection<>();
        cityCollection = new TerritoryCollection<>();
        providerCollection = new OrganizationCollection<>();
        acquisitionCollection = new BusinessCollection<>();
    }

    /**
     * Método responsável por retornar instância do controlador.
     * @return Retorna instância do controlador.
     */
    public static IController getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Método responsável por retornar coleção de ruas.
     * @return Retorna coleção de ruas.
     */
    @Override
    public ITerritoryCollection<IStreet> getStreetCollection() {
        return streetCollection;
    }

    /**
     * Método responsável por retornar coleção de bairros.
     * @return Retorna coleção de bairros.
     */
    @Override
    public ITerritoryCollection<INeighborhood> getNeighborhoodCollection() {
        return neighborhoodCollection;
    }

    /**
     * Método responsável por retornar coleção de cidades.
     * @return Retorna coleção de cidades.
     */
    @Override
    public ITerritoryCollection<ICity> getCityCollection() {
        return cityCollection;
    }

    /**
     * Método responsável por retornar coleção de fornecedores.
     * @return Retorna coleção de fornecedores.
     */
    @Override
    public IOrganizationCollection<IProvider> getProviderCollection() {
        return providerCollection;
    }

    /**
     * Método responsável por retornar coleção de aquisições.
     * @return Retorna coleção de aquisições.
     */
    @Override
    public IBusinessCollection<IAcquisition> getAcquisitionCollection() {
        return acquisitionCollection;
    }
    
    private void setStreetCollection(final ITerritoryCollection<IStreet> streetCollection) {
        this.streetCollection = streetCollection;
    }

    private void setNeighborhoodCollection(final ITerritoryCollection<INeighborhood> neighborhoodCollection) {
        this.neighborhoodCollection = neighborhoodCollection;
    }

    private void setCityCollection(final ITerritoryCollection<ICity> cityCollection) {
        this.cityCollection = cityCollection;
    }

    private void setProviderCollection(final IOrganizationCollection<IProvider> providerCollection) {
        this.providerCollection = providerCollection;
    }

    private void setAcquisitionCollection(IBusinessCollection<IAcquisition> acquisitionCollection) {
        this.acquisitionCollection = acquisitionCollection;
    }
    
}