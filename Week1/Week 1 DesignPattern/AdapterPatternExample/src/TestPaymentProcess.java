public class TestPaymentProcess {
    public static void main(String[] args) {
        PhonePeGateway phonePeGateway = new PhonePeGateway();
        PaytmGateway paytmGateway = new PaytmGateway();


        PaymentProcessor phonepeAdapter = new PhonePeAdapter(phonePeGateway);
        PaymentProcessor paytmAdapter = new PaytmAdapter(paytmGateway);


        phonepeAdapter.processPayment("Credit Card", 1580); ;
        paytmAdapter.processPayment("Debit Card", 1890);

    }
}
