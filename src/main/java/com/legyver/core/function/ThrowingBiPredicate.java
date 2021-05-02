package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * Version of {@link java.util.function.BiPredicate} that throws a CoreException
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 */
@FunctionalInterface
public interface ThrowingBiPredicate<T, U> {
	/**
	 * Version of {@link java.util.function.BiPredicate} that throws a CoreException
	 * @param t the first parameter to test
	 * @param u the second parameter to test
	 * @throws CoreException if the predicate throws an exception
	 * @return true if the test passes
	 */
	boolean test(T t, U u) throws CoreException;

}
