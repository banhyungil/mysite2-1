package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.BoardVo;

public class BoardDao {

	Connection connection;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public List<BoardVo> getList(int pageRows){
		connection();
		List<BoardVo> list = new ArrayList<BoardVo>();

		String sql = "select b.*, u.name from board b, user u where b.user_no = u.no order by g_no, o_no limit ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, pageRows);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setgNo(rs.getInt("g_no"));
				vo.setHit(rs.getInt("hit"));
				vo.setNo(rs.getLong("no"));
				vo.setoNo(rs.getInt("o_no"));
				vo.setRegDate(rs.getString("reg_date"));
				vo.setContents(rs.getString("contents"));
				vo.setTitle(rs.getString("title"));
				vo.setUserName(rs.getString("name"));
				vo.setUserNo(rs.getInt("user_no"));
				
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
	
	public List<BoardVo> getList(String keyword, int pageRows) {
		connection();
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		keyword = "%" + keyword + "%";				//Like 연산자를 위함
		String sql = "select b.*, u.name from board b, user u where b.user_no = u.no and (title like ? or contents like ?) order by g_no, o_no limit ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, pageRows);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setContents(rs.getString("contents"));
				vo.setgNo(rs.getInt("g_no"));
				vo.setHit(rs.getInt("hit"));
				vo.setNo(rs.getLong("no"));
				vo.setoNo(rs.getInt("o_no"));
				vo.setRegDate(rs.getString("reg_date"));
				vo.setTitle(rs.getString("title"));
				vo.setUserName(rs.getString("name"));
				vo.setUserNo(rs.getInt("user_no"));
				
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
	
	public Boolean insert(String title, String content, Long userNo) {
		Boolean result = false;
		
		connection();
		
		String sql = "insert into board(no, title, contents, hit, reg_date, g_no, o_no, depth, user_no) values(null,?,?,0,now(), (select * from (select ifnull(max(g_no),0)+1 from board) t_max), 1, 0, ?);";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, userNo);
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	/*
	 * 답글 작성시 insert 구문
	 */
	public Boolean insert(String title, String content, Long userNo, Long boardNo) {
		connection();
		
		Boolean result = false;
		BoardVo vo = get(boardNo);
		
		String sql = "insert into board(no, title, contents, hit, reg_date, g_no, o_no, depth, user_no) values (null,?,?,0,now(), ?, 2, ?, ?)";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, vo.getgNo());
			pstmt.setInt(4, vo.getDepth() + 1);
			pstmt.setLong(5, userNo);
			
			int count = pstmt.executeUpdate();
			result = (count > 0);
			
			update(vo.getgNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	public void connection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(ConnectionInfo.URL,ConnectionInfo.USER, ConnectionInfo.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(stmt != null)
				stmt.close();
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	public BoardVo get(Long no) {
		
		connection();
		
		String sql = "select b.*, u.name from board b, user u where b.user_no = u.no and b.no = ?";
		BoardVo vo = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVo();
				vo.setContents(rs.getString("contents"));
				vo.setgNo(rs.getInt("g_no"));
				vo.setHit(rs.getInt("hit"));
				vo.setNo(rs.getLong("no"));
				vo.setoNo(rs.getInt("o_no"));
				vo.setRegDate(rs.getString("reg_date"));
				vo.setTitle(rs.getString("title"));
				vo.setUserName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

	public Boolean update(Long no, String title, String contents) {
		Boolean result = false;
		
		connection();
		String sql = "update board set title=?, contents=? where no=?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, no);
			int count = pstmt.executeUpdate();
			
			result = (count==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public Boolean update(int gNo) {
		Boolean result = false;
		
		connection();
		String sql = "update board set o_no = o_no + 1 where g_no=?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, gNo);
	
			int count = pstmt.executeUpdate();
			
			result = (count > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	public Boolean delete(Long no) {
		Boolean result = false;
		
		connection();
		String sql = "delete from board where no=?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	

	


}
