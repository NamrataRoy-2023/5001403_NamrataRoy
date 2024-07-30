public class TestingInventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        Product p1 = new Product("1", "Product A", 10, 100.0);
        Product p2 = new Product("2", "Product B", 5, 200.0);

        inventory.addProduct(p1);
        inventory.addProduct(p2);

        inventory.displayAllProducts();

        Product p3 = new Product("2", "Product B Updated", 40, 150.0);
        inventory.updateProduct(p3);

        inventory.displayAllProducts();

        inventory.deleteProduct("1");

        inventory.displayAllProducts();
    }
}
