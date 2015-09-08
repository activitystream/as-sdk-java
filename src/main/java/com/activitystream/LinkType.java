package com.activitystream;

public class LinkType {
    private final String aka;

    public LinkType(String aka) {

        this.aka = aka;
    }

    public String toJson(){
        return aka;
    }

    public static LinkType AKA() {return new LinkType("AKA");}
}
