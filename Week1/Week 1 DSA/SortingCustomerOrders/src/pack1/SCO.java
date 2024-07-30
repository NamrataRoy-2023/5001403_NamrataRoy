package pack1;
import java.util.Scanner;

public class SCO {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Order[] orders = {
                new Order(1, "Anand", 120),
                new Order(2, "Bimal", 600),
                new Order(3, "Chandan", 1000),
                new Order(4, "Divya", 90),
                new Order(5, "Emily", 170),
                new Order(6, "Firdous", 150)
        };

        Order[] ordersForBubbleSort = orders.clone(); // Cloning array for Bubble Sort
        Order[] ordersForQuickSort = orders.clone(); // Cloning array for Quick Sort

        Sorting bsort = new BubbleSort();
        bsort.sort(ordersForBubbleSort);

        System.out.println("Orders sorted using Bubble Sort:");
        for (Order order : ordersForBubbleSort) {
            System.out.println(order);
        }

        Sorting qsort = new QuickSort();
        qsort.sort(ordersForQuickSort);

        System.out.println("\nOrders sorted using Quick Sort:");
        for (Order order : ordersForQuickSort) {
            System.out.println(order);
        }
    }

}
