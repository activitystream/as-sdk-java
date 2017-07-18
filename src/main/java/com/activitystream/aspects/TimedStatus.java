package com.activitystream.aspects;

public enum TimedStatus {
    VALID, DUE, SCHEDULE, PERIOD, ONSALE, DOOR, DURATION, AVAILABLE, BEGINS, ACTIVE;

    @Override
    public String toString() {
        if (this == VALID) return "valid";
        if (this == DUE) return "due";
        if (this == SCHEDULE) return "schedule";
        if (this == PERIOD) return "period";
        if (this == ONSALE) return "onsale";
        if (this == DOOR) return "door";
        if (this == DURATION) return "duration";
        if (this == AVAILABLE) return "available";
        if (this == BEGINS) return "begins";
        if (this == ACTIVE) return "active";
        throw new RuntimeException("unhandled action of timed action");
    }
}
