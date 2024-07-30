public class PhonePeAdapter implements PaymentProcessor{
    private PhonePeGateway phonePeGateway;

    public PhonePeAdapter(PhonePeGateway phonePeGateway){
        this.phonePeGateway = phonePeGateway;
    }

    public void processPayment(String paymentMethod, double amount){
        phonePeGateway.phonePePayment(paymentMethod,amount);
    }
}
