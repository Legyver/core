package com.legyver.core.license;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * License service. Every library is responsible to declare an implementation.
 * ie Both:
 * 1. Create an implementation of this interface that is able identify licenses of every library it uses.
 * 2. Declare that implementation in the META-INF\services\com.legyver.core.license.LicenseService file
 */
public interface LicenseService {
	/**
	 * Load the licenses used by the library
	 * @return the licenses used by the library
	 * @throws IOException if there is a problem loading the license.properties
	 */
	Properties loadLicenseProperties() throws IOException;

	/**
	 * Convenience method to read the license detail from a license.properties file in the same package as the implementing service
	 * @param klass the class to use to identify the package
	 * @return the Properties
	 * @throws IOException if there is a problem reading the file
	 */
	default Properties loadFromResourceStream(Class klass) throws IOException {
		Properties properties = new Properties();
		try (InputStream inputStream = klass.getResourceAsStream("license.properties")) {
			properties.load(inputStream);
		}
		return properties;
	}
}
