package ir.maktab.snappfood.repository.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int region;
    private int shippingCost;
    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantMenu> menus = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Set<RestaurantMenu> getMenus() {
        return menus;
    }

    public void setMenus(Set<RestaurantMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", region=" + region +
                ", shippingCost=" + shippingCost +
                '}';
    }
}
