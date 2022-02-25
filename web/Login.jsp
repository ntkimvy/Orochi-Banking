<%-- 
    Document   : SignIn
    Created on : Jan 25, 2022, 2:40:16 PM
    Author     : 84708
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignIn</title>
        <link rel="icon" type="image/x-icon" href="./assets/imgs/Logo.png">
        <link href="./assets/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/main.css" rel="stylesheet" type="text/css">
        <link href=>
    </head>
    <body>
    <center> <img src="./assets/imgs/Bank.jpg" style="width: 20%;margin-left: 2%;">
        <form action="login" method="post">
            <p>CHÀO MỪNG ĐẾN VỚI OROCHI-BANKING</p>
            <input id="userName" type="text" name="userName" placeholder="Tên tài khoản..."/><br>
            <input id="password" type="password" name="password" placeholder="Mật khẩu..."/><br>                
            <button  class="log" type="submit">ĐĂNG NHẬP</button>
            <a class="create" href="register">Tạo tài khoản mới?</a>
        </form>
        <footer>
            <div class="footer1 row">
                <a href="#" class="col-sm-2 col-xs-6">OROCHI là gì?</a>
                <a href="#" class="col-sm-2 col-xs-6">Góp ý cho OROCHI</a>
            </div>
            <p class="col-xs-12"><span>Since 2022.</span></p>
            <p class="col-xs-12"><span>OROCHI</span> is Powered by <span>OROCHI</span></p>
        </footer>
        <style>
            img.logo {display: block; margin-left: auto; margin-right: auto;}
            body{
                background-color: #d4ebe5 ;	
                margin-top: 60px;	
            }
            form{
                text-align: center;
                display: block; margin-left: auto; margin-right: auto;
            }
            form, input{
                border-radius: 35px;
                margin-top: 10px;
                height: 40px;
                width: 50%;
                font-size: 15pt;
                font-family: Montserrat;
                padding-left: 2%;   
            }
            *{
                font-family: auto!important;
            }
            input:focus{
                outline: none;   
            }
            input{
                border: 1px solid #2e6657 ;
            }
            input[type=radio]{

                height: 1.1em;
                color: #2e6657;
                border-radius: 50%;
                width: 20px;
                height: 20px;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;

            }

            .log{
                background-image: linear-gradient(to right, #ecb161,#fa8a66);
                border-radius: 35px;
                color: #fff;
                text-decoration: none;
                height: 40px;
                width: 50%;
                font-weight: bold;
                display: flex;    
                justify-content: center;
                align-items: center;             
                margin-bottom: 8px;
                border: 0;
                font-size: 20px;
                margin-top: 25px;
                margin-left: 25%;
            }
            a{
                font-size: 85%;
                text-decoration: none;
            }
            .create{
                text-decoration: none;
            }
            footer{
                top:100%;
                position: absolute;
                top: 50%;
                -ms-transform: translateY(200%);
                transform: translateY(200%);
                clear: both;
                width: 98.99%;
                height: 80px;
                background-color: #a8d7ba;
                color: white;
                text-align: center;
                padding: auto;
                margin-top: 7.8%;
            }
            .footer1 a, footer p{
                color: #2d6556;
                font-family: Montserrat;
                font-weight: bold;
                font-size: 10pt; 

            }
            footer p{
                font-weight: 500;
                margin:0;
                font-size: 10pt;
            }
            footer a{
                margin-left: 13%;
            }
            footer div{
                padding-left: 60%;
            }
            footer span{
                font-weight: bold;
            }
        </style>
    </body>
</html>
