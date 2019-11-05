# Security Traits

![badge](https://action-badges.now.sh/abedra/security_traits?action=Java+CI)

This project is meant to serve as a set of security focused unit tests. It can be used inside of a standalone test project or embedded into a JVM language project test suite. They should be used to test drive the security aspects of your systems.

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

## Traits

### Web

```java
@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits({
            SecureHeaders.class,
            SecureRedirect.class,
            SecureCookies.class
    })
    public IO<Requester> secureHeaders() {
        return requester("getrepsheet.com");
    }
}
```

#### Secure Headers

Makes the following assertions on response headers:

| Header                   | Expected Value |
|--------------------------|------------------------------------|
|X-Frame-Options           | DENY                               |
|X-Content-Type-Options    | nosniff                            |
|X-XSS-Protection          | 1; mode=block                      |
|Strict-Transport-Security | max-age=31536000; includeSubDomains|

#### Secure Redirect

Makes the following assertions:

* HTTP response status is 301
* Location header is the HTTPS version of the requested URL.

#### Secure Cookies

Collects all cookies presented in the response and ensures they are marked `HttpOnly` and `secure`

### Password

```java
@RunWith(Traits.class)
public class PasswordTest {
    @TestTraits({
            AtLeastTwelveCharacters.class,
            AtLeastOneNumber.class,
            AtLeastOneUpper.class,
            AtLeastOneLower.class,
            AtLeastOneSpecial.class
    })
    public String passwordGeneratorSingleExecution() {
        return generatePassword(getConfiguration()).unsafeToString();
    }

    @TestTraits(Unique.class)
    public Fn0<String> passwordGeneratorMultipleExecutions() {
        return () -> generatePassword(getConfiguration()).unsafeToString();
    }

    @TestTraits({
            HasRedactedDefaultGetters.class,
            HasUnsafeToString.class
    })
    public Password redactedToString() {
        return password("testing");
    }

    private PasswordConfiguration getConfiguration() {
        return passwordConfiguration(
                passwordRequiredLength(12),
                passwordRequiredLowerCaseCharacters(1),
                passwordRequiredUpperCaseCharacters(1),
                passwordRequiredNumberCharacters(1),
                passwordRequiredSpecialCharacters(1)
        );
    }
}
```

#### Password Generation

The following traits are available to demonstrate secure password generation:

* AtLeastOneNumber
* AtLeastOneUpper
* AtLeastOneLower
* AtLeastOneSpecial
* AtLeastTwelveCharacters
* Unique

#### Password Objects

The following traits are available on constructed password objects:

* HasRedactedDefaultGetters
* HasUnsafeToString

## Contributing

Pull requests, questions, and ideas for new test are always welcome. Feel free to open an issue or pull request at any time. The requirement for submission is that the idea be complete and the test suite passing.
