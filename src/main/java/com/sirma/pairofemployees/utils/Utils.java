package com.sirma.pairofemployees.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Utils {
    public static boolean isNumber(String value) {
        try {
            if (Objects.isNull(value))
                return false;
            Long.parseLong(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Long getLongValue(String[] cells, int index) {
        return cells.length > index && Utils.isNumber(cells[index]) ? Long.parseLong(cells[index]) : null;
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }
}
