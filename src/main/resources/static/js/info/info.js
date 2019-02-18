$(function () {
    $('#dataManage').datagrid({
        url: '/info/selectUserInfo',
        title: '您的位置：能力目录管理->用户信息',
        toolbar: '#tb',
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect:true,
        pageNumber: 1,
        pageList: [10, 20, 30, 50],
        pageSize: 10,
        rownumbers:true,
        //	method:'post',
        onBeforeLoad: function (param) {
            param.userName = $('#userName').val();
            param.msisdn = $('#msisdn').val();
            var admin = $('#isAdmin').combobox('getValue');//状态
            if(admin=="-1") {
                param.isAdmin = "";
            } else {
                param.isAdmin = admin;
            }
            param.startTime = $('#startTime').datebox('getValue');
            param.endTime = $('#endTime').datebox('getValue');
        },
        columns: [[
            {field : 'ck',checkbox : true},
            {title: '用户编码', field: 'userId', width: 50, hidden: true, align: 'center'},
            {title: '用户姓名', field: 'userName', width: 80, hidden: false, align: 'center'},
            {title: '微信', field: 'weixin', width: 80, hidden: false, align: 'center'},
            {title: 'QQ',	field:'qq', width:50,hidden:false,align:'center'},
            {title: '手机号码',	field:'msisdn', width:50,hidden:false,align:'center'},
            {title: '创建时间', field: 'createTime', width: 80, hidden: false, align: 'center',
                formatter:function(value,row,index){
                    if(value==""||value==null){
                        return "未知";
                    }else{
                        return value;
                    }
                }
            },
            {title: '是否管理员', field: 'isAdmin', width: 50, hidden: false, align: 'center',
                formatter:function(value,row,index){
                    if(value==0){
                        return "管理员";
                    }else if(value==1){
                        return "普通用户";
                    }
                }
            }
        ]],
    });
});

//添加表数据
function addData(){
    $('#detail').window({
        title: '添加数据',
        width: '80%',
        height: '80%',
        content: '<iframe id = "add" scrolling="auto" frameborder="0" src="/info/userInfoAdd" style="width:100%;height:99%;overflow-x:hidden;overflow:auto;"></iframe>',
        minimizable: false,
        maximizable: false,
        closable: true
    });
    $('#detail').window('center');
    $('#detail').window('open');
}
//删除数据
function deleteData() {
    var row = $('#dataManage').datagrid('getSelected');
    if (row == null) {
        $.messager.alert('提示', "请选择一条记录");
        return;
    }
    $.messager.confirm('删除', '请确认是否删除该条数据？该操作不可逆转，请谨慎操作。', function (r) {
        if (r) {

            var parm = {};
            parm.userId = row.userId;
            $.ajax({
                type: "post",
                url: '/info/deleteUserInfo',
                data: parm,
                async: false,
                success: function (data) {
                    //console.log(data);
                    if (data == 1) {
                        $.messager.alert('提示', "删除成功！");
                        queryData();
                    }else{
                        $.messager.alert('提示', "删除失败！");
                    }
                },
                error: function () {
                    $.messager.alert('提示', "删除失败！");
                }
            })
        } else {
            return false;
        }
    });
}

//查询
function queryData() {
    $('#dataManage').datagrid('reload');
}
//清空
function emptyCondition(){
    $('#userName').textbox('clear');
    $('#msisdn').textbox('clear');
    $('#createTime').datebox('setValue',"");
    $('#isAdmin').combobox('select',1);
}

function previewData(){
    $.messager.alert('提示',"该功能正在开发中..");
}

//日期控件
$('#startTime').datebox({
    editable: false, //不允许手动输入
    formatter:function(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    },
    parser:function(s){
        if (!s) return new Date();
        var ss = (s.split('-'));
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    },
    panelWidth:150,
});
$('#endTime').datebox({
    editable: false, //不允许手动输入
    formatter:function(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    },
    parser:function(s){
        if (!s) return new Date();
        var ss = (s.split('-'));
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    },
    panelWidth:150,
});