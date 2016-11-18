/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
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

    public Wonder(String name) {
        Preconditions.checkNotNull(name, "The name is null");
        this.name = name;
        sides = new HashMap<String, Side>();
    }

    public Side getSide(String key){
        return sides.get(key);
    }

    public String getName() {
        return name;
    }

    public void setSide(String nameSide, Side side){
        Preconditions.checkNotNull(nameSide, "The name side is null");
        Preconditions.checkNotNull(side, "The side is null");
        sides.put(nameSide, side);
    }
}
