
# Asset Simulation Java Application

This Java application simulates real-time updates of asset data for a digital twin system. It uses Java's `Timer` and `TimerTask` classes to periodically update the simulated asset data. Here's an overview of the components and functionality of the code:

## Components:

### `AssetSimulation` Class:
- The main class containing the entry point of the application.
- Sets up a timer to periodically update asset data and visualizes the data using the `DigitalTwinDemo` class.

### `DigitalTwinDemo` Class:
- Represents the digital twin application window.
- Provides methods to manage assets and update the application's UI with new data.

### `Asset` Class:
- Represents an asset with properties like temperature and pressure.
- Contains methods to set and retrieve asset data.

## How it Works:

1. The application creates an instance of the `DigitalTwinDemo` class, which represents the digital twin application.

2. A timer (`simulationTimer`) is set up to run a `TimerTask` at regular intervals (`SIMULATION_INTERVAL_MS`) to simulate asset data updates.

3. The `simulateAssetDataUpdate` method generates random values for temperature and pressure for each asset in the application. The new values are within a specified range of the current values.

4. The `getRandomValue` method generates a random double value between the specified minimum and maximum values.

5. The updated asset data is displayed in the `DigitalTwinDemo` application window by calling the `updateTable` method.

## How to Run:

1. Compile the Java code using a Java compiler.
   ```
   javac AssetSimulation.java
   ```

2. Run the compiled Java application.
   ```
   java AssetSimulation
   ```

## Dependencies:
- The code uses Java's built-in libraries and does not require any external dependencies.

## Notes:
- This simulation provides a basic framework for simulating asset data updates in a digital twin system. You can customize the simulation interval and data update logic based on specific requirements.

Feel free to modify and extend this code to suit your application's needs.
