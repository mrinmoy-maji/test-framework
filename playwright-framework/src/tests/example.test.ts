import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.goto('https://demo.playwright.dev/todomvc/');
});

test('should allow adding a new todo item', async ({ page }) => {
  await page.locator('.new-todo').fill('Buy groceries');
  await page.locator('.new-todo').press('Enter');
  const todoText = await page.locator('.todo-list li').textContent();
  expect(todoText).toContain('Buy groceries');
});

test('should allow marking a todo item as completed', async ({ page }) => {
  await page.locator('.new-todo').fill('Buy groceries');
  await page.locator('.new-todo').press('Enter');
  await page.locator('.todo-list li .toggle').click();
  const completedClass = await page.locator('.todo-list li').getAttribute('class');
  expect(completedClass).toContain('completed');
});