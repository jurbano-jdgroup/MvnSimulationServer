
package simulationserver.services.simulation.initializer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Julio
 */
@Getter
@Setter
@NoArgsConstructor
public class PidController {
    double kp = 1.0;
    double ki = 0.0;
    double kd = 0.0;
    double N = 20.0;
    double Ts = 0.01;
    double b = 1.0;
    double c = 1.0;
    double bias = 0.0;
    boolean inverse = false;
    double outputMax = 100.0;
    double outputMin = -100.0;
    double setpoint = 50.0;
    double setpointMax = 100.0;
    double setpointMin = 0.0;
    boolean showSetpointGui = false;
}
