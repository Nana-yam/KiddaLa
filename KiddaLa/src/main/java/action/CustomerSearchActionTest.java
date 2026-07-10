package action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerSearchActionTest {
	CustomerSearchAction action = new CustomerSearchAction();

//	①tel,kana両方入力
	@Test
	void testExecute_telAndKana() throws Exception {
		String[] data = {"0314142135", "ワタナベ"};
		String[][] result = action.execute(data);
		assertEquals(1, result.length);
	}
	
//	②telのみ入力
	@Test
	void testExecute_telOnly() throws Exception {
		String[] data = {"09012345678",""};
		String[][] result = action.execute(data);
		assertEquals(1, result.length);
	}
	
//	③kanaのみ入力
	@Test
	void testExecute_kanaOniy() throws Exception {
		String[] data = {"", "イトウハナエ"};
		String[][] result = action.execute(data);
		assertEquals(2, result.length);
	}
	
//	④該当なし（nullが返るか）
    @Test
    void testExecute_notFound() throws Exception {
        String[] data = {"00000000000", ""};
        String[][] result = action.execute(data);
        assertNull(result);
    }

 // ⑤前後・間にスペースが入っていてもtrim/除去して検索できるか
    @Test
    void testExecute_withSpaces() throws Exception {
        String[] data = {" 090 1234 5678 ", ""};
        String[][] result = action.execute(data);
        assertEquals(1, result.length);
    }
	
//	⑥nullを指定
	@Test
	void testExecute_null() throws Exception {
		String[] data = null;
		assertThrows(Exception.class,() -> {
			action.execute(data);
		});
	}
}
