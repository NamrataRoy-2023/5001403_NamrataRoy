package Pack1;
import java.util.Scanner;

public class EcommercePlatformSearchFunction {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Product[] products = {
			new Product(1, "A", "A1"),
			new Product(2, "B", "B2"),
			new Product(3, "C", "C3"),
			new Product(4, "D", "D4"),
			new Product(5, "E", "E5")
		};
		
		LinearSearch ls = new LinearSearch(products, "B");
		Product searchResult = ls.search();
		System.out.print("Linear Search : Product found - "+searchResult);
		
		
		BinarySearch bs = new BinarySearch(products, "B");
		Product searchResult1 = bs.search();
		System.out.print("\nBinary Search : Product found - "+searchResult1);
		
		
	}
}
