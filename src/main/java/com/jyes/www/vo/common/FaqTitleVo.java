package com.jyes.www.vo.common;

import com.jyes.www.vo.AbstractVo;

public class FaqTitleVo extends AbstractVo {
	
	String faq_title_id;
	String title;
	String is_deleted;
	String etc;
	String reg_id;
	String reg_date;
	String mod_id;
	String mod_date;
	public String getFaq_title_id() {
		return faq_title_id;
	}
	public void setFaq_title_id(String faq_title_id) {
		this.faq_title_id = faq_title_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}

}
