import com.gosecuri.security.SHA1Hashing;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class SHA1HashingTests {

    @Test
    public void hash_password_0000() throws NoSuchAlgorithmException {
        SHA1Hashing md5 = new SHA1Hashing();
        String password = "0000";
        String expected = "39dfa55283318d31afe5a3ff4a0e3253e2045e43";
        String actual = md5.hash(password);

        assertEquals(expected, actual);
    }

    @Test
    public void hash_password_azerty() throws NoSuchAlgorithmException {
        SHA1Hashing md5 = new SHA1Hashing();
        String password = "azerty";
        String expected = "9cf95dacd226dcf43da376cdb6cbba7035218921";
        String actual = md5.hash(password);

        assertEquals(expected, actual);
    }

    @Test
    public void hash_password_a5gzghe86hze5heH54ez() throws NoSuchAlgorithmException {
        SHA1Hashing md5 = new SHA1Hashing();
        String password = "a5gzghe86hze5heH54ez";
        String expected = "c6e509aded03ff0ff308731d2c8cfe290418f5dd";
        String actual = md5.hash(password);

        assertEquals(expected, actual);
    }

}
