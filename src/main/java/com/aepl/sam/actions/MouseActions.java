package com.aepl.sam.actions;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MouseActions {
	private WebDriver driver;
	private Actions actions;

	public MouseActions(WebDriver driver) {
		if (driver == null) {
			throw new IllegalArgumentException("WebDriver instance cannot be null");
		}

		this.driver = driver;
		this.actions = new Actions(this.driver);
	}

	public void moveToElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}
		try {
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
			actions.moveToElement(element).perform();
		} catch (Exception e) {
		}
	}

	public void clickElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
			actions.click(element).build().perform();
		} catch (Exception e) {
		}
	}

	public void doubleClickElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {
			Thread.sleep(1000);
			actions.doubleClick(element).build().perform();
		} catch (Exception e) {
		}
	}

	public void rightClickElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {
			actions.contextClick(element).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	public void selectAndCopy(WebElement startElement, WebElement endElement) {
		if (startElement == null || endElement == null) {
			throw new IllegalArgumentException("WebElement(s) cannot be null");
		}

		try {
			actions.clickAndHold(startElement).moveToElement(endElement).release().build().perform();

			actions.sendKeys(Keys.CONTROL, "c").build().perform();

		} catch (Exception e) {
			throw e;
		}
	}

	public void pasteText(WebElement targetElement) {
		if (targetElement == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {
			actions.moveToElement(targetElement).click().build().perform();

			actions.sendKeys(Keys.CONTROL, "v").build().perform();

		} catch (Exception e) {
			throw e;
		}
	}

	public void dragAndDrop(WebElement source, WebElement target) {
		if (source == null || target == null) {
			throw new IllegalArgumentException("Source or target WebElement cannot be null");
		}

		try {
			actions.dragAndDrop(source, target).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	public void dragAndDropByOffset(WebElement source, int xOffset, int yOffset) {
		if (source == null) {
			throw new IllegalArgumentException("Source WebElement cannot be null");
		}

		try {
			actions.dragAndDropBy(source, xOffset, yOffset).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	public void hoverOverElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("WebElement cannot be null");
		}

		try {
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}
}
