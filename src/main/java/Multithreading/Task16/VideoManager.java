package Multithreading.Task16;

import java.util.HashMap;
import java.util.Map;

public class VideoManager {
    private Map<String, Integer> views;
    private final Object lock = new Object();

    public VideoManager() {
        views = new HashMap<>();
    }

    public void addVideo(String videoId) {
        synchronized (lock) {
            views.put(videoId, views.getOrDefault(videoId, 0) + 1);
        }
    }

    public int getViewsCount(String videoId) {
        synchronized (lock) {
            return views.get(videoId);
        }
    }
}