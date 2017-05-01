package cn.itcast.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.Comments;
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
	private int id;
	//存储log的内容，传给save方法
	private String content;
	//表达式封装数据（模型驱动封装不能同时使用属性封装所以在这里选择表达式封装）
	private SearchVO searchVO;
	//放到struts2值栈中
	private List<TimeLine> list = new ArrayList<TimeLine>();
	private List<String> listUsername = new ArrayList<String>();
	//发布说说的图片
	private List<File> timeLineImage;
	private List<String> timeLineImageFileName;
	
	public List<String> getTimeLineImageFileName() {
		return timeLineImageFileName;
	}

	public void setTimeLineImageFileName(List<String> timeLineImageFileName) {
		this.timeLineImageFileName = timeLineImageFileName;
	}

	public List<File> getTimeLineImage() {
		return timeLineImage;
	}

	public void setTimeLineImage(List<File> timeLineImage) {
		this.timeLineImage = timeLineImage;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void save(){
		
		timeLineService.save(timeLineImage,content,timeLineImageFileName);
		
	}
	
	public String searchTimeLine(){
		list = timeLineService.getTimeLineyBySearchVO(searchVO);
		
		return SUCCESS;
	}
	
	public String deleteTimeLineById(){
		timeLineService.deleteTimeLineById(id);
		pagination();
		
		return SUCCESS;
	}
	
	@SuppressWarnings({"all"})
	public String pagination(){
		Map map = timeLineService.pagination(curpage, searchVO);
		list = (List<TimeLine>) map.get("list");
		
		return SUCCESS;
	}
	
	public String saveReply(){
		timeLineService.saveTimeLine(id, content);
		
		return SUCCESS;
	}
	
	public String saveSubReply(){
		timeLineService.saveSubComments(id, content);
		
		return SUCCESS;
	}

}
