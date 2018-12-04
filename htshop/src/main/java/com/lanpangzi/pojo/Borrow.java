package com.lanpangzi.pojo;


public class Borrow {
    private Integer bid;

    private String uid;

    private String discern;

    private String discernState;

    private String bankA;

    private String bankB;

    private String bankState;

    private String operatorPhone;

    private String operatorIdcard;

    private String operatorName;

    private String isAgreement;

    private String operatorState;

    private String taobaoToken;

    private String taobaoState;

    private String alipayToken;

    private String alipayState;

    private String payA;

    private String payB;

    private String payC;

    private String payD;

    private String payE;

    private String payState;

    private String auditor;

    private String borrowState;

    private String bankNumber;
    
    public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	private String bankPhone;

    public Borrow() {}
	public Borrow(String uid, String bankNumber, String bankPhone) {
		super();
		this.uid = uid;
		this.bankNumber = bankNumber;
		this.bankPhone = bankPhone;
	}
	public Borrow(String operatorPhone, String operatorIdcard, String operatorName, String isAgreement) {
		this.operatorPhone = operatorPhone;
		this.operatorIdcard = operatorIdcard;
		this.operatorName = operatorName;
		this.isAgreement = isAgreement;
	}

	public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getDiscern() {
        return discern;
    }

    public void setDiscern(String discern) {
        this.discern = discern == null ? null : discern.trim();
    }

    public String getDiscernState() {
        return discernState;
    }

    public void setDiscernState(String discernState) {
        this.discernState = discernState == null ? null : discernState.trim();
    }

    public String getBankA() {
        return bankA;
    }

    public void setBankA(String bankA) {
        this.bankA = bankA == null ? null : bankA.trim();
    }

    public String getBankB() {
        return bankB;
    }

    public void setBankB(String bankB) {
        this.bankB = bankB == null ? null : bankB.trim();
    }

    public String getBankState() {
        return bankState;
    }

    public void setBankState(String bankState) {
        this.bankState = bankState == null ? null : bankState.trim();
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone == null ? null : operatorPhone.trim();
    }

    public String getOperatorIdcard() {
        return operatorIdcard;
    }

    public void setOperatorIdcard(String operatorIdcard) {
        this.operatorIdcard = operatorIdcard == null ? null : operatorIdcard.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getIsAgreement() {
        return isAgreement;
    }

    public void setIsAgreement(String isAgreement) {
        this.isAgreement = isAgreement == null ? null : isAgreement.trim();
    }

    public String getOperatorState() {
        return operatorState;
    }

    public void setOperatorState(String operatorState) {
        this.operatorState = operatorState == null ? null : operatorState.trim();
    }

    public String getTaobaoToken() {
        return taobaoToken;
    }

    public void setTaobaoToken(String taobaoToken) {
        this.taobaoToken = taobaoToken == null ? null : taobaoToken.trim();
    }

    public String getTaobaoState() {
        return taobaoState;
    }

    public void setTaobaoState(String taobaoState) {
        this.taobaoState = taobaoState == null ? null : taobaoState.trim();
    }

    public String getAlipayToken() {
        return alipayToken;
    }

    public void setAlipayToken(String alipayToken) {
        this.alipayToken = alipayToken == null ? null : alipayToken.trim();
    }

    public String getAlipayState() {
        return alipayState;
    }

    public void setAlipayState(String alipayState) {
        this.alipayState = alipayState == null ? null : alipayState.trim();
    }

    public String getPayA() {
        return payA;
    }

    public void setPayA(String payA) {
        this.payA = payA == null ? null : payA.trim();
    }

    public String getPayB() {
        return payB;
    }

    public void setPayB(String payB) {
        this.payB = payB == null ? null : payB.trim();
    }

    public String getPayC() {
        return payC;
    }

    public void setPayC(String payC) {
        this.payC = payC == null ? null : payC.trim();
    }

    public String getPayD() {
        return payD;
    }

    public void setPayD(String payD) {
        this.payD = payD == null ? null : payD.trim();
    }

    public String getPayE() {
        return payE;
    }

    public void setPayE(String payE) {
        this.payE = payE == null ? null : payE.trim();
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public String getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(String borrowState) {
        this.borrowState = borrowState == null ? null : borrowState.trim();
    }

	@Override
	public String toString() {
		return "Borrow [bid=" + bid + ", uid=" + uid + ", discern=" + discern + ", discernState=" + discernState
				+ ", bankA=" + bankA + ", bankB=" + bankB + ", bankState=" + bankState + ", operatorPhone="
				+ operatorPhone + ", operatorIdcard=" + operatorIdcard + ", operatorName=" + operatorName
				+ ", isAgreement=" + isAgreement + ", operatorState=" + operatorState + ", taobaoToken=" + taobaoToken
				+ ", taobaoState=" + taobaoState + ", alipayToken=" + alipayToken + ", alipayState=" + alipayState
				+ ", payA=" + payA + ", payB=" + payB + ", payC=" + payC + ", payD=" + payD + ", payE=" + payE
				+ ", payState=" + payState + ", auditor=" + auditor + ", borrowState=" + borrowState + ", bankNumber="
				+ bankNumber + ", bankPhone=" + bankPhone + "]";
	}
    
}