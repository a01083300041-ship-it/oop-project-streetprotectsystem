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
import javax.swing.*;
import java.awt.event.*;

public class Project01_E {

    JTextField address;
    JLabel resAddress, resX, resY, jibunAddress;
    JLabel imageLabel;

    public void initGUI() {
        JFrame frm = new JFrame("지도 보기");                    // 프레임 생성
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // 프레임의 X 클릭 시 종료.
        Container c = frm.getContentPane();                     // JFrame 안쪽 영역.

        c.setBackground(Color.WHITE); // 컨테이너의 배경을 흰색으로
        //c.setLocationRelativeTo(); // 실행 화면을 중앙에 띄움

        imageLabel = new JLabel(" ");                    // JFrame 안쪽 영역 상단에 들어갈 지도보기를 공백으로 함 -> 글자뜨기 X
        JPanel pan = new JPanel(new BorderLayout(2, 15)); //가로갭 10, 세로갭 10
        pan.setBackground(Color.WHITE); //pan의 배경을 흰색으로

        ImageIcon Back = new ImageIcon("src\\images\\Back.png");
        JButton b = new JButton(Back);
        b.setBackground(Color.WHITE); // 이전 버튼 배경 흰색
        b.setLocation(15, 15); // 이전 버튼 위치
        b.setSize(55, 35); // 이전 버튼 사이즈
        // 이전 버튼 

        JLabel gido = new JLabel("지도 보기");
        gido.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 50));
        gido.setHorizontalAlignment(JLabel.CENTER); //JLabel 가운데 정렬

        JLabel addressLbl = new JLabel("    주소입력    ");             // JFrame 안쪽 영역 상단에 들어갈 주소입력
        addressLbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        address = new JTextField(50);
        address.setSize(10, 30);

        JButton btn = new JButton("조회");                      // JFrame 안쪽 영역에 들어갈 클릭 버튼
        btn.setBackground(Color.WHITE);

        pan.add(b, BorderLayout.WEST);
        pan.add(gido, BorderLayout.NORTH);

        pan.add(addressLbl, BorderLayout.WEST);
        pan.add(address);
        pan.add(btn, BorderLayout.EAST);

        btn.addActionListener(new NaverMap(this));              // pan에 생성한 버튼(btn) 클릭 시 처리하는 이벤트 핸들러.

        JPanel pan1 = new JPanel(new BorderLayout(10, 25));
        pan1.setBackground(Color.WHITE);

        pan1.setLayout(new GridLayout(19, 1));                   // 지도 동쪽 그리드 20행 1열로 생성.
        //pan1.add(d);
        //pan1.add(s);
        //resAddress = new JLabel("도로명");                      // 그리드 1행에 들어갈 도로명
        //jibunAddress = new JLabel("지번주소");                  // 그리드 2행에 들어갈 지번주소
        //resX = new JLabel("경도");                              // 그리드 3행에 들어갈 경도
        //resY = new JLabel("위도");                              // 그리드 4행에 들어갈 위도
        //pan1.add(resAddress);
        //pan1.add(jibunAddress);
        // pan1.add(resX);
        // pan1.add(resY);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StreetProtectSystem();
                frm.setVisible(false);
            }

        }); // 이전 버튼을 눌렀을 때 이전 창으로 돌아감

        c.add(BorderLayout.NORTH, pan);                         // 상단 pan 세팅
        c.add(BorderLayout.CENTER, imageLabel);                 // 센터 imageLabel 세팅
        c.add(BorderLayout.EAST, pan1);                        // 하단 pan1 세팅

        frm.setSize(800, 600);
        frm.setVisible(true);

    }

}
