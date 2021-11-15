package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase {
    //login
    @BeforeMethod
    public void preCondition() {
        if (!app.getUser().isLogged()) {
            String email = "noa@gmail.com";
            String password = "Nnoa12345$";
            User user = new User().withEmail(email).withPassword(password);
            app.getUser().openLoginRegistrationForm();
            app.getUser().fillLoginRegistrationForm(user);
            app.getUser().submitLogin();
        }
    }

    @Test(invocationCount = 4)//тест пройдет 4 раза
    public void addNewContactTest() {
        int index = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Lis")
                .lastName("Snow")
                .phone("1234567" + index)
                .email("lis" + index + "@gmail.com")
                .address("Haifa")
                .description("friend")
                .build();

        app.contact().openContactForm();
        app.contact().fillContactForm(contact);
        app.contact().submitContactForm();

        Assert.assertTrue(app.getUser().isElementPresent(By.cssSelector(".contact-page_leftdiv__yhyke")));
        Assert.assertTrue(app.contact().isContactPresent(contact));

    }


}
