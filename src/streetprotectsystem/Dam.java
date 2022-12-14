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
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import org.json.simple.*;
import org.json.simple.parser.*;
import static streetprotectsystem.Modify.r2;

public class Dam extends JFrame {

    Container contentPane;
    DefaultListModel model;
    JList list1;

    public Dam() {

        setTitle("양천구 담배꽁초함 위치");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MyDamPanel());
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
        contentPane.setBackground(Color.WHITE); // 흰색 배경 설정

        setLocationRelativeTo(null); // 실행 화면을 중앙에 띄움
        contentPane.setLayout(null); // 배치 관리자 삭제
        
        // <조회하기 버튼>
        JButton jo = new JButton("조회하기");
        jo.setBackground(Color.WHITE);
        jo.setLocation(350, 100);
        jo.setSize(100, 35);
        jo.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        contentPane.add(jo);

        if (r2.isEmpty()) {
            try { //일단 들어와서 실행함, 파일이 없으면 catch문으로 가서 catch문 실행
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("dambae.txt")));
                String s;
                while ((s = br.readLine()) != null) {
                    r2.add(s);
                }
            } catch (FileNotFoundException ex) { //파일이 없으면 여기 실행함
                String key = "IkuyRbLtmwMkCvrIcC2FgJm87%2BZ6f4Pv8kn7iOh6hNnwLJ09Ri8TWNp9chOTlCsoS%2BpWqOaOUleEU2csW478HA%3D%3D";
                String result = "";

                try {
                    URL url = new URL("https://api.odcloud.kr/api/15104425/v1/uddi:a5ef534c-aa48-404a-8948-cf37391eeadd?page=1&perPage=50&returnType=JSON&serviceKey=" + key);

                    BufferedReader bf;
                    bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                    result = bf.readLine();

                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                    JSONArray data = (JSONArray) jsonObject.get("data");

                    BufferedWriter bw = new BufferedWriter(new FileWriter("dambae.txt"));
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject data_Ddoro = (JSONObject) data.get(i);
                        JSONObject data_sulChi = (JSONObject) data.get(i);
                        String sulChi = data_sulChi.get("설치장소").toString();
                        String Ddoro = data_Ddoro.get("도로명주소").toString();
                        r2.add(Ddoro + sulChi);
                        bw.write("\n" + r2.get(i));
                    }
                    bw.flush();

                } catch (Exception err) {
                    // TODO Auto-generated catch block
                    err.printStackTrace();
                }
            } catch (IOException ex) {
                Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // <조회하기 버튼을 눌렀을 때 실행되는 함수>
        jo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = new DefaultListModel();
                list1 = new JList(model);
                list1.setBorder(new LineBorder(Color.BLACK));
                JScrollPane scroll = new JScrollPane(list1);
                for (Object s2 : Modify.r2) {
                    model.addElement(s2);
                }
                scroll.setBounds(160, 180, 500, 250); // (x ,y, 폭, 높이)
                contentPane.add(scroll);
                setVisible(true);
            }
        });
        // <이전 버튼> 
        ImageIcon Back = new ImageIcon("src\\images\\Back.png");
        JButton b = new JButton(Back);
        b.setBackground(Color.WHITE); // 이전 버튼 배경 흰색
        b.setLocation(5, 15); // 이전 버튼 위치
        b.setSize(55, 35); // 이전 버튼 사이즈
        contentPane.add(b);
         
        // <이전 버튼을 눌렀을 때 이전 창으로 돌아감>
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Next();
                setVisible(false);
            }
        }); 

        setSize(800, 600);
        setVisible(true);

    }

    class MyDamPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 50)); // 글씨체, 굵기, 글씨 크기
            g.drawString("양천구 담배꽁초함 위치", 155, 60); // 제목 글 위치

        }
    }

}
