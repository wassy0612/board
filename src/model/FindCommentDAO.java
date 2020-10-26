package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindCommentDAO {

	public List<Board> findcomment() {

		// id,name,commentを格納するリスト
		List<Board> list = new ArrayList<>();

		final String jdbcId = "root";
		final String jdbcPass = "Goripanee0321@";
		final String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";

		Connection con = null;

		try {
			con = DriverManager.getConnection(jdbcUrl,jdbcId,jdbcPass);

			System.out.println("FindCommentDAO_Connected...");

			try {
				Statement st = con.createStatement();
				String sql = "select * from board";

				try {
					// sqlを送信
					ResultSet rs = st.executeQuery(sql);

					while (rs.next()) {
						//DBから取り出したid,name,commentをJavaBeansにset
						Board bo = new Board();
						bo.setId(rs.getInt("id"));
						bo.setName(rs.getString("name"));
						bo.setComment(rs.getString("comment"));
						bo.setTime(rs.getTimestamp("time"));

						// リストに1個ずつ格納。末尾に要素が追加されていく
						list.add(bo);
					}

					rs.close();
					st.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
					// データベース接続の切断
					if (con != null) {
						try {
							con.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("FindCommentDAO_Connection faild.");
				return null;
			}
			return list;
		}
	}
