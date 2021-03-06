package com.activitystream;

/**
 * The relation _type_ describing the nature of relation between two entities
 */
public class EntityRelationType {
    private final String aka;

    public EntityRelationType(String aka) {

        this.aka = aka;
    }

    public String value() {
        return aka;
    }

    public EntityRelationType extend(String extenstion) {
        return new EntityRelationType(aka + ":" + extenstion);
    }
}
