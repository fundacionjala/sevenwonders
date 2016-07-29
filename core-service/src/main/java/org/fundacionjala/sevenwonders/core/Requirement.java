/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import java.util.Map;

/**
 * @author Vania Catorceno
 */
public interface Requirement {
    Map<ResourceType, Integer> getRequirements();
    boolean validate(City city);
}
