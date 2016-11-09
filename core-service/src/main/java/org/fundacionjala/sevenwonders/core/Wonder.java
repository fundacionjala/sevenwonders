/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Vania Catorceno on 6/2/2016.
 */
public class Wonder {
    private Side sideA;
    private Side sideB;
    private String name;

    public Wonder(String name) {
        this.name = name;
    }

    public Wonder(String name, Side sideA, Side sideB) {
        Preconditions.checkNotNull(name, "The stages is null");
        Preconditions.checkNotNull(sideA, "The stages is null");
        Preconditions.checkNotNull(sideB, "The stages is null");
        this.name = name;
        this.sideA = sideA;
        this.sideB = sideB;

    }

    public String getName() {
        return name;
    }

    public Side getSideA() {
        return sideA;
    }

    public Side getSideB() {
        return sideB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wonder)) return false;
        Wonder wonder = (Wonder) o;
        return Objects.equals(getName(), wonder.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
