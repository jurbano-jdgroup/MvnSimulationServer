
package simulationserver.services.simulation.initializer;

import java.util.Map;
import simulationserver.system.RTAbstractModel;

/**
 *
 * @author Julio
 */
public class SecondOrderSystemInitializer implements Initializer {

    @Override
    public Object init(Object host, Object paramsObject) {
        SecondOrderSystem sys = (SecondOrderSystem) host;
        Map<String, Object> params = (Map<String, Object>) paramsObject;
        
        int inputSampling = 1;
        // checking input and output sampling
        if (params.containsKey("inputsampling")) {
            inputSampling = (Integer) params.get("inputsampling");
            // check inputSampling
            if (inputSampling <= 0) {
                inputSampling = 1;
            }
        }
        
        final double sampleTime = ((double) inputSampling) * 1e-3;
        
        // check transfer function params
        double s_k = 1.0;
        double s_a = 0.0;
        double s_b = 1.0;
        double s_c = 1.0;
        double s_d = 1.0;
        double s_t = 0.0;
        // do checking
        if (params.containsKey("k")) {
            s_k = (Double) params.get("k");
        }
        if (params.containsKey("a")) {
            s_a = (Double) params.get("a");
        }
        if (params.containsKey("b")) {
            s_b = (Double) params.get("b");
            // check s_b
            if (s_b == 0.0) {
                s_b = 1.0;
            }
        }
        if (params.containsKey("c")) {
            s_c = (Double) params.get("c");
        }
        if (params.containsKey("d")) {
            s_d = (Double) params.get("d");
        }
        if (params.containsKey("t")) {
            s_t = (Double) params.get("t");
            // check s_t
            if (s_t < 0.0) {
                s_t = 0.0;
            }
            // set s_t to a multiple of the sample time
            int multiple = (int) Math.ceil(s_t / sampleTime);
            s_t = ((double) multiple) * sampleTime;
            // set the input buffer size of the model
            if (RTAbstractModel.INITIAL_INPUT_BUFFER_SIZE < multiple) {
                // TODO: assign double ???
                RTAbstractModel.INITIAL_INPUT_BUFFER_SIZE = 2 * multiple;
            }
        }
        
        int group = 1;
        if (params.containsKey("group")) {
            group = (Integer) params.get("group");
        }
        if (group < 0) {
            group = 1;
        }
        if (group > 20) {
            group = 1;
        }
        
        sys.setS_a(s_a);
        sys.setS_b(s_b);
        sys.setS_c(s_c);
        sys.setS_d(s_d);
        sys.setS_k(s_k);
        sys.setS_t(s_t);
        sys.setGroup(group);
        sys.setStepSize(sampleTime);
        
        return sys;
    }
}