package lambdaschool.gdp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GdpLog implements Serializable
{
    private final String text;
    private final String formattedDate;

    public GdpLog (String text)
    {
        this.text = text;
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat((strDateFormat));
        formattedDate = dateFormat.format(date);
    }
}
