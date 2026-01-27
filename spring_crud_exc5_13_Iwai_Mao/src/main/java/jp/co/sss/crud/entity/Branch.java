package jp.co.sss.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch {
	@Id
	private Integer brId;
	@Column
	private String brName;

	/**
	 * @return brId
	 */
	public Integer getBrId() {
		return brId;
	}

	/**
	 * @param brId セットする brId
	 */
	public void setBrId(Integer brId) {
		this.brId = brId;
	}

	/**
	 * @return brName
	 */
	public String getBrName() {
		return brName;
	}

	/**
	 * @param brName セットする brName
	 */
	public void setBrName(String brName) {
		this.brName = brName;
	}

}
