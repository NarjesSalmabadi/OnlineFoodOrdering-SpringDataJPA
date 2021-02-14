package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.dao.RestaurantDao;
import ir.maktab.snappfood.repository.dto.RestaurantDto;
import ir.maktab.snappfood.repository.entity.Restaurant;
import ir.maktab.snappfood.repository.enums.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    @Autowired
    RestaurantDao restaurantDao;

    public boolean addNewRestaurant(Restaurant restaurant) {
        if (Objects.nonNull(restaurant.getName()) && restaurant.getName().length()>0){
            if(Objects.isNull(restaurantDao.findByName(restaurant.getName()))){
                restaurantDao.save(restaurant);
                return true;
            }
        }
        return false;
    }

    public Restaurant findByName(String restaurantName){
        return  restaurantDao.findByName(restaurantName);
    }

    public List<Restaurant> findByRegion(int region) {
        return restaurantDao.findByRegion(region);
    }

    public List<Restaurant> findByRegionAndFoodType(int region, FoodType foodType) {
        return restaurantDao.findByRegionAndFoodType(region, foodType);
    }

    public List<RestaurantDto> observeRestaurantReport() {
        return restaurantDao.observeRestaurantReport();
    }
}
