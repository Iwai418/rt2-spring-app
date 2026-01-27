package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Branch;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//ログイン機能
	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);

	//全件検索
	List<Employee> findAllByOrderByEmpId();

	//条件検索①社員IDによる社員名検索
	List<Employee> findByEmpNameContaining(String empName);

	//条件検索②部署名による社員名検索
	List<Employee> findByDepartmentOrderByEmpId(Department department);

	//社員登録はJpaRepositoryがSave機能を保持

	//①社員情報更新:JpaRepositoryがgetReferenceById保持

	//②社員情報更新

	//条件検索:支店による社員名検索
	List<Employee> findByBranchOrderByEmpId(Branch branch);
}
