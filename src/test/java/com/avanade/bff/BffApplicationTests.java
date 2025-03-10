package com.avanade.bff;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BffApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void whenEmptyString_thenAccept() {
		Palindrome palindromeTester = new Palindrome();

		assertTrue(palindromeTester.isPalindrome(""));
	}
	@Test
	void whenNotAPalindrome_thenRefuse() {
		Palindrome palindromeTester = new Palindrome();

		assertFalse(palindromeTester.isPalindrome("ab"));
	}
}
