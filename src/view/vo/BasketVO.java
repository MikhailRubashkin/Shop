package view.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BasketVO {

    private Map<Long, AtomicInteger> basket;

    public BasketVO(HashMap<Long, AtomicInteger> objectObjectHashMap) {
        this.basket = objectObjectHashMap;
    }

    public Map<Long, AtomicInteger> getBasket() {
        if (basket == null) {
            basket = new HashMap<>();
        }
        return basket;
    }

    public void setBasket(Map<Long, AtomicInteger> basket) {
        this.basket = basket;
    }
}
