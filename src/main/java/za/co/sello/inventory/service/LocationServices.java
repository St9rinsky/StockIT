package za.co.sello.inventory.service;

import za.co.sello.inventory.model.Location;
import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;


import za.co.sello.inventory.repository.LocationRepository;
import za.co.sello.inventory.repository.ProductRepository;

import java.util.List;

public class LocationServices {
    private final LocationRepository locationRepo;
    private final ProductRepository productRepo;

    public LocationServices(LocationRepository locationRepo,
                            ProductRepository productRepo){
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
    }

    //add new
    public void addLocation(User currentUser, Location location){
        requiredAccess(currentUser);
        if (locationRepo.findByName(location.getName()).isPresent()) {
            throw new IllegalArgumentException("Location already exists");
        }
        locationRepo.add(location);

    }

    //change name
    public void renameCategory(User currentUser, String oldName, String newName) {
        requiredAccess(currentUser);
        Location location = locationRepo.findByName(oldName)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        if (locationRepo.findByName(newName).isPresent()) {
            throw new IllegalArgumentException("Location name already exists");
        }

        location.rename(newName);
        locationRepo.update(location);
    }

    //delete
    public void deleteLocation(User currentUser, String locationName) {
        requiredAccess(currentUser);
        Location location = locationRepo.findByName(locationName)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        if (!productRepo.findByLocationId(location.getId()).isEmpty()) {
            throw new IllegalArgumentException("Location contains products");
        }
        locationRepo.deleteById(location.getId());
    }

    //get all
    public List<Location> getAllLocations() {
        return locationRepo.getAll();
    }

    //validators
    private void requiredAccess(User currentUser){
        if (currentUser.getRole() != Role.ADMIN || currentUser.getRole() != Role.STOCK_MANAGER){
            throw new IllegalArgumentException("Unauthorised access");
        }
    }
}
