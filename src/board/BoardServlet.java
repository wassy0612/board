package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddCommentDAO;
import model.AddCommentLogic;
import model.Board;
import model.FindCommentLogic;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {

    public BoardServlet() {
    	Board board = new Board();
      	board.setName("");
      	board.setComment("");
      	AddCommentDAO ACD = new AddCommentDAO(board);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");

    	// 入力された値を取得
    	String name = request.getParameter("name");
    	String comment = request.getParameter("comment");

    	// JavaBeansに格納
    	Board bo = new Board();
    	bo.setName(name);
    	bo.setComment(comment);

    	// mysqlに格納
    	AddCommentLogic acl = new AddCommentLogic();
    	acl.executeAddComment(bo);

    	// 今入力されたコメントと既存のコメントをmysqlから取得
    	FindCommentLogic fcl = new FindCommentLogic();
    	List<Board> list = fcl.executeFindComment();

    	// セッションスコープにコメントリストを保存
    	HttpSession session = request.getSession();
    	session.setAttribute("listAttribute", list);

        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        rd.forward(request, response);

    }

}