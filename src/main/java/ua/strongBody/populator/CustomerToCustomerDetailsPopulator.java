package ua.strongBody.populator;

import org.springframework.stereotype.Component;
import ua.strongBody.models.Customer;
import ua.strongBody.models.CustomerDetails;

@Component
public class CustomerToCustomerDetailsPopulator implements Populator<Customer, CustomerDetails> {

    @Override
    public void convert(Customer customer, CustomerDetails customerDetails) {
        customerDetails.setUsername(customer.getUsername());
        customerDetails.setPassword(customer.getPassword());
        customerDetails.setAccountNonExpired(true);
        customerDetails.setCredentialsNonExpired(true);
        customerDetails.setEnabled(true);
        customerDetails.setRole(customer.getRole());
    }
}
