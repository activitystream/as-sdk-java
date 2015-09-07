package com.activitystream;

public class LinkType {
    private String aka;

    public LinkType(String aka) {

        this.aka = aka;
    }

    public static LinkType AKA() {return new LinkType("AKA");}
}
