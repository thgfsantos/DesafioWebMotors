package com.webmotors.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchTypeCarPage {

	private WebDriver driver;
	private WebDriverWait wait; 

	public SearchTypeCarPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5);
	}
	
	public void selectCarWithBrand(String nameBrand) {	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.pad-gutter-tb.pad-gutter-l")));

		driver.findElement(By.name("Marca")).click();
		driver.findElement(By.name("Search")).clear();
		driver.findElement(By.name("Search")).sendKeys(nameBrand);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[8]/li[4]/a/span")));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[8]/li[4]/a/span")));

		driver.findElement(By.xpath("//ul[8]/li[4]/a/span")).click();

		driver.findElement(By.cssSelector("div.overlay-popover")).click();

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//body/div[2]/div")));
		
		driver.findElement(By.xpath("//body/div[2]/div"));
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='boxResultadoA']/a/div[2]/div/div[2]/h2")));

	}

	public void selectCarWithModel(String typeModelCar) {
		wait.until(ExpectedConditions.elementToBeClickable(By.name("Modelo")));
		driver.findElement(By.cssSelector("div.overlay-popover")).click();

		driver.findElement(By.name("Modelo")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.pad-gutter-tb.pad-gutter-l")));

		//wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Search")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.name("Search")));
		//driver.findElement(By.name("Search")).sendKeys(typeModelCar);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[3]/div[2]/ul/li/a")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li/ul/li[3]/a/span")));

		driver.findElement(By.xpath("//li/ul/li[3]/a/span")).click();
	}

	public void selectCarBetweenDate(String toDate, String fromDate) {
		driver.findElement(By.linkText(toDate)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(fromDate)));
		driver.findElement(By.linkText(fromDate)).click();

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='boxResultadoA']/a/div[2]/div/div[2]/h2")));
	}

	public void selectCarModelVersion() {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Versao']")));
		driver.findElement(By.xpath("//button[@name='Versao']")).click();
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("div.pad-gutter-tb.pad-gutter-l > div.size-ubig.bold")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/ul/li/a/span")));
		driver.findElement(By.xpath("//li/ul/li/a/span")).click();
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='boxResultadoA']/a/div[2]/div/div[2]/h2")));
	}

	public String assertResultSearchDate() {
		return driver.findElement(By.xpath("//div[@id='boxResultadoA']/a/div[2]/div/div[2]/div[3]/div/span")).getText();
	}
	
	public String assertResultSearchCarAndModel() {
		return driver.findElement(By.xpath("//div[@id='boxResultadoA']/a/div[2]/div/div[2]/h2")).getText();
	}
	public String assertResultSearchVersion() {
		return driver.findElement(By.xpath("//div[@id='boxResultadoA']/a/div[2]/div/div[2]/h2/span[2]")).getText();
	}
	

}
