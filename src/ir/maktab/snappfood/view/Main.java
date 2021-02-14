package ir.maktab.snappfood.view;

import ir.maktab.snappfood.config.SpringContext;
import ir.maktab.snappfood.service.GetInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringContext.class);
        while(true) {
            System.out.println("1-Admin");
            System.out.println("2-Customer");
            int input = GetInput.getIntegerInInterval(1, 2);
            switch (input) {
                case 1:
                    AdminView adminView = applicationContext.getBean(AdminView.class);
                    adminView.adminLogin();
                    continue;
                case 2:
                    CustomerView customerView =  applicationContext.getBean(CustomerView.class);
                    customerView.customerPanel();
            }
        }
    }
}
