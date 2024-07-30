public class PaytmAdapter implements PaymentProcessor{
    private PaytmGateway paytmGateway;

    public PaytmAdapter(PaytmGateway paytmGateway){
        this.paytmGateway = paytmGateway;
    }

    public void processPayment(String paymentMethod, double amount){
        paytmGateway.paytmPayment(paymentMethod,amount);
    }
}
