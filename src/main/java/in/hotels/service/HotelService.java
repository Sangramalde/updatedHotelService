package in.hotels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hotels.model.Hotels;
import in.hotels.repository.HotelRepository;

@Service
public class HotelService {
    @Autowired
    private final HotelRepository repo;

    public HotelService(HotelRepository repo) {
        this.repo = repo;
    }

    // Get all hotels
    public List<Hotels> getAll() {
        return repo.findAll();
    }

    // Get hotel by ID
    public Hotels getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    // Save single hotel
    public Hotels save(Hotels hotel) {
        return repo.save(hotel);
    }

    // Save multiple hotels
    public List<Hotels> saveAll(List<Hotels> hotels) {
        return repo.saveAll(hotels);
    }

    // Update hotel
    public Hotels update(Long id, Hotels updated) {
        Hotels hotel = repo.findById(id).orElseThrow();
        hotel.setName(updated.getName());
        hotel.setLocation(updated.getLocation());
        hotel.setImageUrl(updated.getImageUrl());
        hotel.setPrice(updated.getPrice());
        hotel.setOldPrice(updated.getPrice());
        hotel.setRating(updated.getRating());
        hotel.setReviews(updated.getReviews());
        hotel.setTags(updated.getTags());
        hotel.setOffers(updated.getOffers());
        return repo.save(hotel);
    }

    // Delete hotel
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Get all hotels (duplicate of getAll, could remove if unnecessary)
    public List<Hotels> getAllHotels() {
        return repo.findAll();
    }

    public List<Hotels> searchHotels(String destination) {
        if (destination == null || destination.isEmpty()) {
            return repo.findAll(); // fallback
        }
        return repo.findByLocationContainingIgnoreCase(destination);
    }

}
