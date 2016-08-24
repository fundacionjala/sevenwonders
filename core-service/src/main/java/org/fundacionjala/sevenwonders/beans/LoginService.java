/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.rest.LoginModel;
import org.fundacionjala.sevenwonders.core.rest.UserModelService;
import org.springframework.stereotype.Component;

/**
 *
 * @author luisgumucio
 */

@Component
public class LoginService {

    private LoginModel loginMockModel;
    public LoginService(){
        initMock();
    }

    public LoginModel isLogin(UserModelService user) {
        if (user != null) {
            return loginMockModel;
        }
        return null;
    }

    private void initMock() {
        loginMockModel = new LoginModel();
        loginMockModel.setId(12);
        loginMockModel.setLoggedIn(true);
        loginMockModel.setUserName("luis");
        loginMockModel.setToken("ABC123");
    }
}
