package businessLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    public void isInt() {
        assertTrue(Validator.isInt("123"));
    }

    @Test
    public void isNotInt() {
        assertFalse(Validator.isInt("13da"));
    }

    @Test
    public void isEmailAddress() {
        assertTrue(Validator.isEmailAddress("mara.-tfa@gff.com"));
    }

    @Test
    public void emailAddressNoAt() {
        assertFalse(Validator.isEmailAddress("mara.com"));
    }

    @Test
    public void emailAddressNoPoint() {
        assertFalse(Validator.isEmailAddress("mara@com"));
    }

    @Test
    public void emailAddressToManyCharsAfterPoint() {
        assertFalse(Validator.isEmailAddress("mara@gmail.commm"));
    }

    @Test
    public void isPhoneNumber() {
        assertTrue(Validator.isPhoneNr("0128731234"));
    }

    @Test
    public void phoneNumberTooLong() {
        assertFalse(Validator.isPhoneNr("09355544422222"));
    }

    @Test
    public void phoneNumberTooShort() {
        assertFalse(Validator.isPhoneNr("0963655"));
    }

    @Test
    public void phoneNumberNotBeginningWith0() {
        assertFalse(Validator.isPhoneNr("1234567891"));
    }

    @Test
    public void phoneNumberSecondDigit0() {
        assertFalse(Validator.isPhoneNr("0012345679"));
    }

    @Test
    public void isValidDescription() {
        assertTrue(Validator.isValidDescription("aaa"));
    }

    @Test
    public void isNotValidDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i<= 1010; i++) {
            stringBuilder.append("a");
        }

        assertFalse(Validator.isValidDescription(stringBuilder.toString()));
    }
}