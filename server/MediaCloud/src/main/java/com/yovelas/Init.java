package com.yovelas;

import com.yovelas.service.PhotosService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class Init implements InitializingBean {

    @Autowired
    AppConfig appConfig;

    @Autowired
    StringRedisTemplate redisTemplate;

    private static final Logger log = LogManager.getLogger(PhotosService.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!init())
            System.exit(0);
        redisTemplate.delete("progress");
    }

    public boolean init() {
        System.out.println("init");
        File file = new File(appConfig.getDataDirectory());
        if (!file.isDirectory()) {
            log.error(appConfig.getDataDirectory() + " is not a directory, cannot be data directory");
            System.out.print("Whether to create this directory (yes/no):");
            String inputtext = new Scanner(System.in).next();
            if (inputtext.equals("yes")) {
                if (file.mkdirs()) {
                    log.info("create " + file.getAbsolutePath() + " directory success");
                } else {
                    log.info("create " + file.getAbsolutePath() + " directory fail, check system please");
                    return false;
                }
            } else if (inputtext.equals("no")) {
                return false;
            } else {
                System.out.print("Sorry, input error");
                return false;
            }
        }

        if (!file.canRead()) {
            log.error(appConfig.getDataDirectory() + " cannot to read");
            return false;
        }

        if (!file.canWrite()) {
            log.error(appConfig.getDataDirectory() + " cannot to write");
            return false;
        }

        if (!file.canExecute()) {
            log.error(appConfig.getDataDirectory() + " cannot to Execute");
            return false;
        }

        File photos = new File(appConfig.getDataDirectory() + "photos");
        if (!photos.exists()) {
            if (!photos.mkdirs()) {
                log.error(appConfig.getDataDirectory() + "photos directory create fail");
                return false;
            }
        }

        File thumbnail = new File(appConfig.getDataDirectory() + "thumbnail");
        if (!thumbnail.exists()) {
            if (!thumbnail.mkdirs()) {
                log.error(appConfig.getDataDirectory() + "thumbnail directory create fail");
                return false;
            }
        }

        File logDir = new File(appConfig.getDataDirectory() + "log");
        if (!logDir.exists()) {
            if (!logDir.mkdirs()) {
                log.error(appConfig.getDataDirectory() + "log directory create fail");
                return false;
            }
        }
        return true;
    }
}
