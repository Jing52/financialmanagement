function check() {
    var flag=true;
    if($('#userName').val()==""||$('#userName').val()==null){
        $('#userName').addClass('warning-input');
        flag=false;
    }
    if($('#password').val()==""||$('#password').val()==null){
        $('#password').addClass('warning-input');
        flag=false;
    }
    return flag;
}

function severCheck() {
    if (check()) {
        $('#loginBtn').text("登录中..").off('click',severCheck);
        var userName = $.trim($('#userName').val());
        var pwd = $('#password').val();
        var sPwd = hex_md5(pwd);
        $('#password').val(pwd);

        var parm = {};
        parm.userId = userName;
        parm.password = sPwd;

        // $.ajax({
        //     async: true,
        //     type: "post",//数据发送的方式（post 或者 get）
        //     url: "/loginSys",//要发送的后台地址
        //     data: {
        //         userId: userName,
        //         password: sPwd
        //     },
        //     // contentType: "application/json",
        //     dataType: "json",//后台处理后返回的数据格式
        //     success: function (data) {//ajax请求成功后触发的方法
        //         alert(1);
        //         if(data == 'ok'){
        //             window.location.href = ProPath+"/index.do";
        //         }else if(data == '1001'){
        //             $('#myModal').modal('show');
        //         }else{
        //             alert("登陆异常");
        //         }
        //         $('#loginBtn').text('登录').on('click',severCheck);;
        //     },
        //     error: function (msg) {//ajax请求失败后触发的方法
        //         alert(1);
        //         console.log(msg);
        //         alert(msg);//弹出错误信息
        //     }
        // });

        $.post('/loginSys', {
            userId : userName,
            password : sPwd
        }, function(response) {
            if(response == '0000'){
                window.location.href = "/index/index";
            }if(response == '1005'){
                $('#myModal').modal('show');
            }else{

            }
        }).complete(function () {
            $('#loginBtn').text('登录').on('click',severCheck);;
        });
    }
}

$('input').on('click',function(){
    $(this).removeClass('warning-input');
}).focusout(function () {
    if($.trim( $(this).val() ) ==''){
        $(this).addClass('warning-input');
    }
});
$(function() {
    $('#loginBtn').on('click',severCheck);
    $(document).keydown(function(event){
        if(event.keyCode == 13){ //绑定回车
            $('#loginBtn').trigger("click");
        }
    });

    $(".screenbg ul li").each(function(){
        $(this).css("opacity","0");
    });
    $(".screenbg ul li:first").css("opacity","1");
    var index = 0;
    var t;
    var cycle=3900;
    var li = $(".screenbg ul li");
    var number = li.size();
    function change(index){
        if(t){
            clearTimeout(t);
        }
        t=setTimeout(show,cycle);
        li.css("visibility","visible");
        li.eq(index).siblings().animate({opacity:0},cycle);
        li.eq(index).animate({opacity:1},cycle);
    }
    function show(){
        index++;
        if(index>=number){
            index = 0;
        }
        change(index);
    };
    show();
});