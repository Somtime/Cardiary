package com.cardiary.controller.dao;

import java.sql.*;
import java.util.ArrayList;

import com.cardiary.controller.dto.FavoriteVO;

import util.DBManager;

public class FavoriteDAO {
	private static FavoriteDAO instance = new FavoriteDAO();
	
	private FavoriteDAO() { }
	
	public static FavoriteDAO getInstance() {
		return instance;
	}
	
	// ���ƿ� �μ�Ʈ
	public void insertFavorite(int cseq, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO favorite(fseq, cseq, id, favorite_yn) "
				  				 + "VALUES(favorite_seq.nextval, ?, ?, 'y')";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.setString(2, userid);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// ���ƿ� ������Ʈ
	public void updateFavorite(int cseq, String userid, String favorite_yn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE favorite SET favorite_yn=? WHERE cseq=? AND id=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite_yn);
			pstmt.setInt(2, cseq);
			pstmt.setString(3, userid);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	// ���ƿ� ��ȸ y:1 ������:0 n:-1
	public int checkFavorite(int cseq, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT favorite_yn FROM favorite WHERE cseq=? AND id=?";
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("favorite_yn").equals("y")) {
					result = 1;
				} else {
					result = -1;
				}
			} else {
				result = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	// ���ƿ� �ҷ����� By Cseq
	public FavoriteVO listFavoriteByCseq(int cseq) {
		FavoriteVO favorite = new FavoriteVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM favorite WHERE cseq=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				favorite.setCseq(rs.getInt("cseq"));
				favorite.setFseq(rs.getInt("fseq"));
				favorite.setId(rs.getString("id"));
				favorite.setFavorite_yn(rs.getString("favorite_yn"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
			
		return favorite;
	}
	
}
