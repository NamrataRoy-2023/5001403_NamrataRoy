package Pack1;

import java.util.Arrays;

abstract class Searching {
	protected Product[] products;
	protected String productName;
	
	
	public Searching(Product[] products, String productName) {
		this.products = products;
		this.productName = productName;
	}


	public abstract Product search();
}

class LinearSearch extends Searching {
	public LinearSearch(Product[] products, String productName) {
		super(products, productName);
	}

	@Override
	public Product search() {
		for(Product pItem : products) {
			if(pItem.getProductName().equalsIgnoreCase(productName)) {
				return pItem;
			}
		}
		return null;
	}
}

class BinarySearch extends Searching{

	public BinarySearch(Product[] products, String productName) {
		super(products, productName);
		Arrays.sort(this.products);
	}

	@Override
	public Product search() {
		int lower = 0;
		int upper = products.length - 1;
		
		
		while(lower <= upper) {
			int mid = (lower + upper)/2;
			int result = products[mid].getProductName().compareToIgnoreCase(productName);
			
			if(result == 0) {
				return products[mid];
			}else if(result < 0) {
				lower = mid+1; 
			}else {
				upper = mid-1;
			}
		}
		return null;
	}
}