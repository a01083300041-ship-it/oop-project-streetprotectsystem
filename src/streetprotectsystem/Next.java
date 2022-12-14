/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streetprotectsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author 조은진
 */
public class Next extends JFrame {

    Container contentPane; // 컨테이너 클래스의 객체 선언

    public Next() {

        setTitle("양천구");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MyThPanel());
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
        contentPane.setBackground(Color.WHITE); // 흰색 배경 설정

        setLocationRelativeTo(null); // 실행 화면을 중앙에 띄움
        contentPane.setLayout(null); //배치 관리자 삭제

        // <담배 버튼>
        ImageIcon Dam = new ImageIcon("src\\images\\dambae.png");
        JButton dam = new JButton(Dam);
        dam.setBackground(Color.WHITE);
        dam.setLocation(680, 480);
        dam.setSize(70, 50);
        contentPane.add(dam);

        //  <쓰레기통 버튼>
        ImageIcon Trash = new ImageIcon("src\\images\\trash.jpg");
        JButton trash = new JButton(Trash);
        trash.setBackground(Color.WHITE);
        trash.setLocation(680, 410);
        trash.setSize(70, 50);
        contentPane.add(trash);

        // <쓰레기통 버튼 눌렀을 때 처리되는 함수>
        trash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Trash();
                setVisible(false);
            }
        });

        // <담배버튼 눌렀을 때 처리되는 함수>
        dam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dam();
                setVisible(false);
            }
        });
        // <이전 버튼>
        ImageIcon Back = new ImageIcon("src\\images\\Back.png");
        JButton b = new JButton(Back);
        b.setBackground(Color.WHITE); // 이전 버튼 배경 흰색
        b.setLocation(15, 15); // 이전 버튼 위치
        b.setSize(55, 35); // 이전 버튼 사이즈
        contentPane.add(b);

        // <이전 버튼을 눌렀을 때 이전 창으로 돌아감>
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StreetProtectSystem();
                setVisible(false);
            }
        });

        setSize(800, 600);
        setVisible(true);
    }

    class MyThPanel extends JPanel {

        ImageIcon icon = new ImageIcon("src\\images\\YangChun.png"); // 양천구 지도 사진 띄우기
        Image img = icon.getImage();

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 50)); // 글씨체, 굵기, 글씨 크기
            g.drawString("양천구", 320, 60); // 제목 글 위치
            g.drawImage(img, 180, 100, this); // 사진 위치

        }
    }
}
