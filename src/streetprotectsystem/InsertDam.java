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
public class InsertDam extends JFrame {

    Container contentPane;
    DefaultListModel model;
    JTextField input;

    public InsertDam() {

        setTitle("삽입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MyinsertDamPanel());
        Container contentPane = getContentPane(); // 컨텐트팬 알아내기
        setAlwaysOnTop(true);
        contentPane.setBackground(Color.WHITE); // 흰색 배경 설정
        
        setLocationRelativeTo(null); // 실행 화면을 중앙에 띄움
        contentPane.setLayout(null); //배치 관리자 삭제

        JButton hwa = new JButton("확 인");
        hwa.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
        hwa.setBackground(Color.WHITE);
        hwa.setLocation(155, 250);
        hwa.setSize(75, 30);
        contentPane.add(hwa);
        //  확인 버튼 
        input = new JTextField(15);
        input.setBounds(110, 127, 200, 23);
        contentPane.add(input);
        // 주소 입력

        hwa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object stdambae = input.getText();
                Modify.model.addElement(stdambae);

                Modify.r2.add(stdambae.toString());

                Modify.model.clear();
                for (Object s : Modify.r2) {
                    Modify.model.addElement(s);
                }

                setVisible(false);
            }
        }); // 확인 버튼을 눌렀을 때

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
                //new Modify();
                setVisible(false);
            }
        }); // 이전 버튼을 눌렀을 때 이전 창으로 돌아감
        
        
        setSize(400, 350);
        setVisible(true);
    }

    class MyinsertDamPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g); // ???
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 35)); // 글씨체, 굵기, 글씨 크기
            g.drawString("삽 입", 155, 60); // 제목 글 위치

            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13)); // 글씨체, 굵기, 글씨 크기
            g.drawString("주소 입력", 40, 142); // 제목 글 위치
        }
    }
}
