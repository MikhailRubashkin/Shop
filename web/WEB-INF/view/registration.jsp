<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages" var="i18n"/>
<div class="error"><fmt:message bundle="${i18n}" key="${errorMsg}"/></div>
<div class="container text-right">


        <form action="frontController?command=registration" method="post">
            <fmt:setLocale value="${sessionScope.locale}"/>

            <b><fmt:message bundle="${i18n}" key="registration.login"/></b>
            <input type="text" name="login" maxlength="30"/>

            <b><fmt:message bundle="${i18n}" key="registration.password"/></b>
            <input type="password" name="password" maxlength="20"/>


            <b><fmt:message bundle="${i18n}" key="email.email"/></b>
            <input type="email" name="Email" maxlength="20"/>

            <input type="submit" value="<fmt:message bundle="${i18n}" key="registration.submit"/>">

        </form>
</div>
