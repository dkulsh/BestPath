package application;

import location.Customer;
import location.Driver;
import location.Location;
import location.Restaurant;
import service.BestRouteService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        Application application = new Application();

        application.initData();
    }

    private void initData() {

        Driver driver = new Driver(1, 2);
        driver.setName("Aman");

//      Order 1
        Restaurant r1 = new Restaurant(2, 3);
        r1.setName("R1");
        r1.setTimeToPrepare(4);

        Customer c1 = new Customer(2, 3);
        c1.setName("C1");

//        Order 2
        Restaurant r2 = new Restaurant(3, 4);
        r2.setName("R2");
        r2.setTimeToPrepare(8);

        Customer c2 = new Customer(3, 4);
        c2.setName("C2");

        Map<Restaurant, Customer> map = new HashMap<>();
        map.put(r1, c1);
        map.put(r2, c2);

        List<Restaurant> restaurants = new LinkedList<>();
        restaurants.add(r1);
        restaurants.add(r2);

        List<Customer> customers = new LinkedList<>();
        customers.add(c1);
        customers.add(c2);

        List<Location> bestPath = BestRouteService.getInstance().getBestRoute(driver, restaurants, customers, map);

        System.out.println(bestPath);

    }

}
