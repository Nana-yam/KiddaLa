package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;

public class CustomerSearchAction extends Object {
//	顧客情報検索処理を管理する
	public String[][]execute(String[] data) throws Exception {
		String[][] tableData = null;
		
        //	半角スペースと全角スペースを取り除く
		data[0] = data[0].replace(" ", "").replace("　", "");
		data[1] = data[1].replace(" ", "").replace("　", "");
		
		ArrayList<Customer> list = null;
		CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
		
		try {
            //	①data[0]が""でなく　かつdata[1]が""の場合
			if (!data[0].equals("") && data[1].equals("")) {
				list = dao.searchCustomerByTel(data[0]);
            //	②data[0]が"" でかつ data[1]が""でない場合
			}else if (data[0].equals("") && !data[1].equals("")) {
				list = dao.searchCustomerByKana(data[1]);
            //　③data[0]が""でなく かつ data[1]の値が""でない場合
			}else if (!data[0].equals("") && !data[1].equals("")) {
				list = dao.searchCustomer(data[0], data[1]);
			}
			
            //	顧客情報リストが取得できた場合、検索結果表示用データに変換する
			if (list != null && list.size() > 0) {
				tableData = new String[list.size()][5];
				
				for (int i = 0; i < list.size(); i++) {
					Customer customer = list.get(i);
					tableData[i][0] = Integer.toString(customer.getCustId());
					tableData[i][1] = customer.getCustName();
					tableData[i][2] = customer.getKana();
					tableData[i][3] = customer.getTel();
					tableData[i][4] = customer.getAddress();	
				}	
			}
		} catch (Exception e) {
			throw new Exception("電話番号と氏名カナのいずれか、または両方を入力してください。", e);
		}
		return tableData;
	}
}

