package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.dao.RestaurantMenuDao;
import ir.maktab.snappfood.repository.entity.Food;
import ir.maktab.snappfood.repository.entity.RestaurantMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantMenuService {

    @Autowired
    RestaurantMenuDao restaurantMenuDao;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    FoodService foodService;

    public boolean addNewRestaurantMenu(String restaurantName, RestaurantMenu restaurantMenu) {
        if (Objects.nonNull(restaurantName) && restaurantName.length() > 0) {
            if (Objects.nonNull(restaurantMenu.getMenuName())) {
                if (Objects.nonNull(restaurantService.findByName(restaurantName))) {
                    if (Objects.isNull(restaurantMenuDao.findByMenuNameAndRestaurantName(restaurantName, restaurantMenu.getMenuName()))) {
                        restaurantMenu.setRestaurant(restaurantService.findByName(restaurantName));
                        restaurantMenuDao.save(restaurantMenu);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean addNewFoodToRestaurantMenu(String restaurantName, RestaurantMenu restaurantMenu, Food food, Integer count) {
        if (Objects.nonNull(restaurantName) && restaurantName.length() > 0) {
            if (Objects.nonNull(restaurantMenu.getMenuName()) && Objects.nonNull(food.getName())) {
                if (Objects.nonNull(restaurantMenuDao.findByMenuNameAndRestaurantName(restaurantName, restaurantMenu.getMenuName()))) {
                    if (Objects.nonNull(foodService.findByNameAndPrice(food))) {
                        restaurantMenu = restaurantMenuDao.findByMenuNameAndRestaurantName(restaurantName, restaurantMenu.getMenuName());
                        restaurantMenu.getFoods().put(foodService.findByNameAndPrice(food), count);
                        restaurantMenuDao.save(restaurantMenu);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<RestaurantMenu> seeMenuOfRestaurant(String nameOfRest) {
        return restaurantMenuDao.findByRestaurantName(nameOfRest);
    }

    public void update(RestaurantMenu restaurantmenu) {
        restaurantMenuDao.save(restaurantmenu);
    }
}
