package model;

import java.io.Serializable;
import java.util.Objects;

public class Bunch implements Serializable {

    private Integer id;
    private Integer scooter_id;
    private Integer order_id;
    private Integer quantity;

    public Bunch() {
    }

    public Bunch(Integer scooter_id,Integer quantity) {
        this.scooter_id = scooter_id;
        this.quantity = quantity;
    }

    public Bunch(Long scooter_id,Integer quantity) {
        this.scooter_id = Math.toIntExact(scooter_id);
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScooter_id() {
        return scooter_id;
    }

    public void setScooter_id(Integer scooter_id) {
        this.scooter_id = scooter_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bunch bunch = (Bunch) o;
        return scooter_id.equals(bunch.scooter_id) &&
                order_id.equals(bunch.order_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scooter_id, order_id,quantity);
    }
}
