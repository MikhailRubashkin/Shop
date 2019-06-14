package view.handlers;

import view.controller.enums.ControllerType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static view.controller.enums.ControllerType.PRODUCTS;

public class RequestHandler {
    public static ControllerType getCommand(HttpServletRequest req){
        String param = req.getParameter("command");
        if (param == null && "".equals(param)) {
            param = "products.title";
        }

        ControllerType type = ControllerType.getByPageName(param);
        req.setAttribute("title", type.getI18nKey());
        HttpSession session = req.getSession();
        String pageName = (String)session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", PRODUCTS.getPageName());
            session.setAttribute("pagePath", PRODUCTS.getPagePath());
        }

        return type;
    }
}
