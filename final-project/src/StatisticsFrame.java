/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;

public class StatisticsFrame extends JFrame{

    //declare member variables
    private JLabel statisticsLbl;

    private JLabel totalOrdersNumberLbl;
    private JLabel totalCostPureLbl;
    private JLabel totalCostMixedLbl;
    private JLabel mostExpensiveOrderLbl;
    private JLabel leastExpensiveOrderLbl;

    private JLabel orTotalOrdersNumberLbl;
    private JLabel orTotalCostPureLbl;
    private JLabel orTotalCostMixedLbl;
    private JLabel orMostExpensiveOrderLbl;
    private JLabel orLeastExpensiveOrderLbl;
    private Font font1;
    private Font font2;
    private Border statisticsPanelBorder;

    public StatisticsFrame(ArrayList<Order> ordersList) {

        statisticsPanelBorder = BorderFactory.createLoweredBevelBorder();

        font1 = new Font("Arial", Font.BOLD,15);
        font2 = new Font("Courier", Font.ITALIC,15);

        statisticsLbl = new JLabel("Orders' Statistics");
        statisticsLbl.setFont(font1);

        totalOrdersNumberLbl = new JLabel("Total orders: ");
        totalOrdersNumberLbl.setFont(font2);

        totalCostPureLbl = new JLabel("Total cost(pure): ");
        totalCostPureLbl.setFont(font2);

        totalCostMixedLbl = new JLabel("Total cost(mixed): ");
        totalCostMixedLbl.setFont(font2);

        mostExpensiveOrderLbl = new JLabel("Most expensive order: ");
        mostExpensiveOrderLbl.setFont(font2);

        leastExpensiveOrderLbl = new JLabel("Least expensive order: ");
        leastExpensiveOrderLbl.setFont(font2);



        orTotalOrdersNumberLbl = new JLabel(_getTotalOrders(ordersList));
        orTotalCostPureLbl = new JLabel(_getTotalCostPure(ordersList)+"$");
        orTotalCostMixedLbl = new JLabel(_getTotalCostMixed(ordersList)+"$");
        orMostExpensiveOrderLbl = new JLabel(_getMostExpensiveOrder(ordersList));
        orLeastExpensiveOrderLbl = new JLabel(_getLeastExpensiveOrder(ordersList));

    }

    public void prepareUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setBorder(statisticsPanelBorder);
        statisticsPanel.setLayout(new GridLayout(5,2));

        statisticsPanel.add(totalOrdersNumberLbl);
        statisticsPanel.add(orTotalOrdersNumberLbl);
        statisticsPanel.add(totalCostPureLbl);
        statisticsPanel.add(orTotalCostPureLbl);
        statisticsPanel.add(totalCostMixedLbl);
        statisticsPanel.add(orTotalCostMixedLbl);
        statisticsPanel.add(mostExpensiveOrderLbl);
        statisticsPanel.add(orMostExpensiveOrderLbl);
        statisticsPanel.add(leastExpensiveOrderLbl);
        statisticsPanel.add(orLeastExpensiveOrderLbl);

        statisticsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(statisticsLbl);
        mainPanel.add(statisticsPanel);

        this.add(mainPanel);


    }

    private String _getTotalOrders(ArrayList<Order> ordersList){
        return Integer.toString(ordersList.size());
    }

    private String _getTotalCostPure(ArrayList<Order> ordersList){
        double sum = 0;
        for(Order order : ordersList) {
            sum = sum + (order.getUnitsCount() * order.getNetItemPrice());
        }
        sum = Math.floor(sum * 100) / 100;
        return Double.toString(sum);
    }

    private String _getTotalCostMixed(ArrayList<Order> ordersList){
        double sum = 0;
        double tax;
        for(Order order : ordersList) {
            tax = (order.getTaxPercentage() * order.getNetItemPrice())/100;
            sum = sum + ((order.getNetItemPrice() - tax) * order.getUnitsCount());
        }
        sum =  Math.floor(sum * 100) / 100;
        return Double.toString(sum);
    }

    private String _getMostExpensiveOrder(ArrayList<Order> ordersList){
        double orderCost = 0;
        double maxCost = 0;
        String orderID = "";

        for(Order order : ordersList) {
            orderCost = order.getUnitsCount() * order.getNetItemPrice();
            if (orderCost>maxCost){
                maxCost = orderCost;
                orderID = order.getOrderId();
            }
        }
        return orderID;
    }

    private String _getLeastExpensiveOrder(ArrayList<Order> ordersList){
        double orderCost = 0;
        double minCost = Double.POSITIVE_INFINITY;
        String orderID = "";

        for(Order order : ordersList) {
            orderCost = order.getUnitsCount() * order.getNetItemPrice();
            if (orderCost<minCost){
                minCost = orderCost;
                orderID = order.getOrderId();
            }
        }
        return orderID;
    }
}

