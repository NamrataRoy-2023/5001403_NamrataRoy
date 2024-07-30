public class SMSNotifierDecorator extends NotifierDecorator{
    public SMSNotifierDecorator(Notifier notifier){
        super(notifier);
    }

    public void send(String message){
        super.send(message);
        System.out.println("SMS notification : "+message);
    }
}
