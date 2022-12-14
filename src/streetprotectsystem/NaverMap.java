/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streetprotectsystem;

/**
 *
 * @author 조은진
 */

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.*;
import org.json.simple.parser.*;
import streetprotectsystem.AddressVO;


public class NaverMap implements ActionListener  {

    Project01_E naverMap;

    public NaverMap(Project01_E naverMap) {
        this.naverMap = naverMap;
    }
    
    public void actionPerformed(ActionEvent e) {
        String clientId = "YOUR_NAVER_CLIENT_ID";
        String clientSecret = "YOUR_NAVER_CLIENT_SECRET";
        AddressVO vo = null;

        try {
            String address = naverMap.address.getText();
            String addr = URLEncoder.encode(address, "UTF-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;
            URL url = new URL(apiURL); //URL 객체 생성

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET"); // URL 요청에 대한 메소드 설정. 기본값은 GET
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            //setRequestProperty(String key, String value) : key=value 쌍으로 지정된 일반 요청 속성

            int responseCode = con.getResponseCode(); // 서버에서 보낸 HTTP 상태 코드를 반환
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            JSONTokener tokener = new JSONTokener(response.toString());
            JSONObject object = new JSONObject(tokener);
            System.out.println(object);
            //읽어온 json 데이터를 메모리에 로딩 후 JSONObject 객체로 만들기 -> 출력
            
            JSONArray arr = object.getJSONArray("addresses");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject temp = (JSONObject) arr.get(i);
                vo = new AddressVO();
                vo.setRoadAddress((String) temp.get("roadAddress"));
                vo.setJibunAddress((String) temp.get("jibunAddress"));
                vo.setX((String) temp.get("x"));
                vo.setY((String) temp.get("y"));
                System.out.println(vo);
            }

            map_service(vo);

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void map_service(AddressVO vo) {
        String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
        

        try {
            String pos = URLEncoder.encode(vo.getX() + " " + vo.getY(), "UTF-8");
            URL_STATICMAP += "center=" + vo.getX() + "," + vo.getY();
            URL_STATICMAP += "&level=16&w=800&h=400";
            URL_STATICMAP += "&markers=type:t|size:mid|pos:" + pos + "|label:" + URLEncoder.encode(vo.getRoadAddress(), "UTF-8");

            URL url = new URL(URL_STATICMAP);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "YOUR_NAVER_CLIENT_ID");
            con.setRequestProperty("X-NCP-APIGW-API-KEY", "YOUR_NAVER_CLIENT_SECRET");

            int responseCode = con.getResponseCode();
            BufferedReader br;

            // 정상호출인 경우.
            if (responseCode == 200) {
                InputStream is = con.getInputStream();

                int read = 0;
                byte[] bytes = new byte[1024];

                // 랜덤 파일명으로 파일 생성
                String tempName = Long.valueOf(new Date().getTime()).toString();
                File file = new File(tempName + ".jpg");	// 파일 생성.

                file.createNewFile();

                OutputStream out = new FileOutputStream(file);

                while ((read = is.read(bytes)) != -1) {
                    out.write(bytes, 0, read);	// 파일 작성
                }

                is.close();
                ImageIcon img = new ImageIcon(file.getName());
                naverMap.imageLabel.setIcon(img);
                naverMap.resAddress.setText(vo.getRoadAddress());
                naverMap.jibunAddress.setText(vo.getJibunAddress());
                naverMap.resX.setText(vo.getX());
                naverMap.resY.setText(vo.getY());

            } else {
                System.out.println(responseCode);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
