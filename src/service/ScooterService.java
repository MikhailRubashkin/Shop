package service;


import model.Scooter;

import java.util.List;

public interface ScooterService {

    Scooter getScooter(int id);

    List<Scooter> getScooters();

    Scooter addScooter(Scooter scooter);

    void deleteScooter(int id);

    void updateScooter(Scooter scooter);

}
