/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.calculator;

/**
 * @author Alexander Castro
 * @author Diego Fiengo
 */
@FunctionalInterface
public interface Calculable<T> {

    /**
     * Calculates a specific object like a wonder or a card.
     *
     * @param t The object.
     */
    void calculate(T t);
}
