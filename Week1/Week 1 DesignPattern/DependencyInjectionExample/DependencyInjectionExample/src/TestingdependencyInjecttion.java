public class TestingdependencyInjecttion {
    public static void main(String[] args) {
        // Create a CustomerRepositoryImpl instance
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Create a CustomerService instance with the CustomerRepositoryImpl instance
        CustomerService customerService = new CustomerService(customerRepository);

        // Use the CustomerService to find a customer
        int customerId = 1;
        Customer customer = customerService.findCustomerById(customerId);

        System.out.println("Customer found:");
        System.out.println("ID: " + customer.getId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
    }
}
