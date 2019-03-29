package cz.ucl.eshop.model.types;

public enum OrderStatus {
    // PROCESSING = before filling contact details
    // PENDING_PAYMENT = before filling payment details
    // CONFIRMED = order is paid
    PROCESSING, PENDING_PAYMENT, CONFIRMED
}
