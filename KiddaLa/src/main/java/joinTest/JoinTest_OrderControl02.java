package joinTest;

import java.util.ArrayList;

import dao.CustomerSearchDBAccess;
import model.Customer;

// 結合番号2　ドライバクラス
public class JoinTest_OrderControl02 {
    public static void main(String[] args) throws Exception {
        CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
        ArrayList<Customer> list = dao.searchCustomerByTel("09012345678");

        System.out.println("件数=" + list.size());
        if (!list.isEmpty()) {
            Customer c = list.get(0);
            System.out.println(c.getCustId());
            System.out.println(c.getCustName());
            System.out.println(c.getKana());
            System.out.println(c.getTel());
            System.out.println(c.getAddress());
        }
    }
}