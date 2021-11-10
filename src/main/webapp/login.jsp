<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>后台管理</title>
  <link href="css/login.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="login_box">
  <div class="login_l_img"><img src="img/login-img.png" /></div>
  <div class="login">
    <div class="login_logo"><a href="#"><img src="img/login_logo.png" /></a></div>
    <div class="login_name">
      <p>睿峰教务后台管理系统</p>
    </div>
    <form onsubmit="return loginChecked()" action="LoginServlet" method="get">
      <input onblur="idChecked()" name="uid" type="text" placeholder="账号">
      <input onblur="pwdChecked()" name="pwd" type="password" placeholder="密码" />
      <div class="checkcode">
        <input type="text" name="checkcode" placeholder="验证码" />
        <img id="checkcodeImg" onclick="changeImg()" src="checkcode" />
        <!--easy-captcha验证码-->
        <%--<img id="checkcodeImg" onclick="changeImg()" src="captcha" width="130px" height="48px" />--%>
      </div>
      <input type="checkbox" name="saveme" /> <label>记住我</label>
      <input onclick="loginFunc()" value="登 录" style="width:100%;" type="submit">
    </form>
    <div id="info"><%=session.getAttribute("errorInfo")%></div>
  </div>
  <div class="copyright">睿峰科技有限公司 版权所有©20219-2030</div>
</div>

<script type="text/javascript" src="js/jquery-1.12.4.min.js" ></script>
<script>

  function changeImg() {
    // 生成一个随机数
    var number = Math.random();
    $("#checkcodeImg").attr("src", "checkcode?" + number);
  }

  // 用户输入框的验证方法
  function idChecked() {
    // 获取用户名表单元素,变量前加上$是为了跟js进行区别，能一眼就看出是jq对象
    var $uInput = $("input[name='uid']");
    // 获取输入框中的数据
    var uname = $uInput.val();
    // 判断当前的数据是否符合要求
    // 用户名称长度3~12
    if (uname.length < 2 || uname.length > 12) {
      checkTextStyle($uInput, "用户id必须是2~12个字符");
      return false;
    }
    checkTextStyle($uInput, "");
    return true;
  }

  // 密码验证
  function pwdChecked() {
    // 获取密码表单元素,变量前加上$是为了跟js进行区别，能一眼就看出是jq对象
    var $pInput = $("input[name='pwd']");
    // 获取输入框中的数据
    var pwd = $pInput.val();
    // 判断当前的数据是否符合要求
    if (pwd == "") {
      // 设置提示方式，设置边框模糊效果
      checkTextStyle($pInput, "密码不能为空");
      return false;
    }
    // 密码长度6~12
    if (pwd.length < 6 || pwd.length > 12) {
      checkTextStyle($pInput, "密码必须是6~12个字符");
      return false;
    }
    checkTextStyle($pInput, "");
    return true;
  }

  // 登陆验证
  function loginChecked() {
    // 当账号和密码的验证函数都是返回true就表示通过验证，可以登录提交数据
    return idChecked()&& pwdChecked();
  }

  // 提醒样式设置
  function checkTextStyle($obj, info) {
    // 设置提示方式，设置边框模糊效果
    if (info == "") {
      $obj.css("box-shadow", "0 0 1px 0px blue");
    } else {
      $obj.css("box-shadow", "0 0 2px 1px red");
    }
    // 获取span标签
    $("#info").text(info);
  }
</script>
</body>
</html>
