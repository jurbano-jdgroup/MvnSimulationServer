
package simulationserver.services.simulation.initializer;

import java.util.Map;

/**
 *
 * @author Julio
 */
public class PidInitializer implements Initializer {

    @Override
    public Object init(Object host, Object paramsObject) {
        PidController sys = (PidController) host;
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
        
        // pid controller params
        double kp = 1.0;
        double ki = 0.0;
        double kd = 0.0;
        double N = 20.0;
        double Ts = sampleTime;
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
        // check if controller params setted
        if (params.containsKey("kp")) {
            kp = (Double) params.get("kp");
        } if (params.containsKey("ki")) {
            ki = (Double) params.get("ki");
        } if (params.containsKey("kd")) {
            kd = (Double) params.get("kd");
        } if (params.containsKey("N")) {
            N = (Double) params.get("N");
        } if (params.containsKey("b")) {
            b = (Double) params.get("b");
        } if (params.containsKey("c")) {
            c = (Double) params.get("c");
        } if (params.containsKey("bias")) {
            bias = (Double) params.get("bias");
        } if (params.containsKey("outputMax")) {
            outputMax = (Double) params.get("outputMax");
        } if (params.containsKey("outputMin")) {
            outputMin = (Double) params.get("outputMin");
        } if (params.containsKey("setpoint")) {
            setpoint = (Double) params.get("setpoint");
        } if (params.containsKey("setpointMax")) {
            setpointMax = (Double) params.get("setpointMax");
        } if (params.containsKey("setpointMin")) {
            setpointMin = (Double) params.get("setpointMin");
        } if (params.containsKey("setpointGui") ) {
            showSetpointGui = ((String) params.get("setpointGui")).compareToIgnoreCase("true")==0;
        } if (params.containsKey("direction")) {
            inverse = ((String) params.get("direction")).compareToIgnoreCase("inverse")==0;
        }
        
        sys.setB(b);
        sys.setBias(bias);
        sys.setC(c);
        sys.setInverse(inverse);
        sys.setKd(kd);
        sys.setKi(ki);
        sys.setKp(kp);
        sys.setN(N);
        sys.setOutputMax(outputMax);
        sys.setOutputMin(outputMin);
        sys.setSetpoint(setpoint);
        sys.setSetpointMax(setpointMax);
        sys.setSetpointMin(setpointMin);
        sys.setShowSetpointGui(showSetpointGui);
        sys.setTs(Ts);
        
        return sys;
    }
}