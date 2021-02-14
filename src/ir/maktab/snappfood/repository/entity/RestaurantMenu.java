package ir.maktab.snappfood.repository.entity;

import ir.maktab.snappfood.repository.enums.FoodType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private FoodType menuName;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Restaurant restaurant;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "count")
    private Map<Food, Integer> foods = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FoodType getMenuName() {
        return menuName;
    }

    public void setMenuName(FoodType menuName) {
        this.menuName = menuName;
    }

    public Map<Food, Integer> getFoods() {
        return foods;
    }

    public void setFoods(Map<Food, Integer> foods) {
        this.foods = foods;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
