package first_level;

public class OnlineWorker extends Worker{
    private static final double INTERNET_FLAT_RATE_PRICE = 9;

    public OnlineWorker(String name, String surname, int pricePerHour){
        super(name, surname, pricePerHour);
    }

    @Override
    public double calculateSalary(double workedHours){
        return (this.getPricePerHour() * workedHours) + INTERNET_FLAT_RATE_PRICE;
    }

    /**
     *This method calculates the hours without taking into account the Internet plus.
     * @deprecated This method is obsolete.
     * Use {@link #calculateSalary(double)} instead to include the internet bonus.
     * @param workedHours number of hours the employer has worked
     * @return the salary without extras
     */
    @Deprecated
    public double calculateBasicSalary(double workedHours){
        return this.getPricePerHour() * workedHours;
    }
}
