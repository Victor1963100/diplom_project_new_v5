package tests;


import static androidx.test.espresso.action.ViewActions.click;

import static steps.QoutesSteps.rollUpFirstQoute;

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
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.QoutesSteps;

@RunWith(AllureAndroidJUnit4.class)
public class QoutesTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void authorization() {
        Login.login();
    }

    @Test
    @DisplayName("#37: Развернуть и свернуть цитату (Позитивный)")
    public void expandAndRollUpQuotes() {
        MainSteps.waitForMainScreen();
        MainScreen.quotesButton.perform(click());
        QoutesSteps.displayedElementsOfQoutesScreen();
        QoutesSteps.expandFirstQoute();
        rollUpFirstQoute();
    }
}