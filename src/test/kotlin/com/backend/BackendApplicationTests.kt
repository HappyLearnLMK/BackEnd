package com.backend

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendApplicationTests {

	@Test
	fun contextLoads() {
		val properties = System.getProperties()
		val stringPropertyNames = properties.stringPropertyNames()
		for (stringPropertyName in stringPropertyNames) {
			println("stringPropertyName = $stringPropertyName")
			println("getProperty = ${properties.getProperty(stringPropertyName)}")
			println("============================================================")
		}
	}

}
