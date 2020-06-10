package rip;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Myframe extends JFrame{

    private JTable t1=new JTable(8,8);//八行八列
    private JTextPane j1=new JTextPane();
    private JTable t2=new JTable(8,8);//八行八列
    private JTextPane j2=new JTextPane();

    public Myframe(Routing[] array, Routing[] b){
        this.setLayout(null);
        this.setTitle("距离向量路由算法");
        this.setSize(800, 600);
        t1.setBounds(5,35,800, 250);
        t2.setBounds(5, 315, 800,250);

        try{
            for(int i=0;i<7;i++){
                t1.setValueAt((char)(i+'a'), 0, i+1);
                t1.setValueAt((char)(i+'a'), i+1, 0);
                t2.setValueAt((char)(i+'a'), 0, i+1);
                t2.setValueAt((char)(i+'a'), i+1, 0);
            }
            for(int i=1;i<8;i++){
                for(int j=1;j<8;j++){
                    t1.setValueAt(b[i-1].dis[j-1], i, j);
                    t2.setValueAt(array[i-1].dis[j-1], i, j);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        j1.setEditable(false);
        j2.setEditable(false);
        j1.setBounds(5,5,800,25);
        j2.setBounds(5,285, 800, 25);

        this.add(j1);
        this.add(t1);
        this.add(j2);
        this.add(t2);

        printOnScreen(j1, "初始路由表", Color.black, 16);
        printOnScreen(j2, "收敛后的路由表", Color.black, 16);
    }

    public void printOnScreen(JTextPane jp, String str, Color color, int fontSize) {
        SimpleAttributeSet attrSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attrSet, color);
        StyleConstants.setFontSize(attrSet, fontSize);

        insert(jp, str, attrSet);
    }

    public void insert(JTextPane jp, String str, AttributeSet attrSet) {

        Document doc = jp.getDocument();
        try {
            doc.insertString(doc.getLength(), str, attrSet);
        }
        catch (BadLocationException e) {
            System.out.println("BadLocationException: " + e);
        }
    }
}