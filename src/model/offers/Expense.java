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

/**
 * Classe responsável por comportar-se como despesa.
 * @author Everton Bruno Silva dos Santos.
 */
public class Expense implements IExpense, IOfferEditable {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 1758128475801746191L;
    /**
     * Refere-se ao nome da despesa.
     */
    private String name;
    /**
     * Refere-se ao valor da despesa.
     */
    private double value;

    /**
     * Construtor responsável pelo instanciamento da despesa.
     */
    private Expense() {}

    /**
     * Construtor responsável pelo instanciamento da despesa.
     * @param name  Refere-se ao nome da despesa.
     * @param value Refere-se ao valor da despesa.
     * @throws NullObjectException Exceção lançada em caso de nome de despesa nulo.
     */
    public Expense(final String name, final double value) throws NullObjectException {
        Filter.nullObject(name);
        this.name = name;
        this.value = Converter.toNegative(value);
    }

    /**
     * Método responsável por alterar o valor da despesa.
     * @param value Refere-se ao novo valor da despesa.
     */
    @Override
    public void setValue(final double value) {
        this.value = Converter.toNegative(value);
    }

    /**
     * Método responsável por alterar o nome da despesa.
     * @param key Refere-se ao novo nome da despesa.
     * @throws NullObjectException Exceção lançada em caso de nome de despesa nulo.
     */
    @Override
    public void setKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        name = key;
    }

    /**
     * Método responsável por retornar chave da despesa.
     * @return Retorna chave da despesa.
     */
    @Override
    public Comparable<String> getKey() {
        return name.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave da despesa pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de nova chave.
     * @throws NullObjectException Exceção lançada em caso de nome de despesa nula.
     */
    @Override
    public Comparable<String> previewKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        return key.toLowerCase();
    }

    /**
     * Método responsável por retornar valor de despesa.
     * @return Retorna valor de despesa.
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * Método responsável por duplicar despesa.
     * @return Retorna despesa duplicata.
     */
    @Override
    public Expense duplicate() {
        final Expense expense = new Expense();
        expense.name = name;
        expense.value = value;
        return expense;
    }

    /**
     * Método responsável por retornar o nome da despesa.
     * @return Retorna nome da despesa.
     */
    @Override
    public String toString() {
        return name;
    }

}