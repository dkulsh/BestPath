package service;

import location.Location;

public class DistanceService {

    private DistanceService(){}

    private static DistanceService distanceService = new DistanceService();

    public static DistanceService getInstance() {
        return distanceService;
    }

    public int getTravelTime(Location l1, Location l2){

//        Customized per need. Kept fixed for mocking
        return 1;

    }

}
