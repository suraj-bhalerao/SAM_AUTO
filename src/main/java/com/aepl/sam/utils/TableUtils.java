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

			// Check for "no data" indicator
			List<WebElement> noDataImg = table.findElements(By.className("no-data-img"));
			if (!noDataImg.isEmpty()) {
				logger.info("No data available in the table (found 'no-data-img').");
				return tableData;
			}

			List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
			if (rows.isEmpty()) {
				logger.warn("No rows found in the table, and 'no-data-img' not detected.");
				return tableData;
			}

			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				Map<String, String> rowData = new LinkedHashMap<>();

				for (int i = 0; i < cells.size(); i++) {
					String header = i < headers.size() ? headers.get(i) : "Column" + (i + 1);
					WebElement cell = cells.get(i);
					String cellText = cell.getText().trim();

					// ✅ Handle checkbox detection
					List<WebElement> checkboxes = cell.findElements(By.xpath(".//input[@type='checkbox']"));
					if (!checkboxes.isEmpty()) {
						boolean isChecked = checkboxes.get(0).isSelected();
						rowData.put(header, isChecked ? "Checked" : "Unchecked");
						continue;
					}

					// Fallback: handle normal text content
					if (cellText.isEmpty()) {
						List<WebElement> innerTexts = cell.findElements(By.xpath(".//*"));
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

	public boolean areDeleteButtonsEnabled(By tableLocator) {
		boolean allEnabled = true;
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));

			// Find all "delete" buttons inside the Action column
			List<WebElement> deleteButtons = table
					.findElements(By.xpath(".//tbody//tr//td[last()]//button[contains(., 'delete')]"));

			logger.info("Found {} delete buttons", deleteButtons.size());

			for (WebElement btn : deleteButtons) {
				if (!btn.isDisplayed() || !btn.isEnabled()) {
					allEnabled = false;
					logger.warn("A delete button is not enabled or visible!");
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error checking delete button states: {}", e.getMessage(), e);
			allEnabled = false;
		}
		return allEnabled;
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

	/**
	 * Checks if the "No Data Found" image or message is present inside the table.
	 * 
	 * @param tableLocator Locator for the table element.
	 * @return true if a 'no data' indicator (like class 'no-data-img') is found,
	 *         false otherwise.
	 */
	public boolean isNoDataImagePresent(By tableLocator) {
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
			List<WebElement> noDataElements = table.findElements(By.className("no-data-img"));

			if (!noDataElements.isEmpty()) {
				logger.info("No data available in the table (found 'no-data-img').");
				return true;
			}

			// Optional fallback: handle text-based 'no data' messages
			List<WebElement> noDataText = table.findElements(By.xpath(".//*[contains(text(), 'No data')]"));
			if (!noDataText.isEmpty()) {
				logger.info("No data available in the table (found text 'No data').");
				return true;
			}

		} catch (TimeoutException e) {
			logger.error("Table not found while checking for no-data image: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error while checking for no-data image: {}", e.getMessage(), e);
		}

		return false;
	}

}