# TestNG Automation Framework 🚀

## 📌 Overview
This **TestNG Automation Framework** is a robust, scalable, and efficient framework built using **Selenium WebDriver** and **TestNG**. It follows the **Page Object Model (POM)** and supports **data-driven, parallel, and cross-browser testing** with advanced reporting and CI/CD integration.

## 🛠️ Tech Stack
- **Programming Language:** Java
- **Automation Tools:** Selenium WebDriver, TestNG
- **Framework Design:** Page Object Model (POM), Data-Driven Testing
- **CI/CD:** Jenkins, GitHub Actions
- **Reporting Tools:** Extent Reports, Allure Reports
- **Logging:** Log4j
- **Version Control:** Git, GitHub

## 🎯 Key Features
✅ **Page Object Model (POM) Implementation** - Improves code reusability & maintainability.
✅ **Data-Driven Testing** - Fetch test data from external sources (Excel, JSON, DB).
✅ **Parallel & Cross-Browser Execution** - Run tests on multiple browsers simultaneously.
✅ **Customized Reporting** - Generate **Extent Reports & Allure Reports** for better visualization.
✅ **CI/CD Ready** - Integrate with **Jenkins/GitHub Actions** for automated test execution.
✅ **Logging & Debugging** - Implement **Log4j** for effective debugging.
✅ **API Testing Integration** - Extend automation capabilities with **REST Assured**.

## 🏗️ Project Structure
```
├── src
│   ├── main
│   │   ├── java (Main Framework Code)
│   │   ├── resources (Config files, Test Data)
│   ├── test
│   │   ├── java (Test Cases)
│   ├── reports (Test Execution Reports)
│   ├── pom.xml (Maven dependencies)
│   ├── README.md
```

## 🚀 Getting Started
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Rekapost/TestNG_Framework.git
cd TestNG_Framework
```
### 2️⃣ Install Dependencies
Make sure you have **Maven** installed, then run:
```bash
mvn clean install
```
### 3️⃣ Run Tests
Execute all test cases:
```bash
mvn test
```
Run specific test suites using **TestNG XML**:
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```
### 4️⃣ View Reports
- **Extent Reports:** `target/reports/ExtentReport.html`
- **Allure Reports:** Generate and open with:
  ```bash
  mvn allure:serve
  ```

## 🔄 CI/CD Integration
- **Jenkins Pipeline:** Configure `Jenkinsfile` for automated test execution.
- **GitHub Actions:** Add a `.github/workflows/test.yml` file for auto-deployment.

## 🤝 Contributing
We welcome contributions! Feel free to **fork, create issues, and submit PRs**.

## 📞 Contact
📧 Email: rekaharisri@gmail.com  
🔗 LinkedIn: [linkedin.com/in/rekasrimurugan](#)  
🌐 GitHub: [github.com/Rekapost](https://github.com/Rekapost)  

---
🌟 *If you like this repository, please ⭐ it!*

ChainTestReport with logs and screenshot

![image](https://github.com/user-attachments/assets/d10702de-c5b8-418a-a926-467e60c383f4)
![image](https://github.com/user-attachments/assets/bee2fed1-8dcf-47ee-a57c-dee7f57a9a87)
![image](https://github.com/user-attachments/assets/5d4053ad-e227-4f4e-882f-dbb7ad5b0671)
![image](https://github.com/user-attachments/assets/09af5a4d-d350-4fbb-ada0-8c989c390ceb)

