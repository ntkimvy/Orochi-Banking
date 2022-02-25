<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Account.jsp" %>
<div class="col-xs-7 main">
    <table class="table" style="background-color: white">
        <thead>
            <tr>
                <th scope="col">Loại yêu cầu</th>               
                <th scope="col">Số tiền</th>
                <th scope="col">Ghi chú</th>
                <th scope="col">Thời gian</th>
                <th scope="col">Hủy</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listOrder}" var="l">
                <tr>
                    <td>${l.type}</td>
                    <td>${l.amount}</td>
                    <td>${l.note}</td>
                    <td>${l.time}</td>
                    <td> <form action="listorder" method="POST">
                            <input type="text" value="${l.orderID}" name='orderID' readonly hidden>
                            <input type="submit" class="btn btn-danger" value="Hủy">
                        </form> 
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <input type="button" value="Trở lại" class="btn btn-info btn-md" onclick="javascript:history.go(-1);" />

</div>
<%@include file="./include/Footer_Customer.jsp" %>