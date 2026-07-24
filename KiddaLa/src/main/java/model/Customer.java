package model;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
//  5つのprivate属性
	private int custId;
	private String custName;
	private String kana;
	private String tel;
	private String address;
	
//	引数なしコンストラクタ
	public Customer() {		
	}
	
//	引数ありコンストラクタ
	public Customer(int custId,String custName,String kana,String tel,String address) {
		this.custId = custId;
		this.custName = custName;
		this.kana = kana;
		this.tel = tel;
		this.address = address;
	}
	
//	getter/setterメソッド
	public int getCustId() {
		return this.custId;
	}
	public String getCustName(){
		return this.custName;
	}
	public String getKana() {
		return this.kana;
	}
	public String getTel() {
		return this.tel;
	}
	public String getAddress() {
		return this.address;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

