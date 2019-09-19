package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.GuestbookVo;

public class GuestbookDao2 {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Boolean delete(GuestbookVo vo) {
		Boolean result = false;
		try {
			
			connection();
			String sql = "delete from * where no=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			int count = pstmt.executeUpdate();
			result = (count == 0);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	public Boolean insert(GuestbookVo vo) {
		try {
			connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
		
	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		try {
			connection();
			String sql = "select * from guestbook";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookVo vo = new GuestbookVo();
				vo.setContents(rs.getString("contents"));
				vo.setName(rs.getString("name"));
				vo.setNo(rs.getLong("no"));
				vo.setPassword(rs.getString("password"));
				vo.setRegDate(rs.getString("reg_date"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
		
	}
	
	private void connection() throws SQLException {
		try {
			Class.forName(ConnectionInfo.URL);
			connection = DriverManager.getConnection(ConnectionInfo.URL,ConnectionInfo.USER, ConnectionInfo.PASSWORD);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void close() {
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		
	}
}	
