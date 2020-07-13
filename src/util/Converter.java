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

import exceptions.DoubleValueInvalidException;
import exceptions.IntegerValueInvalidException;
import exceptions.NullObjectException;
import java.text.DecimalFormat;
import model.business.IBusinessVisible;
import model.offers.IOfferVisible;
import model.organizations.IOrganizationVisible;

/**
 * Classe responsável por comportar-se como um conversor.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Converter {

    /**
     * Método responsável por converter para valor decimal, eventuais caracteres.
     * @param value Refere-se aos eventuais caracteres.
     * @return Retorna valor decimal.
     * @throws NullObjectException         Exceção lançada em caso de string nula.
     * @throws DoubleValueInvalidException Exceção lançada em caso de valor decimal inválido.
     */
    public static double toDouble(final String value) throws NullObjectException, DoubleValueInvalidException {
        Filter.invalidValueDouble(value);
        return Double.parseDouble(value.replace(',', '.'));
    }

    /**
     * Método responsável por converter para caractere, eventuais valores decimais.
     * @param value Refere-se aos eventuais valores valores decimais.
     * @return Retorna caractere.
     */
    public static String toString(final double value) {
        final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(value);
    }

    /**
     * Método responsável por converter para positivo, eventuais valores negativos.
     * @param value Refere-se ao eventual valor negativo.
     * @return Retorna valor positivo.
     */
    public static double toPositive(double value) {
        if (value < 0) {
            value *= -1;
        }
        return value;
    }

    /**
     * Método responsável por converter para negativo, eventuais valores positivos.
     * @param value Refere-se ao eventual valor positivo.
     * @return Retorna valor negativo.
     */
    public static double toNegative(double value) {
        if (value > 0) {
            value *= -1;
        }
        return value;
    }

    /**
     * Método responsável por converter para valor inteiro, eventuais caracteres.
     * @param value Refere-se aos eventuais caracteres.
     * @return Retorna valor inteiro.
     * @throws NullObjectException          Exceção lançada em caso de string nula.
     * @throws IntegerValueInvalidException Exceção lançada em caso de valor inteiro inválido.
     */
    public static int toInteger(final String value) throws NullObjectException, IntegerValueInvalidException {
        Filter.invalidValueInteger(value);
        return Integer.parseInt(value);
    }

    /**
     * Método responsável por converter para caractere, eventuais valores inteiros.
     * @param value Refere-se aos eventuais valores valores inteiros.
     * @return Retorna caractere.
     */
    public static String toString(final int value) {
        return Integer.toString(value);
    }

    /**
     * Método responsável por converter para positivo, eventuais valores negativos.
     * @param value Refere-se ao eventual valor negativo.
     * @return Retorna valor positivo.
     */
    public static int toPositive(int value) {
        if (value < 0) {
            value *= -1;
        }
        return value;
    }

    /**
     * Método responsável por converter para negativo, eventuais valores positivos.
     * @param value Refere-se ao eventual valor positivo.
     * @return Retorna valor negativo.
     */
    public static int toNegative(int value) {
        if (value > 0) {
            value *= -1;
        }
        return value;
    }

    /**
     * Método responsável por converter para ao nome de extensão indicado, eventuais caminhos de arquivo.
     * @param pathFile      Refere-se ao eventual caminho de arquivo sem o nome de extensão.
     * @param extensionName Refere-se ao novo nome de extensão.
     * @return Retorna caminho de arquivo com nome de extensão.
     */
    public static String toExtensionName(final String pathFile, final String extensionName) {
        if (extensionName != null && pathFile != null) {
            if (!pathFile.contains(extensionName)) {
                return pathFile + extensionName;
            }
        }
        return pathFile;
    }

    /**
     * Método responsável por converter uma dada organização em vetor de objetos.
     * @param organization Refere-se a organização.
     * @return Retorna vetor de objetos.
     */
    public static Object[] toVector(final IOrganizationVisible organization) {
        final Object[] vector = new Object[4];
        vector[0] = organization;
        vector[1] = organization.getStreet();
        vector[2] = organization.getNeighborhood();
        vector[3] = organization.getCity();
        return vector;
    }

    /**
     * Método responsável por converter uma dada oferta em vetor de objetos.
     * @param offer Refere-se a oferta.
     * @return Retorna vetor de objetos.
     */
    public static Object[] toVector(final IOfferVisible offer) {
        final Object[] vector = new Object[2];
        vector[0] = offer;
        vector[1] = Converter.toString(offer.getValue());
        return vector;
    }

    /**
     * Método responsável por converter um dado negócio em vetor de objetos.
     * @param business Refere-se ao negócio.
     * @return Retorna vetor de objetos.
     */
    public static Object[] toVector(final IBusinessVisible business) {
        final Object[] vector = new Object[6];
        vector[0] = business;
        vector[1] = business.getProvider();
        vector[2] = business.getAmount();
        vector[3] = Converter.toString(business.getOffer().getValue());
        vector[4] = Converter.toString(business.getTotalValue());
        vector[5] = business.getDate();
        return vector;
    }

}