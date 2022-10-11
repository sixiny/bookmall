function $(id){
    return document.getElementById(id);
}

function preRegist(){
    var isflag = true;
    //用户名验证
    var unameReg = /[0-9a-zA-Z]{6,16}/;
    var unameText = $("unameText");
    var uname = unameText.value;
    var unameSpan = $("unameSpan");
    var flag = unameReg.test(uname);
    if(flag){
        unameSpan.style.visibility = "hidden";
    }else{
        unameSpan.style.visibility = "visible";
        return false;
    }

    //密码格式验证
    var pwdReg = /[\w]{8,}/; // /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$/;
    var pwdText = $("pwdText");
    var pwdSpan = $("pwdSpan");
    if(pwdReg.test(pwdText.value)){
        pwdSpan.style.visibility = "hidden";
    }else{
        pwdSpan.style.visibility = "visible";
        isflag = false;
    }

    // 密码一致性验证
    var pwd2Text = $("pwd2Text");
    var pwd2Span = $("pwd2Span");
    if(pwd2Text.value == pwdText.value){
        pwd2Span.style.visibility = "hidden";
    }else{
        pwd2Span.style.visibility = "visible";
        isflag = false;
    }


    // 邮箱格式验证
    var emailReg = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    var emailText = $("email").value;
    var emailSpan = $("emailSpan");
    if(emailReg.test(emailText)){
        emailSpan.style.visibility = "hidden";
    }else{
        emailSpan.style.visibility = "visible";
        isflag = false;
    }


    return isflag;
}

//如果想要发送异步请求   需要一个关键对象 XMLHttpRequest  公用代码 考虑兼容性
var xmlHttpRequest;

function createXMLHttpRequest(){
    // 考虑兼容性 创建问题
    if(window.XMLHttpRequest){
        //符合DOM2标准的浏览器 ，xmlHttpRequest的创建方式
        xmlHttpRequest = new XMLHttpRequest() ;
    }else if(window.ActiveXObject){//IE浏览器
        try{
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP")
        }
    }
}
function ckUname(uName){
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", "user.do?operate=ckUname&uName=" + uName, true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    // 发送请求
    xmlHttpRequest.send();
}
function ckUnameCB(){
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        //xmlHttpRequest.responseText 表示 服务器端响应给我的文本内容
        //alert(xmlHttpRequest.responseText);
        var responseText = xmlHttpRequest.responseText ;
        // {'uname':'1'}
        //alert(responseText);
        if(responseText=="{'uname':'1'}"){
            alert("用户名已经被注册！");
        }else{
            alert("用户名可以注册！");
        }
    }
}