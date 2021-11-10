package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    WebDriver wd;
    HelperUser user;

    public void init(){
        wd = new ChromeDriver();
        logger.info("Tests starts on Chrome Driver");
        wd.manage().window().maximize();
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home");

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user=new HelperUser(wd);

    }

    public void stop(){
        logger.info("Tests passed");
        wd.quit();
    }

    public HelperUser getUser() {
        return user;
    }
}
