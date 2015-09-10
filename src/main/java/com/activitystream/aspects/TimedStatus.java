package com.activitystream.aspects;

public enum TimedStatus {
    VALID;

    @Override
    public String toString() {
        if (this == VALID) return "valid";
        throw new RuntimeException("unhandled type of timed type");
    }
}
