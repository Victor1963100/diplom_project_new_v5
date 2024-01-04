package tests;

import static androidx.test.espresso.action.ViewActions.click;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.Login;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import screen.MainScreen;
import steps.AboutSteps;
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.NewsSteps;
import steps.QoutesSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MainTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void authorization() {
        Login.login();
    }

    @Test
    @DisplayName("#12: Разворачивание/сворачивание блока новости")
    public void expandAndRollUpNewsBlock() {
        MainSteps.waitForMainScreen();
        MainSteps.rollUpAllNews();
        MainSteps.expandAllNews();
    }

    @Test
    @DisplayName("#13: Переход в блок Новости с главного экрана")
    public void goToNewsScreenFromMain(){
        MainSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
    }

    @Test
    @DisplayName("#14: Переход в блок О приложении через бургерное меню")
    public void goToAboutScreenFromMenu(){
        MainSteps.waitForMainScreen();
        MainSteps.goToScreen("About");
        AboutSteps.displayedAllElementsOfAboutScreen();
    }

    @Test
    @DisplayName("#15: Переход в блок Цитаты")
    public void goToQoutesScreen(){
        MainSteps.waitForMainScreen();
        MainScreen.quotesButton.perform(click());
        QoutesSteps.displayedElementsOfQoutesScreen();
    }

    @Test
    @DisplayName("#16: Выход из приложения")
    public void logOut(){
        MainSteps.waitForMainScreen();
        AuthorizationSteps.logOut();
    }
}