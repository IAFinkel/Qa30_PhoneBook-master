package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(Contact contact) {
        wd.manage().window().fullscreen();
        type(By.cssSelector("[placeholder='Name']"),contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"),contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"),contact.getPhone());
        type(By.cssSelector("[placeholder='email']"),contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"),contact.getAddress());
        type(By.cssSelector("[placeholder='description']"),contact.getDescription());
    }

    public void openContactForm() {
        click(By.cssSelector("a[href='/add']"));
    }

    public void submitContactForm() {
        click(By.xpath("(//button)[2]"));
    }

    public boolean isContactPresent(Contact contact){
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        for (WebElement w:list) {
            String text = w.getText();
            if(text.contains(contact.getName())||text.contains(contact.getPhone())) return true;
        }
        return false;
    }

}
