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
package model.offers;

import exceptions.NullObjectException;
import util.Converter;
import util.Filter;
import util.IElement;

/**
 * Classe responsável por comportar-se como renda.
 * @author Everton Bruno Silva dos Santos.
 */
public class Income extends Offer implements IIncome, IElement<String> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -4401549819933176806L;
    /**
     * Refere-se ao nome da renda.
     */
    private String name;
    /**
     * Refere-se ao valor da renda.
     */
    private double value;
    
    /**
     * Construtor responsável pelo instanciamento da renda.
     */
    private Income() {}

    /**
     * Construtor responsável pelo instanciamento da renda.
     * @param name Refere-se ao nome da renda.
     * @param value Refere-se ao valor da renda.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    public Income(final String name, final double value) throws NullObjectException {
        Filter.nullObject(name);
        this.name = name;
        this.value = Converter.toPositive(value);
    }

    /**
     * Método responsável por alterar o valor da renda.
     * @param value Refere-se ao novo valor da renda.
     */
    @Override
    public void setValue(final double value) {
        this.value = Converter.toPositive(value);
    }

    /**
     * Método responsável por alterar o nome da renda.
     * @param key Refere-se ao novo nome da renda.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public void setKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        name = key;
    }

    /**
     * Método responsável por retornar nome da renda.
     * @return Retorna nome da renda.
     */
    @Override
    public Comparable<String> getKey() {
        return name.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar nome da renda pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de novo nome.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public Comparable<String> previewKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        return key.toLowerCase();
    }

    /**
     * Método responsável por retornar valor de renda.
     * @return Retorna valor de renda.
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * Método responsável por duplicar renda.
     * @return Retorna renda duplicata.
     */
    @Override
    public IIncome duplicate() {
        final Income income = new Income();
        income.name = name;
        income.value = value;
        return income;
    }
    
    /**
     * Método responsável por retornar o nome da renda.
     * @return Retorna nome da renda.
     */
    @Override
    public String toString() {
        return name;
    }

}
