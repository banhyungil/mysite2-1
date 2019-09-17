package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.itcen.mysite.vo.UserVo;

public class UserDao {
	
	Connection connection;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public UserVo get(String email, String password) {
		connection();
		String sql = "select no, name from user where email=? and password = ?";
		UserVo vo = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
		
	}
	
	public UserVo get(Long no) {
		connection();
		String sql = "select no, name, email, gender from user where no=?";
		UserVo vo = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
		
	}
	
	public Boolean insert(UserVo vo) {	
		Boolean result = false;
		
		connection();
		Statement stmt = null;
		try {
			String sql = "insert into user(no,name,email,password,gender,join_date) values(null,?,?,?,?,now())";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
			
//			stmt = connection.createStatement();				//insert가 잘 되었는지 확인할수 있다.
//			rs = stmt.executeQuery("select last_insert_id()");	//가장 최근에 insert된 key값을 불러온다
//			if(rs.next()) {
//				Long no = rs.getLong(1);
//				vo.setNo(no);
//			}
			System.out.println("insert");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	}
	
	public Boolean update(UserVo vo) {	
		Boolean result = false;
		
		connection();
		Statement stmt = null;
		try {
			String sql = "update user set name=?, email=?, password=?, gender=? where no=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			pstmt.setLong(5, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
			
//			stmt = connection.createStatement();				//insert가 잘 되었는지 확인할수 있다.
//			rs = stmt.executeQuery("select last_insert_id()");	//가장 최근에 insert된 key값을 불러온다
//			if(rs.next()) {
//				Long no = rs.getLong(1);
//				vo.setNo(no);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	}
	
	public void connection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(ConnectionInfo.URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	
}
