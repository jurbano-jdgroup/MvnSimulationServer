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
public class FirstOrderSystem {
    private Double s_k;
    private Double s_a;
    private Double s_b;
    private Double s_c;
    private Double s_t;
    private Integer group;
    private Double stepSize;
}
