
package simulationserver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Ernesto
 */
public class SimulationServer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
        Application app = context.getBean(Application.class);
        app.run(args);
        
        context.close();
    }
}