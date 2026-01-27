package jp.co.sss.crud.service;

import java.util.ArrayList;

/**
 * 支店別従業員検索サービスクラス。
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Branch;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;
import jp.co.sss.crud.util.BeanManager;

/**
 * 支店別従業員検索サービスクラス。
 * 指定された支店IDに所属する従業員情報を取得し、EmployeeBeanリストとして返却します。
 * 検索結果は従業員IDの昇順でソートされます。
 * 
 * @author SystemShared Co., Ltd.
 * @version 1.0
 * @since 1.0
 */
@Service
public class SearchForEmployeesByBranchService {
	@Autowired
	EmployeeRepository repository;

	public List<EmployeeBean> execute(Integer brId) {
		//EntityにbrIdフィールドがないためBranch(brId,brName)を代用
		Branch branch = new Branch();
		//ListContorollerよりbrIdを受け取る
		branch.setBrId(brId);
		List<Employee> employeeList = repository.findByBranchOrderByEmpId(branch);
		List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();
		//BeanManagerクラスのメソッドを呼び出し,結果をリストへ
		employeeBeanList = BeanManager.copyEntityListToBeanList(employeeList);
		return employeeBeanList;
	}
}
