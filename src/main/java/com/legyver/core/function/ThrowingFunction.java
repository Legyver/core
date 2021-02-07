package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * Version of {@link java.util.function.Function} that throws a CoreException
 * @param <T> the type of the argument
 * @param <R> the return type
 */
@FunctionalInterface
public interface ThrowingFunction<T extends Object, R extends Object> {


	/**
	 * Version of {@link java.util.function.Function} that throws a CoreException
	 * @param t the parameter to apply the function to
	 * @return the result
	 * @throws CoreException if the function throws an exception
	 */
	R apply(T t) throws CoreException;
}
