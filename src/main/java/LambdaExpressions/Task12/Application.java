package LambdaExpressions.Task12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
        void main(){
        LocationSearchEngine engine = new LocationSearchEngine();
        List<Location> locations =new ArrayList<>(Arrays.asList(
                new Location("Eiffel Tower",48.8584,2.2945),
                new Location("Statue of Liberty",40.6892, 74.0445),
                new Location("Great Wall of China",40.4319,116.5704)
        ));

        Location actualGeo = new Location("Me",39.5823, 54.9411);

        List<Location> filtered = engine.filterLocations(locations, location -> location.getLongitude() > 40.5);
        engine.processLocations(filtered, location -> System.out.println("Name: " + location.getName() +
                " Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude()));

        List<Double> distances = engine.calculateDistance(filtered, location -> {
            double latitudeDiff = actualGeo.getLatitude() - location.getLatitude();
            double longitudeDiff = actualGeo.getLongitude() - location.getLongitude();
            return Math.sqrt(Math.pow(latitudeDiff, 2) + Math.pow(longitudeDiff, 2));
        });

        for (var distance : distances){
            System.out.println(distance);
        }
    }
}