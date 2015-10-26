package com.activitystream.aspects;

public enum TimedStatus {
    VALID, DUE, SCHEDULE, PERIOD;

    @Override
    public String toString() {
        if (this == VALID) return "valid";
        if (this == DUE) return "due";
        if (this == SCHEDULE) return "schedule";
        if (this == PERIOD) return "period";
        throw new RuntimeException("unhandled action of timed action");
    }
}
