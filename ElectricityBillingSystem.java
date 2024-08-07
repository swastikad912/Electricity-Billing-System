package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElectricityBillingSystem extends JFrame implements ActionListener {
    private JTextField customerNameField, meterNumberField, unitsConsumedField, totalBillField;
    private JButton calculateButton, resetButton;
    private JLabel customerNameLabel, meterNumberLabel, unitsConsumedLabel, tariffRateLabel, totalBillLabel;
    private JComboBox<String> tariffRateCombo;

    private final double[] tariffRates = { 5.0, 7.0, 9.0 };

    public ElectricityBillingSystem() {
        setTitle("Electricity Billing System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        customerNameLabel = new JLabel("Customer Name:");
        add(customerNameLabel);
        customerNameField = new JTextField();
        add(customerNameField);

        meterNumberLabel = new JLabel("Meter Number:");
        add(meterNumberLabel);
        meterNumberField = new JTextField();
        add(meterNumberField);

        unitsConsumedLabel = new JLabel("Units Consumed:");
        add(unitsConsumedLabel);
        unitsConsumedField = new JTextField();
        add(unitsConsumedField);

        tariffRateLabel = new JLabel("Select Tariff Rate:");
        add(tariffRateLabel);
        tariffRateCombo = new JComboBox<>(new String[] { "Low (5.0)", "Medium (7.0)", "High (9.0)" });
        add(tariffRateCombo);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        add(calculateButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        totalBillLabel = new JLabel("Total Bill Amount:");
        add(totalBillLabel);
        totalBillField = new JTextField();
        totalBillField.setEditable(false);
        add(totalBillField);

        getContentPane().setBackground(Color.LIGHT_GRAY); // Set background color

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            calculateBill();
        } else if (e.getSource() == resetButton) {
            resetFields();
        }
    }

    private void calculateBill() {
        try {
            String customerName = customerNameField.getText();
            String meterNumber = meterNumberField.getText();
            int unitsConsumed = Integer.parseInt(unitsConsumedField.getText());
            double tariffRate = getSelectedTariffRate();

            double billAmount = unitsConsumed * tariffRate;

            totalBillField.setText("$" + billAmount);

            String receipt = "Customer Name: " + customerName + "\n" + "Meter Number: " + meterNumber + "\n"
                    + "Units Consumed: " + unitsConsumed + "\n" + "Tariff Rate: " + tariffRate + "\n"
                    + "Total Bill Amount: $" + billAmount + "\n" + "Date & Time: "
                    + new SimpleDateFormat("EEEE, MMMM dd, yyyy HH:mm:ss").format(new Date());

            // Show receipt in a popup dialog
            JOptionPane.showMessageDialog(this, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for units consumed!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private double getSelectedTariffRate() {
        int selectedIndex = tariffRateCombo.getSelectedIndex();
        return tariffRates[selectedIndex];
    }

    private void resetFields() {
        customerNameField.setText("");
        meterNumberField.setText("");
        unitsConsumedField.setText("");
        tariffRateCombo.setSelectedIndex(0);
        totalBillField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ElectricityBillingSystem::new);
    }
}

