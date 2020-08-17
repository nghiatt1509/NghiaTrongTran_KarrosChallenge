package com.amazoneaws.utils;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class KeywordHelper {

    protected WebDriver driver;

    public KeywordHelper(WebDriver driver) {
        this.driver = driver;
    }

    /***
     * Select item from dropdownlist by text
     * @param element
     * @param textItem
     */
    public void SelectDropdownListByText(WebElement element, String textItem) {
        if (!textItem.trim().isEmpty()) {
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(textItem);
        }
    }

    /***
     * Get index of column by header
     * @param tableElement
     * @param headerName
     * @return
     */
    public int getColumnIndexByHeader(WebElement tableElement, String headerName){
        int columnIndex = 0;

        List<WebElement> columnHeaders = tableElement.findElements(By.xpath("//th"));
        for(WebElement expectedHeader : columnHeaders){
            String headerNameToFind = expectedHeader.getText().toLowerCase().trim();
            if(headerNameToFind.equals(headerName.toLowerCase().trim())){
                columnIndex = columnHeaders.indexOf(expectedHeader);
                break;
            }
        }

        return columnIndex;
    }

    /***
     * Get list of table cells by header
     * @param tableElement
     * @param headerName
     * @return
     */
    public List<String> getTableCellByHeader(WebElement tableElement, String headerName) {
        List<String> cellValues = new ArrayList<String>();
        int columnIndex = getColumnIndexByHeader(tableElement, headerName);

        List<WebElement> tableRows = tableElement.findElements(By.xpath(String.format("//tbody/tr/td[%d]", columnIndex + 1)));
        for(WebElement rowElement : tableRows){
            String rowValue = rowElement.getText().toLowerCase().trim();
            cellValues.add(rowValue);
        }

        return cellValues;
    }

    /***
     * Click on Column Header to make a sort
     * @param tableElement
     * @param headerName
     */
    public void sortByColumnHeader(WebElement tableElement, String headerName){
        int columnIndex = getColumnIndexByHeader(tableElement, headerName);
        List<WebElement> columnHeaders = tableElement.findElements(By.xpath("//th"));
        columnHeaders.get(columnIndex).click();
    }

    /***
     * Verify if column is able to sort
     * @param tableElement
     * @param headerName
     * @param sortType
     * @return
     */
    public boolean isColumnSorted(WebElement tableElement, String headerName, String sortType){
        boolean isSorted = false;

        List<String> cellData = getTableCellByHeader(tableElement, headerName);

        if(sortType.toLowerCase().trim() == "asc"){
            isSorted = Ordering.natural().isOrdered(cellData);
        } else if(sortType.toLowerCase().trim() == "desc"){
            isSorted = Ordering.natural().reverse().isOrdered(cellData);
        }

        return isSorted;
    }
}
