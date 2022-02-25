<%-- 
    Document   : SignUp
    Created on : Jan 25, 2022, 10:30:19 AM
    Author     : 84708
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignUp</title>
        <link rel="icon" type="image/x-icon" href="./assets/imgs/Logo.png">
        <link href="./assets/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/main.css" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
    <center> 
        <img src="./assets/imgs/Bank.jpg" style="width: 20%;margin-left: 2%;">
        <form action="register" method="POST">             
            <input id="fullName" type="text" name="fullName" placeholder="Họ và tên..." required/><br>
            <input oninput="checkRegister()" id="userName" type="text" name="userName" placeholder="Tên tài khoản..." autocomplete="off" required/><br>
            <i id="fallName" style="color: red;"></i><br>
            <input oninput="checkPass()" id="password" type="password" name="password" placeholder="Mật khẩu..." required/>
            <i class="bi bi-eye-slash" id="togglePassword"></i><br>
            <input oninput="checkPass()" id="re_password" type="password" name="re_password" placeholder="Nhập lại mật khẩu..." required/>
            <br><i id="fallPass" style="color: red; display: none"></i>
            <i class="bi bi-eye-slash" id="rePassword"></i><br>
            <input id="email" type="email" name="email" placeholder="Email..." required/><br>
            <input id="phone" type="number" name="phone" placeholder="Số điện thoại..." required/><br>
            <input oninput="checkRegister()" id="id" type="number" name="id" placeholder="Số CMND/CCCD..." autocomplete="off" required/><br>
            <i id="fallId" style="color: red;"></i><br>
            <input id="address" type="text" name="address" placeholder="Địa chỉ..." autocomplete="off" required/><br>
            <a class="date" > Ngày sinh: </a><br>
            <input id="dob" type="date" name="dob" placeholder="dd/mm/yyyy" required/><br>
            <a class="sex" > Giới tính: </a><br>
            <div class="nam" style="width: 170px; margin-right: 2%;">
                <label  for="Nam">Nam </label>
                <input type="radio" id="Nam" checked name="sex" value="true"/>
            </div>
            <div class="nu" style="width: 170px">
                <label for="Nu">Nữ</label>
                <input type="radio" id="Nu" name="sex" value="false"/>
            </div> <br> 
            <button class="login" id="register" type="submit" style="margin-right: auto"><i class="fa"></i>ĐĂNG KÍ</button>
            <br>
            <div style="margin: 0!important">
                <a class="fogot" href="#" style="padding-right: 30px">Quên mật khẩu?</a>
                <a class="create" href="login" style="padding-left: 30px">Đăng nhập?</a>
            </div>

        </form>
    </center>

</body>
<script>
    function checkPass () {
        var pass = document.getElementById("password").value;
        var rePass = document.getElementById("re_password").value;
        var fail = document.getElementById("fallPass");
        var button = document.getElementById("register");
        if (rePass != null && rePass.trim() != "") {
            if (pass != rePass) {
                fail.innerHTML = "Mật khẩu không trùng khớp!"
                fail.style = "color: red;";
                button.disabled = true;
            } else {
                fail.style = "display: none";
                button.disabled = false
            }
        }
    }

    function checkRegister () {
        var userName = document.getElementById("userName").value;
        var id = document.getElementById("id").value;
        var failName = document.getElementById("fallName");
        var failId = document.getElementById("fallId");
        var button = document.getElementById("register");
        if ((userName != null && userName.trim() != "") || (id != null && id.trim() != "")) {
            $.ajax({
                url: '/orochi-banking/checkregister',
                type: 'GET',
                data: {
                    userName: userName,
                    id: id
                },
                success: function (data) {
                    if (data == "1") {
                        failName.innerHTML = "Tên tài khoản đã tồn tại!"
                        failName.style = "color: red;";
                        failId.style = "display: none";
                        button.disabled = true;
                    } else if (data == "2") {
                        failId.innerHTML = "CMND/CCCD đã tồn tại!"
                        failId.style = "color: red;";
                        failName.style = "display: none";
                        button.disabled = true;
                    } else if (data == "12") {
                        failName.innerHTML = "Tên tài khoản đã tồn tại!"
                        failName.style = "color: red;";
                        failId.innerHTML = "CMND/CCCD đã tồn tại!"
                        failId.style = "color: red;";
                        button.disabled = true;
                    } else {
                        failName.style = "display: none";
                        failId.style = "display: none";
                        button.disabled = false
                    }
                }
            });
        }
    }
</script>
<style type="text/css">
    body{
        background-color: #d4ebe5;
    }
    img{
        margin-top: 10px;  
    }
    form, input{
        border-radius: 35px;
        height: 30px;
        width: 48%;
        font-weight: 300;
        font-size: 14pt;
        margin-top: 5px;
        padding-left: 2%;
    }
    input:focus{
        outline: none;
    }
    input{
        border: 1.3px solid #2e6657;
    }
    .date,.sex{
        margin-right: 38%;
        color: #396e60;			
        font-weight: bold;
    }
    .nam,.nu{
        height: 30px;
        background-color: #fff;
        border-radius: 35px;		
        font-weight: bold;
        color: #2e6657;
        font-size: 20px;
        align-items: center;
        border: 1px solid #2e6657;
    }
    div{
        display: inline-flex;
    }
    center{
        margin-bottom: 20px
    }

    .login{
        background-image: linear-gradient(to right, #ecb161,#fa8a66);
        border-radius: 35px;
        color: #fff;
        text-decoration: none;
        height: 40px;
        width: 50%;
        font-weight: bold;         
        justify-content: center;
        align-items: center;             
        margin-bottom: 8px;
        border: 0;
        font-size: 20px;
        margin-top: 25px;
    }
    input[type=radio]{
        border: 1px solid #2e6657 ;
        height: 1.1em;
        color: #2e6657;
        border-radius: 50%;
        width: 20px;
        height: 20px;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
    }
    input[type=radio]:checked{
        background: #2e6657;
        outline: 1px solid #2e6657;
        border: 3px solid #fff;
    }
    .nu p, .nam p, .nu input, .nam input{
        margin:  0;
        margin-left: 45px;
        display: flex;   
        text-align: center;
        justify-content: center;
        align-items: center; 
    }
    label{
        margin-left: 20%;
    }
    input {
        margin-top: 12px;
    }
    *{
        font-family: auto!important;
    }
    #Nu:after{
        color: #2e6657;
        background: #000;
    }
    ::marker{

    }
    footer{
        clear: both;        
        height: 70px;
        background-color: #a8d7ba;
        color: white;
        text-align: center;
    }
    .footer1 a, footer p{
        color: #2d6556;
        font-family: Montserrat;
        font-weight: bold;
        font-size: 10pt; 
        text-decoration: none;
    }
    footer p{
        font-weight: 500;
        margin:0;
        font-size: 10pt;
    }
    footer a{
        margin-left: 100px;
    }
    footer div{
        padding-left: 60%;
    }
    footer span{
        font-weight: bold;
    }
    form i {
        margin-left: -30px;
        cursor: pointer;
    }

    form * {
        margin-left: auto!important
    }
</style>
</html>
