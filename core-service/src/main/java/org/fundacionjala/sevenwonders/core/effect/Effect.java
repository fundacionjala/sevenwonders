/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.effect;

import org.fundacionjala.sevenwonders.core.City;

/**
 * @author Diego Fiengo
 */
@FunctionalInterface
public interface Effect {
    void activate(City city);
}
