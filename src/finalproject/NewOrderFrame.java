

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewOrderFrame extends JFrame{

    //declare member variables
    private JLabel addOrderLbl;

    private JLabel orderIDLbl;
    private JLabel dateLbl;
    private JLabel clientNameLbl;
    private JLabel itemNameLbl;
    private JLabel unitsCountLbl;
    private JLabel netItemPriceLbl;
    private JLabel taxPercentageLbl;

    private JTextField orderIDTf;
    private JTextField dateTf;
    private JTextField clientNameTf;
    private JTextField itemNameTf;
    private JTextField unitsCountTf;
    private JTextField netItemPriceTf;
    private JTextField taxPercentageTf;

    private JButton completeOrderBtn;
    private static JLabel errorLbl;

    private Font font;

    private EmptyBorder addOrderLblBorder;
    private Border addOrderPanelBorder;

    public NewOrderFrame() {
        super();
        font = new Font("Arial", Font.BOLD,15);

        addOrderLblBorder = new EmptyBorder(3, 0, 20, 0);
        addOrderPanelBorder = BorderFactory.createLoweredBevelBorder();

        addOrderLbl = new JLabel("Add new order\n");
        addOrderLbl.setBorder(addOrderLblBorder);
        addOrderLbl.setFont(font);

        orderIDLbl = new JLabel("Order ID");
        dateLbl = new JLabel("Order Date");
        clientNameLbl = new JLabel("Client name");
        itemNameLbl = new JLabel("Item name");
        unitsCountLbl = new JLabel("Units count");
        netItemPriceLbl = new JLabel("Net Item Price");
        taxPercentageLbl = new JLabel("Tax Percentage");

        orderIDTf = new JTextField();
        dateTf = new JTextField();
        clientNameTf = new JTextField();
        itemNameTf = new JTextField();
        unitsCountTf = new JTextField();
        netItemPriceTf = new JTextField();
        taxPercentageTf = new JTextField();

        completeOrderBtn = new JButton("Complete Order");

        errorLbl = new JLabel();
        errorLbl.setForeground(Color.RED);
    }

    public void prepareUI() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JPanel addOrderPanel = new JPanel();

        addOrderPanel.setLayout(new GridLayout(7,2));
        addOrderPanel.setBorder(addOrderPanelBorder);

        addOrderPanel.add(orderIDLbl);
        addOrderPanel.add(orderIDTf);
        addOrderPanel.add(dateLbl);
        addOrderPanel.add(dateTf);
        addOrderPanel.add(clientNameLbl);
        addOrderPanel.add(clientNameTf);
        addOrderPanel.add(itemNameLbl);
        addOrderPanel.add(itemNameTf);
        addOrderPanel.add(unitsCountLbl);
        addOrderPanel.add(unitsCountTf);
        addOrderPanel.add(netItemPriceLbl);
        addOrderPanel.add(netItemPriceTf);
        addOrderPanel.add(taxPercentageLbl);
        addOrderPanel.add(taxPercentageTf);

        addOrderLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        completeOrderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(addOrderLbl);
        mainPanel.add(addOrderPanel);
        mainPanel.add(completeOrderBtn);
        mainPanel.add(errorLbl);

        this.add(mainPanel);
        
        completeOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderID = orderIDTf.getText();
                String date = dateTf.getText();
                String clientName = clientNameTf.getText();
                String itemName = itemNameTf.getText();
                String unitsCount = unitsCountTf.getText();
                String netItemPrice = netItemPriceTf.getText();
                String taxPercentage = taxPercentageTf.getText();

                if (_checkInput(orderID,date,clientName,itemName,unitsCount,netItemPrice,taxPercentage)) {
                    MainFrame.addOrder(orderID, date, clientName, itemName, Integer.parseInt(unitsCount), Float.parseFloat(netItemPrice),
                            Float.parseFloat(taxPercentage));
                    dispose();
                }
            }
        });
        
    }

    private boolean _checkInput(String orderID,String date,String clientName, String itemName, String unitsCount,
                                String netItemPrice, String taxPercentage){

        errorLbl.setVisible(false);

        if (orderID.isEmpty() || orderID == null) {
            errorLbl.getText();
            errorLbl.setText("Order ID field is empty");
            errorLbl.setVisible(true);
            return false;
        }
        if (date.isEmpty() || date == null) {
            errorLbl.getText();
            errorLbl.setText("Date field is empty");
            errorLbl.setVisible(true);
            return false;
        }
        if (clientName.isEmpty() || clientName == null) {
            errorLbl.getText();
            errorLbl.setText("Client name field is empty");
            errorLbl.setVisible(true);
            return false;
        }
        if (itemName.isEmpty() || itemName == null) {
            errorLbl.getText();
            errorLbl.setText("Item name field is empty");
            errorLbl.setVisible(true);
            return false;
        }
        try{
            Integer.parseInt(unitsCount);
        }catch (Exception ex){
            errorLbl.getText();
            errorLbl.setText("Units count field is not a integer");
            errorLbl.setVisible(true);
            return false;
        }
        try{
            Float.parseFloat(netItemPrice);
        }catch (Exception ex){
            errorLbl.getText();
            errorLbl.setText("Net Item Price field is not a float");
            errorLbl.setVisible(true);
            return false;
        }
        try{
            Float.parseFloat(taxPercentage);
        }catch (Exception ex){
            errorLbl.getText();
            errorLbl.setText("Tax Percentage field is not a float");
            errorLbl.setVisible(true);
            return false;
        }
        return true;
    }


}

