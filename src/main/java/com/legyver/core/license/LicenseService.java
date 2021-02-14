package com.legyver.core.license;

import java.io.IOException;
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
}
