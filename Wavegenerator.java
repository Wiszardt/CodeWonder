/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wavegenerator;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.TimerTask;
import javax.swing.event.*;
//import java.util.Timer;
//import java.util.TimerTask;

public class Wavegenerator{
    int z1, z2, Vlc;
    
   
       
    JFrame Frm;
    
    JPanel Prot;
    JPanel Pwve;
    
    JLabel Jamp;
    JLabel Jfrq;
    JLabel Jphs;
    JLabel Jvlc;
    
    JLabel Sts;
    
    JSlider JLamp;
    JSlider JLfrq;
    JSlider JLphs;
    JSlider JLvlc;
    
    JTextField Tamp;
    JTextField Tfrq;
    JTextField Tphs;
    JTextField Tvlc;
                            
    JButton BtnStart;
    JButton BtnStop;
    JButton Bamp;
    JButton Bfrq;
    JButton Bphs;
    
    Canvas CnWve;
    Canvas CnRot;
    
    Timer timer;
    
    int A,Ang,Frq,Phs;
        
    public void start() {
        
        Frm= new JFrame("Wave Generator");
        Frm.setLayout(new GridBagLayout());
       // GridBagLayout Grd=new GridBagLayout();
        GridBagConstraints c=new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 30);
        
        int CrotW,CrotH, CwvW, CwvH, inc;
        
        CrotW=300;
        CrotH=300;
        CwvW=500;
        CwvH=300;
        inc=CrotW/50;
        //A=150;
        //Frq=1;
        //Phs=30;
                        
        Prot= new JPanel();
        Pwve= new JPanel();
        
        Double Ang;
        Ang=Math.PI/180;
                
        //Ang*Frq+Phs*Ang.setT
        
        CnRot=new Canvas(){
            @Override
            public void paint(Graphics g) {
                
                z1=(int)Math.round(A*Math.cos(Phs*Ang));
                z2=(int)Math.round(A*Math.sin(Phs*Ang));    
            
                g.setColor(Color.lightGray);
                for(int i = 0;i<CrotW;i=i+inc) {
                    g.drawLine(0, i, CrotW, i);
                g.drawLine(i, 0, i, CrotH);
                }
                
                g.setColor(Color.red);
                g.drawOval(CrotW/2-A, CrotH/2-A, 2*A, 2*A);
                g.drawOval(CrotW/2+z1-5, CrotH/2-z2-5, 10, 10);
                g.setColor(Color.red);
                g.drawLine(CrotW/2, CrotH/2, CrotW/2+z1,CrotH/2-z2);
                g.drawLine(CrotW/2+z1, CrotH/2-z2, CrotW,CrotH/2-z2);
                
                g.setColor(Color.black);
                g.drawLine(0, CrotH/2, CrotW, CrotH/2);
                g.drawLine(CrotW/2, 0, CrotW/2, CrotH);
            }//Graphics
                            
        };
        
        CnWve=new Canvas(){
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.lightGray);
                for(int i = 0;i<CwvW;i=i+inc) {
                    g.drawLine(0, i, CwvW, i);
                g.drawLine(i, 0, i, CwvH);
                }
                g.setColor(Color.black);
                g.drawLine(0, CwvH/2, CwvW, CwvH/2);
                g.drawLine(CwvW/2, 0, CwvW/2, CwvH);
                
                int iY,iY1,j ;
                    Double Y, Y1 ;
                    
                    for(j=-CwvW;j<CwvW;j++) {
                                                
                Y=A*Math.sin(j*Ang/10*Frq+Phs*Ang);
                Y1=A*Math.sin((j+1)*Ang/10*Frq+Phs*Ang);
                iY=(int) Math.round(Y);
                iY1=(int) Math.round(Y1);
                
                g.drawLine(j, CwvH/2-iY,j+1, CwvH/2-iY1);
                    //stem.out.println("Y= "+ iY+ "<---Y1= "+ iY1+"<----| Ang="+ j*Ang);
                    }
                                
            }//Graphics
        };
        
        Jamp=new JLabel("amplitude");
        Jfrq=new JLabel("frequentie");
        Jphs=new JLabel("phase");
        Jvlc=new JLabel("Welocity");
        Sts=new JLabel("STATUS");
        
        
        JLamp=new JSlider(0,150,75);
        JLamp.setPaintTicks(true);
        JLamp.setMajorTickSpacing(25);
        JLamp.setMinorTickSpacing(250);
        JLamp.setPreferredSize(new Dimension(600,50));
        JLamp.setPaintLabels(true);
        JLamp.addChangeListener(new SlideAmp());
        
        JLfrq=new JSlider(1,300,1);
        JLfrq.setPaintTicks(true);
        JLfrq.setMajorTickSpacing(20);
        JLfrq.setMinorTickSpacing(100);
        JLfrq.setPreferredSize(new Dimension(600,50));
        JLfrq.setPaintLabels(true);
        JLfrq.addChangeListener(new SlideFrq());
                
        JLphs=new JSlider(0,360,30);
        JLphs.setPaintTicks(true);
        JLphs.setMajorTickSpacing(30);
        JLphs.setMinorTickSpacing(60);
        JLphs.setPreferredSize(new Dimension(600,50));
        JLphs.setPaintLabels(true);
        JLphs.addChangeListener(new SlidePhs());
                
        JLvlc=new JSlider(0,1000,10);
        JLvlc.setPaintTicks(true);
        JLvlc.setMajorTickSpacing(50);
        JLvlc.setMinorTickSpacing(250);
        JLvlc.setPreferredSize(new Dimension(600,50));
        JLvlc.setPaintLabels(true);
        JLvlc.addChangeListener(new SlideVlc());
                
        Tamp= new JTextField(10);
        Tfrq= new JTextField(10);
        Tphs= new JTextField(10);
        Tvlc= new JTextField(10);
        
        Bamp=new JButton("Amplitude");
        Bfrq=new JButton("frequentie");
        Bphs=new JButton("Phase");
                                        
        JButton BtnStart=new JButton("Start");
        JButton BtnStop=new JButton("Stop");
        
        class NewsletterTask extends TimerTask {
            @Override
            public void run() {
                System.out.println("Email sent at: " );
                  
            }
        }
                        
        Frm.setSize(1400,800);
                
        CnRot.setSize(CrotW,CrotH);
        CnRot.setBackground(Color.white);
        
        CnWve.setSize(CwvW, CwvH);
        CnWve.setBackground(Color.white);
        
        Prot.setSize(200,200);
        Pwve.setSize(500,200);
        
        Prot.add(CnRot);
        Pwve.add(CnWve);
        
        Prot.setBackground(Color.GRAY);
        Pwve.setBackground(Color.GRAY);
        
        c.gridx=0;
        c.gridy=0;
        Frm.add(Prot,c);
        
        c.gridx=1;
        c.gridy=0;
        c.gridwidth=2;
        Frm.add(Pwve,c);
        
        //add Labels
                c.gridx=0;
                c.gridy=1;
                c.anchor=c.NORTHWEST;
                Frm.add(Jamp,c);
                
                c.gridx=0;
                c.gridy=2;
                Frm.add(Jfrq,c);
                
                c.gridx=0;
                c.gridy=3;
                Frm.add(Jphs,c);
                
                c.gridx=0;
                c.gridy=4;
                Frm.add(Jvlc,c);
                
                //add Sliders
                c.gridx=1;
                c.gridy=1;
                Frm.add(JLamp,c);
                
                c.gridx=1;
                c.gridy=2;
                Frm.add(JLfrq,c);
                
                c.gridx=1;
                c.gridy=3;
                Frm.add(JLphs,c);
                
                c.gridx=1;
                c.gridy=4;
                Frm.add(JLvlc,c);
                
                //add Textfields
                c.anchor=c.NORTHEAST;
                c.gridx=3;
                c.gridy=1;
                Frm.add(Tamp,c);
                
                c.gridx=3;
                c.gridy=2;
                Frm.add(Tfrq,c);
                
                c.gridx=3;
                c.gridy=3;
                Frm.add(Tphs,c);
                
                c.gridx=3;
                c.gridy=4;
                Frm.add(Tvlc,c);
                
                c.anchor=c.NORTHEAST;
                c.gridx=2;
                c.gridy=5;
                Frm.add(BtnStart,c);
                BtnStart.addActionListener(new Startlistener());
                
                c.anchor=c.NORTHWEST;
                c.gridx=2;
                c.gridy=5;
                Frm.add(BtnStop,c);
                
                //c.anchor=c.NORTHEAST;
                c.gridx=5;
                c.gridy=1;
                Frm.add(Bamp,c);
                Bamp.addActionListener(new Amplistener());
                
                c.gridx=5;
                c.gridy=2;
                Frm.add(Bfrq,c);
                Bfrq.addActionListener(new Frqlistener());
                
                c.gridx=5;
                c.gridy=3;
                Frm.add(Bphs,c);
                Bphs.addActionListener(new Phslistener());
                
                c.gridx=0;
                c.gridy=6;
                c.gridwidth=5;
                Frm.add(Sts,c);
                                        
        Frm.setDefaultCloseOperation(3);
        Frm.setVisible(true);
                
    }//main

    private void repaint() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public class Amplistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            A=Integer.parseInt(Tamp.getText());
            Sts.setText("Amplitude is: "+A);
            if(A>150) {
                Tamp.setBackground(Color.red);
                Tamp.setForeground(Color.white);
                Tamp.setText("150");
                JLamp.setValue(150);
                Sts.setText("Reached maximum Amplitude");
            }
            JLamp.setValue(A);
            CnRot.repaint();
            CnWve.repaint();
        }
        
    }
    
    
    public class Startlistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Phs=Integer.parseInt(Bphs.getText());
            int i=1;
                    for(i=1;i<30;i++) {
            JLphs.setValue(i);
            Sts.setText("Phase is: "+i);
            CnRot.repaint();
            CnWve.repaint();
                    }
        }
        
        
    }
    
    public class Frqlistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Frq=Integer.parseInt(Tfrq.getText());
            Sts.setText("Frequentie is: "+Frq);
            CnRot.repaint();
            CnWve.repaint();
        }
        
        
    }
    
    public class Phslistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Phs=Integer.parseInt(Tphs.getText());
            Sts.setText("Phase is: "+Phs+"-"+z1+"-"+z2);
            
            CnRot.repaint();
            CnWve.repaint();
        }
        
    }
    
    public class SlideAmp implements ChangeListener{
        public void stateChanged(ChangeEvent e) {
            A=JLamp.getValue();
            Tamp.setText(String.valueOf(A));
            Sts.setText("Amplitude is: "+A);
            CnRot.repaint();
            CnWve.repaint();
        }
    }
    
    public class SlideFrq implements ChangeListener{
        public void stateChanged(ChangeEvent f) {
            Frq=JLfrq.getValue();
            Tfrq.setText(String.valueOf(Frq));
            Sts.setText("Frequentie is: "+Frq);
            CnRot.repaint();
            CnWve.repaint();
        }
    }
    
    public class SlidePhs implements ChangeListener{
        public void stateChanged(ChangeEvent g) {
            Phs=JLphs.getValue();
            Tphs.setText(String.valueOf(Phs));
            Sts.setText("Phase is: "+Phs);
            CnRot.repaint();
            CnWve.repaint();
        }
    }
    
    public class SlideVlc implements ChangeListener{
        public void stateChanged(ChangeEvent g) {
            Vlc=JLvlc.getValue();
            Tvlc.setText(String.valueOf(Vlc));
            Sts.setText("WaveVelocity is: "+Vlc);
            CnRot.repaint();
            CnWve.repaint();
              }
           }
                         
    public static void main(String[] arg) {
        Wavegenerator Wve =new Wavegenerator();
        
        Wve.start();
          
}// end WaveGen
}
