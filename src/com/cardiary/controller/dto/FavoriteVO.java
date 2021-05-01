package com.cardiary.controller.dto;

public class FavoriteVO {
	private int fseq;
	private int cseq;
	private String id;
	private String favorite_yn;
	
	public int getFseq() {
		return fseq;
	}
	public void setFseq(int fseq) {
		this.fseq = fseq;
	}
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
	public String getFavorite_yn() {
		return favorite_yn;
	}
	public void setFavorite_yn(String favorite_yn) {
		this.favorite_yn = favorite_yn;
	}
}
