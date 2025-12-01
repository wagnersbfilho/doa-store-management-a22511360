package pt.ipp.estg.doa.store.utils;

import java.time.format.DateTimeFormatter;

public class ValidationUtil {

    public static final String FORMAT_9_DIGITS = "^\\d{9}$";
    public static final String FORMAT_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
