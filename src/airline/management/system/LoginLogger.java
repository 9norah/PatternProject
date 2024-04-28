package airline.management.system;

import java.util.logging.*;

public class LoginLogger implements LoginBehavior {
    private LoginBehavior loginBehavior;
    private Logger logger;

    public LoginLogger(LoginBehavior loginBehavior) {
        this.loginBehavior = loginBehavior;
        logger = Logger.getLogger(LoginLogger.class.getName());
    }

    @Override
    public void login(String username, String password) {
        // Log the login attempt
        logger.info("Login attempt with username: " + username);
        
        // Perform the login using the wrapped instance
        loginBehavior.login(username, password);
        
        // Check if login was successful
        if (loginBehavior instanceof Login) {
            boolean isSuccessful = ((Login) loginBehavior).isLoginSuccessful();
            if (isSuccessful) {
                logger.info("Login successful for username: " + username);
            } else {
                logger.info("Login failed for username: " + username);
            }
        }
    }
}
