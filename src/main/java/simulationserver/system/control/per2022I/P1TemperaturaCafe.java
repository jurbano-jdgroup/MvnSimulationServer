
package simulationserver.system.control.per2022I;

import org.springframework.stereotype.Component;
import simulationserver.system.control.SecondOrderSystem;

/**
 *
 * @author Julio
 */
@Component("p1_temperatura_cafe")
public class P1TemperaturaCafe extends SecondOrderSystem {
    // y(s)/u(s) = k*(a*s + 1)/(b*s^2 + c*s + d)
    public P1TemperaturaCafe() {
        super();
        this.s_k = 1.5;
        this.s_b = 240.0;
        this.s_c = 38;
        this.s_d = 1.0;
        this.minDeadZoneValue = -1.0;
        this.maxDeadZoneValue = 1.0;
        this.minInputValue = -10.0;
        this.maxInputValue = 10.0;
        this.delay_time = 20.0;
    }
    
    @Override
    public String getName() {
        return "Temperatura de café";
    }
}