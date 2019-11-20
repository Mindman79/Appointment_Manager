package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTime {


    public static String cardPattern = "MMM d yyyy";
    private static String fullPattern = "MM/dd/yyyy HH:mm AA";
    public static SimpleDateFormat fullDateFormat = new SimpleDateFormat(fullPattern);


    /*public static LocalDateTime convertUTCToLocal(LocalDateTime ldt) {

     LocalDateTime input(ZoneOffset.UTC) = ldt;
        ZoneOffset zoneOffset = zoneOffset.getRules().getOffset(ldt);



}*/

}
