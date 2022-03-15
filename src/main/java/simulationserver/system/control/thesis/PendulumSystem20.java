
package simulationserver.system.control.thesis;

import simulationserver.system.System;
import simulationserver.system.maths.Matrix;

/**
 *
 * @author Julio
 */
public class PendulumSystem20 extends System {
    
    public PendulumSystem20(Matrix<Number> num, Matrix<Number> den) throws Exception {
        super(num, den);
    }

    @Override
    public void makeStep() {
        // update the time
        ++t_iter;
        this.setCurrentTime(((double)t_iter)*step_size);
        
        // calculate the value of the states
        final double inputValue = this.getCurrentInputValue();
        final Matrix bMulu = this.B.multiply(inputValue);
        
        for(int state=0; state<this.states.rows(); ++state) {
            final double currentStateValue = (double) this.states.get(state, 0);
            
            final Matrix nextStateScalar = this.A.getRow(state).multiply(this.states);
            final double nextStateValue = currentStateValue + 
                    (((double) nextStateScalar.get(0, 0)) + ((double) bMulu.get(state, 0)))*this.step_size;
            
            this.states.set(state, 0, nextStateValue);
        }
        
        // calculate the output
        final Matrix dMulu = this.D.multiply(inputValue);
        final Matrix cMulx = this.C.multiply(this.states);
        double output = ((double) dMulu.get(0,0)) + ((double) cMulx.get(0,0));
        
        // update the output
        this.setCurrentOutputValue(output);
    }

    @Override
    public String getName() {
        return "Pendulum system at 20° angle";
    }
}