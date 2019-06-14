package service.impl;


import dao.repository.ScooterRepository;
import dao.repository.impl.ScooterRepositoryImpl;
import model.Scooter;
import service.ScooterService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class ScooterServiceImpl  extends AbstractService implements ScooterService {

    private static ScooterServiceImpl instance;

    private ScooterRepository scooterRepository = ScooterRepositoryImpl.getInstance();

    private ScooterServiceImpl() {
    }
    public static ScooterService getInstance() {
        if (instance == null) {
            instance = new ScooterServiceImpl();
        }
        return instance;
    }

    @Override
    public Scooter getScooter(int id) {
        try {
            return scooterRepository.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Scooter> getScooters() {
        try {
            return scooterRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Scooter addScooter(Scooter scooter) {
        try {
            return scooterRepository.save(scooter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteScooter(int id) {
        try {
            scooterRepository.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScooter(Scooter scooter) {
        try {
            scooterRepository.update(scooter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
