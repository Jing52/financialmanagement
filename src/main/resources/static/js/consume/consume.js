var param = {};
$(function () {
    consumeType();
    getNation();

    $('#dataManage').datagrid({
        url: '/history/selectConsume',
        title: '您的位置：能力目录管理->消费记录',
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
            // console.log(${userMap});
            param.userId = $('#userId').val();

            if($('#consumeType').combobox('getValue')=="-1"){
                param.consumeType = "";
            }else{
                param.consumeType = $('#consumeType').combobox('getValue');
            }
            if($('#nation').combobox('getValue')=="-1"){
                param.nation = "";
            }else{
                param.nation = $('#nation').combobox('getValue');
            }
            if($('#provinceId').combobox('getValue')=="-1"){
                param.provinceId = "";
            }else{
                param.provinceId = $('#provinceId').combobox('getValue');
            }
            if($('#cityId').combobox('getValue')=="-1"){
                param.cityId = "";
            }else{
                param.cityId = $('#cityId').combobox('getValue');
            }

            param.minSum = $('#minSum').val();
            param.maxSum = $('#maxSum').val();

            if(parseInt(param.minSum)>parseInt(param.maxSum)){
                $.messager.alert('提示', "请检查消费金额范围！");
                return false;
            }
            param.startTime = $('#startTime').datebox('getValue');
            param.endTime = $('#endTime').datebox('getValue');

            if(param.startTime==""&&param.endTime!=""){
                $.messager.alert('提示', "请填写起始时间！");
                return false;
            }

            if(param.startTime!=""&&param.endTime==""){
                $.messager.alert('提示', "请填写结束时间！");
                return false;
            }

            if(param.startTime>param.endTime){
                $.messager.alert('提示', "起始时间不能大于结束时间！");
                return false;
            }
        },
        onLoadSuccess:function(data){
            if (data.error_key=="0") {
                $.messager.alert('提示', data.error_value);
            }
        },
        columns: [[
            {field : 'ck',checkbox : true},
            {title: '消费类型编码', field: 'consumeType', width: 50, hidden: true, align: 'center'},
            {title: '消费类型', field: 'typeName', width: 40, hidden: false, align: 'center',
                formatter:function(value,row,index){
                    return row.type.typeName;
                }
             },
            {title: '消费内容', field: 'consumeName', width: 50, hidden: false, align: 'center'},
            {title: '国家', field: 'nation', width: 50, hidden: true, align: 'center'},
            {title: '省份', field: 'provinceId', width: 50, hidden: true, align: 'center'},
            {title: '地市', field: 'cityId', width: 50, hidden: true, align: 'center'},
            {title: '消费地址', field: 'area', width: 70, hidden: false, align: 'center',
                formatter:function(value,row,index){
                    return row.region.currentName;
                }
            },
            {title: '消费金额',	field:'consumeSum', width:30,hidden:false,align:'center'},
            {title: '消费详情',	field:'consumeInfo', width:80,hidden:false,align:'center'},
            {title: '创建时间', field: 'createTime', width: 80, hidden: false, align: 'center',
                formatter:function(value,row,index){
                    if(value==""||value==null){
                        return "未知";
                    }else{
                        return convdate(value);
                    }
                }
            }
        ]],
    });
});

//消费类型
function consumeType(){
    var type_dict_key = 0;
    $('#consumeType').combobox({
        url: '/select/type',
        method : 'post',
        panelHeight : 200,
        editable : false, //不允许手动输入
        valueField : 'consumeCode',
        textField : 'typeName',
        onLoadSuccess : function() {
            var data = $('#consumeType').combobox('getData');
            if(type_dict_key == 0){
                data.unshift({
                    "consumeCode" : -1,
                    "typeName" : "--请选择--",
                    selected:true
                });
                type_dict_key = 1;
                $('#consumeType').combobox('loadData', data);
            }
        }
    });
}

//国家
function getNation(){
    var dict_key = 0;
    var world = "1";
    $('#nation').combobox({
        url: '/select/nation?world='+ world,
        method : 'post',
        panelHeight : 200,
        editable : false, //不允许手动输入
        valueField : 'currentLevel',
        textField : 'currentName',
        onLoadSuccess : function() {
            var data = $('#nation').combobox('getData');
            if(dict_key == 0){
                data.unshift({
                    "currentLevel" : -1,
                    "currentName" : "--请选择--",
                    selected:true
                });
                dict_key = 1;
                $('#nation').combobox('loadData', data);
            }
        },
        onSelect:function(rec){
            getProvince(rec);
        }
    });
}

function init(){
    var data0 = [{
        "consumeCode" : -1,
        "consumeType" : "--请选择--",
        selected:true
    }];
    $('#provinceId').combobox({
        data: data0,
        editable:false, //不可编辑状态    
        cache: false,
        // panelHeight: '200',//自动高度适合    
        valueField:'consumeCode',
        textField:'consumeType'
    });
    $('#cityId').combobox({
        data: data0,
        editable:false, //不可编辑状态    
        cache: false,
        // panelHeight: '200',//自动高度适合    
        valueField:'consumeCode',
        textField:'consumeType'
    });
}

//省份
function getProvince(rec){
    var province_dict_key = 0;
    var v_con =rec.currentLevel;
    $('#provinceId').combobox({
        url: '/select/province?nation='+v_con,
        method : 'post',
        panelHeight : 200,
        editable : false, //不允许手动输入
        valueField : 'currentLevel',
        textField : 'currentName',
        onLoadSuccess : function() {
            var data = $('#provinceId').combobox('getData');
            if(province_dict_key == 0){
                data.unshift({
                    "currentLevel" : -1,
                    "currentName" : "--请选择--",
                    selected:true
                });
                province_dict_key = 1;
                $('#provinceId').combobox('loadData', data);
            }
        },
        onSelect:function(rec){
            getCity(rec);
        }
    });
}
//地市
function getCity(rec){
    var city_dict_key = 0;
    var v_con =rec.currentLevel;
    $('#cityId').combobox({
        url: '/select/city?province='+v_con,
        method : 'post',
        panelHeight : 200,
        editable : false, //不允许手动输入
        valueField: 'currentLevel',
        textField: 'currentName',
        onLoadSuccess : function() {
            var data = $('#cityId').combobox('getData');
            if (city_dict_key == 0){
                data.unshift({
                    "currentLevel" : -1,
                    "currentName" : "--请选择--",
                    selected:true
                });
                city_dict_key = 1;
                $('#cityId').combobox('loadData', data);
            }
        }
    });
}
//添加表数据
function addData(){
    $('#detail').window({
        title: '添加消费记录',
        width: '80%',
        height: '80%',
        content: '<iframe id = "add" scrolling="auto" frameborder="0" src="/history/toConsumeAdd" style="width:100%;height:99%;overflow-x:hidden;overflow:auto;"></iframe>',
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
            parm.consumeId = row.consumeId;
            $.ajax({
                type: "post",
                url: '/history/deleteConsume',
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
    $('#consumeType').combobox('select',-1);
    $('#nation').combobox('select',-1);
    $('#provinceId').combobox('select',-1);
    $('#cityId').combobox('select',-1);
    $('#minSum').textbox('clear');
    $('#maxSum').textbox('clear');
    $('#startTime').datebox('setValue',"");
    $('#endTime').datebox('setValue',"");
}

//获取消费总额
function getAllSum() {
    var sum = 0;
    var data = $('#dataManage').datagrid('getData').rows;
    for (var i=0;i<data.length;i++){
        var consumeSum = data[i].consumeSum;
        sum += consumeSum;
    }
    $.messager.alert('消费总额：',sum,'info');
}

//当年最高消费
function getMaxSum() {
    parm = {};
    parm.userId = $('#userId').val();
    $.ajax({
        type: "post",
        url: '/history/getMaxSum',
        data: parm,
        async: false,
        success: function (data) {
            $.messager.alert('消费总额：',data.maxSum,'info');
        },
        error: function () {
            $.messager.alert('提示', "查询失败！");
        }
    })
}

//消费最多的月份
function getMaxDate(){
    parm = {};
    parm.userId = $('#userId').val();
    $.ajax({
        type: "post",
        url: '/history/getMaxDate',
        data: parm,
        async: false,
        success: function (data) {
            $.messager.alert('消费总额：',data.maxSum,'info');
        },
        error: function () {
            $.messager.alert('提示', "查询失败！");
        }
    })
}

function previe5wData(){
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