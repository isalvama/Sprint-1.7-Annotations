import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import second_and_third_level.OnlineWorker;

import java.lang.reflect.Method;

public class OnlineWorkerTest implements WithAssertions {
    OnlineWorker onlineWorker;

    @BeforeEach
    public void setUp() {
        onlineWorker = new OnlineWorker("Joe", "Gonzalez", 20);
    }

    @Test
    @DisplayName("An assertion that demonstrates that calculateSalary method returns an expected value")
    void calculateSalary_whenPassingNumOfHours_returnsExpectedValue() {
        assertThat(onlineWorker.calculateSalary(8)).isEqualTo(169);
    }

    @Test
    @DisplayName("An assertion that demonstrates that calculateBasicSalary method has @Deprecated annotation")
    void calculateBasicSalary_hasDeprecatedAnnotation_returnsTrue() throws NoSuchMethodException {
        Method method = OnlineWorker.class.getDeclaredMethod("calculateBasicSalary", double.class);
        assertThat(method.isAnnotationPresent(Deprecated.class)).isTrue();
        assertThat(method.getAnnotation(Deprecated.class)).isNotNull();
    }

}
