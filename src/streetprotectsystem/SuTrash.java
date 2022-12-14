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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SuTrash extends JFrame {

    Container contentPane;
    DefaultListModel model;
    JTextField input;
    String str;

    public SuTrash() throws IOException {

        setTitle("수정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료버튼 활성화
        setContentPane(new MySuTrashPanel());
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

                Modify.r1.set(Modify.rowIndex, input.getText());

                Modify.model.clear();

                for (Object s : Modify.r1) {
                    Modify.model.addElement(s);
                }

                setVisible(false);

                try {
                    Modify.saveFile("trash.txt", Modify.r1);
                } catch (IOException ex) {
                }
            }
        }); // 확인 버튼을 눌렀을 때

        ImageIcon Back = new ImageIcon("src\\images\\Back.png");
        JButton b = new JButton(Back);
        b.setBackground(Color.WHITE);
        b.setLocation(15, 15);
        b.setSize(55, 35);
        contentPane.add(b);
        // 이전 버튼 

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Modify();
                setVisible(false);
            }
        }); // 이전 버튼을 눌렀을 때 이전 창으로 돌아감

        setSize(400, 350);
        setVisible(true);
    }

    class MySuTrashPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g); // ???
            g.setColor(Color.BLACK); // 글씨 색깔
            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 35)); // 글씨체, 굵기, 글씨 크기
            g.drawString("수 정", 155, 60); // 제목 글 위치

            g.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13)); // 글씨체, 굵기, 글씨 크기
            g.drawString("주소 입력", 40, 142); // 제목 글 위치
        }
    }
}
