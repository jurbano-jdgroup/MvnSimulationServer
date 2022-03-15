
package simulationserver.system.control.thesis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simulationserver.system.RTAbstractModel;
import simulationserver.system.maths.BaseMatrix;

/**
 *
 * @author Julio
 */
@Configuration
public class PendulumSystemBuilder {
    // Y(s)/U(s) = 11.9420 / (s^3 + 4.0599s^2 + 44.7989s + 161.0357)
    @Bean("thesis_pendulum_system_20")
    public RTAbstractModel get20DegPendulumSystem() {
        BaseMatrix num = new BaseMatrix(4,1);
        BaseMatrix den = new BaseMatrix(4,1);
        
        num.fill(0.0);
        num.set(3, 0, 11.9420);
        
        den.set(0, 0, 1.0);
        den.set(1, 0, 4.0599);
        den.set(2, 0, 44.7989);
        den.set(3, 0, 161.0357);
        
        simulationserver.system.System system = null;
        
        try {
            system = new PendulumSystem20(num, den);
        }catch (Exception ex) {
            System.out.println("Error creating the pendulum system: " + ex.getMessage());
        }
        
        return system;
    }
}