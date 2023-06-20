package simulationserver.system.control.sac1;

import org.springframework.stereotype.Component;
import simulationserver.system.System;
import simulationserver.system.maths.Matrix;

@Component("secondorder2")
public class SecondOrder2 extends System {
    private double u_1=0, u_2=0, y_1=0, y_2=0;
    public SecondOrder2(Matrix<Number> num, Matrix<Number> den) throws Exception {
        super(num, den);
    }
    @Override
    public void makeStep() {
        // update the time
        ++t_iter;
        this.setCurrentTime(((double)t_iter)*step_size);

        // calculate the value of the states
        final double inputValue = this.getCurrentInputValue();
        this.u_2 = this.u_1;
        this.u_1 = inputValue;

        final double currentValue = (5.768e-07 * this.u_1) + (5.766e-07 * this.u_2) +
                (1.999 * this.y_1) - (0.9992 * this.y_2);

        this.y_2 = this.y_1;
        this.y_1 = currentValue;

        // update the output
        this.setCurrentOutputValue(currentValue);
    }

    @Override
    public String getName() {
        return "Second order system with integrator";
    }
}