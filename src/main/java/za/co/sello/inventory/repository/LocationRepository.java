package za.co.sello.inventory.repository;

import za.co.sello.inventory.model.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepository {

    //adding Locations
    void add(Location location);

    //Reading data
    List<Location> getAll();
    Optional<Location> findById(UUID id);
    Optional<Location> findByName(String name);

    // Update
    void update(Location location);

    // Delete
    void deleteById(UUID id);
}
