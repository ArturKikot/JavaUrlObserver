public class CodeMatcher {
    //match server response to know server status
    public boolean matchCode(int code) {
        if (code >= 100 && code <= 199) {// Informational response
            return false;
        } else if(code >= 200 && code <= 299) { //Successful response
            return true;
        } else if(code >= 300 && code <= 399) {//Redirection message
            return true;
        } else if(code >= 400 && code <= 499) {//Client error response
            return false;
        } else if(code >= 500 && code <= 599) {//Server error response
            return false;
        } else {
            return false;
        }
    }
}
