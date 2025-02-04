# TestNG Framework

## Overview
This project is a TestNG Framework designed to facilitate automated testing for Java applications. It includes configurations and utilities to help you write and run tests effectively.

## Features
- Easy integration with Java projects
- Simple configuration for TestNG
- Support for HTML reports
- Docker integration for containerized testing

## Getting Started
### Prerequisites
- Java JDK 8 or higher
- Maven
- Docker (optional)

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/Rekapost/TestNG_Framework.git
    ```
2. Navigate to the project directory:
    ```sh
    cd TestNG_Framework
    ```
3. Install dependencies:
    ```sh
    mvn clean install
    ```

### Running Tests
To run the tests, use the following command:
```sh
mvn test


### 1. To Run the maven project
Go to the project directory where pom.xml is located
`mvn clean`
`mvn compile`
`mvn test`        /  `mvn install`
`mvn clean test`  /  `mvn clean install`

### 2. To Run the maven project using chaintest Service
Go to the docker folder and run the following command 
`docker-compose -f docker-compose-h2.yml up` 
open url for chaintest report = http://localhost:8081/

### Verify the Port Availability
Before starting your service, it's important to ensure that the port you're using (e.g., port `8081`) is free and not being used by another application. If the service fails to start, it could be because another application is already using the port.

### On Windows
1. Open **Command Prompt** and run the following command to check if port `8081` is in use:
   netstat -ano | findstr :8081
If another process is using the port, you will see an output like:
TCP    127.0.0.1:8081    0.0.0.0:0    LISTENING    [PID]

2. To stop the process using the port, use the following command to kill the process:
taskkill /PID [PID] /F

### Report is saved 
 /target/chaintest/Index.html 
 /target/chaintest/Email.html

### 3. Set up a Selenium Grid with a Hub and a Chrome Node using Docker and Selenium. 
### Prerequisites

Before you begin, ensure that you have the following installed:
- Docker (for running containers)
- wget (to download files)

### 3a. `wget https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.27.0/selenium-server-4.27.0.jar`
Purpose: This command downloads the Selenium Server JAR file from the official Selenium GitHub releases.
What it does:
It fetches the selenium-server-4.27.0.jar file, which contains all the necessary components to run the Selenium Hub. This is required for setting up the Hub on a machine (before you run the Hub).

### 3b. `java -jar selenium-server-4.27.0.jar hub`
Purpose: This command starts the Selenium Hub by running the Selenium Server JAR file.
What it does:
The -jar flag tells Java to execute the selenium-server-4.27.0.jar file.
hub is the command that starts the Selenium Grid Hub. This Hub acts as a central point that controls the Selenium Nodes (browsers) and distributes test scripts to them.
It listens on port 4444 by default, and this is where the test scripts will connect to execute tests on different browsers and platforms.

### 3c. `docker pull selenium/standalone-chrome`
Purpose: This command pulls the Selenium Standalone Chrome Docker image from the Docker Hub.
What it does:
docker pull downloads the Docker image selenium/standalone-chrome from the Docker Hub, which contains both a Selenium Node (specifically with Chrome) and a Selenium WebDriver for browser automation.
This image is used to create a Docker container that will run the Chrome browser in a Selenium Grid as a Node.

### 3d. `docker run -d -p 5555:4444 --name selenium-hub1 selenium/standalone-chrome`
Purpose: This command runs a Selenium Node in a Docker container using the previously pulled Selenium Standalone Chrome image.
What it does:
-d runs the container in detached mode (in the background).
-p 5555:4444 maps port 4444 inside the container (which Selenium uses to communicate with the Hub) to port 5555 on your local machine, so you can access the Node via localhost:5555.
--name selenium-hub1 gives a custom name to the container (selenium-hub1), which you can reference later.
This starts the Selenium Node with Chrome as the browser, which will register itself with the Selenium Hub that is running on port 4444.

### 3e. `curl http://localhost:5555/wd/hub/status`
Purpose: This command checks the status of the Selenium Node to ensure it’s running and properly connected to the Hub.
What it does:
curl sends a request to the given URL (http://localhost:5555/wd/hub/status).
It checks if the Selenium Node running on port 5555 is up and functioning by returning a status JSON response.
If everything is set up correctly, you should see a response with information about the Node, including its capabilities, browser information, and status.

### 4. run.bat
bat file is batch file , executable file
cd C:\Users\Reka\eclipse-workspace\TestNG_Framework\Reka.TestNG_Framework_DDT
C:\Users\Reka\eclipse-workspace\TestNG_Framework\Reka.TestNG_Framework_DDT>mvn clean install

### 5. Allure Report
Go to folder where allure-results folder is generated and run the below command
`allure serve allure-results`

### 6. Extent Report 
/test-output/Test-Report-********.html

## 7. Running your TestNG tests inside a Docker container. 
### 7a. Dockerfile Setup
Make sure your Dockerfile is set up correctly to build the image with all dependencies for TestNG.
### 7b. Build the image
 `docker build -t testng-framework .`
### 7c. To run the container 
This will start the container and execute the CMD defined in your Dockerfile.
 `docker run --name testng-container testng-framework`
### 7d. To run the container in interactive mode
This gives you a terminal inside the container if you need to debug or run commands manually.
 `docker run -it --name testng-container testng-framework`
Once inside the container, you can run:
`mvn test`

1. Stop the Running Container
You need to stop the container first before removing it. To do this, run:
`docker stop testng-container`
This will stop the running container.

2. Remove the Stopped Container
Once the container is stopped, you can remove it:
`docker rm testng-container`

3. If You Need to Rerun Without Rebuilding
To avoid errors like "container already exists," remove the old container:
`docker rm testng-container`

### 8. Chromedriver setup
System.setProperty(CHROME_DRIVER, System.getProperty("user.dir")+"//drivers//chromedriver.exe");		
WebDriverManager.chromedriver().setup(); or WebDriverManager.chromedriver().clearDriverCache().setup();	
or  WebDriverManager.chromedriver().browserVersion("132.0.6834.159").setup();
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
chromeOptions.setAcceptInsecureCerts(true);
chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
chromeOptions.addArguments("--remote-allow-origins=*");	  
chromeOptions.setBinary("/usr/bin/google-chrome-stable");  // Path to Chrome binary
chromeOptions.setBinary("/usr/bin/google-chrome"); // This is the path where Chrome is installed in Docker
chromeOptions.addArguments("--headless");  // Run Chrome in headless mode (no GUI)
chromeOptions.addArguments("--no-sandbox");  // Avoid running into sandbox issues in Docker
chromeOptions.addArguments("--disable-dev-shm-usage");  // Overcome issues with limited shared memory in containers
chromeOptions.addArguments("--remote-debugging-port=9222");  // Enable debugging if needed
driver =new ChromeDriver(chromeOptions);	or  driver=new ChromeDriver();  // instantiate chromedriver	

### To initialize your ChromeDriver without manually handling setup or system properties
driver = WebDriverManager.chromedriver().create();

### Automatically downloads and sets up the compatible ChromeDriver
driver.set(WebDriverManager.chromedriver().capabilities(chromeOptions).create());

## 9. To run classes in parallel
Thread Safety: Ensure ThreadLocal<WebDriver> Is Used
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
Since you're running in parallel, ensure you're using ThreadLocal<WebDriver> correctly. use getDriver() for any interactions.
For example, replace any direct driver.findElement(...) with getDriver().findElement(...).
thread-count="X": Specifies how many threads to use for parallel execution. You can set the value based on your requirements (e.g., thread-count="4").
parallel="classes": Runs entire test classes in parallel.
<suite name="TestSuite" thread-count="2" parallel="classes">
<classes>
            <class name="testCases.TestCase"></class>
            <class name="testCases.TestCaseDDT"></class>
</classes>

## To run classes and methods in parallel
parallel="both": Runs both test methods and test classes in parallel.
class 1 with 1 method and class 2 with 1 dataprovider method that runs 4 times 
<suite name="TestSuite" thread-count="4" parallel="both">

parallel="methods": Runs test methods in parallel within the same test class.
   @Test
    public void testLogin1() {
        System.out.println("Test 1: Logging in...");
    }
    @Test
    public void testLogin2() {
        System.out.println("Test 2: Logging in...");
    }
    <suite name="ParallelTestSuite" parallel="methods" thread-count="4">
    <class name="LoginTest"/>

### Optional Parameter: It makes the parameter optional in the sense that the test can still run if the parameter isn't provided. You can configure it to fall back to a default browser like Chrome or Firefox, depending on what you need.
The @Optional("chrome") annotation in TestNG allows you to specify a default value for a method parameter in case the parameter is not provided explicitly via the testng.xml file or other test configuration.
@Optional("chrome") String br

### 10. Cross-browser Testing 
TestNG.xml provides the browser name
<parameter name="browser" value="firefox" />
BaseClass get the browser name from TestNG.xml and executes 
@Parameters({"browser"}) 
public void setup(String br)

### To execute your tests in a distributed manner or want to connect to a remote Selenium Grid or a Selenium server.
driver = new RemoteWebDriver(new URL("http://localhost:6666/wd/hub"),chromeOptions);
To instantiate a RemoteWebDriver object that communicates with a remote Selenium WebDriver server (such as a Selenium Grid, BrowserStack, Sauce Labs, or a local Selenium server running on localhost). The URL points to the Selenium Hub (which can manage multiple WebDriver instances) and the chromeOptions are passed to configure Chrome's settings.
Parallel Execution: You can run tests on multiple machines or environments simultaneously.
Cross-Browser Testing: You can test your application on various browsers and OS combinations, even if those browsers are not installed on your local machine.
### Hub Console:
URL: http://localhost:5555/grid/console
Purpose: This page will show the current status of your Selenium Grid. It will display the connected nodes, their status (idle or busy), and allow you to see the grid configuration. You won't see individual test logs here, but it gives a general view of the grid.
### WebDriver Endpoint:
URL: http://localhost:5555/wd/hub
Purpose: This URL serves as the WebDriver endpoint that allows your tests to communicate with the Selenium Grid. If the server is running, you may see a JSON response showing the status of the WebDriver, but it won't show detailed logs or outputs.
When starting a Selenium Node, you can enable logging by using the -log option. This will direct the logs to a specified file, or you can simply view them in the terminal.
java -Dselenium.verbose=true -jar selenium-server-<version>.jar -role node -hub http://localhost:5555/grid/register -log selenium-node.log
### This will:
Start the Selenium Node and register it with the Hub (localhost:5555).
Write logs to a file named selenium-node.log in the current directory.
Output logs to the terminal if the -verbose flag is used.
### Key parts of the command:
-Dselenium.verbose=true: This will provide verbose output to show detailed logs.
-log selenium-node.log: Logs will be saved to this file.

### Selenium Grid Hub logs
`java -Dselenium.verbose=true -jar selenium-server-<version>.jar -role hub -log selenium-hub.log`

### 11. Lambda Test
LambdaTest is a cloud-based testing platform that allows you to perform cross-browser testing of your web applications. It provides a wide range of real browsers, operating systems, and devices, so you can ensure your web app works perfectly across different environments without needing to maintain a physical device lab.
11a. Set Up LambdaTest Capabilities: When running Selenium tests on LambdaTest, you’ll configure your desired capabilities to specify the browser, OS, and version.
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability("browserName", "Chrome");
capabilities.setCapability("browserVersion", "latest");
capabilities.setCapability("platformName", "Windows 10");
capabilities.setCapability("LT:Options", new HashMap<String, Object>() {{
    put("user", "YOUR_USERNAME");
    put("accessKey", "YOUR_ACCESS_KEY");
    put("build", "Your Build Name");
    put("name", "Your Test Name");
}});
WebDriver driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);

11b. Run Tests in the Cloud: Your Selenium scripts will now run in the LambdaTest cloud instead of your local browser.
        <parameter name="browser" value="chrome" />
        <parameter name="isLambdaTest" value="true"/>
        <parameter name="isHeadless" value="true"/>

### 12. Run the tests through testng.xml 
To ensure a clean build and avoid issues from old compiled files.
To run tests defined in a specific TestNG configuration (testng.xml).
Useful for custom test executions like running only regression tests or parallel test suites.
`mvn clean test "-Dsurefire.suiteXmlFiles=testng.xml"cls`

### 13. containerized Jenkins environment, you can run Jenkins itself inside Docker.
To start a Jenkins container
`docker run -d --name jenkins \
  -p 8080:8080 -p 50000:50000 \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v jenkins_home:/var/jenkins_home \
  jenkins/jenkins:lts`
  #### or 
  `docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v //var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts`
  
### To retrieve passsword
  docker exec -it jenkinsdocker cat /var/jenkins_home/secrets/initialAdminPassword

### 14. Run the Test in Jenkins through Jenkins pipeline 
Jenkinsfile

### Creating image for maven-chrome Build and push this image to Docker Hub
 `docker build -t reka83/maven-chrome -f Dockerfile-maven-chrome .`
 `docker run -d --name maven-chrome reka83/maven-chrome`
 `docker push reka83/maven-chrome`
 `docker ps -a`
 `docker start maven-chrome`

