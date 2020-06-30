package com.example.finedust;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AirService {

    private static String baseURL = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty";
    private static String ServiceKey = "%2FVGy2CnS5kHlp9sJGD%2FcTwSiWCj4rYHjSqulTHDret6Jscz3CTqwn55eusk7ciHAKcOhMClBw1QKZ7SIU%2BfZgg%3D%3D";

    URL url = null;
    HttpURLConnection conn;
    InputStream is;
    BufferedReader br;

    public Air getAir(String station) {

        Air air = new Air();

        try {

            String _url = baseURL + "?ServiceKey=" + ServiceKey + "&stationName=" + station + "&dataTerm=DAILY&pageNo=1&numOfRows=1&ver=1.3&_returnType=json";
            url = new URL(_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            Log.i("URL", _url);

            StringBuilder sb = new StringBuilder();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line;
                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                Log.e("AIR SERVICE", "MESSAGE RECEIVED ");

//                Log.e("RECEIVED MESSAGE ", sb.toString());
//                JSONObject json = new JSONObject(sb.toString());
                JSONArray list = new JSONObject(sb.toString()).getJSONArray("list");
                JSONObject item = list.getJSONObject(0);



                String pm10val = item.getString("pm10Value");
                String pm25val = item.getString("pm25Value");
                String pm10grade, pm25grade;

                if(pm10val.equals("-") || pm10val.equals("")) pm10grade = "-";
                else {
                    int i10val = Integer.parseInt(pm10val);
                    if (i10val <= 30)   pm10grade = "good";
                    else if(i10val <= 50)  pm10grade = "fine";
                    else if(i10val <= 100) pm10grade = "not_good";
                    else    pm10grade = "bad";
                }

                if(pm25val.equals("-") || pm25val.equals("")) pm25grade = "-";
                else {
                    int i25val = Integer.parseInt(pm25val);
                    if (i25val <= 15)   pm25grade = "good";
                    else if(i25val <= 25)  pm25grade = "fine";
                    else if(i25val <= 50) pm25grade = "not bad";
                    else    pm25grade = "bad";
                }


                air.setDataTime(item.getString("dataTime"));
                air.setPm10(pm10val);
                air.setPm25(pm25val);
                air.setPm10Grade(pm10grade);
                air.setPm25Grede(pm25grade);

                return air;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    if (br != null)
                        br.close();
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }
}
