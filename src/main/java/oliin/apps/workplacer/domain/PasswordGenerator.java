package oliin.apps.workplacer.domain;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

    public String generatePassword() {
        return RandomStringUtils.random(PASSWORD_LENGTH, CHARACTERS);
    }
}
