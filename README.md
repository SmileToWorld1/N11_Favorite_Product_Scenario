# N11 FAVORITE PRODUCT SCENARIO

### SYSTEM REQUİREMENT
>-  **Maven**
>- **Selenium WebDriver**
>-  **JUnit**
>- **Java 8**
>- **Cucumber Report**

### EXPLANATION
>- The project has been prepared based on Cucumber BDD style.
>- In this project, **Java** programming language and **Maven** build management tool were used.
>- The project includes one scenario which is about adding and then deleting one product from the Favorites' page on the www.n11.com.

### HOW TO RUN TEST SCENARIOS

>- Under the runners package, there is a **"CukesRunner"** class and inside this class right click and click the **"Run 'CukesRunner'"**.
>- **mvn clean verify** -> in the IDE console or navigate project path in command line and run this command.
>- **mvn clean verify -DBROWSER=firefox** -> Browser type can control easily from command line with this command.
>- **mvn verify "-Dcucumber.filter.tags=@UI" -DBROWSER=firefox** Both browserType and tags which you want to run can control easily from the command line with this command. 

### REPORT 
>- Cucumber report is generated under the target folder: `target/cucumber/cucumber-html-reports/overview-features.html`
>- Under the target folder also generated **rerun.txt** file, and with this file only the failed test scenarios can rerun through the **FailedTestRunner** class.   


### CONFIGURATION PROPERTIES

> **configuration.properties** file is designed for avoiding hard coded. Browser type and also some important test data's can be stored in this file.
This way easily browser type can be changed and also if it is necessary every important test data's can control directly from this file.
```properties
browser=chrome
n11.url=https://www.n11.com/
expected.facebook.url=https://www.facebook.com/login
anasayfa=n11 - Hayat Sana Gelir
username=taksutyata@gmail.com
password=tester123
```


### DEPENDENCIES
```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>3.141.59</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.1.1</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.3.4</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.3.4</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>me.jvt.cucumber</groupId>
        <artifactId>reporting-plugin</artifactId>
        <version>7.3.0</version>
    </dependency>

    <!--If you want to get rid of SLF4J Failed to load message from the console -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>1.7.32</version>
    </dependency>
</dependencies>

```

### BUILD PLUGINS
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>8</source>
                <target>8</target>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
            <configuration>
                <parallel>methods</parallel>
                <!--   <useUnlimitedThreads>true</useUnlimitedThreads> -->
                <threadCount>4</threadCount>
                <perCoreThreadCount>false</perCoreThreadCount>
                <testFailureIgnore>true</testFailureIgnore>
                <includes>
                    <include>**/CukesRunner*.java</include>
                </includes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Ismail Sonmez / QA Automation Engineer     
https://www.linkedin.com/in/ismail-sonmez



