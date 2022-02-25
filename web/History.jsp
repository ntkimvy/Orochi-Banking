<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Account.jsp" %>
<div class="col-xs-7 main">
    <div><b>Số tài khoản: ${acc.accNo}</b></div>
    <div><b>Số dư: ${acc.balance} VNĐ</b></div> 
    <table class="table" style="background-color: white">
        <thead>
            <tr>
                <th scope="col"><a href="transfer">Chuyển tiền</a></th>               
                <th scope="col"><a href="deposit">Nộp tiền</a></th>
                <th scope="col"><a href="withdraw">Rút tiền</a></th>
                <th scope="col"><a style="color: limegreen" href="history">Xem lịch sử</a></th>
                <th scope="col"><a href="listorder">Xem yêu cầu</a></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="4">
                    <table  class="table" style="background-color: white">
                        <colgroup>                                
                            <col width=100 span="1">
                            <col width=100 span="1">
                            <col width="150" span="1">
                            <col width="150" span="1">
                            <col width="auto" span="1">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">Số tiền</th>
                                <th scope="col">Số dư</th>
                                <th scope="col">Thời gian</th>
                                <th scope="col">Loại giao dịch</th>
                                <th scope="col">Nhân viên xử lý</th>
                                <th scope="col">Ghi chú</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${history}" var="h">
                                <tr>
                                    <td>${h.amount}</td>
                                    <td>${h.postBalance}</td>
                                    <td>${h.time}</td>
                                    <td>${h.type}</td>
                                    <td>${h.staffID}</td>  
                                    <td>${h.note}</td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>

                </td>
            </tr>  
        </tbody>
    </table>
</div>
<%@include file="./include/Footer_Customer.jsp" %>

