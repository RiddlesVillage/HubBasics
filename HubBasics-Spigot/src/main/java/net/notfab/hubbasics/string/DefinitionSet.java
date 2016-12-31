package net.notfab.hubbasics.string;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.HashSet;
import java.util.Set;

public class DefinitionSet {
    private Set<Definition<?>> definitionSet;

    public DefinitionSet() {
        this.definitionSet = new HashSet<>();
    }

    public DefinitionSet addDefinition(Definition<?> definition) {
        this.definitionSet.add(definition);
        return this;
    }

    public <T> Definition<T> getDefinition(String path) {
        return (Definition<T>) this.definitionSet.parallelStream().filter(def -> def.getPath().equals(path)).findFirst().orElse(null);
    }

    public boolean isDefined(String path) {
        return this.definitionSet.parallelStream().filter(def -> def.getPath().equals(path)).findFirst().isPresent();
    }
}
