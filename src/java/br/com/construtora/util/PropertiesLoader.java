/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author agostinho
 */
public class PropertiesLoader {

    public PropertiesLoader() {
    }

    public String getPropriedade(String key) throws IOException {

        Properties props = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("TCoopUtil.properties");
        props.load(in);

        return props.getProperty(key);
    }
}
