package com.legyver.core.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CoreExceptionTest {

	@Test
	public void checkedExceptionInStream() throws Exception {
		List<ClassA> items = Arrays.asList(new ClassA());
		try {
			unwrap(() -> items.stream().forEach(item -> wrap(() -> item.method())));
			fail("Excpected exception to be thrown");
		} catch (CoreException corex) {
			assertThat(corex.getMessage(), is("Test Exception"));
		}
	}

	private static class ClassA {

		public void method() throws CoreException {
			throw new CoreException("Test Exception");
		}
	}
}
