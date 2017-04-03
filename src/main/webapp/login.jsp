    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
    <%@ taglib uri="/struts-tags" prefix="s" %>  
    <%@ page import="cn.itcast.entity.User" %>  
    <%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
    %>  
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
      <head>  
        <base href="<%=basePath%>">  
        <title><s:text name="title_login"></s:text></title>  
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">  
      </head>  
      <body>  
      <div><s:fielderror /></div>  
        <form action="userAction" method="post" name="form1">  
        <table align="center">  
            <tr>  
                <td><s:text name="username"></s:text></td>  
                <td><input type="text" name="user.username"></td>  
            </tr>  
            <tr>  
                <td><s:text name="password"></s:text></td>  
                <td><input type="password" name="user.password"></td>  
            </tr>  
            <tr>  
                <%--struts标签这样写 <td colspan="2"><s:radio name="user.role" list="#{'0':'普通用户','1':'管理员'}"/></td> --%>  
                <td>普通用户：<input type="radio" name="user.role" value="0"></td>  
                <td>超级用户：<input type="radio" name="user.role" value="1"></td>  
            </tr>  
            <tr>  
                <td><input type="submit" value=<s:text name="submit"></s:text>></td>  
                <td><input type="reset" value=<s:text name="reset"></s:text>>    <input type="button" value=<s:text name="regist"></s:text> onclick="window.location.href='Page/register.jsp'"></td>  
            </tr>  
        </table>  
        <s:actionerror/>  
        </form>  
      </body>  
    </html>  