package cn.itcast.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class ValidateLoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		if(user != null)
			return invocation.invoke();
			
		return "login";
	}

}
