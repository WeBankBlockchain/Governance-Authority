package com.webank.authmanager.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class ResourceUtils {

    public static String loadString(String resource){
        StringBuilder builder = new StringBuilder();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try(InputStream is = cl.getResourceAsStream(resource)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while((line = reader.readLine()) != null){
                builder.append(line);
            }
        }
        catch (Exception ex){
            log.error("Error reading string", ex);
        }
        return builder.toString();
    }
}
