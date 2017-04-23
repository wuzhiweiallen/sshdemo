package cn.itcast.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@Controller("userAction")
@Scope("prototype")  
public class UserAction extends ActionSupport {

	
	@Autowired
	private UserService userService;
	
	private User user;
	private File uploadImage; //得到上传的文件
    private String uploadImageContentType; //得到文件的类型
    private String uploadImageFileName; //得到文件的名称

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login() throws Exception {
		if(user == null){
			return "login";
		}
		boolean loginFlag = userService.login(user.getUsername(), user.getPassword());
		if(!loginFlag)
			return "login";
		
		return SUCCESS;
	}
	
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String imagepath = (String) session.getAttribute("imagepath");
		ServletContext servletContext = ServletActionContext.getServletContext();
		servletContext.removeAttribute(user.getUsername());
		session.removeAttribute("user");
		session.removeAttribute("imagepath");
		
		
		return "login";
	}
	
	public String register(){
		System.out.println("fileName:"+this.getUploadImageFileName());
        System.out.println("contentType:"+this.getUploadImageContentType());
        System.out.println("File:"+this.getUploadImage());
        String fileType = uploadImageFileName.split("\\.")[1];
        this.setUploadImageFileName(user.getUsername()+"."+fileType);
        //获取要保存文件夹的物理路径(绝对路径)
        String realPath = ServletActionContext.getServletContext().getRealPath("images");
		user.setImagePath("/sshdemo/images/"+user.getUsername()+"."+fileType);
        File file = new File(realPath);
        String realPathEclipse = "E:\\sshdemo\\src\\main\\webapp\\images";
        File fileEclipse = new File(realPathEclipse);
        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if(!file.exists())file.mkdirs();
        
        try {
            //保存文件到tomcat下面的路径
            FileUtils.copyFile(uploadImage, new File(file,uploadImageFileName));
            //保存文件在eclipse weorkspace面的项目中。保证项目的内容和tomcat里面是一样的，不然发布后会覆盖掉tomcat里面不一样的内容。
            FileUtils.copyFile(uploadImage, new File(fileEclipse,uploadImageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.register(user);
        
        return "login";
	}
	
	public String updateUserInfo(){
		if(uploadImage != null){
			String fileType = uploadImageFileName.split("\\.")[1];
	        this.setUploadImageFileName(user.getUsername()+"."+fileType);
	        //获取要保存文件夹的物理路径(绝对路径)
	        String realPath = ServletActionContext.getServletContext().getRealPath("images");
			user.setImagePath("/sshdemo/images/"+user.getUsername()+"."+fileType);
	        File file = new File(realPath);
	        String realPathEclipse = "E:\\sshdemo\\src\\main\\webapp\\images";
	        File fileEclipse = new File(realPathEclipse);
	        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
	        if(!file.exists())file.mkdirs();
	        
	        try {
	            //保存文件到tomcat下面的路径
	            FileUtils.copyFile(uploadImage, new File(file,uploadImageFileName));
	            //保存文件在eclipse weorkspace面的项目中。保证项目的内容和tomcat里面是一样的，不然发布后会覆盖掉tomcat里面不一样的内容。
	            FileUtils.copyFile(uploadImage, new File(fileEclipse,uploadImageFileName));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		}
		
		
		userService.updateUser(user);
		
		return SUCCESS;
	}
	
	public String toUpdateUserInfoPage(){
		
		return "updateUserInfo";
	}
	
}
