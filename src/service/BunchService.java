package service;


import model.Bunch;

import java.util.List;

public interface BunchService {

    Bunch getBunch(int id);

    List<Bunch> getBunchs();

    Bunch addBunch(Bunch bunch);

    void deleteBunch(int id);

    void updateBunch(Bunch bunch);

    List<Bunch> getByOrderId(Integer order_id);
}
