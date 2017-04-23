package cn.itcast.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TimeLine implements Serializable{
	
	private Integer id;
	private String username;
	private String content;
	private Date logTime;
	private String recordStatus;
	private String imageName;
	private String[] fileNames;
	private String[] commentsArray;
	private Set<Comments> commentsSet = new HashSet<Comments>();

	public String[] getCommentsArray() {
		return commentsArray;
	}
	public void setCommentsArray(String[] commentsArray) {
		this.commentsArray = commentsArray;
	}
	public Set<Comments> getCommentsSet() {
		return commentsSet;
	}
	public void setCommentsSet(Set<Comments> commentsSet) {
		this.commentsSet = commentsSet;
	}
	public String[] getFileNames() {
		return fileNames;
	}
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
	

}
