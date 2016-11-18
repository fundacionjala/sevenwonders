/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Vania Catorceno on 6/2/2016.
 */
public class Wonder {
    private String name;
    private Map<String, Side> sides;
    private boolean isSequencial;

    public Wonder(String name, Map<String, Side> sides) {
        this.name = name;
        this.sides = sides;
    }
    public Wonder(String name){
        this.name = name;
    }

    public Side getSide(String key){
        return sides.get(key);
    }

    public String getName() {
        return name;
    }
}
