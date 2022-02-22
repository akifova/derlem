package com.kazu.carp.common.util.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * @author akifova
 * 16.02.2021
 */
@Service
public class ComplementaryServiceImpl implements IComplementaryService {
    private static final Logger logger = LoggerFactory.getLogger(ComplementaryServiceImpl.class);

    @Override
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim())	&& !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim())	&& !"unknown".equalsIgnoreCase(ip)) {
        // get first ip from proxy ip
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String fixedTurkishCharacter(String value) {
        return value.replace("Ç", "C").replace("Ö", "O")
                .replace("Ş", "S").replace("İ", "I").replace("Ü", "U")
                .replace("Ğ", "G").replace("ç", "c").replace("ö", "o")
                .replace("ş", "s").replace("ı", "i").replace("ü", "u")
                .replace("ğ", "g");
    }

    @Override
    public String createUniqueFromMillis() {
        return Long.toHexString(getCurrentTimeStamp());
    }

    @Override
    public String createUniqueFromSecond() {
        return Long.toHexString(getCurrentTimeStamp()/1000);
    }

    @Override
    public String createUniqueFromDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public <T> void replaceSpaceToUnderscore(T tClass, boolean fixTurkishCharacter) {
        try {
            for(Field field: tClass.getClass().getDeclaredFields()) {
                if(field.getType() == String.class && !field.getName().contains("id") && !field.getName().contains("Id")) {
                    field.setAccessible(true);
                    var newValue = (String) field.get(tClass);
                    newValue = newValue.replace(" ", "_");
                    if(fixTurkishCharacter) { newValue = fixedTurkishCharacter(newValue);}
                    newValue = replaceSpecialCharactersToUnderscore(newValue);
                    field.set(tClass, newValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void replaceSpaceToUnderscore(List<T> ts, boolean fixTurkishCharacter) {
        ts.stream().forEach(m-> replaceSpaceToUnderscore(m, fixTurkishCharacter));
    }

    @Override
    public String replaceSpecialCharactersToUnderscore(String newValue) {
        return newValue.replaceAll("[^A-zA-Z0-9]+","_");
    }

    @Override
    public long getCurrentTimeStamp() {
        try {
            Thread.sleep(25);
            Thread.sleep(5);
        } catch (InterruptedException e) {
            logger.warn(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return Instant.now().toEpochMilli();
    }
}