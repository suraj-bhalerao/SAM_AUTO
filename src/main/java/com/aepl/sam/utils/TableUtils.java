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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TableUtils {
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(TableUtils.class);

	public TableUtils(WebDriverWait wait) {
		this.wait = wait;
	}

	public List<Map<String, String>> getTableDetails(By tableLocator) {
		List<Map<String, String>> tableData = new ArrayList<>();

		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
			List<WebElement> headers = table.findElements(By.xpath(".//thead/tr/*"));

			// fallback if no headers found
			if (headers.isEmpty()) {
				logger.warn("No <thead> headers found, trying first row as header...");
				headers = table.findElements(By.xpath(".//tr[1]/*"));
			}

			List<String> headerTexts = headers.stream().map(header -> header.getText().trim())
					.collect(Collectors.toList());

			logger.info("Table Headers Found: {}", headerTexts);

			List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				Map<String, String> rowData = new LinkedHashMap<>();

				for (int i = 0; i < cells.size(); i++) {
					String header = i < headerTexts.size() ? headerTexts.get(i) : "Column" + (i + 1);

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

		} catch (TimeoutException e) {
			logger.error("Table not found or not visible: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error while extracting table data: {}", e.getMessage(), e);
		}

		return tableData;
	}
}
