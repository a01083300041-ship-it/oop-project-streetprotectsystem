/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streetprotectsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;

/**
 *
 * @author 조은진
 */
public class Modify extends JFrame {

    Container contentPane;
    public static int rowIndex;
    JScrollPane s1; // 쓰레기
    JScrollPane s2; //담배
    public static DefaultListModel model;
    JList list1, list2;
    String Ddoro;
    String sulChi;
    public static ArrayList<String> r1 = new ArrayList(); //쓰레기
    public static ArrayList<String> r2 = new ArrayList(); // 담배
    int trashType;
    boolean dambaeC = true, trashC = true;

    public Modify() {

        setTitle("관리자 설정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MyMoPanel());
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
        contentPane.setBackground(Color.WHITE); // 흰색 배경 설정

        setLocationRelativeTo(null); // 실행 화면을 중앙에 띄움
        contentPane.setLayout(null); //배치 관리자 삭제

        JButton su = new JButton("수 정");
        su.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
        su.setBackground(Color.WHITE);
        su.setLocation(60, 140);
        su.setSize(70, 25);
        contentPane.add(su);
        //  수정 버튼 

        JButton insert = new JButton("삽 입");
        insert.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
        insert.setBackground(Color.WHITE);
        insert.setLocation(150, 140);
        insert.setSize(70, 25);
        contentPane.add(insert);
        //  삽입 버튼 

        JButton delete = new JButton("삭 제");
        delete.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
        delete.setBackground(Color.WHITE);
        delete.setLocation(240, 140);
        delete.setSize(70, 25);
        contentPane.add(delete);
        //  삭제 버튼 

        ImageIcon Back = new ImageIcon("src\\images\\Back.png");
        JButton b = new JButton(Back);
        b.setBackground(Color.WHITE); // 이전 버튼 배경 흰색
        b.setLocation(15, 15); // 이전 버튼 위치
        b.setSize(55, 35); // 이전 버튼 사이즈
        contentPane.add(b);
        // 이전 버튼 

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager();
                setVisible(false);
            }
        }); // 이전 버튼을 눌렀을 때 이전 창으로 돌아감

        ImageIcon Dam = new ImageIcon("src\\images\\dambae.png");
        JButton dam = new JButton(Dam);
        dam.setBackground(Color.WHITE);
        dam.setLocation(100, 80);
        dam.setSize(70, 50);
        contentPane.add(dam);
        // 담배 버튼

        ImageIcon Trash = new ImageIcon("src\\images\\trash.jpg");
        JButton trash = new JButton(Trash);
        trash.setBackground(Color.WHITE);
        trash.setLocation(200, 80);
        trash.setSize(70, 50);
        contentPane.add(trash);
        //  쓰레기통 버튼

        // <쓰레기통 버튼 눌렀을 때 처리되는 함수>
        trash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r1.isEmpty()) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("trash.txt")));
                        String s;
                        while ((s = br.readLine()) != null) {
                            r1.add(s);
                        }
                    } catch (FileNotFoundException ex) {
                        String key = "IkuyRbLtmwMkCvrIcC2FgJm87%2BZ6f4Pv8kn7iOh6hNnwLJ09Ri8TWNp9chOTlCsoS%2BpWqOaOUleEU2csW478HA%3D%3D";
                        String result = "";
                        try {
                            URL url = new URL("https://api.odcloud.kr/api/15038096/v1/uddi:8a39d135-4298-4746-b8a3-aa6d8edbfe37?page=1&perPage=107&returnType=JSON&serviceKey=" + key);
                            BufferedReader bf;
                            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                            result = bf.readLine();

                            JSONParser jsonParser = new JSONParser();
                            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                            JSONArray data = (JSONArray) jsonObject.get("data");
                            BufferedWriter bw = new BufferedWriter(new FileWriter("trash.txt"));
                            for (int i = 0; i < data.size(); i += 2) {
                                JSONObject data_Ddoro = (JSONObject) data.get(i);
                                JSONObject data_sulChi = (JSONObject) data.get(i);
                                sulChi = data_sulChi.get("설치위치").toString();
                                Ddoro = data_Ddoro.get("도로(가로)명").toString();
                                r1.add(Ddoro + sulChi);
                                bw.write("\n" + r1.get(i / 2));
                            }

                            bw.flush();
                        } catch (Exception err) {
                            // TODO Auto-generated catch block
                            err.printStackTrace();
                        }
                    } catch (IOException ex) {

                    }
                }
                model = new DefaultListModel();
                list1 = new JList(model);
                list1.setBorder(new LineBorder(Color.BLACK));
                s1 = new JScrollPane(list1);
                for (String st : r1) {
                    model.addElement(st);
                }
                s1.setBounds(10, 180, 365, 250); // (x ,y, 폭, 높이)
                contentPane.add(s1);
                setVisible(true);
                trashType = 1;

                dam.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        s1.setVisible(false);

                    }
                }); // 쓰레기통 버튼 누른 후 담배 버튼 눌렀을 때 버그 해결하기 위함

                //<수정 눌렀을 때>
                if (trashC) {
                    su.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (trashType == 1) {
                                rowIndex = list1.getAnchorSelectionIndex();
                                try {
                                    new SuTrash();
                                } catch (IOException ex) {
                                }
                                setVisible(true);

                            }
                        }
                    });
                }

                //<삭제 눌렀을 때>
                if (trashC) {
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (trashType == 1) {
                                rowIndex = list1.getAnchorSelectionIndex();
                                r1.remove(rowIndex);
                                model.clear();
                                for (String st : r1) {
                                    model.addElement(st);
                                }
                                s1.setVisible(true);
                                try {
                                    saveFile("trash.txt", r1);
                                } catch (IOException ex) {
                                }
                            }
                        }
                    });
                }
                //<삽입 눌렀을때>
                if (trashC) {

                    insert.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (trashType == 1) {
                                new InsertTrash(); //쓰레기삽입
                                setVisible(true);
                            }
                        }
                    });
                }
                trashC = false;
            }
        });

        // <담배 버튼 눌렀을 때 처리되는 함수>
        dam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r2.isEmpty()) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("dambae.txt")));
                        String s;
                        while ((s = br.readLine()) != null) {
                            r2.add(s);
                        }
                    } catch (FileNotFoundException ex) {
                        String key = "IkuyRbLtmwMkCvrIcC2FgJm87%2BZ6f4Pv8kn7iOh6hNnwLJ09Ri8TWNp9chOTlCsoS%2BpWqOaOUleEU2csW478HA%3D%3D";
                        String result = "";

                        try {
                            URL url = new URL("https://api.odcloud.kr/api/15104425/v1/uddi:a5ef534c-aa48-404a-8948-cf37391eeadd?page=1&perPage=50&returnType=JSON&serviceKey=" + key);

                            BufferedReader bf, input;
                            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                            result = bf.readLine();

                            JSONParser jsonParser = new JSONParser();
                            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                            JSONArray data = (JSONArray) jsonObject.get("data");

                            BufferedWriter bw = new BufferedWriter(new FileWriter("dambae.txt"));
                            for (int i = 0; i < data.size(); i++) {
                                JSONObject data_Ddoro = (JSONObject) data.get(i);
                                JSONObject data_sulChi = (JSONObject) data.get(i);
                                sulChi = data_sulChi.get("설치장소").toString();
                                Ddoro = data_Ddoro.get("도로명주소").toString();
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
                model = new DefaultListModel();
                list2 = new JList(model);
                list2.setBorder(new LineBorder(Color.BLACK));
                s2 = new JScrollPane(list2);
                for (String s : r2) {
                    model.addElement(s);
                }

                s2.setBounds(10, 180, 365, 250); // (x ,y, 폭, 높이)
                contentPane.add(s2);
                setVisible(true);

                trashType = 2;
                trash.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        s2.setVisible(false);

                    }
                }); // 담배 버튼 누른 후 쓰레기통 버튼 눌렀을 때 버그 해결하기 위함

                //<수정 눌렀을 때>
                if (dambaeC) {
                    su.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (trashType == 2) {
                                rowIndex = list2.getAnchorSelectionIndex();
                                try {
                                    new SuDam();
                                } catch (IOException ex) {
                                }
                                setVisible(true);
                            }
                        }
                    });
                }

                //<삭제 눌렀을 때>
                if (dambaeC) {
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (trashType == 2) {
                                System.out.println(list2.getAnchorSelectionIndex());
                                rowIndex = list2.getAnchorSelectionIndex();
                                r2.remove(rowIndex);
                                model.clear();
                                for (String s : r2) {
                                    model.addElement(s);
                                }
                                try {
                                    saveFile("dambae.txt", r2);
                                } catch (IOException ex) {
                                }
                                s2.setVisible(true);
                            }

                        }
                    });
                }
                //<삽입 눌렀을때>
                if (dambaeC) {
                    insert.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (trashType == 2) {
                                new InsertDam(); //담배
                                setVisible(true);
                            }
                        }
                    });
                }
                dambaeC = false;
            }
        }); // 담배버튼 눌렀을 때 처리되는 함수

        setSize(400, 600);
        setVisible(true);
    }

    class MyMoPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g); // ???
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 35)); // 글씨체, 굵기, 글씨 크기
            g.drawString("관리자 설정", 110, 60); // 제목 글 위치

        }
    }

    // <수정된 내용을 txt파일에 저장하는 메소드>
    public static void saveFile(String fileName, ArrayList<String> r) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (String s : r) {
            bw.write("\n" + s);
        }
        bw.flush();
    }
}
