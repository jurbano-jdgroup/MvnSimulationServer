
package simulationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simulationserver.services.simulation.SimulationServer;

/**
 *
 * @author Julio
 */
@Component
public class Application {
    @Autowired
    private SimulationServer simulationServer;
    
    public void run(String[] args) {
        Request request = new Request();
        
        try {
            request.buildRequest(args);
            this.simulationServer.setConfig(request.getConfig());
            this.simulationServer.runService();
        } catch (Exception ex) {
            System.out.println("Error parsing commands: " + ex.getMessage());
            System.exit(0);
        }
    }
}