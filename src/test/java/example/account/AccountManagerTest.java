package example.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AccountManagerTest {

    private AccountManager accountManager;
    private Customer customer;

    @BeforeEach
    void setUp() {
        accountManager = new AccountManagerImpl();
        customer = new Customer();
    }

    @Test
    void givenSufficientBalance_whenWithdraw_thenSuccess() {
        // Arrange
        int initialBalance = 1000;
        customer.setBalance(initialBalance);

        // Act
        int withdrawAmount = 500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(initialBalance - withdrawAmount);
    }

    @Test
    void givenCreditNotAllowed_whenWithdraw_thenFail() {
        // Arrange
        int initialBalance = 100;
        customer.setBalance(initialBalance);
        customer.setCreditAllowed(false);

        // Act
        int withdrawAmount = 500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("insufficient account balance");
        assertThat(customer.getBalance()).isEqualTo(initialBalance);
    }

    @Test
    void givenCreditAllowedAndWithinLimit_whenWithdraw_thenSuccess() {
        // Arrange
        int initialBalance = 100;
        customer.setBalance(initialBalance);
        customer.setCreditAllowed(true);

        // Act
        int withdrawAmount = 500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(initialBalance - withdrawAmount);
    }

    @Test
    void givenCreditAllowedAndOverLimit_whenWithdraw_thenFail() {
        // Arrange
        int initialBalance = 100;
        customer.setBalance(initialBalance);
        customer.setCreditAllowed(true);

        // Act
        int withdrawAmount = 1500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("maximum credit exceeded");
        assertThat(customer.getBalance()).isEqualTo(initialBalance);
    }

    @Test
    void givenVipWithInsufficientBalanceAndCreditNotAllowed_whenWithdraw_thenFail() {
        // Arrange
        int initialBalance = 100;
        customer.setBalance(initialBalance);
        customer.setVip(true);
        customer.setCreditAllowed(false);

        // Act
        int withdrawAmount = 500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("insufficient account balance");
        assertThat(customer.getBalance()).isEqualTo(initialBalance);
    }

    @Test
    void givenVipAndCreditWithinLimit_whenWithdraw_thenSuccess() {
        // Arrange
        int initialBalance = 100;
        customer.setBalance(initialBalance);
        customer.setCreditAllowed(true);
        customer.setVip(true);

        // Act
        int withdrawAmount = 500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(initialBalance - withdrawAmount);
    }

    @Test
    void givenVipAndCreditOverLimit_whenWithdraw_thenSuccess() {
        // Arrange
        int initialBalance = 100;
        customer.setBalance(initialBalance);
        customer.setCreditAllowed(true);
        customer.setVip(true);

        // Act
        int withdrawAmount = 1500;
        String result = accountManager.withdraw(customer, withdrawAmount);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(initialBalance - withdrawAmount);
    }
}
