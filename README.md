# Core
- Checked Exceptions and Throwing Functions/Consumers/Suppliers common to Legyver libraries
- LicenseService and LicenseServiceRegistry

## Usage
```groovy
api group: 'com.legyver', name: 'core', version: '2.1.0'
```

## LicenseService
Allow retrieval of License data from all self-reporting libraries
```java
public class MyClass {
    public static void main(String[] args) {
        //load license data for all self-reporting libraries on classpath
        Properties autoProperties = LicenseServiceRegistry.getInstance().loadLicenseProperties();
    }
}
```
### Obligations of self-reporting libraries
1. Define properties as appropriate for all your open source dependencies
2. Implement a *LicenseService* that loads the license properties defined in step 1
3. In **META-INF/services** create a file called *com.legyver.core.license.LicenseService* which contains the full class name of your implementation
4. In your **module-info.java** declare implementation
```java
module com.company.project {
    provides com.legyver.core.license.LicenseService with <your impl>
}
```
#### Module name
The module name of the library should be used as the *.license.metadata.* prefix.
It can be retrieved from 
- The **module-info.class** in the root of the jar
- The **module-info.class** in the **META-INF/versions/9** entry
- The *Automatic-Module-Name* in the **META-INF/MANIFEST.MF**

If the library has not been adapted, you may have to do so in order for it to be consumed by a modular application.
Use the project package as a basis, ie: com.company.project.  Module names must be globally unique.  If you do adapt a library, this needs to be declared under changes in your license data.

```groovy
legacyJavaModuleInfo {
    automaticModule('json-path-2.7.0.jar', 'com.jayway.jsonpath')
    automaticModule('json-smart-2.4.7.jar', 'net.minidev.json')
    automaticModule('accessors-smart-2.4.7.jar', 'net.minidev.accessors')
}
```

```properties
#some license metadata omitted for brevity
com.jayway.jsonpath.license.metadata.name=Jayway JsonPath
com.jayway.jsonpath.license.metadata.change[0]=Added Automatic-Module-Name for 'com.jayway.jsonpath' and dependent artifacts ('net.minidev.json', 'net.minidev.accessors')
```

### Versions
#### v1
**Deprecated**
#### v2
##### Grammar
- All keys prefixed with the module name.
- The following *.license.metadata.* indicates the type of metadata
- The trailing parameter defines the parameter the value applies to
```properties
<module_name>.license.metadata.<parameter>=
```
- Lists are defined as arrays
```properties
<module_name>.license.metadata.<parameter>[0]=
```
- Links are defined as a pair of resource keys where
  - **text** identifies what the link is for
  - **link** defines the hyperlink URL to the resource
```properties
<module_name>.license.metadata.<parameter>[0].text=
<module_name>.license.metadata.<parameter>[0].link=
```
##### Schema
- **name**: *text*
  - the name of the library
```properties
<module_name>.license.metadata.name=
```
- **retrieved**: *text*
  - the date the library was retrieved
```properties
<module_name>.license.metadata.retrieved=
```
- **author**: *array of text/link properties*
  - the author(s) of the library.
    - text: Text name of author's of the library
    - link: Link to author or contributors page 
```properties
<module_name>.license.metadata.author[0].text=
<module_name>.license.metadata.author[0].link=
```
- **title**: *array of text/link properties*
  - the title(s) of the library.
    - text: Text title the library
    - link: Link to library web page
```properties
<module_name>.license.metadata.title[0].text=
<module_name>.license.metadata.title[0].link=
```
- **copyright**: *array of text/link properties*
  - the license(s) the library is distributed under.
    - text: Text title the license
    - link: Link to the license
```properties
<module_name>.license.metadata.copyright[0].text=
<module_name>.license.metadata.copyright[0].link=
```
- **change**: *array of text*
    - Any changes made to the underlying library as required by license
```properties
<module_name>.license.metadata.change[0]=
```

### v2 Example ###
Since the commons-lang3 jar has a defined Automatic-Module-Name, use the automatic module name.
```properties
#Apache commons-lang3
org.apache.commons.lang3.license.metadata.name=Commons Lang
org.apache.commons.lang3.license.metadata.retrieved=2022-12-17
org.apache.commons.lang3.license.metadata.author[0].text=Apache Software Foundation
org.apache.commons.lang3.license.metadata.author[0].link=https://commons.apache.org/proper/commons-lang/team.html
org.apache.commons.lang3.license.metadata.title[0].text=Commons Lang
org.apache.commons.lang3.license.metadata.title[0].link=https://commons.apache.org/proper/commons-lang/
org.apache.commons.lang3.license.metadata.copyright[0].text=Apache License 2.0
org.apache.commons.lang3.license.metadata.copyright[0].link=https://github.com/apache/commons-lang/blob/master/LICENSE.txt
org.apache.commons.lang3.license.metadata.change[0]=No change.
```

## Built with
- [Java 11](https://www.azul.com/downloads/?version=java-11-lts&os=windows&architecture=x86-64-bit&package=jdk): Zulu11.56+19-CA (build 11.0.15+10-LTS)
- [Gradle](https://docs.gradle.org/7.4/release-notes.html) 

## Versioning
As of version 2.0.0 follows [SemVer 2.0](https://semver.org/spec/v2.0.0.html)