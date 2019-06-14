package view.controller.enums;

import view.controller.Controller;
import view.controller.impl.*;

public enum ControllerType {
    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("login.jsp", "Logout", "logout.title", new LogoutController()),
    ORDERS("orders/main.jsp", "Orders", "orders.title", new OrderController()),
    ADD_ORDER("", "addOrder", "", new CreateOrderController()),
    ADD_USER("registration.jsp", "Registration", "registration.title", new CreateUserController()),
    ADD_SCOOTER("", "addScooter", "", new AddToBasketController()),
    REMOVE_SCOOTER("", "removeScooter", "", new RemoveFromBasketController()),
    PRODUCTS("products/main.jsp", "Products", "products.title", new ScooterController());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    ControllerType(String pagePath, String pageName, String i18nKey, Controller controller) {
        this.pagePath = pagePath;
        this.pageName = pageName;
        this.i18nKey = i18nKey;
        this.controller = controller;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с продуктами
        return PRODUCTS;
    }

    public Controller getController() {
        return controller;
    }
}
