public interface PaymentProcessor {
    default void processPayment(String paymentMethod, double amount){};
}
