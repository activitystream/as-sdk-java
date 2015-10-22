package com.activitystream.aspects;

public enum TimedStatus {
    VALID, DUE;

    @Override
    public String toString() {
        if (this == VALID) return "valid";
        if (this == DUE) return "due";
        throw new RuntimeException("unhandled action of timed action");
    }
}
