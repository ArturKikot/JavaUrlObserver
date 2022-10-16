public class CodeMatcher {
    public boolean matchCode(int code) {

        if (code >= 100 && code <= 199) {
            return false;
        } else if(code >= 200 && code <= 299) {
            return true;
        } else if(code >= 300 && code <= 399) {
            return true;
        } else if(code >= 400 && code <= 499) {
            return false;
        } else if(code >= 500 && code <= 599) {
            return false;
        } else {
            return false;
        }
    }
}
