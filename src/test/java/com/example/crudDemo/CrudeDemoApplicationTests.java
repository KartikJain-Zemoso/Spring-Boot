package com.example.crudDemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudeDemoApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertThat(10).isEqualTo(10);
	}

}
