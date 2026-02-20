package second_level;

@ToJsonFile(directoryPath = "Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(\"\")).getPath();")
public class OnlineWorker extends Worker {
    private int priceMaterialPerHour;
    private static final double INTERNET_FLAT_RATE_PRICE = 9;

    public OnlineWorker(String name, String surname, int pricePerHour, int priceMaterialPerHour) {
        super(name, surname, pricePerHour);
        this.priceMaterialPerHour = priceMaterialPerHour;
    }

    public int getPriceMaterialPerHour() {
        return priceMaterialPerHour;
    }

    @Override
    public double calculateSalary(double nWorkedHours) {
        return (this.getPricePerHour() * nWorkedHours) + INTERNET_FLAT_RATE_PRICE;
    }

    /**
     * This method calculates the hours without taking into account the Internet plus.
     *
     * @param nWorkedHours number of hours the employer has worked
     * @return the salary without extras
     * @deprecated This method is obsolete.
     * Use {@link #calculateSalary(double)} instead to include the internet bonus.
     */
    @Deprecated
    public double calculateBasicSalary(double nWorkedHours) {
        return (this.getPricePerHour() * nWorkedHours) + INTERNET_FLAT_RATE_PRICE;
    }

}