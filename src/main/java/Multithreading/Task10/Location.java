package Multithreading.Task10;

public record Location(double x, double y) {
    public double distanceTo(Location destination) {
        return Math.sqrt(Math.pow(this.x - destination.x, 2) + Math.pow(this.y - destination.y, 2));
    }
}