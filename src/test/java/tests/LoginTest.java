package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }


    @Test
    public void loginTest() {
        String email = "noa@gmail.com";
        String password = "Nnoa12345$";
        System.out.println( email +"   "+password);
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email, password);
        app.getUser().submitLogin();
        app.getUser().pause(100);
        Assert.assertTrue(app.getUser().isLogged());

    }
    @Test
    public void loginTestModel() {
        String email = "noa@gmail.com";
        String password = "Nnoa12345$";
        User user = new User().withEmail(email).withPassword(password);

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(100);
        Assert.assertTrue(app.getUser().isLogged());

    }


}
