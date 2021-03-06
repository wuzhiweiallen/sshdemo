package cn.itcast.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Comments {
	private int cid;
	private String content;
	private String cusername;
	private TimeLine timeLine;
	private Date replytime;
    private Set<SubComments> subCommentsSet = new HashSet<SubComments>();
    
	public Set<SubComments> getSubCommentsSet() {
		return subCommentsSet;
	}
	public void setSubCommentsSet(Set<SubComments> subCommentsSet) {
		this.subCommentsSet = subCommentsSet;
	}
	public Date getReplytime() {
		return replytime;
	}
	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}
	public TimeLine getTimeLine() {
		return timeLine;
	}
	public void setTimeLine(TimeLine timeLine) {
		this.timeLine = timeLine;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCusername() {
		return cusername;
	}
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	
	

}
