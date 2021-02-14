package ir.maktab.snappfood.view;

import ir.maktab.snappfood.repository.entity.Admin;
import ir.maktab.snappfood.repository.entity.Food;
import ir.maktab.snappfood.repository.entity.Restaurant;
import ir.maktab.snappfood.repository.entity.RestaurantMenu;
import ir.maktab.snappfood.repository.enums.FoodType;
import ir.maktab.snappfood.service.AdminService;
import ir.maktab.snappfood.service.GetInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminView {

    @Autowired
    AdminService adminService;

    public void adminLogin() {
        outer1:
        while (true) {
            System.out.println("1-Log In");
            System.out.println("2-Sign In");
            System.out.println("3-Back");
            int input = GetInput.getIntegerInInterval(1, 3);
            switch (input) {
                case 1:
                    boolean adminExist = adminService.login();
                    if (!adminExist) {
                        System.out.println("Your username or password is wrong!");
                        continue outer1;
                    } else {
                        System.out.println("You are Logged in Successfully!");
                        adminMenu();
                        break outer1;
                    }
                case 2:
                    Admin admin = new Admin();
                    System.out.println("Enter Your Username:");
                    admin.setUsername(adminService.getUserPassFromAdmin());
                    System.out.println("Enter Your Password:");
                    admin.setPassword(adminService.getUserPassFromAdmin());
                    adminService.addNewAdmin(admin);
                    continue outer1;
                case 3:
                    break outer1;

            }
        }
    }

    public void adminMenu() {
        adminMenu:
        while (true) {
            System.out.println("1-Add a New Restaurant");
            System.out.println("2-Add a New Food");
            System.out.println("3-Add a New Restaurant Menu For a Restaurant");
            System.out.println("4-Add a New Food to a Restaurant Menu");
            System.out.println("5-Observe Customers' Report");
            System.out.println("6-Observe Restaurants' Report");
            System.out.println("7-Back");
            int input = GetInput.getIntegerInInterval(1, 7);
            switch (input) {
                case 1:
                    addNewRestaurant();
                    break;
                case 2:
                    addNewFood();
                    break;
                case 3:
                    addNewRestaurantMenu();
                    break;
                case 4:
                    addNewFoodToRestaurantMenu();
                    break;
                case 5:
                    adminService.observeCustomerReport();
                    break;
                case 6:
                    adminService.observeRestaurantReport();
                    break;
                case 7:
                    break adminMenu;
            }
        }
    }


    public void addNewRestaurant() {
        Restaurant restaurant = new Restaurant();
        System.out.println("Enter name of Restaurant:");
        restaurant.setName(GetInput.getStringFromUser());
        System.out.println("Enter the restaurant Region:");
        restaurant.setRegion(GetInput.getInt());
        System.out.println("Enter the price of service:");
        restaurant.setShippingCost(GetInput.getInt());
        boolean addNewRestaurant = adminService.addNewRestaurant(restaurant);
        if (addNewRestaurant)
            System.out.println(restaurant.getName() + " is added successfully into the DB");
        else
            System.out.println("Unsuccessfully! Check entry and try again.");
    }

    public void addNewFood() {
        Food food = new Food();
        System.out.println("Enter name of Food:");
        food.setName(GetInput.getStringFromUser());
        System.out.println("Enter type of Food:");
        food.setFoodType(GetInput.getFoodTypeFromUser());
        System.out.println("Enter price of Food:");
        food.setPrice(GetInput.getInt());
        boolean addNewFood = adminService.addNewFood(food);
        if (addNewFood)
            System.out.println(food.getName() + " is added successfully into the DB");
        else
            System.out.println("Unsuccessfully! Check entry and try again.");
    }

    public void addNewRestaurantMenu() {
        RestaurantMenu menu = new RestaurantMenu();
        System.out.println("Enter name of Restaurant:");
        String restaurantName = GetInput.getStringFromUser();
        System.out.println("Enter Name of menu:");
        menu.setMenuName(GetInput.getFoodTypeFromUser());
        boolean addNewRestaurantMenu = adminService.addNewRestaurantMenu(restaurantName, menu);
        if (addNewRestaurantMenu)
            System.out.println("Menu is added successfully into the DB");
        else
            System.out.println("Unsuccessfully! Check entry and try again.");
    }

    public void addNewFoodToRestaurantMenu() {
        System.out.println("Enter name of Restaurant:");
        String restaurantName = GetInput.getStringFromUser();

        RestaurantMenu menu = new RestaurantMenu();
        System.out.println("Enter Name of menu:");
        FoodType menuName = GetInput.getFoodTypeFromUser();
        menu.setMenuName(menuName);

        Food food = new Food();
        System.out.println("Enter name of Food:");
        food.setName(GetInput.getStringFromUser());
        System.out.println("Enter price of Food:");
        food.setPrice(GetInput.getInt());
        food.setFoodType(menuName);

        System.out.println("Enter the number of Food:");
        int count = GetInput.getInt();

        boolean add = adminService.addNewFoodToRestaurantMenu(restaurantName,menu,food,count);
        if (add)
            System.out.println(food.getName()+" is added successfully into the Menu");
        else
            System.out.println("Unsuccessfully! Check entry and try again.");
    }

}
