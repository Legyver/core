package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * Version of {@link java.util.function.Predicate} that throws a CoreException
 * @param <T> the type of the argument
 */
@FunctionalInterface
public interface ThrowingPredicate<T> {
	/**
	 * Version of {@link java.util.function.Predicate} that throws a CoreException
	 * @param t the parameter to test
	 * @throws CoreException if the predicate throws an exception
	 * @return true if the test passes
	 */
	boolean test(T t) throws CoreException;

}
