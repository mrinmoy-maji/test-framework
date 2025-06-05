import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  //testDir: '../tests',
  testDir: './src/tests',
  timeout: 30000,
  fullyParallel: true,
  forbidOnly: !!process.env.CI,
  retries: process.env.CI ? 2 : 0,
  workers: process.env.CI ? 1 : 1,
  expect: {
    timeout: 5000,
  },
  // reporter: [
  //   ['html', { outputFolder: 'src/reports', open: 'on-failure' }], // Custom report directory
  // ],
  reporter: [
    ['list'],
    ['html', { outputFolder: './reports' }], // Custom report directory
    ['monocart-reporter', {
      name: 'Monocart Report',
      outputFile: 'playwright-report/monocart-reports/monoReport.html',
    }],
    ['junit', { outputFile: 'src/reports/junit-report.xml' }],
  ],
  use: {
    // baseURL: 'http://localhost:3000',
    headless: false,
    browserName: 'chromium',
    ignoreHTTPSErrors: true,
    actionTimeout: 10000,
    navigationTimeout: 15000,
    trace: 'on-first-retry',
    screenshot: 'only-on-failure',
    video: 'off',
    // ...devices['Desktop Chrome'],
  },
  // projects: [
  //   {
  //     name: 'chromium',
  //     use: { ...devices['Desktop Chrome'] },
  //   },
  //   {
  //     name: 'firefox',
  //     use: { ...devices['Desktop Firefox'] },
  //   },
  //   {
  //     name: 'webkit',
  //     use: { ...devices['Desktop Safari'] },
  //   },
  // ],
});