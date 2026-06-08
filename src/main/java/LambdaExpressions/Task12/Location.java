package LambdaExpressions.Task12;

public class Location {
    private final String name;
    private final double longitude;
    private final double latitude;

    public Location(String name, double longitude, double latitude){
        //String and double incorrect checks would be provided
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName(){
        return name;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }
}