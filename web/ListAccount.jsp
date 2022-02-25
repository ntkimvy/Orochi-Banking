<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Customer.jsp" %>
<div class="col-xs-7 main">
    <table class="table" style="background-color: white; font-size: 25px">
        <thead>
            <tr>
                <th scope="col">Số tài khoản</th>               
                <th scope="col">Số dư</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="l">
                <tr>
                    <td><a href="loginacc?accNo=${l.accNo}">${l.accNo}</a></td>
                    <td>${l.balance}</td>
                </tr>
            </c:forEach>

        </tbody>
    </table>

</div>
<%@include file="./include/Footer_Customer.jsp" %>