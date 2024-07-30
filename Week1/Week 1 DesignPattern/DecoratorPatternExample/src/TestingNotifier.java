public class TestingNotifier {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();

        // Decorator with SMS and Slack notifier

        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        slackNotifier.send("This is a notifier....");
    }
}
