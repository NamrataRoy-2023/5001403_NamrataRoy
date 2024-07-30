public class LoggerTesting {
    public static void main(String[] args) {
        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();

        System.out.println("Are instance l1 and l2 are same ? : "+(l1==l2));

        l1.message("We are trying to implement Singleton Design Pattern");
        l2.message("Using this class we are testing the Logger Class");
    }
}
