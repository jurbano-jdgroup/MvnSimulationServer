
package simulationserver.system.control.thesis;

import org.springframework.stereotype.Component;
import simulationserver.system.control.SecondOrderSystem;

/**
 *
 * @author Julio
 */
@Component("thesis_thermic_system")
public class ThermicSystem extends SecondOrderSystem{
    // y(s)/u(s) = k*(a*s + 1)/(b*s^2 + c*s + d)
    // y(s)/u(s) = 0.47*exp(-53s)/(1940s^2 + 117s + 1)
    public ThermicSystem() {
        super();
        
        this.s_k = 0.47;
        this.s_b = 1940.0;
        this.s_c = 117.0;
        this.s_d = 1.0;
        this.s_t = 53.0;
        this.setDelayTime(53.0);
    }

    @Override
    public String getName() {
        return "Sistema térmico";
    }
}