package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class RegistrationTest extends TestBase{

@BeforeMethod(alwaysRun = true)
public void preCondition(){
    if(app.getUser().isLogged()){
        app.getUser().logout();
    }
}

    @Test
    public void registrationTestPositive(){

        int i=(int) (System.currentTimeMillis()/1000)%3600;
        String email = "nik"+i+"@gmail.com";
        String password ="Nik12345$";
        System.out.println("Email: " +email);
        logger.info("Test Registration Positive starts with email >>>" + email );
        logger.info("Test Registration Positive starts with password>>>" +  password);


        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitRegistration();

        Assert.assertTrue(app.getUser().isLogged());

    }


    @Test(groups = {"web"})
    public void registrationTestWrongEmail(){

        int i=(int) (System.currentTimeMillis()/1000)%3600;
        String email = "nik"+i+"gmail.com";
        String password ="Nik12345$";
        System.out.println("Email: " +email);

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(email,password);
        app.getUser().submitRegistration();
        app.getUser().pause(2000);

        Assert.assertTrue(app.getUser().isErrorMessageWrongFormat());
        Assert.assertTrue(app.getUser().isAlertPresent());

    }

}
