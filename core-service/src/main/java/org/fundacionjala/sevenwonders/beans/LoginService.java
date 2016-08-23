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

    public LoginModel isLogin(UserModelService user) {
        LoginModel temp = new LoginModel();
        temp.setId(12);
        temp.setLoggedIn(true);
        temp.setUserName("luis");
        temp.setToken("ABC123");
        return temp;
    }
}
