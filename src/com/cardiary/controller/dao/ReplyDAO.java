package com.cardiary.controller.dao;

import java.sql.*;
import java.util.ArrayList;

import com.cardiary.controller.dto.ReplyVO;
import com.cardiary.controller.dto.ReplyviewVO;

import util.DBManager;

public class ReplyDAO {
	private static ReplyDAO instance = new ReplyDAO();
	
	private ReplyDAO() { }
	
	public static ReplyDAO getInstance() {
		return instance;
	}
	
	// 댓글 인서트 메소드
	public void insertReply(ReplyVO reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO reply(rseq, cseq, content, id) "
							  + "VALUES(reply_seq.nextval, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getCseq());
			pstmt.setString(2, reply.getContent());
			pstmt.setString(3, reply.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 댓글 목록 불러오기 메소드
	public ArrayList<ReplyviewVO> listReply(int cseq) {
		ArrayList<ReplyviewVO> replyList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reply_view WHERE cseq=? ORDER BY rseq ASC";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReplyviewVO reply = new ReplyviewVO();
				
				reply.setRseq(rs.getInt("rseq"));;
				reply.setCseq(rs.getInt("cseq"));
				reply.setId(rs.getString("id"));
				reply.setImage(rs.getString("image"));
				reply.setContent(rs.getString("content"));
				
				replyList.add(reply);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return replyList;
	}
}
