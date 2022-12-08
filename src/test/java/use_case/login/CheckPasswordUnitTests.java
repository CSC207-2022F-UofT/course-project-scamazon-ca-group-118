package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckPasswordUnitTests {
    @Test
    void testPasswordsMatchYes() {
        CheckPassword passwords = new CheckPassword("1234", "1234");
        assert (passwords.passwordsMatch());
    }

    @Test
    void TestPasswordsMatchNo() {
        CheckPassword passwords = new CheckPassword("1235", "1234");
        assertFalse(passwords.passwordsMatch());
    }
}
