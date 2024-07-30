//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
            Image image1 = new ProxyImage("image1.jpg");
            Image image2 = new ProxyImage("image2.jpg");

            // Image will be loaded from remote server
            image1.display();
            System.out.println("");

            // Image will not be loaded from remote server, it will use the cached one
            image1.display();
            System.out.println("");

            // Image will be loaded from remote server
            image2.display();
            System.out.println("");

            // Image will not be loaded from remote server, it will use the cached one
            image2.display();
        }

}