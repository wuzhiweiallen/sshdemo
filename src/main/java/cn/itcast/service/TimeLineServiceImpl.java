package cn.itcast.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.TimeLineDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.Comments;
import cn.itcast.entity.SubComments;
import cn.itcast.entity.TimeLine;
import cn.itcast.entity.User;
import cn.itcast.searchvo.SearchVO;

@Transactional
@Service("timeLineService")
public class TimeLineServiceImpl implements TimeLineService {

	@Autowired
	private TimeLineDao timeLineDao;
	@Autowired
	private UserDao userDao;

	public void save(List<File> timeLineImage,String content,List<String> timeLineImageFileName) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		for(int i=0 ; i<timeLineImage.size() ; i++){
			if(timeLineImage.get(i) != null){
				String fileType = timeLineImageFileName.get(i).split("\\.")[1];
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date date = new Date();
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-");
				timeLineImageFileName.set(i, df.format(date)+user.getUsername()+"."+fileType);
		        //获取要保存文件夹的物理路径(绝对路径)
		        String realPath = ServletActionContext.getServletContext().getRealPath("timeline");
				/*user.setImagePath("/sshdemo/images/"+user.getUsername()+"."+fileType);*/
		        File file = new File(realPath);
		        String realPathEclipse = "E:\\sshdemo\\src\\main\\webapp\\images\\timeline";
		        File fileEclipse = new File(realPathEclipse);
		        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
		        if(!file.exists())file.mkdirs();
		        
		        try {
		            //保存文件到tomcat下面的路径
		            FileUtils.copyFile(timeLineImage.get(i), new File(file,timeLineImageFileName.get(i)));
		            //保存文件在eclipse weorkspace面的项目中。保证项目的内容和tomcat里面是一样的，不然发布后会覆盖掉tomcat里面不一样的内容。
		            FileUtils.copyFile(timeLineImage.get(i), new File(fileEclipse,timeLineImageFileName.get(i)));
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
			}
		}
		
		TimeLine timeLine = new TimeLine();
		timeLine.setContent(content);
		Date date = new Date();
		timeLine.setLogTime(date);
		timeLine.setUsername(user.getUsername());
		timeLine.setRecordStatus("A");
		timeLine.setImageName(getTimeLineImageFileName(timeLineImageFileName));
		timeLineDao.save(timeLine);

	}
	
	public String getTimeLineImageFileName(List<String> timeLineImageFileNames){
		String timeLineImageFileNameString = "";
		for(String timeLineImageFileName : timeLineImageFileNames){
			if("".equals(timeLineImageFileNameString)){
				timeLineImageFileNameString += timeLineImageFileName;
			}else{
				timeLineImageFileNameString = timeLineImageFileNameString+","+timeLineImageFileName;
			}
		}
		
		return timeLineImageFileNameString;
	}

	public List<TimeLine> getAllTimeLineByUserame(String username) {
		return timeLineDao.getAllTimeLineByUserame(username);
	}

	public List<TimeLine> getTimeLineyBySearchVO(SearchVO searchVO) {
		List<TimeLine> list = timeLineDao.getTimeLine(searchVO);

		return list;
	}

	public void deleteTimeLineById(int id) {
		timeLineDao.deleteTimeLineById(id);
	}

	public Map findOnePage(int offset, int pagesize,SearchVO searchVO) {
		List<TimeLine> list = new ArrayList<TimeLine>();
		Map map = timeLineDao.findOnePage(offset, pagesize,searchVO);
		List<TimeLine> lst = (List<TimeLine>) map.get("list");
		for(TimeLine timeLine : lst){
			if(timeLine.getImageName().indexOf(",") == -1){
				String[] filePath = {timeLine.getImageName()};
				timeLine.setFileNames(filePath);
			}else{
				String[] fileNames = timeLine.getImageName().split(",");
				timeLine.setFileNames(fileNames);
			}
			list.add(timeLine);
			
		}
		map.put("list", list);
		
		return map;
	}
	
	public List<TimeLine> getAllTimeLine() {
		List<TimeLine> list = timeLineDao.getAllTimeLine();
		
		return list;
	}
	
	public List<String> findAllTineLineUsername(){
		List<String> list = timeLineDao.findAllTineLineUsername();
		
		return list;
	}
	
	public Map pagination(Integer curpage,SearchVO searchVO){
		int pagesize = 8;// 每页显示数
		// 查询出第curpage页的记录,当第一次登录进来，curpage为0 了，所以默赋值为1，默认显示出第一页的数据。
		if(curpage == 0){
			curpage = 1;
		}
		Map map = findOnePage(curpage, pagesize,searchVO);
		//得到记录总数
		Integer total = (Integer) map.get("count");
		int lastpage = total%pagesize==0?total/pagesize:total/pagesize+1;//得到尾页的值
		List<String> listUsername = findAllTineLineUsername();
		map.put("listUsername", listUsername);
		HttpServletRequest request = ServletActionContext.getRequest();
		//put into Servlet field
		request.setAttribute("lastpage",lastpage);
		request.setAttribute("total",total);
		request.setAttribute("curpage",curpage);
		request.setAttribute("nextpage",curpage+1<=lastpage?curpage+1:lastpage);
		request.setAttribute("prepage",curpage-1>0?curpage-1:1);
		
		return map;
	}
	
	public void saveTimeLine(int id,String content){
		TimeLine timeLine = timeLineDao.getTimeLineById(id);
		HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
		HttpSession Session = request.getSession();
		User user = (User) Session.getAttribute("user");
		Comments comments = new Comments();
		comments.setContent(content);
		comments.setTimeLine(timeLine);
		comments.setCusername(user.getUsername());
		Date date = new Date();
		comments.setReplytime(date);
		timeLineDao.saveComments(comments);
	}
	
	public void saveSubComments(int cid,String content){
		Comments comments = timeLineDao.getSubCommentsById(cid);
		HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
		HttpSession Session = request.getSession();
		User user = (User) Session.getAttribute("user");
		SubComments subComments = new SubComments();
		subComments.setContent(content);
		Date date = new Date();
		subComments.setReplytime(date);
		subComments.setComments(comments);
		subComments.setSusername(user.getUsername());
		timeLineDao.saveSubComments(subComments);
	}
	/*//得到拼接后的comments
	public String getComments(String comments,String content){
		String commentsAfterProcess = "";
		if(comments == null || "".equals(comments)){
			commentsAfterProcess  = content;
		}else{
			commentsAfterProcess = comments+","+content;
		}
		
		return commentsAfterProcess;
		
	}*/

}
