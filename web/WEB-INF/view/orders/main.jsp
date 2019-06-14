<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>

</div>
<input class="btn-primary paymentBtn" style="float: left" type="button" title="Оплатить" value="Оплатить"/>
<div>

    <div class="container text-center">

        <div class="col-md-12">Заказы</div>
        <table class="table">
            <tr>
                <div class="col-md-8">
                    <th class="col-md-4"></th>
                    <th class="col-md-4"></th>
                    <th class="col-md-4"></th>
                    <th class="col-md-1"></th>
                    <th class="col-md-1"></th>
                    <th class="col-md-1"></th>
                    <th class="col-md-1"></th>
                </div>
            </tr>
        </table>
    </div>
</div>

<TABLE>
    <tr>
        <th style="width: 100px;">№</th>
        <th style="width: 200px;">Order Id</th>
        <th style="width: 200px;"></th>
        <th style="width: 100px;"></th>
        <th style="width: 200px;">Price</th>
    </tr>

    <c:forEach var="order" items="${orders}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${order.id}</td>
            <td></td>
            <td></td>
            <td>${order.price}</td>
        </tr>
        <tr>
            <td>scooter</td>
            <td>id</td>
            <td>model</td>
            <td>quantity</td>
            <td></td>
        </tr>
        <c:forEach var="scooter" items="${order.scooters}" varStatus="status">
            <tr>
                <td></td>
                <td>${scooter.key.id}</td>
                <td>${scooter.key.model}</td>
                <td>${scooter.value}</td>
                <td>${scooter.key.price}</td>
            </tr>
        </c:forEach>
    </c:forEach>
</TABLE>
