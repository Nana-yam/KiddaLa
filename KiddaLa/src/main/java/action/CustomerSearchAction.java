package action;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;
import model.OrderControlUtility;

public class CustomerSearchAction {
//	顧客情報検索処理を管理する
	public String[][]execute(String[] data) throws Exception {
		String[][] tableData = null;
		
        //	半角スペースと全角スペースを取り除く
		String tel = data[0].replace(" ", "").replace("　", "");
		String kana = data[1].replace(" ", "").replace("　", "");
		
		// 011：電話番号と氏名カナの両方が空の場合はエラー
		if (tel.equals("") && kana.equals("")) {
            throw new Exception("電話番号と氏名カナのいずれか、または両方を入力してください。");
        }
		
		ArrayList<Customer> list = null;
		CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
		
         //	①telが""でなく　かつkanaが""の場合
	    if (!tel.equals("") && kana.equals("")) {   	
			list = dao.searchCustomerByTel(tel);
         //	②telが"" でかつ kanaが""でない場合
		}else if (tel.equals("") && !kana.equals("")) {
			list = dao.searchCustomerByKana(kana);
        //　③telが""でなく かつ kanaの値が""でない場合
		}else if (!tel.equals("") && !kana.equals("")) {
			list = dao.searchCustomer(tel, kana);
		}
		
		// 顧客情報リストが取得できた場合、検索結果表示用データに変換する
        if (list != null && list.size() != 0) {
        	tableData = OrderControlUtility.customerToArray(list);	
        }   
		return tableData;
	}
}

