package pack1;

public abstract class Sorting {
	public abstract Order[] sort(Order[] orders);
}


class BubbleSort extends Sorting{

	@Override
	public Order[] sort(Order[] orders) {
		int index = orders.length - 1;
		
		for(int i=0; i<index; i++) {
			for(int j=0; j<index - i; j++) {
				if(orders[j].getTotalPrice() > orders[j+1].getTotalPrice()) {
					Order temp = orders[j];
					orders[j] = orders[j+1];
					orders[j+1] = temp;
				}
			}
		}
		return orders;
	}
	
}


class QuickSort extends Sorting{

	@Override
	public Order[] sort(Order[] orders) {
		quickSort(orders, 0, orders.length - 1);
		return orders;
	}
	
	public void quickSort(Order[] orders, int l, int u) {
		if(l<u) {
			int mid = partition(orders, l, u);
			quickSort(orders, l, mid-1);
			quickSort(orders, mid+1, u);
		}
	}
	
	public int partition(Order[] orders, int l, int u) {
		int pivot = orders[u].getTotalPrice();
		int i = l - 1;
		for(int j=l; j<u; j++) {
			if(orders[j].getTotalPrice() <= pivot) {
				i++;
				Order temp = orders[i];
				orders[i] = orders[j];
				orders[j] = temp;
			}
		}
		Order temp = orders[i+1];
		orders[i+1] = orders[u];
		orders[u] = temp;
		return i+1;
	}
}