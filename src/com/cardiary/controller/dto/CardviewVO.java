package com.cardiary.controller.dto;

import java.util.ArrayList;

public class CardviewVO {
	private String image_member;
	private String id;
	private int cseq;
	private String content;
	private String image_card;
	private ArrayList<FavoriteVO> favorite;
	private ArrayList<ReplyviewVO> reply;
	
	public String getImage_member() {
		return image_member;
	}
	public void setImage_member(String image_member) {
		this.image_member = image_member;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage_card() {
		return image_card;
	}
	public void setImage_card(String image_card) {
		this.image_card = image_card;
	}
	public ArrayList<FavoriteVO> getFavorite() {
		return favorite;
	}
	public void setFavorite(ArrayList<FavoriteVO> favorite) {
		this.favorite = favorite;
	}
	public ArrayList<ReplyviewVO> getReply() {
		return reply;
	}
	public void setReply(ArrayList<ReplyviewVO> reply) {
		this.reply = reply;
	}
}
