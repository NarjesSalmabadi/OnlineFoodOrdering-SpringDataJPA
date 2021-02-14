package ir.maktab.snappfood.repository.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer factorNumber;
    @CreationTimestamp
    private Date registeredDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Restaurant restaurant;
    private Integer totalCost;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "count")
    private Map<Food, Integer> orderedFoods = new HashMap<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(Integer factorNumber) {
        this.factorNumber = factorNumber;
    }

    //@Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Map<Food, Integer> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(Map<Food, Integer> orderedFoods) {
        this.orderedFoods = orderedFoods;
    }
}
