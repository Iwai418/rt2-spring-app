package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

//@Component
public class LoginCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//リクエストURLを取得
		String requestURL = request.getRequestURI();
		//リクエスト URL が以下の場合、ログインチェックを実施せずリクエスト対象のコントローラの処理に移る
		if (requestURL.endsWith("/") ||
				requestURL.contains("/login") || //①ログイン画面、ログインへの遷移画面への処理も例外に
				requestURL.contains("/html/") ||
				requestURL.contains("/css/") ||
				requestURL.contains("/img/") ||
				requestURL.contains("/js/")) {
			chain.doFilter(request, response);//リクエスト対象外の処理
		} else {
			//セッション情報を取得
			HttpSession session = request.getSession();//→権限フィルタでも使えるgetauthorityが1か2かをsessionから引っ張ってくる

			//セッション情報からのログイン情報（セッション属性loginUser）を取得
			EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");//loginUserの型はIndexContorollerよりF3で
			//ログインしていない場合、ログイン画面へリダイレクトさせる
			if (loginUser == null) {
				response.sendRedirect("/spring_crud");
				return;
			} else {
				chain.doFilter(request, response);
			}
		}
	}
}
//①ログイン画面、ログインへの遷移画面への処理も例外に
//②session.setAttributeで名前つけた属性を呼び出す
//③セッション属性が持つ型でキャスト