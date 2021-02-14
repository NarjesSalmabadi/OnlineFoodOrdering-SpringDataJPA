package ir.maktab.snappfood.repository.dao;

import ir.maktab.snappfood.repository.dto.RestaurantDto;
import ir.maktab.snappfood.repository.entity.Restaurant;
import ir.maktab.snappfood.repository.enums.FoodType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {

    Restaurant findByName(String restaurantName);

    List<Restaurant> findByRegion(int region);

    @Query("select r from Restaurant r join fetch RestaurantMenu rm on r.id=rm.restaurant.id " +
            "where r.region=:region and rm.menuName=:name")
    List<Restaurant> findByRegionAndFoodType(@Param("region") int region, @Param("name") FoodType foodType);

    @Query("select new ir.maktab.snappfood.repository.dto.RestaurantDto (res.name, res.region, (count(distinct r.factorNumber)*res.shippingCost), KEY(map), " +
            "max(VALUE(map) )) from Restaurant res join Reservation r on res.id=r.restaurant.id join r.orderedFoods map group by res.name")
    List<RestaurantDto> observeRestaurantReport();
}
