# WebstaurantStore Automated tests
This repository contains a Maven-based Java project for automating a test case on the WebstaurantStore website.

## Project Overview

The project is designed to automate the following test case:

1. Navigate to Home Page.
2. Search for 'stainless work table'.
3. Verify that every product in the search results has the word 'Table' in its title.
4. Add the last item from the search results to the cart.
5. Empty the cart.

## Requirements

- **Operating System:** Windows 10.
- **Language:** Java.
- **Automation Tool:** Utilizes Selenium WebDriver for web automation.
- **Browser:** Uses Chrome browser.
- **Dependency Management:** Maven is used for dependency management.
- **WebDriverManager:** The project uses WebDriverManager for managing WebDriver binaries.

## How to Run the Test

1. Clone this repository to your local machine.
2. Make sure you have JDK, Maven, and Chrome browser installed on your machine.
3. Open the project in your preferred Java IDE.
4. Run the `SearchSteps` class, which contains the test automation code.
5. The test will open Chrome browser, navigate to the WebstaurantStore website, perform the test steps, and verify the results.
6. After completing the test, the browser will close automatically.

## Contributors

- Dennis Nikolayenko - Main Contributor
