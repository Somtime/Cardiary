package com.cardiary.controller.dto;

public class ReplyviewVO {
	private int rseq;
	private int cseq;
	private String id;
	private String image;
	private String content;
	
	public int getRseq() {
		return rseq;
	}
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public void setRseq(int rseq) {
		this.rseq = rseq;
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
}
