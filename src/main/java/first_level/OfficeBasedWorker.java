package first_level;

public class OfficeBasedWorker extends Worker {
    private static double gasPrice = 1.99;

    public OfficeBasedWorker(String name, String surname, int pricePerHour){
        super(name, surname, pricePerHour);
    }

    public static double getGasPrice() {
        return gasPrice;
    }

    @Override
    public double calculateSalary(double nWorkedHours) {
        return (this.getPricePerHour() * nWorkedHours) + gasPrice;
    }

    /**
     * This method sets the gas price without boundaries.
     * @deprecated This method is obsolete as the gas price can not be increased or decreased more than a unity of difference.
     * Use {@link #increaseGasPrice(double)} or {@link #decreaseGasPrice(double)} instead to increase or decrease the gasPrice.
     * @param gasPrice new price of gas to be set.
     */
    @Deprecated
    public static void setGasPrice(double gasPrice){
        OfficeBasedWorker.gasPrice = gasPrice;
    }

    public static void increaseGasPrice(double increaseInPrice){
        if (increaseInPrice > 1){
            throw new IllegalArgumentException("The gas price can not increase more than a unity of difference");
        }
        if (increaseInPrice < 0){
            throw new IllegalArgumentException("The gas price increase can not be negative");
        }
        gasPrice += increaseInPrice;
    }

    public static void decreaseGasPrice(double decreaseInPrice){
        if (decreaseInPrice < -1){
            throw new IllegalArgumentException("The gas price can not decrease more than a unity of difference");
        }
        if (decreaseInPrice > 0){
            throw new IllegalArgumentException("The gas price increase can not be positive");
        }
        gasPrice -= decreaseInPrice;
    }

    @Override
    public String toString() {
        return "OfficeBasedWorker{}";
    }
}
