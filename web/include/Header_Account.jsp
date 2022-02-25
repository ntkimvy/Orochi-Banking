<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="./assets/imgs/Logo.png">
        <link href="./assets/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/main.css" rel="stylesheet" type="text/css">
        <title>Ngân hàng Orochi</title>
    </head>
    <body style="background-color: #D4EBE5;">
        <header>
            <div class="row header">
                <a id="logo" class="col-sm-2 col-xs-12"><img src="./assets/imgs/Bank.jpg" height="100" width="250"></a>
                <a href="logout" class="log col-sm-2 col-xs-2" style="float: right">ĐĂNG XUẤT</a>  
                <a class="uname col-sm-2 col-xs-3" style="float: right; cursor: context-menu">Chào mừng <span>${cust.fullName}</span>!</a>     
            </div>
        </header> 
        <div class="col-xs-2 col-left">
            <ul>
                <li><a href="listaccount"><button class="btn-success btn">Xem danh sách tài khoản</button></a></li>
                <li><a href="listorder"><button class="btn-success btn">Xem yêu cầu</button></a></li>
                <li><a href="changepassaccount"><button class="btn-success btn">Đổi mật khẩu</button></a></li>
            </ul>
        </div>

