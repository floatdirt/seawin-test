package com.seawin.test.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.seawin.test.base.BaseTest;

public class HashTableTest extends BaseTest {

    //审核
    @Test
	public void test_audit() {
		Map<String, String> map = new HashMap<String, String>(4);
		map.put("1", "a");
		map.put("2", "b");
		map.put("5", "c");
		map.put("9", "d");

    }

    
}
