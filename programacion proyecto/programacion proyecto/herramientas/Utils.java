package herramientas;

public class Utils {
    
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
   
    public static String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    
    public static String decryptPassword(String encryptedPassword) {
        return new StringBuilder(encryptedPassword).reverse().toString();
    }
}

