package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size()>0;
    }

    public void type (By locator, String text){
        if(text !=null&& !text.isEmpty()){
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public  void click(By locator){
        wd.findElement(locator).click();
    }

    public void pause(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShot(String link){
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);//кастинг вебдрайвера+метод для скрина
        File screenshot = new File(link);//папка для хранения скрина

        try{
            Files.copy(tmp,screenshot);
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

    public void should (By locator, int time){
        new WebDriverWait(wd,time)
                .until(ExpectedConditions.visibilityOf(wd.findElement(locator)));
    }

    public boolean shouldHave(By locator, String text,int time){
        return  new WebDriverWait(wd, time)
                .until(ExpectedConditions.textToBePresentInElement(wd.findElement(locator), text));
    }
}
