package com.kazu.carp.common.util.business;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author akifova
 * 16.02.2021
 */
public interface IComplementaryService {
    String getIpAddr(HttpServletRequest request);
    public String generateUUID();
    public String fixedTurkishCharacter(String value);
    public String createUniqueFromMillis();
    public String createUniqueFromSecond();
    public String createUniqueFromDateTime();
    public <T> void replaceSpaceToUnderscore(T tClass, boolean fixTurkishCharacter);
    public <T> void replaceSpaceToUnderscore(List<T> tList, boolean fixTurkishCharacter);
    public String replaceSpecialCharactersToUnderscore(String newValue);
    public long getCurrentTimeStamp();
}
