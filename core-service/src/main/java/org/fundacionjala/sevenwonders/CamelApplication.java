package org.fundacionjala.sevenwonders;

import org.apache.camel.spring.javaconfig.Main;

/**
 * Created by dwits on 05/08/2016.
 */
public class CamelApplication {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setConfigClasses(GameConfig.class.getName());

        try{
            main.run();
        } catch (Exception ex){

        }
    }

}
