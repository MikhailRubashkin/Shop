package model.dto;

import model.Scooter;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Integer id;
    private Map<Scooter, Integer> scooters;
    private Double price;

    public Order(Integer id, Map<Scooter, Integer> scooters) {
        this.id = id;
        this.scooters = scooters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Scooter, Integer> getScooters() {
        if (scooters == null) {
            scooters = new HashMap<>();
        }
        return scooters;
    }

    public void setScooters(Map<Scooter, Integer> scooters) {
        this.scooters = scooters;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
