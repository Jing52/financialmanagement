var resultDom=document.getElementById("result");
var dotFlag=true;
var opFlag=true;
var equFlag=true;
function command(num){
    if(!equFlag){
        resultDom.value="0";
        equFlag=true;
    }
    opFlag=true;
    var str=resultDom.value;
    str=(str=="0"?"":str);
    resultDom.value=str+num;
};

//+ - * /
function op(op){
    if(opFlag){
        resultDom.value+=op;
        dotFlag=true;
        opFlag=false;
        equFlag=true;
    }
};

//小数点
function dot(){
    if(dotFlag){
        //拿到文本框的值
        var num=resultDom.value;
        resultDom.value=num+".";
        dotFlag=false;
    }
}
//运算
function equal(){
    var num=resultDom.value;
    resultDom.value=eval(num);
    var r=resultDom.value;
    dotFlag=(r.indexOf(".")==-1?true:false);
    opFlag=true;
    equFlag=false;
}
function dzero(){
    var num = resultDom.value;//获取文本框的值
    if(num=="0"){
        return;//如果等返回000
    }
    var str = resultDom.value;
    resultDom.value = str + "00";
};

//清空
function clearzero(){
    resultDom.value="0";
    opFlag=true;
    dotFlag=true;
}

//后退
function del(){
    var val=resultDom.value;
    if(!val){return;}
    var str=val.substring(0,val.length-1);
    if(str && /[\.|\+|\-|\*|\/|\%]$/.test(str)){
        resultDom.value = str.replace(/[\.|\+|\-|\*|\/|\%]$/,"")||0;
        dotFlag = true;//小数点锁打开
    }else{
        resultDom.value = str||0;
    }
}

//百分号
function getPercent(num) {
    var val=resultDom.value;
    var add=val.indexOf("+");
    var sub=val.indexOf("-");
    var mul=val.indexOf("*");
    var divide=val.indexOf("/");
    var max = Math.max(add,sub,mul,divide);
    var lastStr = val.substring(max+1,val.length);
    var beforeStr = val.substring(0,max+1);
    var percent = lastStr/100;
    resultDom.value = beforeStr+percent;
}