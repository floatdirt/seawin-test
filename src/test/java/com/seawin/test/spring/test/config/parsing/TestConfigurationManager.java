package com.seawin.test.spring.test.config.parsing;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.seawin.test.spring.edu.jyu.config.Bean;
import com.seawin.test.spring.edu.jyu.config.parsing.ConfigurationManager;

public class TestConfigurationManager {

	@Test
	public void testGetBeanConfig() {
		Map<String, Bean> beanConfig = ConfigurationManager.getBeanConfig("/applicationContext.xml");
		for (Entry<String, Bean> e : beanConfig.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}
}
