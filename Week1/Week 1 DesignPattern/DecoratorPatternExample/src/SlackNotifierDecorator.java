public class SlackNotifierDecorator extends NotifierDecorator{
    public SlackNotifierDecorator(Notifier notifier){
        super(notifier);
    }

    public void send(String message){
        super.send(message);
        System.out.println("Slack notification : "+message);
    }
}
