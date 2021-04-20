
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AboutFrame extends JFrame{

    //declare member variables

    private JLabel nameLbl;
    private JLabel surnameLbl;
    private JLabel AMLbl;
    private JLabel emailLbl;
    private JLabel timeLbl;
    private JLabel IDELbl;

    private JLabel crNameLbl;
    private JLabel crAMLbl;
    private JLabel crEmailLbl;
    private JLabel crSurnameLb;
    private JLabel crTimeLbl;
    private JLabel crIDELbl;

    private JButton showScreenshotBtn;

    private  Font font;
    public AboutFrame() {
        font = new Font("Courier", Font.ITALIC,15);

        nameLbl = new JLabel("Name: ");
        nameLbl.setFont(font);

        surnameLbl = new JLabel("Surname: ");
        surnameLbl.setFont(font);

        AMLbl = new JLabel("A.M: ");
        AMLbl.setFont(font);

        emailLbl = new JLabel("E-mail: ");
        emailLbl.setFont(font);

        timeLbl = new JLabel("Development time: ");
        timeLbl.setFont(font);

        IDELbl = new JLabel("IDE used: ");
        IDELbl.setFont(font);

        crNameLbl = new JLabel("Antonis");
        crSurnameLb = new JLabel("Agapiou");
        crAMLbl = new JLabel(MainFrame.APP_ID);
        crEmailLbl = new JLabel("cs141081@uniwa.gr");
        crTimeLbl = new JLabel("12 hours");
        crIDELbl = new JLabel("IntelliJ IDEA & NetBeans 8.2");

        showScreenshotBtn = new JButton("Show Screenshot");
    }

    public void prepareUI() {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BoxLayout(aboutPanel,BoxLayout.Y_AXIS));

        JPanel creatorInfoPanel = new JPanel();
        creatorInfoPanel.setLayout(new GridLayout(4,2));
        creatorInfoPanel.setBorder(new TitledBorder("Creator Info"));

        JPanel appInfoPanel = new JPanel();
        appInfoPanel.setLayout(new GridLayout(2,2));
        appInfoPanel.setBorder(new TitledBorder("App Info"));

        creatorInfoPanel.add(nameLbl);
        creatorInfoPanel.add(crNameLbl);
        creatorInfoPanel.add(surnameLbl);
        creatorInfoPanel.add(crSurnameLb);
        creatorInfoPanel.add(AMLbl);
        creatorInfoPanel.add(crAMLbl);
        creatorInfoPanel.add(emailLbl);
        creatorInfoPanel.add(crEmailLbl);

        appInfoPanel.add(timeLbl);
        appInfoPanel.add(crTimeLbl);
        appInfoPanel.add(IDELbl);
        appInfoPanel.add(crIDELbl);

        showScreenshotBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        aboutPanel.add(creatorInfoPanel);
        aboutPanel.add(appInfoPanel);
        aboutPanel.add(showScreenshotBtn);

        this.add(aboutPanel);

        //button Listener
        showScreenshotBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIcon image = new ImageIcon(new ImageIcon("C:\\Users\\green\\IdeaProjects\\FinalProject\\src\\about.png")
                            .getImage().getScaledInstance(920, 600, Image.SCALE_DEFAULT));
                    JFrame ImFrame = new JFrame();
                    JLabel lbl = new JLabel();
                    lbl.setIcon(image);
                    ImFrame.add(lbl);
                    ImFrame.pack();
                    ImFrame.setVisible(true);
                    ImFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (Exception exception) {
                    System.out.println("Cant load image"+exception);
                }
            }
        });
    }

}

