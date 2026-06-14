package za.co.sello.inventory.repository.InMemory;

import za.co.sello.inventory.model.Location;
import za.co.sello.inventory.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IMLocationRepo implements LocationRepository {
    private final List<Location> locations = new ArrayList<>();

    @Override
    public void add(Location location) {locations.add(location);}

    @Override
    public List<Location> getAll() {return locations;}

    @Override
    public Optional<Location> findById(UUID id) {
        for (Location location : locations){
            if(location.getId().equals(id)) return Optional.of(location);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Location> findByName(String name) {
        for (Location location : locations){
            if(location.getName().equals(name)) return Optional.of(location);
        }
        return Optional.empty();
    }

    @Override
    public void update(Location location) {
        for(int i = 0; i < locations.size(); i++) {
            Location current = locations.get(i);

            if (current.getId().equals(location.getId())) {
                locations.set(i, location);
                return;
            }
        }
    }

    @Override
    public void deleteById(UUID id) {
        Location category = this.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
        locations.remove(category);

    }
}
