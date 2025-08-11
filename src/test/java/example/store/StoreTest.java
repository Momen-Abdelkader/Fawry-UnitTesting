package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


public class StoreTest {

    private AccountManager accountManager;
    private Store storeUnderTest;
    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        accountManager = mock(AccountManager.class);
        storeUnderTest = new StoreImpl(accountManager);
        customer = new Customer();
        product = new Product();
    }

    @Test
    void givenSufficientProductQuantity_whenBuyProduct_thenSucceed() {
        // Arrange
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        int initialQuantity = 4;
        product.setQuantity(initialQuantity);

        // Act
        storeUnderTest.buy(product, customer);

        // Assert
        verify(accountManager).withdraw(customer, product.getPrice());
        assertThat(product.getQuantity()).isEqualTo(initialQuantity - 1);
    }

    @Test
    void givenInsufficientProductQuantity_whenBuyProduct_thenThrowException() {
        // Arrange
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        int initialQuantity = 0;
        product.setQuantity(initialQuantity);

        // Act
        Throwable thrown = catchThrowable(() -> storeUnderTest.buy(product, customer));

        // Assert
        assertThat(thrown).isInstanceOf(RuntimeException.class).hasMessage("Product out of stock");
        verify(accountManager, never()).withdraw(any(), anyInt());
        assertThat(product.getQuantity()).isEqualTo(initialQuantity);
    }

    @Test
    void givenFailedWithdraw_whenBuyProduct_thenThrowException() {
        // Arrange
        when(accountManager.withdraw(any(), anyInt())).thenReturn("fail");
        int initialQuantity = 5;
        product.setQuantity(initialQuantity);

        // Act
        Throwable thrown = catchThrowable(() -> storeUnderTest.buy(product, customer));

        // Assert
        verify(accountManager).withdraw(customer, product.getPrice());
        assertThat(thrown).isInstanceOf(RuntimeException.class).hasMessage("Payment failure: fail");
        assertThat(product.getQuantity()).isEqualTo(initialQuantity);
    }
}
