package oliin.apps.workplacer.auth.domain.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Qualifier("DEVICE_INFO")
public class DeviceInfoStorage implements Supplier<DeviceInfo> {

    private final ThreadLocal<DeviceInfo> storedValue = new ThreadLocal<>();

    @Override
    public DeviceInfo get() {
        return storedValue.get();
    }

    public void store(DeviceInfo value) {
        storedValue.set(value);
    }

    public void clear() {
        storedValue.remove();
    }
}
