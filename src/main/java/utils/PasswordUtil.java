package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash a plain text password
    public static String hashPassword(String plainPassword) {
        // The gensalt() method generates a random salt with a default log_rounds of 10.
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Check a plain text password against a hashed password
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
