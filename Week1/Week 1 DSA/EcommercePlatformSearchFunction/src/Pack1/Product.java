package Pack1;

public class Product implements Comparable<Product> {
	
	private int productId;
	private String productName;
	private String category;
	
	public Product(int productId, String productName, String category) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category + "]";
	}

	public int compareTo(Product otherProduct) {
		return this.productName.compareToIgnoreCase(otherProduct.productName);
	}
	
	
	
}
