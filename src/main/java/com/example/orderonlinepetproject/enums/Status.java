package com.example.orderonlinepetproject.enums;
public enum Status {
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(Status newStatus) {
        return switch (this) {
            case PROCESSING -> newStatus == SHIPPED || newStatus == CANCELLED;
            case SHIPPED -> newStatus == DELIVERED || newStatus == CANCELLED;
            case DELIVERED -> false;
            case CANCELLED -> false;
            default -> false;
        };
    }
}
