package com.demo.automation.bean;

import java.util.Map;

import com.qmetry.qaf.automation.data.BaseFormDataBean;
import com.qmetry.qaf.automation.ui.annotations.UiElement;
import com.qmetry.qaf.automation.ui.annotations.UiElement.Type;

public class FillContactUSForm extends BaseFormDataBean {

	@UiElement(fieldLoc = "text.box.page.contact.us.first.name.loc", fieldType = Type.textbox, order = 1)
	public String firstName;
	@UiElement(fieldLoc = "text.box.page.contact.us.last.name.loc", fieldType = Type.textbox, order = 2)
	public String lastName;
	@UiElement(fieldLoc = "text.box.page.contact.us.business.email.loc", fieldType = Type.textbox, order = 3)
	public String emailAddress;

	public UserDataBean userDataBean;

	public FillContactUSForm() {
		userDataBean = new UserDataBean();
	}
	public void fillData(Map<String, String> data) {
		super.fillData(data);
		userDataBean.fillData(data);
	}
}
