package tests;

import manager.HelperBase;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RemoveContact extends TestBase {
    //before
    @BeforeMethod
    public void preCondition() {
        if (!app.getUser().isLogged()) {
            String email = "goodwin49@mail.ru";
            String password = "Gor12345$";
            User user = new User().withEmail(email).withPassword(password);
            app.getUser().openLoginRegistrationForm();
            app.getUser().fillLoginRegistrationForm(user);
            app.getUser().submitLogin();
        }

    }
    //removeOneContact - test1
    @Test
    public void removeOneContactTest(){
        int numberBeforRemove = app.contact().numberOfContacts();
        app.contact().removeOneContact();
        app.getUser().pause(5000);
        int numberAfterRemove = app.contact().numberOfContacts();
        Assert.assertEquals(numberAfterRemove,numberBeforRemove-1);
    }

    //removeAllContact-test2
    @Test
    public void removeAllContacts(){
        app.contact().removeAllContacts();
        app.getUser().pause(5000);
        int numberAfterRemove = app.contact().numberOfContacts();
        Assert.assertEquals(0,numberAfterRemove);
        Assert.assertFalse(app.getUser().isElementPresent(By.cssSelector(".contact-item_card__2SOIM")));

    }

    @Test
    public void removeAllContacts2(){
        app.contact().removeAllContacts2();
        app.getUser().pause(5000);
        int numberAfterRemove = app.contact().numberOfContacts();
        Assert.assertEquals(0,numberAfterRemove);
        Assert.assertTrue(app.contact().isContactNotHere());
    }
}
