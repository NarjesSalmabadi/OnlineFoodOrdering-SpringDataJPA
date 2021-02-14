package ir.maktab.snappfood.repository.dao;

import ir.maktab.snappfood.repository.entity.RestaurantMenu;
import ir.maktab.snappfood.repository.enums.FoodType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMenuDao extends CrudRepository<RestaurantMenu, Integer> {

    @Query("select rm from RestaurantMenu rm join fetch rm.restaurant where rm.menuName=:menuName" +
            " and rm.restaurant.name=:restaurantName")
    RestaurantMenu findByMenuNameAndRestaurantName(@Param("restaurantName") String restaurantName,
                                                   @Param("menuName") FoodType menuName);

    @Query("select distinct rm from RestaurantMenu rm join fetch rm.foods where rm.restaurant.name=:name")
    public List<RestaurantMenu> findByRestaurantName(@Param("name") String restaurantName);
}
