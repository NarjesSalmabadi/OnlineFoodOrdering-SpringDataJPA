package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.dao.AdminDao;
import ir.maktab.snappfood.repository.dto.CustomerDto;
import ir.maktab.snappfood.repository.dto.RestaurantDto;
import ir.maktab.snappfood.repository.entity.Admin;
import ir.maktab.snappfood.repository.entity.Food;
import ir.maktab.snappfood.repository.entity.Restaurant;
import ir.maktab.snappfood.repository.entity.RestaurantMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    FoodService foodService;
    @Autowired
    RestaurantMenuService restaurantMenuService;
    @Autowired
    CustomerService customerService;

    public boolean login() {
        System.out.println("Enter Your Username:");
        String username = getUserPassFromAdmin();
        System.out.println("Enter Your Password:");
        String password = getUserPassFromAdmin();
        return verifyAdmin(username, password);
    }

    public String getUserPassFromAdmin() {
        String input;
        Scanner scan = new Scanner(System.in);
        while (true) {
            input = scan.next();
            if (input.length() >= 5) {
                return input;
            } else {
                System.out.println("Your Input is Invalid. The length is must be at least 5!");
            }
        }
    }

    public boolean verifyAdmin(String username, String password) {
        return adminDao.findByUsernameAndPassword(username, password).isPresent();
    }

    public void addNewAdmin(Admin admin) {
        adminDao.save(admin);
    }

    public void editAdmin(Admin admin) {

    }

    public boolean addNewRestaurant(Restaurant restaurant) {
        return restaurantService.addNewRestaurant(restaurant);
    }

    public boolean addNewFood(Food food) {
        return foodService.addNewFood(food);
    }

    public boolean addNewRestaurantMenu(String restaurantName, RestaurantMenu restaurantMenu) {
        return restaurantMenuService.addNewRestaurantMenu(restaurantName, restaurantMenu);
    }

    public boolean addNewFoodToRestaurantMenu(String restaurantName, RestaurantMenu restaurantMenu, Food food, Integer count) {
        return restaurantMenuService.addNewFoodToRestaurantMenu(restaurantName, restaurantMenu, food, count);
    }

    public void observeCustomerReport() {
        List<CustomerDto> customerReport = customerService.observeCustomerReport();
        final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int i : array) {
            customerReport.stream().filter(u -> u.getRegistrationDate().getMonth() + 1 == i).filter(u -> u.getTotalPurchase() < 100000)
                    .forEach(u -> System.out.println((u.getRegistrationDate().getMonth() + 1) + " " + "<100 " + u.getFirstName() + " " + u.getLastName() +
                            " " + u.getPhoneNumber()));
            customerReport.stream().filter(u -> u.getRegistrationDate().getMonth() + 1 == i).filter(u -> u.getTotalPurchase() > 100000.0 && u.getTotalPurchase() < 500000.0)
                    .forEach(u -> System.out.println((u.getRegistrationDate().getMonth() + 1) + " " + "100< <500 " + u.getFirstName() +
                            " " + u.getLastName() + " " + u.getPhoneNumber()));
            customerReport.stream().filter(u -> u.getRegistrationDate().getMonth() + 1 == i).filter(u -> u.getTotalPurchase() > 500000)
                    .forEach(u -> System.out.println((u.getRegistrationDate().getMonth() + 1) + " " + ">500 " + u.getFirstName() + " " + u.getLastName() +
                            " " + u.getPhoneNumber()));
        }
    }

    public void observeRestaurantReport() {
        List<RestaurantDto> restaurantReport = restaurantService.observeRestaurantReport();
        restaurantReport.stream().forEach(System.out::println);
        final int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : array2) {
            restaurantReport.stream().filter(r -> r.getRegion().equals(i)).filter(r -> r.getTotalService() <= 5000)
                    .forEach(r -> System.out.println(r.getRegion() + " =<5000 " + r.getNameOfRest() + " " + r.getFood().getName()));
            restaurantReport.stream().filter(r -> r.getRegion().equals(i)).filter(r -> r.getTotalService() > 5000 && r.getTotalService() < 8000)
                    .forEach(r -> System.out.println(r.getRegion() + " 5000<<8000 " + r.getNameOfRest() + " " + r.getFood().getName()));
            restaurantReport.stream().filter(r -> r.getRegion().equals(i)).filter(r -> r.getTotalService() > 5000 && r.getTotalService() >= 8000)
                    .forEach(r -> System.out.println(r.getRegion() + " >=8000 " + r.getNameOfRest() + " " + r.getFood().getName()));
        }
    }
}
