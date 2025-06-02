import { Page } from 'playwright';

export async function takeScreenshot(page: Page, name: string): Promise<void> {
    const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
    const screenshotPath = `./screenshots/${name}-${timestamp}.png`;
    await page.screenshot({ path: screenshotPath });
}