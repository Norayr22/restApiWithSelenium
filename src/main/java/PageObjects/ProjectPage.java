package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//tg-svg[@svg-icon = 'icon-like']")
	private WebElement like_button;
	
	public ProjectPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver , this);
	
	}
	
	public boolean isLikeButtonDisplayed() {
		
			return like_button.isDisplayed();
	}

}
