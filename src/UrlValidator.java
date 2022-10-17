import java.net.URL;

public class UrlValidator {

    public boolean checkUrl(String url) {
        //check user url for validity
        //return true if valid otherwise false
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
}
