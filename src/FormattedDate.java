import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattedDate {

    public String getDate() {
        //get a date and modify it to match the given format
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String strDate = dateFormat.format(date);
        String strTime = timeFormat.format(date);

        String[] split = strDate.split("-");

        //shorten the month to the first tree letters and capitalize the remaining letters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i == 1) {
                sb.append(split[i].substring(0, Math.min(split[i].length(), 3)).toUpperCase());
                sb.append("-");
            }else{
                sb.append(split[i]);
                if (i != split.length - 1) {
                    sb.append("-");
                }
            }
        }

        //combine date and time
        String formDate = sb.toString();
        String dateTimeStamp = (formDate + " " + strTime);
        return dateTimeStamp;
    }

}
