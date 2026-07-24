package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CustomerTest {
	
	@Test
    void test_コンストラクタ_正常値() {
        Customer customer = new Customer(1, "青木まゆみ", "アオキマユミ", "09012345678", "東京都千代田区神田小川町1-1-1");
  
        assertEquals(1, customer.getCustId());
        assertEquals("青木まゆみ", customer.getCustName());
        assertEquals("アオキマユミ", customer.getKana());
        assertEquals("09012345678", customer.getTel());
        assertEquals("東京都千代田区神田小川町1-1-1", customer.getAddress());
    }

    @Test
    void test_コンストラクタ_null値() {
        Customer customer = new Customer(0, null, null, null, null);

        assertEquals(0, customer.getCustId());
        assertNull(customer.getCustName());
        assertNull(customer.getKana());
        assertNull(customer.getTel());
        assertNull(customer.getAddress());
    }

	@ParameterizedTest
	@CsvSource({
		"1,  '青木まゆみ', 'アオキマユミ', '09012345678', '東京都千代田区神田小川町1-1-1'",
        "2,  '伊藤華英',   'イトウハナエ',   '09087654321', '東京都千代田区神田小川町2-1-1'",
        "12, '伊藤英恵',   'イトウハナエ',   '09011112222', '東京都千代田区神田小川町1-4-1'",
        "15, '渡部香生子', 'ワタナベカナコ', '0314142135',  '東京都千代田区神田神保町1-1-1'",
        "16, '瀬戸大也',   'セトダイヤ',     '0314142135',  '東京都千代田区神田神保町1-1-1'",
        "17, '池江璃花子', 'イケエリカコ',   '0314142135',  '東京都千代田区神田神保町1-1-1'"
	})
	void test_setterGetter_複数パターン(int id, String name, String kana, String tel, String address) {
		Customer customer = new Customer();
		customer.setCustId(id);
		customer.setCustName(name);
		customer.setKana(kana);
		customer.setTel(tel);
		customer.setAddress(address);	
		
		assertEquals(id, customer.getCustId());
		assertEquals(name, customer.getCustName());
		assertEquals(kana, customer.getKana());
		assertEquals(tel, customer.getTel());
		assertEquals(address, customer.getAddress());
	}
}
