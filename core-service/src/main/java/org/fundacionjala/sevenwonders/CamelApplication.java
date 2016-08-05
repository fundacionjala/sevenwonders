/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders;

import org.apache.camel.spring.javaconfig.Main;

/**
 *  This contains how to start a camel rest service.
 *
 * @author Juan Barahona
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
