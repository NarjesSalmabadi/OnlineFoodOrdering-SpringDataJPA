package ir.maktab.snappfood.repository.dao;

import ir.maktab.snappfood.repository.dto.CustomerDto;
import ir.maktab.snappfood.repository.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

    Customer findByPhoneNumber(String phoneNumber);

    @Query("select new ir.maktab.snappfood.repository.dto.CustomerDto (c.firstname, c.lastname, c.phoneNumber, c.registrationDate" +
            ", sum(r.totalCost)) from Customer c ,Reservation r where r.customer.id=c.id group by c.phoneNumber")
    List<CustomerDto> observeCustomerReport() ;
}
