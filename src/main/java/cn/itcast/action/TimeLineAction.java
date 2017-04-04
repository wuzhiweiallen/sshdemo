package cn.itcast.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.TimeLine;
import cn.itcast.service.TimeLineService;
import cn.itcast.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("timeLineAction")
public class TimeLineAction  extends ActionSupport{
	
	@Autowired
	private TimeLineService timeLineService;
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@SuppressWarnings("unchecked")
	public String getTimeLine(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = (String) request.getAttribute("username");
		List<TimeLine> list = timeLineService.getAllTimeLineByUserId(username);
		ActionContext actionContext = ActionContext.getContext(); 
        
		//get HttpServletRequest 
		/*Map<String,Object> requestMap = (Map<String, Object>) actionContext.get("request");*/ 
		actionContext.put("timeLineList", list); 
		return SUCCESS;
	}
	
	public String save(){
		
		timeLineService.save(content);
		
		return NONE;
	}

}
