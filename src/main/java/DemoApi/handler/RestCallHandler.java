package DemoApi.handler;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public
class RestCallHandler {
    private BufferedReader in;
    private HttpURLConnection con;


    public String stringContentCall(URL url, String callType) throws IOException {
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(callType);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        if(con.getResponseCode()>200) {
            throw new IOException(con.getResponseCode() +" status code received from remote service");
        }
        in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }


}
