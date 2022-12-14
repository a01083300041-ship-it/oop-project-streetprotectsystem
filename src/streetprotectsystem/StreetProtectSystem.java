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
public class StreetProtectSystem extends JFrame {

    Container contentPane; // 컨테이너 클래스의 객체 선언

    public StreetProtectSystem() {

        setTitle("길거리 무단투기 개선 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MyPanel());
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
        contentPane.setBackground(Color.WHITE); // 흰색 배경 설정

        setLocationRelativeTo(null); // 실행 화면을 중앙에 띄움
        contentPane.setLayout(null); // 배치 관리자 삭제

        // <지도 보기 버튼>
        JButton gido = new JButton("지도 보기");
        gido.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 14));
        gido.setLocation(555, 125); // 지도 보기 버튼 위치
        gido.setSize(90, 45); // 버튼 사이즈
        gido.setForeground(Color.WHITE); // 지도 보기 글씨 색깔 흰색
        contentPane.add(gido); // 팬에 지도 보기 버튼 붙이기
        gido.setBackground(new Color(0, 140, 0)); // 버튼 색상

        // <지도 보기 버튼을 눌렀을 때 건의사항 게시판 창이 뜨게 함>
        gido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Project01_E().initGUI();
                setVisible(false);
            }
        });

        // <다음 버튼> 
        ImageIcon next = new ImageIcon("src\\images\\hwa.png");
        JButton n = new JButton(next);
        n.setBackground(Color.WHITE); // 다음 버튼 배경 흰색
        n.setLocation(580, 380); // 다음 버튼 위치
        n.setSize(70, 50); // 다음 버튼 사이즈
        contentPane.add(n);

        // <next버튼 눌렀을 때 실행되는 함수>
        n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Next();
                setVisible(false);
            }
        });

        // <관리자 버튼>
        ImageIcon manager = new ImageIcon("src\\images\\manager.png");
        JButton m = new JButton(manager);
        m.setBackground(Color.WHITE);
        m.setLocation(35, 380);
        m.setSize(70, 50);
        contentPane.add(m);

        // <next버튼 눌렀을 때 실행되는 함수>
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager();
                setVisible(false);
            }
        });

        setSize(700, 500);
        setVisible(true);

    }

    class MyPanel extends JPanel {

        ImageIcon icon = new ImageIcon("src\\images\\fi.png"); // 이미지 경로
        Image img = icon.getImage();
        // 메인화면 맨 밑 큰 그림

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 55)); // 글씨체, 굵기, 글씨 크기
            g.drawString("길거리 지킴이", 180, 60); // 제목 글 위치
            g.drawImage(img, 120, 338, this); // 사진 위치

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new StreetProtectSystem();
    }

}
