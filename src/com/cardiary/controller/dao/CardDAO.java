package com.cardiary.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cardiary.controller.dto.CardVO;
import com.cardiary.controller.dto.CardviewVO;
import com.cardiary.controller.dto.FavoriteVO;
import com.cardiary.controller.dto.ReplyviewVO;

import util.DBManager;

public class CardDAO {
	private static CardDAO instance = new CardDAO();
	
	private CardDAO() { }
	
	public static CardDAO getInstance() {
		return instance;
	}
	
	// 카드목록 불러오기 메소드
	public ArrayList<CardviewVO> listCard(String loginUser) {
		ArrayList<CardviewVO> cardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM card_view ORDER BY cseq DESC";
		
		Connection connr = null;
		PreparedStatement pstmtr = null;
		ResultSet rsr = null;
		String sqlr = "SELECT * FROM reply_view WHERE cseq=? ORDER BY rseq ASC";
		
		Connection connf = null;
		PreparedStatement pstmtf = null;
		ResultSet rsf = null;
		String sqlf = "SELECT * FROM favorite WHERE cseq=? AND id=?";
		
		try {
			// 카드
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardviewVO card = new CardviewVO();
				
				card.setImage_member(rs.getString("image_member"));
				card.setId(rs.getString("id"));
				card.setCseq(rs.getInt("cseq"));
				card.setContent(rs.getString("content"));
				card.setImage_card(rs.getString("image_card"));
				
				// 댓글
				connr = DBManager.getConnection();
				pstmtr = connr.prepareStatement(sqlr);
				pstmtr.setInt(1, rs.getInt("cseq"));
				
				ArrayList<ReplyviewVO> replyList = new ArrayList<>();
				rsr = pstmtr.executeQuery();
				while (rsr.next()) {
					ReplyviewVO reply = new ReplyviewVO();
					
					reply.setRseq(rsr.getInt("rseq"));
					reply.setCseq(rsr.getInt("cseq"));
					reply.setId(rsr.getString("id"));
					reply.setImage(rsr.getString("image"));
					reply.setContent(rsr.getString("content"));
					
					replyList.add(reply);
					card.setReply(replyList);
				}
				
				
				
				DBManager.close(connr, pstmtr, rsr);
				// 댓글 끝
				
				// 좋아요
				connf = DBManager.getConnection();
				pstmtf = connf.prepareStatement(sqlf);
				pstmtf.setInt(1, rs.getInt("cseq"));
				pstmtf.setString(2, loginUser);
				
				ArrayList<FavoriteVO> favoriteList = new ArrayList<>();
				rsf = pstmtf.executeQuery();
				if (rsf.next()) {
					FavoriteVO favorite = new FavoriteVO();
					
					favorite.setFseq(rsf.getInt("fseq"));
					favorite.setCseq(rsf.getInt("cseq"));
					favorite.setId(rsf.getString("id"));
					favorite.setFavorite_yn(rsf.getString("favorite_yn"));
					
					favoriteList.add(favorite);
					card.setFavorite(favoriteList);
				}
				
				
				
				DBManager.close(connf, pstmtf, rsf);
				// 좋아요 끝
				
				cardList.add(card);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return cardList;
	}
	
	// 새 카드 생성
	public void insertCard(CardVO card) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO card(cseq, id, image, content) "
							 + "VALUES(card_seq.nextval, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, card.getId());
			pstmt.setString(2, card.getImage());
			pstmt.setString(3, card.getContent());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	// 좋아요 메소드 : card-favorite에 id 입력
	public void updateFavorite(int cseq, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE card SET favorite=? WHERE cseq=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, cseq);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 카드목록 불러오기 by id : 사용자 정보 페이지에서 보여줄 것
	public ArrayList<CardviewVO> listCardByID(String userid) {
		ArrayList<CardviewVO> cardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM card_view WHERE id=? ORDER BY cseq DESC";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CardviewVO card = new CardviewVO();
				
				card.setImage_member(rs.getString("image_member"));
				card.setId(rs.getString("id"));
				card.setCseq(rs.getInt("cseq"));
				card.setContent(rs.getString("content"));
				card.setImage_card(rs.getString("image_card"));
				
				cardList.add(card);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return cardList;
	}
	
	// 카드 보기 메소드 By Cseq
	public CardviewVO listCardByCseq(int cseq, String loginUser) {
		CardviewVO card = new CardviewVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM card_view WHERE cseq=? ORDER BY cseq DESC";
		
		Connection connr = null;
		PreparedStatement pstmtr = null;
		ResultSet rsr = null;
		String sqlr = "SELECT * FROM reply_view WHERE cseq=? ORDER BY rseq ASC";
		
		Connection connf = null;
		PreparedStatement pstmtf = null;
		ResultSet rsf = null;
		String sqlf = "SELECT * FROM favorite WHERE cseq=? AND id=?";
		
		try {
			// 카드 목록 불러오기
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				card.setImage_member(rs.getString("image_member"));
				card.setId(rs.getString("id"));
				card.setCseq(rs.getInt("cseq"));
				card.setContent(rs.getString("content"));
				card.setImage_card(rs.getString("image_card"));
				
				// 댓글
				connr = DBManager.getConnection();
				pstmtr = connr.prepareStatement(sqlr);
				pstmtr.setInt(1, rs.getInt("cseq"));

				ArrayList<ReplyviewVO> replyList = new ArrayList<>();
				rsr = pstmtr.executeQuery();
				while (rsr.next()) {
					ReplyviewVO reply = new ReplyviewVO();
					
					reply.setRseq(rsr.getInt("rseq"));
					reply.setCseq(rsr.getInt("cseq"));
					reply.setId(rsr.getString("id"));
					reply.setImage(rsr.getString("image"));
					reply.setContent(rsr.getString("content"));
					
					replyList.add(reply);
					
				}
				card.setReply(replyList);
				
				DBManager.close(connr, pstmtr, rsr);
				// 댓글 끝
				
				// 좋아요
				connf = DBManager.getConnection();
				pstmtf = connf.prepareStatement(sqlf);
				pstmtf.setInt(1, cseq);
				pstmtf.setString(2, loginUser);
				
				ArrayList<FavoriteVO> favoriteList = new ArrayList<>();
				rsf = pstmtf.executeQuery();
				while (rsf.next()) {
					FavoriteVO favorite = new FavoriteVO();
					
					favorite.setFseq(rsf.getInt("fseq"));
					favorite.setCseq(rsf.getInt("cseq"));
					favorite.setId(rsf.getString("id"));
					favorite.setFavorite_yn(rsf.getString("favorite_yn"));
					
					favoriteList.add(favorite);
					
				}
				
				card.setFavorite(favoriteList);
				
				DBManager.close(connf, pstmtf, rsf);
				// 좋아요 끝
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return card;
	}
	
	// 카드 수정 메소드
	public void updateCard(CardVO card) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE card SET image=?, content=? WHERE cseq=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, card.getImage());
			pstmt.setString(2, card.getContent());
			pstmt.setInt(3, card.getCseq());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	// 카드 삭제 메소드
	public void deleteCard(int cseq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM card WHERE cseq=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
}
