package reflection.generic;

import entity.Customer;

import java.sql.SQLException;

public class DemoGeneric {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        MasterModel<Customer> model = new MasterModel<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Tan");
        customer.setEmail("vntan1109@gmail.com");
        customer.setBalance(100000);
        model.save(customer);
    }
}
