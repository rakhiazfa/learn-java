package core;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ServiceContainer {
    private static final Map<String, Object> services = new HashMap<>();

    public static <T> void register(Class<T> service) throws Exception {
        Constructor<T> constructor = service.getDeclaredConstructor();
        T instance = constructor.newInstance();

        ServiceContainer.services.put(service.getSimpleName(), instance);
    }

    public static void set(String key, Object object) {
        ServiceContainer.services.put(key, object);
    }

    public static <T> T get(Class<T> service) {
        @SuppressWarnings("unchecked")
        T instance = (T) ServiceContainer.services.get(service.getSimpleName());

        return instance;
    }

    public static <T> T get(String key) {
        @SuppressWarnings("unchecked")
        T instance = (T) ServiceContainer.services.get(key);

        return instance;
    }
}
