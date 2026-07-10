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

@WebServlet("/KiddaLaController")
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
				rd = request.getRequestDispatcher("/MainMenu.jsp");
				rd.forward(request, response);
            //	CommandがCustomerSearchDisplayの場合、顧客情報検索画面に遷移する
			} else if (command.equals("CustomerSearchDisplay")){
				HttpSession session = request.getSession();
			    session.removeAttribute("tableData");   // 前回の検索結果をクリア
				rd = request.getRequestDispatcher("/CustomerSearch.jsp");
				rd.forward(request, response);
            //	Commandが"CustomerSearch"と等しい場合は，顧客情報検索アクションを実行して「顧客情報検索」画面に遷移する。
			} else if (command.equals("CustomerSearch")){
				String[] data = new String[2];
				data[0] = request.getParameter("tel");
				data[1] = request.getParameter("kana");
				
				HttpSession session = request.getSession();
				
				try {
					CustomerSearchAction action = new CustomerSearchAction();
			        String[][] tableData = action.execute(data);

			        if (tableData == null) {
			            // 012：該当する顧客情報が0件
			            request.setAttribute("errorCode", "012");
			            request.setAttribute("errorMessage", "一致する情報は見つかりませんでした。");
			            session.removeAttribute("tableData");
			        } else {
			            session.setAttribute("tableData", tableData);
			        }

			        rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			        rd.forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
			        String msg = e.getMessage();
			        
			        if (msg != null && msg.contains("いずれか、または両方")) {
			            // 011：入力不足 → 検索画面にとどまる
			            request.setAttribute("errorCode", "011");
			            request.setAttribute("errorMessage", msg);
			            rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			            rd.forward(request, response);

			        } else if (msg != null && msg.contains("DB接続処理")){
			        	// 010：DB接続失敗 → 共通エラー画面へ
			            request.setAttribute("errorCode", "010");
			            request.setAttribute("errorMessage", msg);
			            rd = request.getRequestDispatcher("/Error.jsp");
			            rd.forward(request, response);
			        } else {
			        	// 013：それ以外の検索処理失敗 → 検索画面にとどまる
			            request.setAttribute("errorCode", "013");
			            request.setAttribute("errorMessage", "顧客情報検索処理に失敗しました！管理者に連絡してください。");
			            rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			            rd.forward(request, response);
			        }
				}
			}
		} catch (Exception e){
			e.printStackTrace();

		    String msg = e.getMessage();
		    String errorCode;

		    if (msg != null && msg.contains("DB接続処理")) {
		        errorCode = "010";
		    } else if (msg != null && msg.contains("いずれか、または両方")) {
		        errorCode = "011";
		    } else if (msg != null && msg.contains("一致する情報")) {
		        errorCode = "012";
		    } else if (msg != null && msg.contains("検索処理に失敗")) {
		        errorCode = "013";
		    } else {
		        errorCode = "999";
		    }
		    
		    request.setAttribute("errorCode", errorCode);
		    request.setAttribute("errorMessage", msg);
			rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);	
		}
	}
}
