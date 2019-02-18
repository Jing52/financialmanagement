function check() {
    if($("#userName").val()==null||$("#userName").val()==""){
        $.messager.alert('提示', "请输入姓名！");
        return false;
    }
    if($("#password").val()==null||$("#password").val()==""){
        $.messager.alert('提示', "请输入密码！");
        return false;
    }
    if($("#msisdn").val()==null||$("#msisdn").val()==""){
        $.messager.alert('提示', "请输入手机号码！");
        return false;
    }
    if($('#isAdmin').combobox('getValue')==null||$('#isAdmin').combobox('getValue')==""){
        $.messager.alert('提示', "请选择权限！");
        return false;
    }
    return true;
}

function submitForm() {
    var parm = {};
    parm.userName = $("#userName").val();
    parm.password = hex_md5($("#password").val());
    parm.msisdn = $("#msisdn").val();
    parm.qq = $("#qq").val();
    parm.isAdmin = $('#isAdmin').combobox('getValue');
    parm.weixin = $("#weixin").val();
    parm.userId = $("#msisdn").val();
    parm.userLogo = "";
    
    if(check()){
        $.ajax({
            type: "post",
            url: '/info/addUserInfo',
            data : parm,
            async : false,
            success : function(data) {
                //console.log(data);
                if (data == 1) {
                    $.messager.alert('提示', "添加成功！");
                    parent.location.reload();
                }else{
                    $.messager.alert('提示', "添加失败！");
                }
            },
            error : function() {
                $.messager.alert('提示', "添加失败！");
            }
        });
    }
}

function clearForm(){
    location.reload();
}

//上传图片
var image = new Image();
var image_b64;
function setImageURL(url) {
    image.src = url;
}

$('#reportlogo').filebox({
    onChange:function () {
        $('#logoImg').empty();
        var file = $("input[name='reportlogo']")[0];

        var reader = new FileReader();
        reader.onload = function () {
            // 通过 reader.result 来访问生成的 DataURL
            var url = reader.result;
            //        console.log("file.name----->"+file.name);
            setImageURL(url);
            image.className = "img";

            //生成比例
            var width = image.naturalWidth, height = image.naturalHeight, scale = width / height;
            width = parseInt(200);
            height = parseInt(100);//等比 width / scale


            var canvas = $('<canvas width="' + width + '" height="' + height + '"></canvas>')[0];
            var ctx = canvas.getContext('2d');
            ctx.drawImage(image, 0, 0, width, height);
            $('#logoImg canvas').remove();
            $('#logoImg').append(canvas);

            var data = canvas.toDataURL();
            image_b64 = data.substring(22);
            ////dataURL 的格式为 “data:image/png;base64,****”,逗号之前都是一些说明性的文字，我们只需要逗号之后的就行了
            //        data=data.split(',')[1];
            //        data=window.atob(data);
            //        var ia = new Uint8Array(data.length);
            //        for (var i = 0; i < data.length; i++) {
            //            ia[i] = data.charCodeAt(i);
            //        };
            //
            ////canvas.toDataURL 返回的默认格式就是 image/png
            //        var blob=new Blob([ia], {type:"image/png"});

        };
        reader.readAsDataURL(file.files[0]);
    }
})
//预览
function preview(){
    $.messager.alert("提示","该功能正在开发中...");
}