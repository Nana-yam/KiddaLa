package control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.OrderControlUtility;
import action.CustomerSearchAction;

@WebServlet("/KiddalaController")
public class KiddaLaController extends HttpServlet {
//	Getリクエストに対応する、doPostメソッドを呼び出す
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
			doPost(request,response);
	}
//	POSTリクエストに対応する
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        //	リクエスト情報に文字コードを設定する
		request.setCharacterEncoding("UTF-8");
        //	リクエストパラメータcommandの値を取得する
		String command = request.getParameter("command");
		RequestDispatcher rd = null;
		try {
            //	コマンドがnullと等しいか""と等しい場合は「メインメニュー」画面に遷移する
			if (command == null || command.equals("")) {
				rd = request.getRequestDispatcher("/WEB-INF/jsp/mainMenu.jsp");
				rd.forward(request, response);
            //	CommandがCustomerSearchDisplayの場合、顧客情報検索画面に遷移する
			} else if (command.equals("CustomerSearchDisplay")){
				rd = request.getRequestDispatcher("/WEB-INF/jsp/customerSearch.jsp");
				rd.forward(request, response);
            //	Commandが"CustomerSearch"と等しい場合は，顧客情報検索アクションを実行して「顧客情報検索」画面に遷移する。
			} else if (command.equals("CustomerSearch")){
				String[] data = new String[2];
				data[0] = request.getParameter("tel");
				data[1] = request.getParameter("kana");
				
				CustomerSearchAction action = new CustomerSearchAction();
				String[][] tableData = action.execute(data);
				HttpSession session = request.getSession();
				session.setAttribute("tableData", tableData);
				
				rd = request.getRequestDispatcher("/WEB-INF/jsp/customerSearch.jsp");
				rd.forward(request, response);	
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", e.getMessage());
			rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			rd.forward(request, response);	
		}
	}
}
