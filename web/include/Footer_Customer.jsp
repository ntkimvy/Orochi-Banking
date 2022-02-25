<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>

<div class="col-xs-3 col-right">
    <section class="msger">
        <header class="msger-header" style="height: 105px; background-color: #a1f8ef;">
            <div style="width: 100%">
                <div style="display: flex">
                    <img src="./assets/imgs/Logo.png" style="width: 70px; height: 70px; margin: auto">
                    <div style="margin: auto 1%">
                        <h4 style="text-align: left; user-select: none">Chat cùng Orichi Banking</h4>
                        <h6 style="text-align: left; user-select: none">(Từ 8h - 11h30 và 13h - 17h30 các ngày từ thứ 2 đến thứ 6)</h6>
                    </div>
                </div >
            </div>
        </header>

        <div class="msger-chat" id="umess">
<!--            <div class="left-msg-full">
                <div class="msg-info-name"><p>Hồ Tấn Thành Nhân</p></div>
                <div class="left-msg">
                    <div class="msg-img">
                        <img class="msg-img" src="./assets/imgs/Logo.png">
                    </div>
                    <div class="left-msg-bubble">
                        <div class="msg-text">
                            Hi, welcome to OrochiBanking! Go ahead and send me a message.😄
                        </div>
                        <div class="msg-info-time">12:46</div>
                    </div>
                </div>
            </div>
            <div class="right-msg-full">
                <div class="right-msg">
                    <div class="right-msg-bubble">
                        <div class="msg-text">
                            Hi, welcome to OrochiBanking! Go ahead and send me a message.😄
                        </div>
                        <div class="msg-info-time">12:46</div>
                    </div>
                </div>
            </div>-->
            
        </div>
        <form id="sent" class="msger-inputarea" method="post" onsubmit="return false">
            <input type="text" id="mess" class="msger-input" placeholder="Aa..." autocomplete="off">
            <button onclick="userSend()" type="button" class="msger-send-btn">Gửi</button>
        </form>
    </section>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $('#sent input[type="text"]').keypress(function () {
        const mess = document.getElementById('mess').value;
        if (mess != null && mess.trim() != "") {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                userSend();
            }
        }
    })
    function userSend() {
        const mess = document.getElementById('mess').value;
        if (mess != null && mess.trim() != "") {
            $.ajax({
                url: '/orochi-banking/umessenger',
                type: 'POST',
                data: {
                    mess: mess
                },
                success: function () {
                    document.getElementById('mess').value = '';
//                    var m = document.getElementById("umess").innerHTML;
//                    m.innerHTML = "<div class='right-msg-full'><div class='right-msg'><div class='right-msg-bubble'><div class='msg-text'>"+mess+"</div><div class='msg-info-time'>"+1246+"</div></div></div></div>" + m.innerHTML;
                }
            });
        }
    }
    setInterval(function () {
        $.ajax({
            url: '/orochi-banking/loadumess',
            type: 'POST',
            data: {
            },
            success: function (data) {
                var umess = document.getElementById("umess");
                umess.innerHTML = data;
            }
        });
    }, 500);
</script>
</body>
</html>
