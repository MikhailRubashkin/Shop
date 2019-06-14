<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<input class="btn-primary addOrderBtn" style="float: right" type="button" title="Добавить товар в корзину" value="Добавить товар в корзину"/>
<div>

    <div class="container-fluid">

        <div class="col-md-12">Товары</div>
        <table class="table">
            <tr>
                <div class="col-md-8">
                    <th class="col-md-4">Model</th>
                    <th class="col-md-1">Quantity</th>
                    <th class="col-md-1">Price</th>
                    <th class="col-md-1"></th>
                    <th class="col-md-1"></th>
                </div>
            </tr>

            <script>
                function callAlert(productId) {
                    alert(productId);
                }
            </script>
            <c:forEach var="scooter" items="${scooters}" varStatus="status">
                <tr class="Warning">

                    <div class="col-md-8">
                        <td class="col-md-4">${scooter.model}</td>
                        <td id="count${scooter.id}" class="col-md-1">0</td>
                        <td class="col-md-1">${scooter.price}</td>
                        <td class="col-md-1"><input id="${scooter.id}" class="btn-primary addScooterBtn" type="button" title="Добавить в корзину" value="+"/></td>
                        <td class="col-md-1"><input id="${scooter.id}" class="btn-primary reduceScooterBtn" type="button" title="Удалить 1 из корзину" value="-"/></td>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>




