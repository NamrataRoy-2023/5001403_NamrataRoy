public class WebApp implements Observer {
    private String name;

    public WebApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println(name + " received a new update on web app: Stock " + stockName + " is now " + stockPrice);
    }
}