package oliin.apps.workplacer.rest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oliin.apps.workplacer.domain.model.DeviceInfoStorage;
import oliin.apps.workplacer.domain.model.DeviceInfo;
import oliin.apps.workplacer.rest.advice.wrapper.ErrorResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceInfoStorageFilter extends OncePerRequestFilter {

    private static final String DEVICE_ID_HEADER = "X-Device-ID";
    private static final String DEVICE_OS_HEADER = "X-Device-OS";
    private static final String SHOULD_FILTER_PREFIX = "/api/v1";
//    private static final String DEFAULT_LANGUAGE = "az";

    private final DeviceInfoStorage valueStorage;
    private final Validator validator;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            DeviceInfo deviceInfo = getValueToStore(request);
            doFilterAndStore(request, response, filterChain, deviceInfo);
        } catch (ConstraintViolationException ex) {
            log.error("An error occurred while retrieving device information - {}", ex.getMessage());
            writeBadRequest(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(SHOULD_FILTER_PREFIX);
    }

    private DeviceInfo getValueToStore(HttpServletRequest request) {
        String deviceId = request.getHeader(DEVICE_ID_HEADER);

        String deviceOs = Optional.ofNullable(request.getHeader(DEVICE_OS_HEADER))
                .map(String::toLowerCase)
                .orElse(null);

//        String language = Optional.ofNullable(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE))
//                .map(String::toLowerCase)
//                .orElse(DEFAULT_LANGUAGE);

        DeviceInfo deviceInfo = new DeviceInfo(deviceId, deviceOs);

        Set<ConstraintViolation<DeviceInfo>> violations = validator.validate(deviceInfo);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return deviceInfo;
    }

    @SneakyThrows
    private void doFilterAndStore(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,
                                  DeviceInfo valueToStore) {
        try {
            valueStorage.store(valueToStore);
            filterChain.doFilter(request, response);
        } finally {
            valueStorage.clear();
        }
    }

    @SneakyThrows
    private void writeBadRequest(HttpServletRequest request, HttpServletResponse response) {
        String operationInfo = String.join(" ", request.getMethod(), request.getRequestURI());
        log.error("Failed to retrieve device information for operation: {}", operationInfo);
        var error = new ErrorResponseWrapper("invalid-request", "Request input parameters are missing or invalid");

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(error));
        writer.flush();
    }
}
