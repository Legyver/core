package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * Version of {@link java.util.function.BiFunction} that throws a CoreException
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 * @param <R> the return type
 */
@FunctionalInterface
public interface ThrowingBiFunction<T extends Object, U extends Object, R extends Object> {

	/**
	 * Version of {@link java.util.function.BiFunction} that throws a CoreException
	 * @param t the first parameter to apply the function to
	 * @param u the second parameter to apply the function to
	 * @return the result
	 * @throws CoreException if the function throws an exception
	 */
	R apply(T t, U u) throws CoreException;
}
