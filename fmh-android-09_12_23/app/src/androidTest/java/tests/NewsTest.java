package tests;

import static androidx.test.espresso.action.ViewActions.click;

import static java.lang.Thread.sleep;

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
import screen.NewsScreen;
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.NewsSteps;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void authorization() {
        Login.login();
    }

    @Test
    @DisplayName("#17: Переход на экран Панель управления Новостей (Позитивный)")
    public void goToControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.isControlPanelNews();
    }

    @Test
    @DisplayName("#18: Сортировка новостей (Позитивный)")
    public void sortingNewsControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Объявление");
        NewsSteps.enterDateOfNewsPublication("17.12.2023");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Объявление");
        NewsSteps.enterNewsTitle("Объявление");
        NewsSteps.enterDateOfNewsPublication("17.12.2023");
    }

    @Test
    @DisplayName("#19: Удаление активной новости во вкладке Панель управления (Позитивный)")
    public void deletingActiveNews() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Объявление");
        NewsScreen.clickButtonDeleteNews();
        NewsScreen.clickOkButton();
    }

    @Test
    @DisplayName("#20: Редактирование новости во вкладке Панель управления (Позитивный)")
    public void editNewsControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Объявление");
        NewsScreen.titleTextInput.perform();
        NewsScreen.clickSaveButton();
    }

    @Test
    @DisplayName("#21: Смена статуса новости, находящейся в статусе АКТИВНА на статус НЕ АКТИВНА, во вкладке Панель управления (Позитивный)")
    public void statusChangeNews() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Объявление");
        NewsScreen.activeCheckBox();
        NewsScreen.notActiveCheckBox();
        NewsScreen.clickSaveButton();
    }

    @Test
    @DisplayName("#22: Фильтрация новостей по критерию Активна (Позитивный)")
    public void filterNewsByCriterionActive() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Active");
    }

    @Test
    @DisplayName("#23: Фильтрация новостей по критерию Не активна (Позитивный)")
    public void filterNewsByCriterionNotActive() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Not active");
    }

    @Test
    @DisplayName("#24: Создание новости во вкладке Панель управления (Позитивный)")
    public void addNewsFromControlPanel () {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("18.12.2023");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Новостная сводка");
    }

    @Test
    @DisplayName("#25: Поле Категория пустое, при создании новости, во вкладке Панель управления (Негативный)")
    public void fieldCategoryEmptyCreationNewsInControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("19.12.2023");
        NewsSteps.enterTimeOfNewsPublication("14:00");
        NewsSteps.enterNewsDescription("Обсуждение");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Собрание");
    }

    @Test
    @DisplayName("#26: Поле Заголовок пустое при создании новости во вкладке Панель управления (Негативный)")
    public void fieldTitleEmptyCreationNewsInControlPanel() {
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.enterNewsTitle("");
        NewsSteps.enterDateOfNewsPublication("19.12.2023");
        NewsSteps.enterTimeOfNewsPublication("15:00");
        NewsSteps.enterNewsDescription("Обсуждение");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
    }

    @Test
    @DisplayName("#27: Поле Дата публикации пустое, при создании новости во вкладке Панель управления (Негативный)")
    public void fieldDateEmptyCreationNewsInControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
    }

    @Test
    @DisplayName("#28: Поле Время пустое при создании новости во вкладке Панель управления (Негативный)")
    public void fieldTimeEmptyCreationNewsInControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("18.12.2023");
        NewsSteps.enterTimeOfNewsPublication("");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
    }

    @Test
    @DisplayName("#29: Поле Описание пустое при создании новости во вкладке Панель управления (Негативный)")
    public void fieldDescriptionEmptyCreationNewsInControlPanel() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("18.12.2023");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
    }

    @Test
    @DisplayName("#30: Ввод в поле Категория собственного названия категории при создании новости во вкладке Панель управления (Негативный)")
    public void customCategoryName() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsScreen.categoryOfNews("Приглашение на обед");
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("18.12.2023");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Приглашение на обед");
    }

    @Test
    @DisplayName("#31: Поле Категория состоит из цифр при создании новости во вкладке Панель управления (Негативный)")
    public void fieldCategoryConsistsOfNumbers() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsScreen.categoryOfNews("1234567890");
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("18.12.2023");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("1234567890");
    }

    @Test
    @DisplayName("#32: Поле Категория состоит из спецсимволов при создании новости во вкладке Панель управления (Негативный)")
    public void fieldCategoryConsistsOfSpecialCharacters() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsScreen.categoryOfNews("@#$^&**");
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("18.12.2023");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("@#$^&**");
    }

    @Test
    @DisplayName("#33: Поле Дата публикации состоит из даты будущего года при создании новости во вкладке Панель управления (Позитивный)")
    public void fieldDateConsistsOfNextYearCreatingNews() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("19.12.2024");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("19.12.2024");
    }

    @Test
    @DisplayName("#34: Просмотр новостей во вкладке Новости (Позитивный)")
    public void viewingNews() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsScreen.editNewsItemButton();
    }

    @Test
    @DisplayName("#35: Фильтрация новостей без указания категории во вкладке Новости (Позитивный)")
    public void filteringNewsNoCategoryPositive() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsScreen.filterNewsButton();
    }

    @Test
    @DisplayName("#36: Фильтрация новостей, без указания категории, в определенный период времени (Позитивный)")
    public void filteringNewsCertainPeriodTime() {
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsScreen.dateStartPeriod("13.12.2023");
        NewsScreen.dateEndPeriod("19.12.2023");
    }
}