package data;

import static java.lang.Thread.sleep;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;

import steps.AuthorizationSteps;

public class Login {
    public static void login() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.clickLoginField("login2");
        AuthorizationSteps.clickPasswordField("password2");
        AuthorizationSteps.clickSignIn();
    }
}
