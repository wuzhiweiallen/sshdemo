package cn.itcast.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.TimeLine;
import cn.itcast.searchvo.SearchVO;
import cn.itcast.service.TimeLineService;

import com.opensymphony.xwork2.ActionSupport;

@Controller("timeLineAction")
@Scope("prototype")  
public class TimeLineAction  extends ActionSupport {
	
	@Autowired
	private TimeLineService timeLineService;
	
	//属性封装
	private int curpage ;
	private String userId;
	//存储log的内容，传给save方法
	private String content;
	//表达式封装数据（模型驱动封装不能同时使用属性封装所以在这里选择表达式封装）
	private SearchVO searchVO;
	//放到struts2值栈中
	private List<TimeLine> list = new ArrayList<TimeLine>();
	private List<String> listUsername = new ArrayList<String>();
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public List<String> getListUsername() {
		return listUsername;
	}

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
		pagination();
		
		return SUCCESS;
	}
	
	/*@SuppressWarnings({"all"})
	public String paginationAjax(){
		int pagesize = 8;// 每页显示数
		// 查询出第curpage页的记录,当第一次登录进来，curpage为0 了，所以默赋值为1，默认显示出第一页的数据。
		if(curpage == 0){
			curpage = 1;
		}
		map = timeLineService.findOnePage(curpage, pagesize,searchVO);
		list = (List<TimeLine>) map.get("list");
		//得到记录总数
		Integer total = (Integer) map.get("count");
		int lastpage = total%pagesize==0?total/pagesize:total/pagesize+1;//得到尾页的值
		//put into map
		map.put("lastpage",lastpage);
		map.put("total",total);
		map.put("curpage",curpage);
		map.put("nextpage",curpage+1<=lastpage?curpage+1:lastpage);
		map.put("prepage",curpage-1>0?curpage-1:1);
		
		map.put("list", list);
		
		return SUCCESS;
	}*/
	
	@SuppressWarnings({"all"})
	public String pagination(){
		int pagesize = 8;// 每页显示数
		// 查询出第curpage页的记录,当第一次登录进来，curpage为0 了，所以默赋值为1，默认显示出第一页的数据。
		if(curpage == 0){
			curpage = 1;
		}
		Map map = timeLineService.findOnePage(curpage, pagesize,searchVO);
		list = (List<TimeLine>) map.get("list");
		//得到记录总数
		Integer total = (Integer) map.get("count");
		int lastpage = total%pagesize==0?total/pagesize:total/pagesize+1;//得到尾页的值
		listUsername = timeLineService.findAllTineLineUsername();
		HttpServletRequest request = ServletActionContext.getRequest();
		//put into Servlet field
		request.setAttribute("lastpage",lastpage);
		request.setAttribute("total",total);
		request.setAttribute("curpage",curpage);
		request.setAttribute("nextpage",curpage+1<=lastpage?curpage+1:lastpage);
		request.setAttribute("prepage",curpage-1>0?curpage-1:1);
		
		return SUCCESS;
	}

}
