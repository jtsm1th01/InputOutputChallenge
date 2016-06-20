package com.tsmith.mypackage;

import java.io.*;
import java.util.*;

/**
 * Created by travis on 6/18/16.
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();


    public static void main(String[] args) {

        System.out.println("Writing the files...");
        try(BufferedWriter locFileWriter = new BufferedWriter(new FileWriter("locations2.txt"));
            BufferedWriter dirFileWriter = new BufferedWriter(new FileWriter("directions2.txt"))) {
            for(Location location: locations.values()) {
                locFileWriter.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for(Map.Entry<String, Integer> exit: location.getExits().entrySet()) {
                    dirFileWriter.write(location.getLocationID() + "," + exit.getKey() + "," + exit.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {

        System.out.println("Static initialization started...");
        try (BufferedReader locFile = new BufferedReader(new FileReader("locations2.txt"))) {
            String input;
            while((input = locFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
                System.out.println("Imported: " + loc + "," + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // And now the exits

        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions2.txt"))) {
            String input;
            while((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                System.out.println("Imported: " + loc + ", " + direction + ", " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
