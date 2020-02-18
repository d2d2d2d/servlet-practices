package com.douzone.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.douzone.guestbook.vo.GuestbookVo;

public class GuestbookDao {

	public List<GuestbookVo> findAll(){
		List<GuestbookVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no, name, contents, leg_date, password" + 
					" from guestbook"
					+ " order by leg_date desc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			//5. 결과 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String legDate = rs.getString(4);
				String password = rs.getString(5);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setLegDate(legDate);
				vo.setPassword(password);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//6. 자원정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		return result;
	}

	public Boolean insert(GuestbookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert" + 
					" into guestbook" + 
					" values(null, ?, ?, now(), ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getPassword());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//6. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;		
	}
	
	public static Boolean delete(GuestbookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC Driver(MyDriver) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. Statement 객체 생성
			
			//4. SQL문 실행
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			int count = pstmt.executeUpdate();
			
			//5. 성공여부
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//6. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		return conn;

	}

}
