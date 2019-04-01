package cz.ucl.eshop.model.types;

public enum OrderStatus {
    // NEW = before filling contact details
    // PENDING_PAYMENT = before filling payment details
    // CONFIRMED = order is paid
    NEW, PENDING_PAYMENT, CONFIRMED
}
