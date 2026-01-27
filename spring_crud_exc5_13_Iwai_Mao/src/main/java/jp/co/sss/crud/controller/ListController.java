package jp.co.sss.crud.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.service.SearchAllEmployeesService;
import jp.co.sss.crud.service.SearchForEmployeesByBranchService;
import jp.co.sss.crud.service.SearchForEmployeesByDepartmentService;
import jp.co.sss.crud.service.SearchForEmployeesByEmpNameService;

@Controller
public class ListController {

	@Autowired
	SearchAllEmployeesService searchAllEmployeesService;

	@Autowired
	SearchForEmployeesByEmpNameService searchForEmployeesByEmpNameService;

	@Autowired
	SearchForEmployeesByDepartmentService searchForEmployeesByDepartmentService;

	@Autowired
	SearchForEmployeesByBranchService searchForEmployeesByBranchService;

	/**
	 * 社員情報を全件検索した結果を出力
	 *
	 * @param model モデル
	 * @return 遷移先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String findAll(Model model) {

		List<EmployeeBean> allEmployeeList = null;
		//searchAllEmployeesServiceクラスのexecuteメソッドを呼び出し実行
		allEmployeeList = searchAllEmployeesService.execute();

		model.addAttribute("employees", allEmployeeList);
		return "list/list";
	}

	/**
	 * 社員情報を社員名検索した結果を出力
	 *
	 * @param empName 検索対象の社員名
	 * @param model モデル
	 * @return 遷移先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/list/empName", method = RequestMethod.GET)
	public String findByEmpName(String empName, Model model) {

		//List<EmployeeBean> searchByEmpNameList;
		//Serviceクラスのexecuteメソッドを呼び出し検索を実行
		List<EmployeeBean> searchByEmpNameList = searchForEmployeesByEmpNameService.execute(empName);

		model.addAttribute("employees", searchByEmpNameList);
		return "list/list";
	}

	/**
	 * 社員情報を部署ID検索した結果を出力
	 *
	 * @param deptId 検索対象の部署ID
	 * @param model モデル
	 * @return 選先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/list/deptId", method = RequestMethod.GET)
	public String findByDeptId(Integer deptId, Model model) {

		List<EmployeeBean> searchByDepartmentList = null;

		//deptIdをServiceクラスへ
		searchByDepartmentList = searchForEmployeesByDepartmentService.execute(deptId);

		model.addAttribute("employees", searchByDepartmentList);
		return "list/list";
	}

	/**
	 * 独自機能：社員情報を支店ID検索した結果を出力
	 *
	 * @param brId 検索対象の支店ID
	 * @param model モデル
	 * @return 選先のビュー
	 * @throws ParseException 
	 */

	@RequestMapping(path = "/list/brId", method = RequestMethod.GET)
	public String findByBrId(Integer brId, Model model) {

		List<EmployeeBean> searchByBranchList = null;

		//deptIdをServiceクラスへ
		searchByBranchList = searchForEmployeesByDepartmentService.execute(brId);

		model.addAttribute("employees", searchByBranchList);
		return "list/list";
	}
}
