
package simulationserver.services.simulation.initializer;

import java.util.Map;
import simulationserver.services.simulation.Server;

/**
 *
 * @author Julio
 */
public class ServerInitializer implements Initializer {

    @Override
    public Object init(Object host, Object paramsObject) {
        Server server = (Server) host;
        Map<String, Object> params = (Map<String, Object>) paramsObject;

        // check port
        if (params.containsKey("port")) {
            server.setPort((Integer) params.get("port"));
        }
        // check max connections
        if (params.containsKey("maxconnections")) {
            server.setBacklog((Integer) params.get("maxconnections"));
        }

        // input and output sampling
        int inputSampling = 1;
        int outputSampling = 10;
        // checking input and output sampling
        if (params.containsKey("inputsampling")) {
            inputSampling = (Integer) params.get("inputsampling");
            // check inputSampling
            if (inputSampling <= 0) {
                inputSampling = 1;
            }
        }
        if (params.containsKey("outputsampling")) {
            outputSampling = (Integer) params.get("outputsampling");
            // check output sampling
            if (outputSampling <= 0) {
                outputSampling = 10;
            }
        }
        // updating the input and ouput sampling on the server
        server.setInputSampling(inputSampling);
        server.setOutputSampling(outputSampling);
        
        return server;
    }
}