package org.fundacionjala.sevenwonders.beans;

import org.springframework.stereotype.Component;

/**
 * Created by ale on 7/18/2016.
 */
@Component
public class SampleBean {
    public String hello(String msg) {
        return "Hello " + msg;
    }

}
