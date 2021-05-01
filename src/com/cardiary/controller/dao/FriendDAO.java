package com.cardiary.controller.dao;

import java.sql.*;
import java.util.*;

import com.cardiary.controller.dto.FriendVO;
import com.cardiary.controller.dto.MemberVO;

import util.DBManager;

public class FriendDAO {
	private static FriendDAO instance = new FriendDAO();
	
	private FriendDAO() {}
	
	public static FriendDAO getInstance() {
		return instance;
	}
	
	// 친구 추가
	public void friendAdd(MemberVO loginUser, String friendid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO friend (fseq, id, friendid, friendyn)"
							    + "VALUES(friend_seq.nextval, ?, ?, 'y')";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginUser.getId());
			pstmt.setString(2, friendid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 친구 삭제
	public void friendDelete(MemberVO loginUser, String friendid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE friend SET friendyn='n' WHERE id=? AND friendid=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginUser.getId());
			pstmt.setString(2, friendid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 친구 추가 : freindyn = 'n' 일 때 업데이트로 'y'로 변경
	public void friendUpdate(MemberVO loginUser, String friendid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE friend SET friendyn='y' WHERE id=? AND friendid=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginUser.getId());
			pstmt.setString(2, friendid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 친구 상태 확인 메소드
	public FriendVO getFriend(String id, String friendid) {
		FriendVO friend = new FriendVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM friend WHERE id=? AND friendid=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, friendid);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				friend.setFseq(rs.getInt("fseq"));
				friend.setId(rs.getString("id"));
				friend.setFriendid(rs.getString("friendid"));
				friend.setFriendyn(rs.getString("friendyn"));
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return friend;
	}
	
	// 나에게 친구 추가를 한 유저 조회
	public List<String> friendAddMe(String myid) {
		List<String> fridList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM friend WHERE friendid=? AND friendyn='y'";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String userid = null;
				
				userid = rs.getString("id");
				
				fridList.add(userid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return fridList;
	}
	
	// 내가 친구추가를 한 유저 조회
	public List<String> friendAddByMe(String myid) {
		List<String> fridList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT friendid FROM friend WHERE id=? AND friendyn='y'";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String userid = null;
				
				userid = rs.getString("friendid");
				
				fridList.add(userid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return fridList;
	}
	
	
	
}
