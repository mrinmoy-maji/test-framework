import { test, expect } from '@playwright/test';
import { takeScreenshot } from './helpers/screenshotHelper';

test.beforeEach(async ({ page }) => {
    await page.pause();
    await page.goto('https://example.com');
});

test('example test case 1', async ({ page }) => {
    const title = await page.title();
    expect(title).toBe('Example Domain');
    await takeScreenshot(page, 'example-test-case-1.png');
});

test('example test case 2', async ({ page }) => {
    const heading = await page.locator('h1').textContent();
    expect(heading).toBe('Example Domain');
    await takeScreenshot(page, 'example-test-case-2.png');
});

test.afterEach(async ({ page }) => {
    // Additional teardown logic can be added here if needed
});