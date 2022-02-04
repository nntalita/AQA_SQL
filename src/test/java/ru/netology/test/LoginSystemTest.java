package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.DbInteractionDbUtils;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginSystemTest {


    @BeforeEach
    void setup() {

        open("http://localhost:9999");


    }

    @Test
    void shouldTransferMoneyBetweenFromFirstCardsOnLimit() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        String verificationCode = DbInteractionDbUtils.getCodeVerification();
        verificationPage.validVerify(verificationCode);
        DashboardPage page = new DashboardPage();
        page.visibleDashboardPage();


    }
    @AfterEach
    void close() {
        DbInteractionDbUtils.clean();
    }
}
