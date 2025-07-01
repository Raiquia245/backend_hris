package valdez.backend_hris.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    // Pastikan kunci ini cukup panjang dan kuat. Minimal 256 bit untuk HS256.
    // Direkomendasikan untuk menggunakan variabel lingkungan atau KeyStore
    // daripada hardcode di sini untuk produksi.
    private final String SECRET_KEY_STRING = "sayavaldezhuhuhahahihiSECRETVALDEZ123ValdezSuperSecretKeyMoreThan256Bits"; // Tambahkan panjang untuk kekuatan

    // Gunakan SecretKey langsung untuk menghindari konversi berulang
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    // Waktu kadaluarsa token: 24 jam dalam milidetik
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // Gunakan signWith(SecretKey, SignatureAlgorithm)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        // Gunakan parser() baru dengan verifyWith() dan build()
        return Jwts.parser()
                .verifyWith(SECRET_KEY) // Menggunakan SecretKey langsung
                .build() // Membangun parser
                .parseSignedClaims(token) // Parsing dan memverifikasi klaim
                .getPayload() // Mendapatkan payload
                .getSubject(); // Mendapatkan subjek (email)
    }

    public boolean isTokenValid(String token) {
        try {
            // Sama seperti extractEmail, gunakan verifyWith() dan build()
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            // Log exception untuk debugging, tapi jangan tampilkan ke pengguna akhir
            System.err.println("JWT Validation Error: " + e.getMessage());
            return false;
        }
    }
}