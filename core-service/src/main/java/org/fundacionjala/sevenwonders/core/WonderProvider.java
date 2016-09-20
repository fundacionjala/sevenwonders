package org.fundacionjala.sevenwonders.core;


import org.fundacionjala.sevenwonders.core.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dwits on 04/08/2016.
 */
public class WonderProvider {

    private List<Wonder> wonders;

    public List<Wonder> getDefault() {
        wonders = new ArrayList<>();
        wonders.add(new Wonder("The Sphinx"));
        wonders.add(new Wonder("The Statue of Zeus"));
        wonders.add(new Wonder("The Great Library"));
        wonders.add(new Wonder("The Mausoleum"));
        wonders.add(new Wonder("Babylon"));
        wonders.add(new Wonder("Ephesos"));
        wonders.add(new Wonder("Rodhos"));
        return wonders;
    }
}
