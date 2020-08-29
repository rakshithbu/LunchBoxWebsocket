import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getDateTime(long dateTime) {

        String strDateTime = "";
       SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
        Date d = new Date(dateTime);
        strDateTime = sdf.format(d);
        return strDateTime;

    }
}
