
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.beans.binding.ObjectExpression;




import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame{

    //declare member variables
    public static final String APP_ID = "711141081";
    private String filePath = "";
    private static boolean unsavedChanges = false;

    private JScrollPane scrollPane;
    private JButton newOrderBtn;
    private JButton statisticsBtn;
    private JButton aboutBtn;
    private JButton openBtn;
    private JButton saveBtn;
    private JButton saveAsBtn;
    private static ArrayList<Order> ordersList;
    
    private static DefaultListModel<String> ordersModel;
    private JList<String> ordersJLst;

    private final ButtonListener myListener;

    // MENU STUFF

    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem newOrderItem, openItem, saveItem,
            saveAsItem, exitItem, aboutItem, statisticsItem;

    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV Files (*.csv)","csv");
    public MainFrame() {
        super();

        // create a button listener
        myListener = new ButtonListener();


        // create menus and menu items

        JMenu fileMenu = new JMenu("File");
        JMenu infoMenu = new JMenu("Info");

        fileMenu.setMnemonic('F');
        infoMenu.setMnemonic('I');

        newOrderItem = fileMenu.add("New Order");
        newOrderItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
        newOrderItem.addActionListener(myListener);

        openItem = fileMenu.add("Open File");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O',Event.CTRL_MASK));
        openItem.addActionListener(myListener);

        saveItem = fileMenu.add("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S',Event.CTRL_MASK));
        saveItem.addActionListener(myListener);

        saveAsItem = fileMenu.add("Save as");
        saveAsItem.addActionListener(myListener);

        fileMenu.addSeparator();

        exitItem = fileMenu.add("Exit");
        exitItem.addActionListener(myListener);

        statisticsItem = infoMenu.add("Statistics");
        statisticsItem.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK ));
        statisticsItem.addActionListener(myListener);

        aboutItem = infoMenu.add("About");
        aboutItem.addActionListener(myListener);

        menuBar.add(fileMenu);
        menuBar.add(infoMenu);


        ordersList = new ArrayList<>();

        ordersModel = new DefaultListModel<>();
        ordersJLst = new JList<>(ordersModel);

        newOrderBtn = new JButton("New Order");
        newOrderBtn.addActionListener(myListener);

        statisticsBtn = new JButton("Statistics");
        statisticsBtn.addActionListener(myListener);

        aboutBtn = new JButton("About");
        aboutBtn.addActionListener(myListener);

        openBtn = new JButton("Open File");
        openBtn.addActionListener(myListener);

        saveBtn = new JButton("Save");
        saveBtn.addActionListener(myListener);

        saveAsBtn = new JButton("Save as");
        saveAsBtn.addActionListener(myListener);
    }

    public void prepareUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(ordersJLst);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));


        buttonsPanel.add(newOrderBtn);
        buttonsPanel.add(statisticsBtn);
        buttonsPanel.add(aboutBtn);


        buttonsPanel.add(openBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(saveAsBtn);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!_checkForUnsavedChanges())
                    System.exit(0);
            }
        });

    }





    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            if(buttonName == "New Order"){
                _openNewOrderFrame();

            }else if(buttonName == "About"){
                _openAboutFrame();

            }else if(buttonName == "Statistics"){
                _openStatisticsFrame();

            }else if(buttonName == "Open File"){
                if(!_checkForUnsavedChanges()){
                    if (e.getSource() == openBtn)  _loadFromFile(openBtn);
                    else _loadFromFile(openItem);
                }

            }else if(buttonName == "Save as"){
                if (e.getSource() == saveAsBtn) _saveToFile(saveAsBtn);
                else _saveToFile(saveAsItem);

            }else if(buttonName == "Save"){
                if (filePath == ""){
                    if (e.getSource() == saveBtn) _saveToFile(saveBtn);
                    else _saveToFile(saveItem);
                }else _saveOrdersList(filePath);

            }else if(buttonName == "Exit"){
                if(!_checkForUnsavedChanges()){
                    dispose();
                    System.exit(0);
                }
            }
        }
    }



    // ---------- METHODS ---------------------
    private void _openNewOrderFrame(){
        NewOrderFrame newOrderFrame = new NewOrderFrame();
        newOrderFrame.setTitle("New Order");
        newOrderFrame.prepareUI();
        newOrderFrame.setSize(340,270);
        newOrderFrame.setLocation(this.getLocation());
        newOrderFrame.setResizable(true);
        newOrderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newOrderFrame.setVisible(true);
    }

    private void _openAboutFrame(){
        AboutFrame aboutFrame = new AboutFrame();
        aboutFrame.setTitle("About");
        aboutFrame.prepareUI();
        aboutFrame.setSize(340, 250);
        aboutFrame.setLocation(this.getLocation());
        aboutFrame.setResizable(true);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutFrame.setVisible(true);
    }

    private void _openStatisticsFrame(){
        StatisticsFrame statisticsFrame = new StatisticsFrame(ordersList);
        statisticsFrame.setTitle("Statistics");
        statisticsFrame.prepareUI();
        statisticsFrame.setSize(340,250);
        statisticsFrame.setLocation(this.getLocation());
        statisticsFrame.setResizable(true);
        statisticsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statisticsFrame.setVisible(true);
    }

    private void _loadFromFile(Component component){
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(fileFilter);
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(component);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getPath();

            if (fileName != null && !fileName.isEmpty()) {
                _loadOrdersList(fileName);
                _setTitle(fileName);
                filePath = fileName;
                unsavedChanges = false;
            }

        }
    }

    private  void _saveToFile(Component component){
        if (ordersList.isEmpty()) {
            JOptionPane.showMessageDialog(saveBtn,
                    "Nothing to save",
                    "File access error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(fileFilter);
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showSaveDialog(component);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getPath();

            if (fileName != null && !fileName.isEmpty()) {
                _saveOrdersList(fileName);
            }
        }
    }

    public static void addOrder(String orderID,String date,String clientName,
                                 String itemName,int unitsCount,double netItemPrice,double taxPercentage){
        Order order = new Order(APP_ID,orderID,date,clientName,itemName,unitsCount,netItemPrice,taxPercentage);
        ordersModel.addElement(order.toString());
        ordersList.add(order);
        unsavedChanges = true;
    }

    private void _saveOrdersList(String fileName) {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(fileName));

            for (Order order : ordersList) {
                file.write(order.toCsv());
                file.newLine();
            }
            file.close();
            JOptionPane.showMessageDialog(saveBtn,
                    ordersList.size() + " records saved to " + fileName,
                    "Save completed",
                    JOptionPane.INFORMATION_MESSAGE);
            unsavedChanges = false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: Couldn't save data", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void _loadOrdersList(String fileName) {
        try {
            ordersList.clear();
            ordersModel.clear();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            String[] token;

            while (reader.ready()) {

                line = reader.readLine();
                token = line.split(",");

                if (token.length == 8) {
                    addOrder(token[1],token[2],token[3],token[4],Integer.parseInt(token[5])
                            ,Double.parseDouble(token[6]),Double.parseDouble(token[7]));
                }
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error: Couldn't load data", "Load Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: Couldn't load data", "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void _setTitle(String filename){
        this.setTitle("FinalProject "+ filename);
    }

    private boolean _checkForUnsavedChanges(){
        boolean cancelOption = false;
        if (unsavedChanges){
            int i = JOptionPane.showConfirmDialog(this,"This file has been modified,\nSave changes?");
            if (i == JOptionPane.YES_OPTION){
                if (filePath == ""){
                    _saveToFile(this);
                }else _saveOrdersList(filePath);
            }else if(i == JOptionPane.CANCEL_OPTION){
                cancelOption = true;
            }

        }
        return cancelOption;
    }

}