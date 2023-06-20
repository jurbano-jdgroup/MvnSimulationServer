package simulationserver.system.control.sac1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simulationserver.system.RTAbstractModel;
import simulationserver.system.control.thesis.PendulumSystem20;
import simulationserver.system.maths.BaseMatrix;

@Configuration
public class SacMapper {
    @Bean("secondorder2")
    public RTAbstractModel getSecondOder2System() {
        BaseMatrix num = new BaseMatrix(3,1);
        BaseMatrix den = new BaseMatrix(3,1);

        num.set(0, 0, 0.0);
        num.set(1, 0, 0.0);
        num.set(2, 0, 1.5);

        den.set(2, 0, 0.0);
        den.set(1, 0, 2.0);
        den.set(0, 0, 1.6);

        simulationserver.system.System system = null;

        try {
            system = new simulationserver.system.System(num, den);
        }catch (Exception ex) {
            System.out.println("Error creating the pendulum system: " + ex.getMessage());
        }

        return system;
    }
}
