package com.aepl.sam.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TableUtils {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(TableUtils.class);

	public TableUtils(WebDriverWait wait) {
		this.wait = wait;
	}

	public TableUtils(WebDriver driver, WebDriverWait wait) {
		this.wait = wait;
		this.driver = driver;
	}

	public List<String> getTableHeaders(By tableLocator) {
		List<String> headerTexts = new ArrayList<>();
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));

			// Try thead first
			List<WebElement> headers = table.findElements(By.xpath("//tr/th"));

			// Fallback: sometimes headers are in the first row
//			if (headers.isEmpty()) {
//				logger.warn("No <thead> headers found, trying first row as header...");
//				headers = table.findElements(By.xpath(".//tr[1]/*"));
//			}

			headerTexts = headers.stream().map(h -> h.getText()).filter(t -> !t.isEmpty()).collect(Collectors.toList());

			logger.info("Table Headers Found: {}", headerTexts);

		} catch (TimeoutException e) {
			logger.error("Table not found or not visible: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error while extracting table headers: {}", e.getMessage(), e);
		}

		return headerTexts;
	}

	public List<Map<String, String>> getTableData(By tableLocator, List<String> headers) {
		List<Map<String, String>> tableData = new ArrayList<>();
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
			List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				Map<String, String> rowData = new LinkedHashMap<>();

				for (int i = 0; i < cells.size(); i++) {
					String header = i < headers.size() ? headers.get(i) : "Column" + (i + 1);

					String cellText = cells.get(i).getText().trim();
					if (cellText.isEmpty()) {
						List<WebElement> innerTexts = cells.get(i).findElements(By.xpath(".//*"));
						for (WebElement inner : innerTexts) {
							if (!inner.getText().trim().isEmpty()) {
								cellText = inner.getText().trim();
								break;
							}
						}
					}
					rowData.put(header, cellText);
				}
				tableData.add(rowData);
			}

			logger.info("Total rows extracted: {}", tableData.size());

		} catch (Exception e) {
			logger.error("Unexpected error while extracting table data: {}", e.getMessage(), e);
		}

		return tableData;
	}

	public boolean areViewButtonsEnabled(By tableLocator) {
		boolean allEnabled = true;
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));

			// Find all "view" buttons inside the Action column
			List<WebElement> viewButtons = table
					.findElements(By.xpath(".//tbody//tr//td[last()]//button[contains(., 'visibility')]"));

			logger.info("Found {} view buttons", viewButtons.size());

			for (WebElement btn : viewButtons) {
				if (!btn.isDisplayed() || !btn.isEnabled()) {
					allEnabled = false;
					logger.warn("A view button is not enabled or visible!");
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error checking view button states: {}", e.getMessage(), e);
			allEnabled = false;
		}
		return allEnabled;
	}

	public boolean areDeleteButtonsValid(By tableLocator, List<String> headers) {
		boolean allValid = true;
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
			List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

			int statusIndex = headers.indexOf("Status");
			int actionIndex = headers.indexOf("Action");

			if (statusIndex == -1 || actionIndex == -1) {
				logger.error("Could not find 'Status' or 'Action' headers in: {}", headers);
				return false;
			}

			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));

				String status = cells.get(statusIndex).getText().trim();
				WebElement deleteButton = cells.get(actionIndex)
						.findElement(By.xpath(".//button[contains(., 'delete')]"));

				boolean enabled = deleteButton.isEnabled();

				if ("In-active".equalsIgnoreCase(status)) {
					if (enabled) {
						logger.warn("❌ Delete button should be disabled for row with status In-active");
						allValid = false;
						break;
					}
				} else {
					if (!enabled) {
						logger.warn("❌ Delete button should be enabled for row with status " + status);
						allValid = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error validating delete buttons: {}", e.getMessage(), e);
			allValid = false;
		}
		return allValid;
	}

	public boolean clickFirstViewButton(By tableLocator) {
		try {
			By viewButtonLocator = By.xpath("//table//tbody//tr//td[last()]//button[contains(., 'visibility')]");
			WebElement firstViewButton = wait.until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
			firstViewButton.click();
			logger.info("Clicked the first view button successfully.");
			return true;
		} catch (Exception e) {
			logger.error("Error clicking the first view button: {}", e.getMessage(), e);
			return false;
		}
	}
}