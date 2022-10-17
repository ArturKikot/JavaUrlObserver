import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        System.out.println("Bitte geben Sie eine URL zur Beobachtung ein");
        System.out.println("Beispiel: https://arturcode.de/index.html");
        getUrl(false);// Try to get url from user, it will loop until the user enters a valid url
    }

    static void getUrl(Boolean loop) {
        Scanner UserInput = new Scanner(System.in);
        while (loop == false) {
            String userUrl = UserInput.nextLine();
            UrlValidator isUrlValid = new UrlValidator();
            if (isUrlValid.checkUrl(userUrl)) {
                sendRequest(userUrl);
            } else {
                System.out.println("Keine gültige URL, bitte versuchen Sie es nochmal");
                getUrl(false);
            }
        }
    }

    static void sendRequest(String targetUrl) {
        //a new thread to not stop the main process
        Thread newThread = new Thread(() -> {
            System.out.println(targetUrl + " wird überwacht, drücken Sie die Enter Taste, um das Programm zu beenden...");
            Scanner UserInput = new Scanner(System.in);
            UserInput.nextLine();
            System.exit(0);//program will exit if user presses enter
        });
        newThread.start();

        Runnable getRequest = new Runnable() {
            // send a GET request to targeted url
            public void run() {
                try {
                    URL url = new URL(targetUrl);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    // receive a code which tells us about the status of the url
                    int code = connection.getResponseCode();
                    CodeMatcher isCodeMatching = new CodeMatcher();
                    writeFile(isCodeMatching.matchCode(code), targetUrl);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(getRequest, 0, 30, TimeUnit.SECONDS);
    }

    static void writeFile(boolean available, String targetUrl) throws IOException {
        //Log the current date,time,url and type of connection
        FormattedDate finalDate = new FormattedDate();
        File log = new File("Log.txt");//select the log file
        FileWriter fileWriter = new FileWriter(log, true);

        if (available == true) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" + finalDate.getDate() + " Uhr:" + " " + targetUrl + " -> erreichbar!" +"\n");
            bufferedWriter.close();
        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" + finalDate.getDate() + " Uhr:" + " " + targetUrl + " -> nicht erreichbar!" + "\n");
            bufferedWriter.close();
        }

    }

}