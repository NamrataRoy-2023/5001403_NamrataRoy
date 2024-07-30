package pack1;

public class Order {
	private int orderId;
	private String customerName;
	private int totalPrice;
	
	
	public Order(int orderId, String customerName, int totalPrice) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.totalPrice = totalPrice;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerName=" + customerName + ", totalPrice=" + totalPrice + "]";
	}
}
