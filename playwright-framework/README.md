# Playwright Framework

This project is a robust testing framework built using Playwright, designed to facilitate automated testing of web applications. It includes features for executing test cases, capturing screenshots, and generating detailed reports.

## Project Structure

```
playwright-framework
├── src
│   ├── tests
│   │   ├── example.test.ts       # Contains test cases for the application
│   │   └── helpers
│   │       └── screenshotHelper.ts # Helper functions for taking screenshots
│   ├── config
│   │   └── playwright.config.ts   # Configuration settings for Playwright
│   └── reports
│       └── README.md              # Documentation for test reports
├── package.json                    # npm configuration and dependencies
├── tsconfig.json                  # TypeScript configuration
└── README.md                       # Project documentation
```

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd playwright-framework
   ```

2. **Install Dependencies**
   ```bash
   npm install
   ```

3. **Run Tests**
   To execute the tests, use the following command:
   ```bash
   npx playwright test
   ```

## Usage

- Test cases are located in the `src/tests` directory. You can add new test files or modify existing ones.
- The `screenshotHelper.ts` file provides a utility function to capture screenshots during test execution. This can be integrated into your test cases as needed.
- Configuration settings for Playwright can be adjusted in the `playwright.config.ts` file.

## Contribution Guidelines

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your branch and create a pull request.

## Reporting Issues

If you encounter any issues or have suggestions for improvements, please open an issue in the repository.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.