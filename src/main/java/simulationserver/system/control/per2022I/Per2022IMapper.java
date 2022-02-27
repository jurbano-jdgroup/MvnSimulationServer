
package simulationserver.system.control.per2022I;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simulationserver.system.RTAbstractModel;
import simulationserver.system.maths.BaseMatrix;

/**
 *
 * @author Julio
 */
@Configuration
public class Per2022IMapper{
    @Bean("p4_control_posicion")
    public RTAbstractModel loadP4() {
        BaseMatrix num = new BaseMatrix(4,1);
        BaseMatrix den = new BaseMatrix(4,1);
        
        num.fill(0.0);
        num.set(3, 0, 20.0);
        
        den.set(0, 0, 1.0);
        den.set(1, 0, 10.1);
        den.set(2, 0, 26.0);
        den.set(3, 0, 250.0);
        
        simulationserver.system.System system = null;
        
        try {
            system = new simulationserver.system.System(num, den);
        }catch (Exception ex) {
            System.out.println("Error creating the system P4: " + ex.getMessage());
        }
        
        return system;
    }
    
    @Bean("p5_control_posicion")
    public RTAbstractModel loadP5() {
        BaseMatrix num = new BaseMatrix(4,1);
        BaseMatrix den = new BaseMatrix(4,1);

        num.fill(0.0);
        num.set(3, 0, 6.0);
        num.set(2, 0, 3.04);
        num.set(1, 0, 1.0);

        den.set(0, 0, 1.0);
        den.set(1, 0, 1.04);
        den.set(2, 0, 3.04);
        den.set(3, 0, 3.0);

        simulationserver.system.System system = null;

        try {
            system = new simulationserver.system.System(num, den);
            system.setMaxDeadZoneValue(1.0);
            system.setMinDeadZoneValue(-1.0);
            system.setMaxInputValue(10.0);
            system.setMinInputValue(-10.0);
        }catch (Exception ex) {
            System.out.println("Error creating the system P5: " + ex.getMessage());
        }
        
        return system;
    }
}