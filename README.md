# Web Application Security Traits

This project is meant to serve as a set of unit tests for web applications. It can be used inside of a standalone test project or embedded into a JVM language project test suite.

## Installation

Maven
```xml
TODO: publish
```

Gradle
```groovy
TODO: publish
```

## For Non JVM based Projects

In order to create a test project, you will need to first create a JVM project. These examples use Java, but use is possible inside of any JVM language project capable of running JUnit tests.

## For JVM Based Projects

Simply create a new test file and follow the examples below. Please be aware that the traits will execute live HTTP requests. A network connection that has access to the destination url is required.

## Example

```java
@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits({
            SecureHeaders.class,
            SecureRedirect.class
    })
    public IO<Requester> secureHeaders() {
        return requesterIO("getrepsheet.com");
    }
}
```

## Traits

### Secure Headers

Makes the following assertions on response headers:

| Header                   | Expected Value |
|--------------------------|------------------------------------|
|X-Frame-Options           | DENY                               |
|X-Content-Type-Options    | nosniff                            |
|X-XSS-Protection          | 1; mode=block                      |
|Strict-Transport-Security | max-age=31536000; includeSubDomains|

### Secure Redirect

Makes the following assertions:

* HTTP response status is 301
* Location header is the HTTPS version of the requested URL.

## Contributing

TODO: Write