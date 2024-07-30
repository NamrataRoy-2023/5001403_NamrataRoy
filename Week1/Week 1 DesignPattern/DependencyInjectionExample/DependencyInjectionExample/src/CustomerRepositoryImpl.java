public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(int id) {
        return new Customer(id, "Namrata Roy", "roynamrata54@gmail.com");
    }

}