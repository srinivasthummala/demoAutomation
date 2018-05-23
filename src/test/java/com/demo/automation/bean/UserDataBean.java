package com.demo.automation.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;

/**
 * @author
 */
public class UserDataBean extends BaseDataBean {

	@Randomizer(skip = true)
	private String dataType;
	@Randomizer(skip = true)
	private String firstName;
	@Randomizer(skip = true)
	private String lastName;
	// @Randomizer(prefix = "auto.user", suffix = "@ally.com", format = "11111")
	private String emailAddress;
	@Randomizer(skip = true)
	private String result;

	private String userName;

	private String password;

	private String saveUserName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSaveUserName() {
		return saveUserName;
	}
	public void setSaveUserName(String saveUserName) {
		this.saveUserName = saveUserName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getDataType() {
		return dataType;
	}
	public String getResult() {
		return result;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
