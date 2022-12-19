package com.legyver.core.license;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * Registry to load license data from all {@link LicenseService} on runtime classpath.
 * This allows for runtime aggregation of licenses based on self-reporting libraries
 * @since 2.1.0
 */
public class LicenseServiceRegistry {
    private final ServiceLoader<LicenseService> licenseServiceLoader;
    private static LicenseServiceRegistry instance;

    private LicenseServiceRegistry() {
        licenseServiceLoader = ServiceLoader.load(LicenseService.class);
    }

    /**
     * Retrieve a singleton instance of the registry
     * @return the instance
     */
    public static LicenseServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (LicenseServiceRegistry.class) {
                if (instance == null) {
                    instance = new LicenseServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Aggregate the license properties for all the application's self-reporting libraries
     * @return the union of all license.properties files provided by application libraries
     * @throws IOException if any license service is unable to load its libraries licenses
     */
    public Properties loadLicenseProperties() throws IOException {
        Properties union = new Properties();
        for (Iterator<LicenseService> it = licenseServiceLoader.iterator(); it.hasNext();) {
            LicenseService licenseService = it.next();
            Properties properties = licenseService.loadLicenseProperties();
            for (String propertyKey : properties.stringPropertyNames()) {
                if (!union.containsKey(propertyKey)) {
                    union.put(propertyKey, properties.getProperty(propertyKey));
                }
            }
        }
        return union;
    }
}
