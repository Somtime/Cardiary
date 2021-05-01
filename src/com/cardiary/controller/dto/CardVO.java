package com.cardiary.controller.dto;

import java.sql.Timestamp;

public class CardVO {
	private int cseq;
	private String id;
	private String image;
	private String content;
	private Timestamp indate;
	private Timestamp redate;
	
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	public Timestamp getRedate() {
		return redate;
	}
	public void setRedate(Timestamp redate) {
		this.redate = redate;
	}
}
