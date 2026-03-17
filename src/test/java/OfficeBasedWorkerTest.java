import first_level.OfficeBasedWorker;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

public class OfficeBasedWorkerTest implements WithAssertions {
    OfficeBasedWorker officeBasedWorker;

    @BeforeEach
    public void setUp() {
        officeBasedWorker = new OfficeBasedWorker("Ignacio", "Perez", 25);
    }

    @Test
    @DisplayName("An assertion that demonstrates that calculateSalary method returns an expected value")
    void calculateSalary_whenPassingNumOfHours_returnsExpectedValue() {
        assertThat(officeBasedWorker.calculateSalary(8)).isEqualTo(201.99);
    }

    @Test
    @DisplayName("An assertion that demonstrates that setGasPrice method has @Deprecated annotation")
    void setGasPrice_hasDepracatedAnnotation_returnsTrue() throws NoSuchMethodException {
        Method method = OfficeBasedWorker.class.getDeclaredMethod("setGasPrice", double.class);
        assertThat(method.isAnnotationPresent(Deprecated.class)).isTrue();
        assertThat(method.getAnnotation(Deprecated.class)).isNotNull();
    }

    @Test
    @DisplayName("An assertion that demonstrates that increaseGasPrice method throws IllegalArgumentException when passing negative numbers or numbers greater than 1")
    void increaseGasPrice_whenPassingNegativeOrGreaterThanOneNumber_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> {OfficeBasedWorker.increaseGasPrice(-1);}).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {OfficeBasedWorker.increaseGasPrice(2);}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("An assertion that demonstrates that decreaseGasPrice method throws IllegalArgumentException when passing positive numbers or numbers smaller than -1")
    void decreaseGasPrice_whenPassingPositiveNumOrSmallerThanMinusOne_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> {OfficeBasedWorker.decreaseGasPrice(1);}).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {OfficeBasedWorker.decreaseGasPrice(-1.5);}).isInstanceOf(IllegalArgumentException.class);
    }
}
