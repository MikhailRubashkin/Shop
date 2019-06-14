package model;

import java.io.Serializable;
import java.util.Objects;

public class Scooter implements Serializable {

    private Integer id;
    private String model;
    private Double price;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scooter scooter = (Scooter) o;
        return model.equals(scooter.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
