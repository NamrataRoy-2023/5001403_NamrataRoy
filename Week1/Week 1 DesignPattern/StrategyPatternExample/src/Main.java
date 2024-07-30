//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        PaymentContext context = new PaymentContext();

        // Pay using Credit Card
        PaymentStrategy creditCardPayment = new CreditCardPayment("Namrata Roy", "1234567890123456", "726", "10/33");
        context.setPaymentStrategy(creditCardPayment);
        context.pay(1000.00);

        // Pay using PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("roynamrata@gmail.com", "my1234");
        context.setPaymentStrategy(payPalPayment);
        context.pay(2050.00);
    }
}