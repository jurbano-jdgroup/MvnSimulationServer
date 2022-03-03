
package simulationserver.system.control.per2022I;

import org.springframework.stereotype.Component;
import simulationserver.system.control.SecondOrderSystem;

/**
 *
 * @author Julio
 */
@Component("p7_control_velocidad")
public class P7ControlVelocidad extends  SecondOrderSystem{
    
    public P7ControlVelocidad() {
        super();
        
        this.s_a = 1.0;
        this.s_b = 1.0;
        this.s_c = 0.2;
        this.s_d = 1.0;
        this.minDeadZoneValue = -1.0;
        this.maxDeadZoneValue = 1.0;
        this.minInputValue = -10.0;
        this.maxInputValue = 10.0;
        //this.delay_time = 3.0;
    }

    @Override
    public String getName() {
        return "Control de velocidad";
    }   
}