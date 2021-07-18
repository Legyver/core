package com.legyver.core.exception;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class CoreExceptionTest {

	@Test
	public void checkedExceptionInStream() throws Exception {
		List<ClassA> items = Arrays.asList(new ClassA());
		try {
			unwrap(() -> items.stream().forEach(item -> wrap(() -> item.method())));
			fail("Excpected exception to be thrown");
		} catch (CoreException corex) {
			assertThat(corex.getMessage()).isEqualTo("Test Exception");
		}
	}

	private static class ClassA {

		public void method() throws CoreException {
			throw new CoreException("Test Exception");
		}
	}
}
