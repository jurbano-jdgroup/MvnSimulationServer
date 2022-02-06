
package simulationserver.services.simulation.initializer;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Julio
 */
@Component
public class InitializerService {
    private final Map<Class<?>, Initializer> map = new HashMap();
    
    public void put(Class<?> className, Initializer initializer) {
        this.map.put(className, initializer);
    }
    
    public Initializer get(Class<?> className) {
        return this.map.get(className);
    }
}