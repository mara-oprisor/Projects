package businessLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for validating input data.
 */
public class Validator {
    /**
     * Checks if a string represents a valid integer.
     *
     * @param nr the string to be checked
     * @return true if the string represents a valid integer, false otherwise
     */
    public static boolean isInt(String nr) {
        try {
            Integer.parseInt(nr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks if a string represents a valid email address.
     *
     * @param email the string to be checked
     * @return <p>true if the string represents a valid email address<br>false otherwise</p>
     */
    public static boolean isEmailAddress(String email) {
        final Pattern regex = Pattern.compile("[a-zA-Z0-9_.-]+@[a-zA-Z0-9]+.[a-z]{2,3}");
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks if a string represents a valid phone number.
     *
     * @param phoneNr the string to be checked
     * @return <p>true if the string represents a valid phone number<br>false otherwise</p>
     */
    public static boolean isPhoneNr(String phoneNr) {
        final Pattern regex = Pattern.compile("0[1-9][0-9]{8}");
        Matcher matcher = regex.matcher(phoneNr);
        return matcher.matches();
    }

    /**
     * Checks if a description string has less than 1000 characters.
     *
     * @param description the description string to be checked
     * @return <p>true if the description is valid<br>false otherwise</p>
     */
    public static boolean isValidDescription(String description) {
        return (description.length() < 1000);
    }
}
