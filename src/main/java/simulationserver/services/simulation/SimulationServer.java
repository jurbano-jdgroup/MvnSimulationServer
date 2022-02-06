
package simulationserver.services.simulation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simulationserver.Service;
import simulationserver.RuntimeConfiguration;

/**
 *
 * @author Ernesto
 */
@Component
public class SimulationServer implements Service {
    @Autowired
    private MainWorker worker;
    
    @Setter @Getter private RuntimeConfiguration config;
        
    @Override
    public void runService() throws Exception {
        worker.addStarters();
        worker.init(config);
        worker.setExecutionStep(1000);
        worker.start();
    }
}