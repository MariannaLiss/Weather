import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MariannaLissTest {
    /*
    1. Открыть страницу https://openweathermap.org/
    2. Набрать в строке поиска город Paris
    3. Нажать пункт меню Search
    4. Из выпадающего списка выбрать Paris, FR
    5. Подтвердить, что заголовок изменился на "Paris, FR"
     */

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/79213/ChromeDrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(7000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(7000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(7000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
    /*
    1. Открыть базовую ссылку
    2. Нажать на пункт меню Guide
    3. Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
    и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testOpenAndCheckTheMenuGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/79213/ChromeDrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(7000);
        WebElement guideElementInMenu = driver.findElement(
                By.xpath("//a[@href='/guide']")
        );
        guideElementInMenu.click();
        String actualResult2 = driver.getTitle();
        String actualResult1 = driver.getCurrentUrl();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /*
    1. Открыть базовую ссылку
    2. Нажать на единицы измерения
    3. Подтвердить, что температура для города показана в Фаренгейтах
    */

    @Test
    public void testOpenAndCheckTheTemperatureInFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/79213/ChromeDrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String fTempSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement menuImperial = driver.findElement(
                By.xpath("//div[@class = 'switch-container']/div[@class='option']/following-sibling::div")
        );
        menuImperial.click();
        Thread.sleep(2000);
        WebElement tempF = driver.findElement(
                By.xpath("//div[@class='current-temp']/span")
        );
        String tempInF = tempF.getText();

        Assert.assertTrue(tempInF.contains(fTempSymbol));

        driver.quit();
    }
}
