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
public class Manager extends JFrame {

    Container contentPane;

    public Manager() {

        setTitle("관리자 설정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MyMaPanel());
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
        contentPane.setBackground(Color.WHITE); // 흰색 배경 설정

        setLocationRelativeTo(null); // 실행 화면을 중앙에 띄움
        contentPane.setLayout(null); // 배치 관리자 삭제

        // <확인 버튼> 
        JButton hwa = new JButton("확 인");
        hwa.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
        hwa.setBackground(Color.WHITE);
        hwa.setLocation(210, 220);
        hwa.setSize(75, 30);
        contentPane.add(hwa);

        // <아이디 입력>
        JTextField id = new JTextField(15);
        id.setBounds(165, 117, 170, 20);
        contentPane.add(id);

        // <비밀번호 입력 필드>
        JPasswordField pw = new JPasswordField(15);
        pw.setBounds(165, 153, 170, 20);
        contentPane.add(pw);

        // < 확인 버튼 눌렀을 때 실행되는 함수>
        hwa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id.getText().equals("javachip") && new String(pw.getPassword()).equals("0000")) {
                    new Modify();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "로그인에 실패하셨습니다.");
                }
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

        setSize(500, 330);
        setVisible(true);
    }

    class MyMaPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 30)); // 글씨체, 굵기, 글씨 크기
            g.drawString("관리자 설정", 170, 45); // 제목 글 위치

            g.setFont(new Font("맑은 고딕", Font.BOLD, 13)); // 글씨체, 굵기, 글씨 크기
            g.drawString("ID", 130, 130);
            g.setFont(new Font("맑은 고딕", Font.BOLD, 13)); // 글씨체, 굵기, 글씨 크기
            g.drawString("PW", 127, 165);

        }
    }
}
