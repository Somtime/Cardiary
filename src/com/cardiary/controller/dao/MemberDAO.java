package com.cardiary.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cardiary.controller.dto.MemberVO;

import util.DBManager;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() { }
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	// ���̵� �ߺ� üũ �޼ҵ�  ���� : 1 / ����x : -1
	public int idCheck(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id=?";
		int result = -1;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
	// ȸ�� ���� �޼ҵ�
	public void insertMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(id, pwd, name, email, phone)"
							   + "VALUES(?, ?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	// �α��� �޼ҵ� �α��� : 1/ ��й�ȣ ���� : 0/ ���̵� ���� : -1 
	public int loginCheck(String userid, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT pwd FROM member WHERE id=?";
		int result = -1;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	// ȸ�� ������ �ҷ����� �޼ҵ�
	public MemberVO memberInfo(String userid) {
		MemberVO member = new MemberVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setImage(rs.getString("image"));
				member.setInfo(rs.getString("info"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return member;
	}
	
	// ȸ�� ���� ���� �޼ҵ�
	public void updateMember(String image, String info, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET image=?, info=? WHERE id=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setString(2, info);
			pstmt.setString(3, userid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	public ArrayList<MemberVO> searchMember(String searchid) {
		ArrayList<MemberVO> searchList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id LIKE '%'||?||'%'";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setImage(rs.getString("image"));
				member.setInfo(rs.getString("info"));
				
				searchList.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return searchList;
	}
}
