import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DigitalTwinDemo extends JFrame {
    private JLabel titleLabel;
    private JTable assetTable;
    private DefaultTableModel tableModel;
    private JTextField assetNameField;
    private JTextField assetTypeField; // New field for asset type
    private JButton addButton;
    private JButton deleteButton; // New delete button
    private List<Asset> assetList;

    public DigitalTwinDemo() {
        setTitle("Digital Twin Demo");
        assetList = new ArrayList<>();

        titleLabel = new JLabel("Assets List:");
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Asset ID");
        tableModel.addColumn("Asset Name");
        tableModel.addColumn("Asset Type"); // New column for asset type
        tableModel.addColumn("Temperature (°C)");
        tableModel.addColumn("Pressure (kPa)");
        tableModel.addColumn("Status");
        tableModel.addColumn("Date Added");

        assetTable = new JTable(tableModel);
        assetTable.setPreferredScrollableViewportSize(new Dimension(600, 200));
        assetTable.setFillsViewportHeight(true);

        assetNameField = new JTextField(20);
        assetTypeField = new JTextField(20); // New field for asset type
        addButton = new JButton("Add Asset");
        deleteButton = new JButton("Delete Asset"); // New delete button

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        add(new JScrollPane(assetTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(new JLabel("Asset Name:"));
        bottomPanel.add(assetNameField);
        bottomPanel.add(new JLabel("Asset Type:")); // New label for asset type
        bottomPanel.add(assetTypeField); // New field for asset type
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton); // Add delete button
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAsset();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedAsset();
            }
        });

        // Initialize and start data update timer
        Timer dataUpdateTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDigitalTwinData();
            }
        });
        dataUpdateTimer.start();

        // Add mouse listener to the table for selecting rows
        assetTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = assetTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Enable or disable the delete button based on row selection
                    deleteButton.setEnabled(true);
                } else {
                    deleteButton.setEnabled(false);
                }
            }
        });
    }

    private void updateDigitalTwinData() {
        // Simulate digital twin data (replace with real data retrieval logic)
        for (Asset asset : assetList) {
            asset.setTemperature(getRandomValue(20, 30));
            asset.setPressure(getRandomValue(100, 200));
            asset.setStatus("Online");
        }
        updateTable();
    }

    private void addAsset() {
        String assetName = assetNameField.getText();
        String assetType = assetTypeField.getText(); // Get asset type from the input field
        double temperature = getRandomValue(20, 30);
        double pressure = getRandomValue(100, 200);
        String status = "Online";
        Date dateAdded = new Date();

        Asset newAsset = new Asset(assetList.size() + 1, assetName, assetType, temperature, pressure, status, dateAdded);
        assetList.add(newAsset);
        assetNameField.setText(""); // Clear the input field for asset name
        assetTypeField.setText(""); // Clear the input field for asset type
        updateTable();
    }

    private void deleteSelectedAsset() {
        int selectedRow = assetTable.getSelectedRow();
        if (selectedRow >= 0) {
            assetList.remove(selectedRow);
            updateTable();
        }
    }

    private double getRandomValue(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear the table
        for (Asset asset : assetList) {
            tableModel.addRow(new Object[]{
                    asset.getId(),
                    asset.getName(),
                    asset.getAssetType(),
                    asset.getTemperature(),
                    asset.getPressure(),
                    asset.getStatus(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(asset.getDateAdded())
            });
        }
    }

    // Getter for asset list
    public List<Asset> getAssetList() {
        return assetList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DigitalTwinDemo digitalTwinDemo = new DigitalTwinDemo();
                digitalTwinDemo.setSize(800, 400);
                digitalTwinDemo.setVisible(true);
            }
        });
    }
}

class Asset {
    private int id;
    private String name;
    private String assetType; // New field for asset type
    private double temperature;
    private double pressure;
    private String status;
    private Date dateAdded;

    public Asset(int id, String name, String assetType, double temperature, double pressure, String status, Date dateAdded) {
        this.id = id;
        this.name = name;
        this.assetType = assetType;
        this.temperature = temperature;
        this.pressure = pressure;
        this.status = status;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAssetType() {
        return assetType;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
}
