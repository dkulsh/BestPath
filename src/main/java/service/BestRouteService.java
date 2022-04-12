package service;

import location.Customer;
import location.Driver;
import location.Location;
import location.Restaurant;
import util.EarliestFirstSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestRouteService {

    DistanceService distanceService = DistanceService.getInstance();

    private BestRouteService() {}

    private static BestRouteService bestRouteService = new BestRouteService();

    public static BestRouteService getInstance() {
        return bestRouteService;
    }

    public List<Location> getBestRoute(Driver driver, List<Restaurant> restaurants,
                                       List<Customer> customers,
                                       Map<Restaurant, Customer> restaurantToCustomerMap){

        List<Location> bestPath = new LinkedList<>();

//        Until all the customers are delivered
        while(! customers.isEmpty()) {

//            From Current location, what are the location that can be traversed ?
            List<Location> traverseableLocations =
                    travelableLocations(restaurants, customers, restaurantToCustomerMap);

//            Find closest location
            Location closestLocation = getClosestLocation(driver, traverseableLocations);
            int timeTaken = getTimeTaken(driver, closestLocation);

            bestPath.add(closestLocation);
            System.out.println("Go to :: " + closestLocation + ". Time to travel :: " + timeTaken);

//            Post processing.
            markTravelled(driver, customers, closestLocation);
        }

        return bestPath;
    }

    private void markTravelled(Driver driver, List<Customer> customers, Location closestLocation) {

//        Mark Restaurant or Customer travelled
        if (closestLocation instanceof Restaurant) {
            ((Restaurant) closestLocation).setPicked(true);
        } else {
            ((Customer) closestLocation).setDelivered(true);
        }

//        If customer is delivered. Remove from list
            customers.remove(closestLocation);

//            Driver reaches new location
        driver.setLatitude(closestLocation.getLatitude());
        driver.setLongitude(closestLocation.getLongitude());
    }

    private int getTimeTaken(Driver driver, Location closestLocation) {
        return closestLocation instanceof Restaurant ?
                distanceService.getTravelTime(driver, closestLocation) + ((Restaurant) closestLocation).getTimeToPrepare() :
                distanceService.getTravelTime(driver, closestLocation);
    }

    private Location getClosestLocation(Driver driver, List<Location> traverseableLocations) {

        traverseableLocations.sort(new EarliestFirstSort(driver));
        return traverseableLocations.get(0);

    }

    //    All unpicked restaurants + any order picked (but not delivered) customers
    private List<Location> travelableLocations(List<Restaurant> restaurants,
                                               List<Customer> customers,
                                               Map<Restaurant, Customer> resToCustMap){

//        List<Location> traverseableLocations = new LinkedList<>();
//
//        Map<Boolean, List<Restaurant>> partitionByDelivered = restaurants
//                .stream()
//                .collect(Collectors.partitioningBy(Restaurant::isDelivered));
//
//        traverseableLocations.addAll(partitionByDelivered.get(Boolean.FALSE));
//
//        partitionByDelivered.get(Boolean.TRUE).stream().map(resToCustMap::get)
//                .forEach(traverseableLocations::add);

        List<Location> traverseableLocations = restaurants.stream()
                .filter(r -> !r.isPicked())
                .collect(Collectors.toList());

        restaurants.stream()
                .filter(Restaurant::isPicked)
                .map(resToCustMap::get)
                .filter(c -> !c.isDelivered())
                .forEach(traverseableLocations::add);

        return traverseableLocations;
    }
}
