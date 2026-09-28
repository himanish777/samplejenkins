# MavenJava Jenkins Example

This example demonstrates a simple Maven Java project that can be used with the `MavenJava_Build` and `MavenJava_Test` Jenkins freestyle jobs.

## Project structure

```text
mavenjava-example/
├── pom.xml
└── src/
    ├── main/java/com/example/App.java
    └── test/java/com/example/AppTest.java
```

## `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>mavenjava-example</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>Maven Java Example</name>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.10.2</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>
        </plugins>
    </build>
</project>
```

## `src/main/java/com/example/App.java`

```java
package com.example;

public class App {
    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        System.out.println(new App().greet("Jenkins"));
    }
}
```

## `src/test/java/com/example/AppTest.java`

```java
package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private final App app = new App();

    @Test
    void greetsTheProvidedName() {
        assertEquals("Hello, Jenkins!", app.greet("Jenkins"));
    }

    @Test
    void usesDefaultGreetingForBlankName() {
        assertEquals("Hello, World!", app.greet(""));
    }
}
```

## Run locally

```bash
mvn clean
mvn install
mvn test
```

The test reports are generated under `target/surefire-reports/`, and the packaged JAR is generated under `target/`.

## Jenkins configuration

Use the repository URL containing this project for the `MavenJava_Build` job and configure these Maven goals as separate build steps:

1. `clean`
2. `install`

Archive `**/*`, then trigger `MavenJava_Test` only when the build is stable. Configure the test job to copy the stable artifacts from `MavenJava_Build`, run the `test` goal, and archive `**/*`.
