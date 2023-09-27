import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AssetSimulation {
    private static final int SIMULATION_INTERVAL_MS = 1000; // Simulation interval in milliseconds

    public static void main(String[] args) {
        // Create an instance of the Digital Twin application
        DigitalTwinDemo digitalTwinDemo = new DigitalTwinDemo();

        // Create a timer to simulate asset data updates
        Timer simulationTimer = new Timer();
        simulationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                simulateAssetDataUpdate(digitalTwinDemo);
            }
        }, 0, SIMULATION_INTERVAL_MS);

        // Set up the Digital Twin application window
        digitalTwinDemo.setSize(800, 400);
        digitalTwinDemo.setVisible(true);
    }

    private static void simulateAssetDataUpdate(DigitalTwinDemo digitalTwinDemo) {
        Random rand = new Random();

        // Simulate data updates for each asset in the application
        for (Asset asset : digitalTwinDemo.getAssetList()) {
            // Generate random values for temperature and pressure
            double newTemperature = getRandomValue(asset.getTemperature() - 1, asset.getTemperature() + 1);
            double newPressure = getRandomValue(asset.getPressure() - 5, asset.getPressure() + 5);

            // Set the new values for temperature and pressure
            asset.setTemperature(newTemperature);
            asset.setPressure(newPressure);
        }

        // Update the Digital Twin application's table with the new data
        digitalTwinDemo.updateTable();
    }

    private static double getRandomValue(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }
}
