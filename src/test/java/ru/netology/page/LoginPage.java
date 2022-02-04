package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class  LoginPage {
    public  VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        $("[data-test-id=login] input").sendKeys(authInfo.getLogin());
        $("[data-test-id=password] input").sendKeys(authInfo.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }
}
