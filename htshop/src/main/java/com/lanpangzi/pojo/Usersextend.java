package com.lanpangzi.pojo;

public class Usersextend {
    private Integer eid;

    private String sex;

    private String idcard;

    private String idcardA;

    private String idcardB;

    private String idcardC;

    private String address;

    private String education;

    private String marriage;

    private String job;

    private String income;

    private String working;

    private String contactname;

    private String contactphone;

    private String relation;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdcardA() {
        return idcardA;
    }

    public void setIdcardA(String idcardA) {
        this.idcardA = idcardA == null ? null : idcardA.trim();
    }

    public String getIdcardB() {
        return idcardB;
    }

    public void setIdcardB(String idcardB) {
        this.idcardB = idcardB == null ? null : idcardB.trim();
    }

    public String getIdcardC() {
        return idcardC;
    }

    public void setIdcardC(String idcardC) {
        this.idcardC = idcardC == null ? null : idcardC.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working == null ? null : working.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

	@Override
	public String toString() {
		return "Usersextend [eid=" + eid + ", sex=" + sex + ", idcard=" + idcard + ", idcardA=" + idcardA + ", idcardB="
				+ idcardB + ", idcardC=" + idcardC + ", address=" + address + ", education=" + education + ", marriage="
				+ marriage + ", job=" + job + ", income=" + income + ", working=" + working + ", contactname="
				+ contactname + ", contactphone=" + contactphone + ", relation=" + relation + "]";
	}
    
}