package util;

import location.Location;
import location.Restaurant;
import service.DistanceService;

import java.util.Comparator;

public class EarliestFirstSort implements Comparator<Location> {

    DistanceService distanceService = DistanceService.getInstance();

    private Location startingLocation;

    public EarliestFirstSort(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    @Override
    public int compare(Location o1, Location o2) {

        int earliest1 = o1 instanceof Restaurant ?
                distanceService.getTravelTime(startingLocation, o1) + ((Restaurant) o1).getTimeToPrepare() :
                distanceService.getTravelTime(startingLocation, o1);

        int earliest2 = o2 instanceof Restaurant ?
                distanceService.getTravelTime(startingLocation, o2) + ((Restaurant) o2).getTimeToPrepare() :
                distanceService.getTravelTime(startingLocation, o2);

        return Integer.compare(earliest1, earliest2);
    }
}
