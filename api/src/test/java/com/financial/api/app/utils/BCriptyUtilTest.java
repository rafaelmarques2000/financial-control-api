package com.financial.api.app.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BCriptyUtilTest {

    @ParameterizedTest
    @ValueSource(strings = {"123456", "HsD#IUspoC$#C8", "X*RlJA4IEuvvcVJ9bvR3$rD0NL#xUkfwq"})
    public void testIfEncryptPassword(String password) {
        //WHEN
            String hashedPassword = BCriptyUtil.Encode(password);
        //THEN
            Assertions.assertTrue(checkLengthHashedPassword(hashedPassword));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "HsD#IUspoC$#C8", "X*RlJA4IEuvvcVJ9bvR3$rD0NL#xUkfwq"})
    public void testIfCheckSuccessPassword(String password) {
        //GIVEN
        String hashedPassword = BCriptyUtil.Encode(password);
        //THEN
        Assertions.assertTrue(BCriptyUtil.checkPassword(password, hashedPassword));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "HsD#IUspoC$#C8", "X*RlJA4IEuvvcVJ9bvR3$rD0NL#xUkfwq"})
    public void testIfCheckFailedPassword(String password) {
        //GIVEN
        String hashedPassword = BCriptyUtil.Encode(password);
        String wrongPassword = "568988";
        //THEN
        Assertions.assertFalse(BCriptyUtil.checkPassword(wrongPassword, hashedPassword));
    }

    private boolean checkLengthHashedPassword(String hashedPassword) {
        return hashedPassword.length() == 60;
    }

}
