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
	
	// ī���� �ҷ����� �޼ҵ�
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
			// ī��
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
				
				// ���
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
				// ��� ��
				
				// ���ƿ�
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
				// ���ƿ� ��
				
				cardList.add(card);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return cardList;
	}
	
	// �� ī�� ����
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
	
	// ���ƿ� �޼ҵ� : card-favorite�� id �Է�
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
	
	// ī���� �ҷ����� by id : ����� ���� ���������� ������ ��
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
	
	// ī�� ���� �޼ҵ� By Cseq
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
			// ī�� ��� �ҷ�����
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
				
				// ���
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
				// ��� ��
				
				// ���ƿ�
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
				// ���ƿ� ��
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return card;
	}
	
	// ī�� ���� �޼ҵ�
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
	
	// ī�� ���� �޼ҵ�
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
