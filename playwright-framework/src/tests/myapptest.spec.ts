import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.goto('http://localhost:3000'); // Pointing to your local React app
});

test('should allow adding a new todo item', async ({ page }) => {
  await page.locator('.new-todo').fill('Buy groceries');
  await page.locator('button').click(); // Click the "Add" button
  const todoText = await page.locator('.todo-list li span').textContent();
  expect(todoText).toBe('Buy groceries');
});

test('should allow marking a todo item as completed', async ({ page }) => {
  await page.locator('.new-todo').fill('Buy groceries');
  await page.locator('button').click(); // Click the "Add" button
  await page.locator('.todo-list li .toggle').click(); // Click the "Complete" button
  const completedClass = await page.locator('.todo-list li').getAttribute('class');
  expect(completedClass).toContain('completed');
});