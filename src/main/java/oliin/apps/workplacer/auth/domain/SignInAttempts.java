package oliin.apps.workplacer.auth.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.auth.domain.exception.MaxLoginAttemptsReachedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInAttempts {
    private static final String KEY_PATTERN = "user:%s:%s:signin-failure";
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("10")
    private int maxAttempts;

    @Value("300")
    private int ttl;

    public int incrementFailedAttempts(String email, String device) {
        String key = getKey(email, device);
        int attempts = getFailedAttempts(key);
        int newFailedAttempts = attempts + 1;
        if (newFailedAttempts > maxAttempts) {
            throw new MaxLoginAttemptsReachedException(getTimeToLive(key));
        }

        redisTemplate.opsForValue().set(key, newFailedAttempts);
        if (newFailedAttempts == maxAttempts) {
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
            throw new MaxLoginAttemptsReachedException(ttl);
        }
        return maxAttempts - newFailedAttempts;
    }

    private long getTimeToLive(String key) {
        Long currentTtl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return currentTtl != null ? currentTtl : 0;
    }

    private int getFailedAttempts(String key) {
        Integer attempts = (Integer) redisTemplate.opsForValue().get(key);
        return attempts != null ? attempts : 0;
    }

    private String getKey(String email, String device) {
        return String.format(KEY_PATTERN, email, device);
    }
}
