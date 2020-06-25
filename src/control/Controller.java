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

import model.sets.Collection;
import model.sets.ICollection;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;

/**
 * Classe responsável por comportar-se como controlador.
 * @author Everton Bruno Silva dos Santos.
 */
public class Controller implements IController {
    private static Controller instance;
    private ICollection<String, IStreet> streetCollection;
    private ICollection<String, INeighborhood> neighborhoodCollection;
    private ICollection<String, ICity> cityCollection;
    
    private Controller() {
        streetCollection = new Collection<>();
        neighborhoodCollection = new Collection<>();
        cityCollection = new Collection<>();
    }
    
    public static IController getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    @Override
    public ICollection<String, IStreet> getStreetCollection() {
        return streetCollection;
    }

    @Override
    public ICollection<String, INeighborhood> getNeighborhoodCollection() {
        return neighborhoodCollection;
    }

    @Override
    public ICollection<String, ICity> getCityCollection() {
        return cityCollection;
    }

    private void setStreetCollection(ICollection<String, IStreet> streetCollection) {
        this.streetCollection = streetCollection;
    }

    private void setNeighborhoodCollection(ICollection<String, INeighborhood> neighborhoodCollection) {
        this.neighborhoodCollection = neighborhoodCollection;
    }

    private void setCityCollection(ICollection<String, ICity> cityCollection) {
        this.cityCollection = cityCollection;
    }
    
}
