package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Customer;

class CustomerSearchDBAccessTest {
	private final CustomerSearchDBAccess dbAccess = new CustomerSearchDBAccess();

    // ===== searchCustomerByTel =====

    // 項番1【正常系】DBに存在する電話番号を指定する
    @Test
    void test_電話番号_存在する場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomerByTel("09012345678");
        assertFalse(result.isEmpty());
    }

    // 項番2【正常系】DBに存在しない電話番号を指定する
    @Test
    void test_電話番号_存在しない場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomerByTel("00000000000");
        assertTrue(result.isEmpty());
    }

    // 項番3【異常系】電話番号にnullを指定する
    @Test
    void test_電話番号_nullの場合() {
        assertThrows(Exception.class, () -> dbAccess.searchCustomerByTel(null));
    }

    // searchCustomerByKana
    
    // 項番4【正常系】DBに存在するカナ名を指定する
    @Test
    void test_カナ名_存在する場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomerByKana("イトウ");
        assertTrue(result.size() >= 2);
    }

    // 項番5【正常系】DBに存在しないカナ名を指定する
    @Test
    void test_カナ名_存在しない場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomerByKana("XXXXXX");
        assertTrue(result.isEmpty());
    }

    // 項番6【異常系】カナ名にnullを指定する
    @Test
    void test_カナ名_nullの場合() {
        assertThrows(Exception.class, () -> dbAccess.searchCustomerByKana(null));
    }

    // searchCustomer(tel, kana)

    // 項番7【正常系】telとkana、両方DBに存在する値を指定する
    @Test
    void test_telとkanaどちらも存在する場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomer("0314142135", "ワタナベ");
        assertEquals(1, result.size());
    }

    // 項番8【正常系】telのみ存在、kanaにnullを指定する
    @Test
    void test_telのみ指定の場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomer("09012345678", null);
        assertFalse(result.isEmpty());
    }

    // 項番9【正常系】telにnull、kanaのみ存在する値を指定する
    @Test
    void test_kanaのみ指定の場合() throws Exception {
        ArrayList<Customer> result = dbAccess.searchCustomer(null, "アオキマユミ");
        assertFalse(result.isEmpty());
    }
    
    // 項番10【異常系】tel・kanaともにnullを指定する
    @Test
    void test_telとkanaどちらもnullの場合() {
        assertThrows(Exception.class, () -> dbAccess.searchCustomer(null, null));
    }
}

