package com.tone.gf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件获取工具类
 */
public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;

    static{
        loadProps();
    }

    private static void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("account.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("account.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException", e);
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("account.properties文件流关闭出现异常");
            }
        }
    }

    public static String getProperty(String key){
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}