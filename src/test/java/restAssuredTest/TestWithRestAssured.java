package restAssuredTest;

import org.testng.annotations.Test;

import PageObjects.ProjectPage;
import RestAssured.ApiHelper;
import WebDriver.BaseWebDriver;
import junit.framework.Assert;

public class TestWithRestAssured extends BaseWebDriver{
	
	
	@Test
	public void likeButtonVisibilityTest() {
		
		ProjectPage project = new ProjectPage(driver);
		Assert.assertTrue(project.isLikeButtonDisplayed());
		
		
	}
	

}
