package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.dao.CustomerDao;
import ir.maktab.snappfood.repository.dto.CustomerDto;
import ir.maktab.snappfood.repository.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public Customer checkUser(String phoneNumber) {
        Customer customer = customerDao.findByPhoneNumber(phoneNumber);
        return customer;
    }

    public void addNewCustomer(Customer customer) {
        if (Objects.nonNull(customer.getFirstname()) && Objects.nonNull(customer.getLastname())
                && Objects.nonNull(customer.getPhoneNumber()) && Objects.nonNull(customer.getPostalCode())) {
            customerDao.save(customer);
        } else throw new NullPointerException("customer is Null");
    }

    public List<CustomerDto> observeCustomerReport() {
        return customerDao.observeCustomerReport();
    }
}
