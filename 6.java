
{
    "base": "cmc stations", 
    "clouds": {
        "all": 90
    }, 
    "cod": 200, 
    "coord": {
        "lat": -35.28, 
        "lon": 149.13
    }, 
    "dt": 1404390600, 
    "id": 2172517, 
    "main": {
        "humidity": 100, 
        "pressure": 1023, 
        "temp": -1, 
        "temp_max": -1, 
        "temp_min": -1
    }, 
    "name": "Canberra", 
    "sys": {
        "country": "AU", 
        "message": 0.313, 
        "sunrise": 1404335563, 
        "sunset": 1404370965
    }, 
    "weather": [
        {
            "description": "overcast clouds", 
            "icon": "04n", 
            "id": 804, 
            "main": "Clouds"
        }
    ], 
    "wind": {
        "deg": 305.004, 
        "speed": 1.07
    }
}
Remote fetch.java
package ah.hathi.simpleweather;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import org.json.JSONObject;
 
import android.content.Context;
import android.util.Log;
 
public class RemoteFetch {
 
    private static final String OPEN_WEATHER_MAP_API = 
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
     
    public static JSONObject getJSON(Context context, String city){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));           
            HttpURLConnection connection = 
                    (HttpURLConnection)url.openConnection();
             
            connection.addRequestProperty("x-api-key", 
                    context.getString(R.string.open_weather_maps_app_id));
             
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
             
            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();
             
            JSONObject data = new JSONObject(json.toString());
             
            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }
             
            return data;
        }catch(Exception e){
            return null;
        }
    }   
}
