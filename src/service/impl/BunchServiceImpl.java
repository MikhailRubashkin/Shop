package service.impl;

import dao.repository.BunchRepository;
import dao.repository.impl.BunchRepositoryImpl;
import model.Bunch;
import service.BunchService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BunchServiceImpl  extends AbstractService implements BunchService {

    private static BunchServiceImpl instance;
    private BunchRepository bunchRepository = BunchRepositoryImpl.getInstance();

    private BunchServiceImpl() {
    }


    public static BunchServiceImpl getInstance() {
        if (instance == null) {
            instance = new BunchServiceImpl();
        }
        return instance;
    }

    @Override
    public Bunch getBunch(int id) {
        try {
            return bunchRepository.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bunch> getBunchs() {
        try {
            return bunchRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Bunch addBunch(Bunch bunch){
        try {
            return bunchRepository.save(bunch);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBunch(int id) {
        try {
            bunchRepository.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateBunch(Bunch bunch) {
        try {
            bunchRepository.update(bunch);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Bunch> getByOrderId(Integer id) {
        try {
            return bunchRepository.getByOrderId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
