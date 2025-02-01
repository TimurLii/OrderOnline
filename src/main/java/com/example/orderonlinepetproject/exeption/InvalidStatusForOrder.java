package com.example.orderonlinepetproject.exeption;

public class InvalidStatusForOrder extends RuntimeException {
    public InvalidStatusForOrder(String message) {
        super(message);
    }
}
