<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Sign-Up/Login Form</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>

  
      <link rel="stylesheet" href="../css/StudentLogin.css">
      <script type="text/javascript">  
  $(document).ready(function() {
	  
	  $("input[name='IDcard']").blur(function() { //失去焦点
		  
    	  var IDstr = $(this).val();
    	 // $("input[name='loginname']").val(IDstr);
    	  var regstr1 = new RegExp("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
    	  if(!regstr1.test(IDstr)) {
    	 // $(this).parent().next().html("身份证号不合法");
    	   //$("input[name='loginname']").val("no");
    	   alert("身份证号不合法");
    	   return false;
    	  }
    	  else{
    		  return true;
    	  }
    	  });
	  $("input[name='loginname']").blur(function() { //失去焦点
    	  var lgnamestr = $(this).val();
    	  var regstr2 = new RegExp("^[a-zA-Z_]\\w{5,15}$");
    	  if(!regstr2.test(lgnamestr)) {
    	   //$(this).html("用户名不合法");
    	   alert("用户名不合法");
    	   return false;
    	  }
    	  else{
    		  return true;
    	  }

    	  });
	    
	  $("input[name='password']").blur(function() { //失去焦点
    	  var pswstr = $(this).val();
    	  var regstr3 =new RegExp("^([A-Z]|[a-z]|[0-9]|['-=\\[\\];,\\./~!@#\\$%\\^\\*\\(\\)_\\+\\}\\{:\\?]){6,20}$");
    	  if(!regstr3.test(pswstr)) {
    	   //$(this).html("密码不合法");
    	   alert("密码不合法");
    	   return false;
    	  }
    	  else{
    		  return true;
    	  }
    	  });
	  
	  
	  $("input[name='tel']").blur(function() { //失去焦点
    	  var telstr = $(this).val();
    	  var regstr4 = new RegExp("(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$");
    	  if(!regstr4.test(telstr)) {
    	   //$(this).html("电话号码不合法");
    	   alert("电话号码不合法");
    	   return false;
    	  }
    	  else{
    		  return true;
    	  }
    	  });
	  
	  //获得输入的用户名内容
	var loginname = $("input[name='loginname']");
	
	// 姓名的正则表达式
	var reg = new RegExp('[a-zA-Z_]\\w{5,15}','g');
	  
	// 获得要提示内容的标签元素
	var notice = $("#loginname");
	
	loginname.blur(function() {
		if(reg.test(loginname.val())){
			// 姓名满足6,16的正则表达式
			$.ajax({
				   type: "get",
				   url: "${pageContext.request.contextPath}/reg/user_valid",
				   data: {"loginname":loginname.val()},
				   async:true,
				   success: function(msg){
				     if(msg == "exist"){
				    	 // 姓名已存在
				    	 notice.html("<font color='red'>用户名已存在</font>");
				     }
				     else {
				    	  // 姓名不存在
				    	 notice.html("<font color='green'>用户名可以使用</font>");
					 }
				   }
			});
		}
		else {
			// 不满足正则表达式
			notice.html("<font color='red'>用户名不合法</font>");
		}
	});
  });
	  
</script>

</head>

<body>
  <div class="form" >
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">注册</a></li>
        <li class="tab"><a href="#login">登录</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>学生注册</h1>
          
          <form action="../register" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                	身份证号<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="IDcard" id="IDcard" />
            </div>
        
            <div class="field-wrap">
              <label>
                	姓名<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="Lname" id="notice_uname"/>
            </div>
          </div>

					<div class="top-row">
            <div class="field-wrap">
              <label>
                	用户账号<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="loginname" id="loginname"/>
            </div>
        
            <div class="field-wrap">
              <label>
                	密码<span class="req">*</span>
              </label>
              <input type="text"required autocomplete="off" name="Lpassword" id='password'/>
            </div>
          </div>

					<div class="top-row">
            <div class="field-wrap">
              <label>
                	生日<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="Lage" />
            </div>
        
            <div class="field-wrap">
              <div class="form-group">
                <label for="name">性别</label>
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			<select class="form-control" name="sex">
      				<option>男</option>
      				<option>女</option>
    			</select>
    		  </div>
            </div>
          </div>

          
          <div class="field-wrap">
            <label>
              	电话<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="tel" id='tel'/>
          </div>
          
          <button type="submit" class="button button-block"/>注册 </button>
          
          </form>

        </div>
        
        <div id="login">   
          <h1>学生登录</h1>
          
          <form action="../login?type=1" method="post">
          
            <div class="field-wrap">
            <label>
              	用户名<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="Sname"/>
          </div>
          
          <div class="field-wrap">
            <label>
              	密码<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="Password"/>
          </div>
          
          <p class="forgot"><a href="#">忘记密码?</a></p>
          
          <button class="button button-block"/>登录</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="../js/StudentLogin.js"></script>

</body>
</html>
