package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.enums.FoodType;

import java.util.Scanner;

public class GetInput {

    static Scanner scan=new Scanner(System.in);

    public static int getIntegerInInterval(int start, int finish) {
        int input;
        while (true) {
            System.out.println("Enter an Option:");
            input = scan.nextInt();
            if (input >= start && input <= finish)
                break;
            else
                System.out.println("Invalid input!Try again!");
        }
        return input;
    }

   public static String getStringFromUser() {
        while (true) {
            String input = scan.nextLine();
            if (input != null && input.length() > 1) {
                return input;
            }else if(input==null) {
                System.out.println("Invalid input!Try again!");
            }
        }
    }

    public static long getLongFromUser() {
        while (true) {
            System.out.println("Enter the price:");
            long input = scan.nextLong();
            if (input >= 1)
                return input;
            else {
                System.out.println("Your Input is invalid!Try again!");
            }

        }
    }

    public static int getInt() {
        while (true) {
            System.out.println("Enter a Integer:");
            int input = scan.nextInt();
            if (input >= 1)
                return input;
            else {
                System.out.println("Your Input is invalid!Try again!");
            }

        }
    }

    public static FoodType getFoodTypeFromUser() {
        while (true) {
            String type = getStringFromUser();
            if (type.equalsIgnoreCase("irani"))
                return FoodType.IRANI;
            else if (type.equalsIgnoreCase("daryaee"))
                return FoodType.DARYAEE;
            else if (type.equalsIgnoreCase("fastfood"))
                return FoodType.FASTFOOD;
            else {
                System.out.println("Wrong Entry for food Type!Try Again...");
                continue;
            }
        }
    }

    public static String getPhoneNumber() {
        while (true) {
            System.out.println("Enter Your Phone Number:");
            String phoneNumber = GetInput.getStringFromUser();
            if (phoneNumber.length() != 11) {
                System.out.println("Your Phone Number's length is less than 11! Try again.");
                continue;
            }
            if (!phoneNumber.startsWith("0") && phoneNumber.charAt(1) != '9') {
                System.out.println("Your Phone Number is Invalid! Try again.");
                continue;
            }
            return phoneNumber;
        }
    }
}
