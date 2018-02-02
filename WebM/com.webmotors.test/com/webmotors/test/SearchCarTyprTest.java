package com.webmotors.test;

import static org.junit.Assert.fail;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.webmotors.page.SearchTypeCarPage;

public class SearchCarTyprTest {

	private WebDriver driver;
	private SearchTypeCarPage car;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "/tmp/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://hk.webmotors.com.br/carros/estoque");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.car = new SearchTypeCarPage(driver);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		Object verificationErrors = "";
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Test
	public void test_SearchCarByBrandHummerAndModelBetweenDateAndVersion() {
		car.selectCarWithBrand("HUMMER");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER"));
		car.selectCarWithModel("H3");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER H3"));
		car.selectCarBetweenDate("2006", "2010");
		assertThat(car.assertResultSearchDate(), containsString("2006"));
		car.selectCarModelVersion();
		assertThat(car.assertResultSearchVersion(), containsString(""));
	}

	@Test
	public void test_SearchCarByBrandHummerAndModelAndOneDate() {
		car.selectCarWithBrand("HUMMER");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER"));
		car.selectCarWithModel("H3");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER H3"));
		car.selectCarBetweenDate("2008", "");
		assertThat(car.assertResultSearchDate(), containsString("2008"));
	}

	@Test
	public void test_SearchCarByBrandHummerAndOneDate() {
		car.selectCarWithBrand("HUMMER");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER"));
		car.selectCarWithModel("");
		car.selectCarBetweenDate("2008", "");
		assertThat(car.assertResultSearchDate(), containsString("2008"));
	}

	@Test
	public void test_SearchCarByBrandHummerAndModel() {
		car.selectCarWithBrand("HUMMER");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER"));
		car.selectCarWithModel("H3");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER H3"));
	}

	@Test
	public void test_SearchCarByBrandHummerAndBetweenDate() {
		car.selectCarWithBrand("HUMMER");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER"));
		car.selectCarWithModel("");
		car.selectCarBetweenDate("2008", "2010");
		assertThat(car.assertResultSearchDate(), containsString("2008/2010"));
	}

	@Test
	public void test_SearchCarByBrandHummerAndModelAndVersion() {
		car.selectCarWithBrand("HUMMER");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER"));
		car.selectCarWithModel("H3");
		assertThat(car.assertResultSearchCarAndModel(), containsString("HUMMER H3"));
		car.selectCarModelVersion();
		assertThat(car.assertResultSearchVersion(), containsString("2006"));
	}

	@Test
	public void test_SearchCarByBrandNotFound() {
		car.selectCarWithBrand("XPTO");
		assertThat(car.assertResultSearchCarAndModel(), containsString("Não Encontrado"));
	}

	@Test
	public void test_SearchCarByWithBrandAndModelNotFound() {
		car.selectCarWithBrand("HUMMER");
		car.selectCarWithModel("XPTO");
		assertThat(car.assertResultSearchCarAndModel(), containsString("Não Encontrado"));
	}

	@Test
	public void test_SearchCarByWithBrandLowCaseName() {
		car.selectCarWithBrand("hummer");
		assertThat(car.assertResultSearchCarAndModel(), containsString("Não Encontrado"));
	}
}