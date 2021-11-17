package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(Contact contact) {
        wd.manage().window().fullscreen();
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public void openContactForm() {
        click(By.cssSelector("a[href='/add']"));
    }

    public void submitContactForm() {
        click(By.xpath("(//button)[2]"));
    }

    public boolean isContactPresent(Contact contact) {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        for (WebElement w : list) {
            String text = w.getText();
            if (text.contains(contact.getName()) && text.contains(contact.getPhone())) return true;
        }
        return false;
    }
    public boolean isContactPresent2(String phone) {
        List<WebElement> contactEmails = wd.findElements(By.cssSelector("h3"));
        for (WebElement w : contactEmails) {
            String text = w.getText();
            if (text.contains(phone)) return true;
        }
        return false;
    }


    public int numberOfContacts() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        return list.size();
    }

    public void removeOneContact() {

        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            click(By.xpath("(//div[@class='contact-item_card__2SOIM'])"));
            click(By.xpath("//button[text()='Remove']"));
            pause(5000);
        }


    }

    public void removeAllContacts() {

        while (getFirst().isPresent()) {
            pause(500);
            getFirst().get().click();
            pause(500);
            click(By.xpath("//button[text()='Remove']"));
            pause(2000);
        }
    }

    private Optional<WebElement> getFirst() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).stream().findFirst();
    }

    public void removeAllContacts2(){
        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeOneContact();
        }

    }

    public boolean isContactNotHere() {
        return shouldHave(By.cssSelector(".contact-page_message__2qafk h1"),"No Contacts here!",10);
    }


//    public void removeContactByNameAndLastName(Contact contact) {
//        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
//        for (WebElement w : list) {
//            String text = w.getText();
//            if (text.contains(contact.getName()) && text.contains(contact.getLastName())) {
//                w.click();
//                click(By.xpath("//button[text()='Remove']"));
//            }
//        }
//
//    }

}
