package first_level;

abstract public class Worker {
    private final String name;
    private final String surname;
    private final double pricePerHour;

    public Worker(String name, String surname, double pricePerHour){
        this.name = name;
        this.surname = surname;
        this.pricePerHour = pricePerHour;
    }

    public double getPricePerHour(){
        return this.pricePerHour;
    }

    public double calculateSalary(double nOfWorkedHours){
        return nOfWorkedHours * this.pricePerHour;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
