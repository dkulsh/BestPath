package service;

import location.Location;

import java.util.Random;

public class DistanceService {

    private DistanceService(){}

    private static DistanceService distanceService = new DistanceService();

    public static DistanceService getInstance() {
        return distanceService;
    }

    public int getTravelTime(Location l1, Location l2){

//        Customized per need. Kept fixed for mocking
//        return 1;
        return new Random().nextInt(10);

    }

}
