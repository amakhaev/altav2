package com.alta_v2.rendering.utils;

import com.badlogic.gdx.graphics.Color;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class GradientResource {

    public Map<Gradient, List<Color>> gradients = new HashMap<Gradient, List<Color>>() {
        {
            put(Gradient.ORANGE, Lists.newArrayList(new Color(0xFF7400AA), new Color(0xA64B00BB), new Color(0xA64B00AA), new Color(0xFF9640BB)));
        }
    };

    public enum Gradient {
        ORANGE
    }

}
