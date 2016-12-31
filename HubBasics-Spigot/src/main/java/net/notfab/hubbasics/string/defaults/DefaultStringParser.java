package net.notfab.hubbasics.string.defaults;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.notfab.hubbasics.string.IntlStringParser;
import net.notfab.hubbasics.string.defaults.components.ColoredStringComponent;
import net.notfab.hubbasics.string.defaults.components.Component;
import net.notfab.hubbasics.string.defaults.components.DynamicComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultStringParser implements IntlStringParser {
    private Map<String, Class<? extends DynamicComponent>> componentMap = new HashMap<>();

    public void registerComponent(String identifier, Class<? extends DynamicComponent> clazz) {
        if (identifier.length() < 3) {
            throw new IllegalArgumentException("Identifier too short");
        }

        this.componentMap.put(identifier, clazz);
    }

    public DefaultString parse(String str) {
        List<Component> componentList = new ArrayList<>();

        char[] chars = str.toCharArray();
        int startIndex = -1, lastIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{') {
                startIndex = i;
                i += 3;
            } else if (chars[i] == '}') {
                if (startIndex == -1) {
                    continue;
                }

                componentList.add(new ColoredStringComponent(str.substring(lastIndex, startIndex)));
                lastIndex = i + 1;

                String rawComponent = str.substring(startIndex, i + 1);
                String identifier = parseComponentIdentifier(rawComponent);

                Property[] properties = parseProperties(rawComponent);
                DynamicComponent component = parseComponent(identifier, properties);
                if (component == null) {
                    throw new IllegalArgumentException("An error occurred while parsing " + identifier);
                }

                componentList.add(component);
            }
        }

        componentList.add(new ColoredStringComponent(str.substring(lastIndex, str.length())));
        return new DefaultString(componentList.toArray(new Component[componentList.size()]));
    }

    private DynamicComponent parseComponent(String identifier, Property[] properties) {
        if (!componentMap.containsKey(identifier)) {
            return null;
        }

        try {
            Class<? extends DynamicComponent> componentClass = componentMap.get(identifier);
            Constructor<? extends DynamicComponent> componentConstructor = componentClass.getConstructor(Property[].class);
            return componentConstructor.newInstance(new Object[]{properties});
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Property[] parseProperties(String str) {
        int startIndex = str.indexOf('[');
        int endIndex = str.indexOf(']');
        if (startIndex == -1 && endIndex == -1) {
            return new Property[0];
        }

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Missing '[' or ']'");
        }

        String[] strings = str.substring(startIndex + 1, endIndex).split(", ");
        Property[] properties = new Property[strings.length];
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            int equalsIndex = string.indexOf('=');
            properties[i] = new Property(string.substring(0, equalsIndex), string.substring(equalsIndex + 1));
        }

        return properties;
    }

    private String parseComponentIdentifier(String expression) {
        int endIndex = expression.indexOf('[');
        if (endIndex == -1) {
            endIndex = expression.length() - 1;
        }

        return expression.substring(1, endIndex);
    }
}
