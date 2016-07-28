/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.ArrayList;
import java.util.List;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author alexander castro
 */
public class WonderTest {
    
    private Wonder wonder;
    
    @Test
    public void testGetBuildingsStages() {
        Effect effect = new VictoryPointEffect(4, CalculatorType.WONDER);
        List<Effect> effects = new ArrayList<>();
        effects.add(effect);
        Stage stage1 = new Stage(new ArrayList<Requirement>(), effects);
        stage1.setBuildState(true);
        Stage stage2 = new Stage(new ArrayList<Requirement>(), effects);
        stage2.setBuildState(false);
        Stage stage3 = new Stage(new ArrayList<Requirement>(), effects);
        stage3.setBuildState(false);
        Stage stage4 = new Stage(new ArrayList<Requirement>(), effects);
        stage4.setBuildState(true);
        List<Stage> stages = new ArrayList();
        stages.add(stage1);
        stages.add(stage2);
        stages.add(stage3);
        stages.add(stage4);
        
        wonder = new Wonder(stages);
        
        List<Stage> result = wonder.getBuildingsStages();
        
        assertEquals(2, result.size());
        assertEquals(stage1, result.get(0));
        assertEquals(stage4, result.get(1));
    }
    
    @Test(expected = NullPointerException.class)
    public void testIfListStageIsNull() {
        wonder = new Wonder(null);
        List<Stage> result = wonder.getBuildingsStages();
    }
}
