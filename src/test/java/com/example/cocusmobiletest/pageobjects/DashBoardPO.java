package com.example.cocusmobiletest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class DashBoardPO extends BasePage {

    @FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
    public WebElement btnAddNote;

    @FindBy(id = "com.example.android.testing.notes.mock:id/notes_list")
    public WebElement listNotes;

    @FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_title")
    public List<WebElement> listNotesTitle;

    @FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_description")
    public List<WebElement> listNotesDescription;

    @FindBy(id = "com.example.android.testing.notes.mock:id/design_menu_item_text")
    public WebElement btnStatistic;

    @FindBy(id = "com.example.android.testing.notes.mock:id/no_statistics")
    public WebElement txtNoStatistics;

    @FindBy(xpath = "//android.widget.TextView[@text='Notes']")
    public WebElement lblNotes;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    public WebElement hamburgerDashboard;

    private AppiumDriver appiumDriver;

    public DashBoardPO(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public void clickAddButton() {
        click(btnAddNote);
    }

    public boolean verifyNoteAdded(String title, String description) {
        isElementClickable(btnAddNote);
        if (verifyTitle(title) && verifyDescription(description)) {
            return true;
        }
        return false;
    }

    public boolean verifyTitle(String title) {
        isElementClickable(btnAddNote);
        if (listNotesTitle.stream().anyMatch(x -> x.getText().equals(title))) {
            return true;
        }
        return false;
    }

    public boolean verifyDescription(String description) {
        isElementClickable(btnAddNote);
        if (listNotesDescription.stream().anyMatch(x -> x.getText().equals(description))) {
            return true;
        }
        return false;
    }

    public void goToStatistics() {
        // Version 8 Appium only supports accessibility id only via AppiumBy and
        click(appiumDriver.findElement(AppiumBy.accessibilityId("Navigate up")));
        click(btnStatistic);
    }

    public boolean verifyAtStatistics() {
        if (txtNoStatistics.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public Object getNoteCount() {
        return listNotesTitle.size();
    }

    public boolean isAtDashboard() {
        if (isElementClickable(btnAddNote)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBlankNotePresent() {
        isElementClickable(btnAddNote);
        if (isBlankTitlePresent() && isBlankDescriptionPresent()) {
            return true;
        }
        return false;
    }

    public boolean isBlankDescriptionPresent() {
        isElementClickable(btnAddNote);
        List<WebElement> layout = listNotes.findElements(AppiumBy.className("android.widget.LinearLayout"));
        for (WebElement element : layout) {
            List<WebElement> desc = element
                    .findElements(By.id("com.example.android.testing.notes.mock:id/note_detail_description"));
            if (desc.size() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isBlankTitlePresent() {
        isElementClickable(btnAddNote);
        List<WebElement> layout = listNotes.findElements(AppiumBy.className("android.widget.LinearLayout"));
        for (WebElement element : layout) {
            List<WebElement> title = element
                    .findElements(By.id("com.example.android.testing.notes.mock:id/note_detail_title"));
            if (title.size() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddButtonPresent() {
        if (isElementClickable(btnAddNote)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHamburgerClickable() {
        if (isElementClickable(hamburgerDashboard)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNoteLabelPresent() {
        if (isElementVisible(lblNotes)) {
            return true;
        } else {
            return false;
        }
    }

}
