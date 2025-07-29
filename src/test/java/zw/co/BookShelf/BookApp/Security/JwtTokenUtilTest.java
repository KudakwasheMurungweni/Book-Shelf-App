package zw.co.BookShelf.BookApp.Security;

import org.junit.jupiter.api.Test;
import zw.co.BookShelf.BookApp.security.JwtTokenUtil;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenUtilTest {

    @Test
    void testGenerateAndValidateToken() {
        JwtTokenUtil util = new JwtTokenUtil();
        String username = "testuser";
        String token = util.generateToken(username);

        assertThat(util.validateToken(token)).isTrue();
        assertThat(util.getUsernameFromToken(token)).isEqualTo(username);
    }

    @Test
    void testValidateToken_invalidToken() {
        JwtTokenUtil util = new JwtTokenUtil();
        String invalidToken = "invalid.token";
        assertThat(util.validateToken(invalidToken)).isFalse();
    }
}
