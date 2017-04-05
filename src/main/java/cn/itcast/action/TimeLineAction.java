package cn.itcast.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.TimeLine;
import cn.itcast.searchvo.SearchVO;
import cn.itcast.service.TimeLineService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("timeLineAction")
public class TimeLineAction  extends ActionSupport{
	
	@Autowired
	private TimeLineService timeLineService;
	private int curpage ;
	private String userId;
	private String content;
	private SearchVO searchVO;
	private List<TimeLine> list = new ArrayList<TimeLine>();
	
	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<TimeLine> getList() {
		return list;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@SuppressWarnings("unchecked")
	public String getTimeLine(){
		/*HttpServletRequest request = ServletActionContext.getRequest();
		String username = (String) request.getAttribute("username");*/
		list = timeLineService.getAllTimeLine();
		
		return SUCCESS;
	}
	
	public String save(){
		
		timeLineService.save(content);
		
		return NONE;
	}
	
	public String searchTimeLine(){
		list = timeLineService.getTimeLineyBySearchVO(searchVO);
		
		return SUCCESS;
	}
	
	public String deleteTimeLineById(){
		timeLineService.deleteTimeLineById(userId);
		
		return SUCCESS;
	}
	
	public String pagination(){
		int pagesize = 8;// 每页显示数
		int total = timeLineService.getAllTimeLine().size();//得到记录总数
		// 查询出第curpage页的记录
		list = timeLineService.findOnePage(curpage, pagesize);
		int lastpage = total%pagesize==0?total/pagesize:total/pagesize+1;//得到尾页的值
		HttpServletRequest request = ServletActionContext.getRequest();
		//put into Servlet feild
		request.setAttribute("lastpage",lastpage);
		request.setAttribute("total",total);
		request.setAttribute("curpage",curpage);
		request.setAttribute("nextpage",curpage+1<=total/8+1?curpage+1:lastpage);
		request.setAttribute("prepage",curpage-1>0?curpage-1:1);
		
		return SUCCESS;
	}

}
