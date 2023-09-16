package com.cobelpvp.hub.util;

import org.bukkit.Color;

import java.lang.reflect.Field;

public class PlayerUtilities {

    public static Color stringToColor(final String value) {
        if (value == null) {
            return Color.BLACK;
        }
        // if we can't decode lets try to get it by name
        try {
            // try to get a color by name using reflection
            final Field f = Color.class.getField(value);

            return (Color) f.get(null);
        } catch (Exception ce) {
            // if we can't get any color return black
            return Color.BLACK;
        }
    }


}
