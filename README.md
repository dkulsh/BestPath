# BestPath

The application is designed to give the best path for a set of locations. "Best" path in this case has been assumed to be "earlist" delivery.
More paramters could add to the "best" path algorithm. Other parameters have not be considered.


"Location" is a main entity. This has 3 children ( types of locations ), each with its unique properties.
  - Driver
  - Restaurant
  - Customer

For 1 Driver - any number of Restaurants and Customers can be defined.

The application uses 2 main servies:

1. BestRouteService
   - Singleton class ( avoid multiple running instances )
   - Takes all information and returns the best route.

  Step 1 : Find all traversable locations. All unpicked restaurants + All picked restaurants and undelivered Customers are valid.
  Step 2 : Find the quickest path
  Step 3 : Mark location as travelled


2. DistanceService
   - Singleton class ( avoid multiple running instances )
   - The service can accept two locations and will give the geolocation based distance between the 2 locations.
   - For now the distance is mocked.

