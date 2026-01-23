package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

@Component
public class AccountCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//①フィルターを適用するリンクを限定
		//リクエストURLを取得
		String requestURL = request.getRequestURI();
		//リクエスト URL が以下の場合、ログインチェックを実施せずリクエスト対象のコントローラの処理に移る
		if (requestURL.endsWith("/") || //ログイン画面
				requestURL.contains("/login") || //ログインへの遷移画面
				requestURL.contains("/html/") ||
				requestURL.contains("/css/") ||
				requestURL.contains("/img/") ||
				requestURL.contains("/js/")) {
			chain.doFilter(request, response);//リクエスト対象外の処理

			//ログインしていない場合
		} else if (request.getSession(false) == null) {
			response.sendRedirect("/spring_crud/");
			//②フィルターを適用する人を限定
		} else {
			//セッション情報を取得
			HttpSession session = request.getSession();//getauthorityが1か2をsessionから引っ張ってくる

			//セッション情報からのログイン情報（セッション属性loginUser）を取得＆保存
			EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");//loginUserの型はIndexContorollerよりF3で
			//ログインしていない場合、ログイン画面へリダイレクトさせる
			if (loginUser == null) {
				response.sendRedirect("/spring_crud");
			} else if (loginUser.getAuthority() == 1 &&
			//権限が1（一般）の場合、ログインへリダイレクトさせる（登録・削除・変更不可）
					(requestURL.contains("/input") ||
							requestURL.contains("/update") ||
							requestURL.contains("/delete"))) {
				response.sendRedirect("/spring_crud");
				return;
			} else

			{
				chain.doFilter(request, response);
			}
		}
	}
}

//	①if分を使ってフィルターを適用するリンクを限定
//②if分を使って①の中でフィルターを適用する人を限定
// →②-1：セッションスコープよりセッション情報（ログインした人の情報）を取得
//     -2：セッション情報をEmployeeBean型の変数に保存
//     -3：EmployeeBean型の変数からゲッターを使って権限情報のフィールドの値を取得

//一般権限の社員は、登録・削除・変更の処理不可→トップページにリダイレクトになるフィルターを作成

//requestURL.contains("/login")を消したらログインできない→このクラスではAithority以外反映されないのでは？