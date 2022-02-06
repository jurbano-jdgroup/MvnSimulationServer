
package simulationserver.services.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import simulationserver.RuntimeConfiguration;
import simulationserver.services.simulation.initializer.SecondOrderSystemInitializer;
import simulationserver.services.simulation.initializer.InitializerService;
import simulationserver.services.simulation.initializer.PidController;
import simulationserver.services.simulation.initializer.PidInitializer;
import simulationserver.services.simulation.initializer.ServerInitializer;
import simulationserver.system.RTAbstractModel;

/**
 *
 * @author Ernesto
 */
@Component
public class MainWorker extends ThreadWorkerAbstract {
    @Autowired ApplicationContext context;
    @Autowired InitializerService initializerService;
    
    private final Server server = new Server();
    private final simulationserver.services.simulation.initializer.SecondOrderSystem secOrderInit = new 
            simulationserver.services.simulation.initializer.SecondOrderSystem();
    private final PidController pidControler = new PidController();
    
    public void addStarters() {
        this.initializerService.put(Server.class, new ServerInitializer());
        this.initializerService.put(simulationserver.services.simulation.initializer.SecondOrderSystem.class, new SecondOrderSystemInitializer());
        this.initializerService.put(PidController.class, new PidInitializer());
    }
    
    public void init(RuntimeConfiguration config) {
        if (config.paramsCount() > 0) {
            final java.util.Map<String, Object> params = config.getParamsMap();
            
            // init server
            this.initializerService.get(Server.class).init(this.server, params);
            this.initializerService.get(simulationserver.services.simulation.initializer.SecondOrderSystem.class).init(secOrderInit, params);
            this.initializerService.get(PidController.class).init(this.pidControler, params);
            
            final double sampleTime = ((double) this.server.getInputSampling()) * 1e-3;

            // checking system, must exist
            if (params.containsKey("system")) {
                final String systemValue = (String) params.get("system");
                RTAbstractModel system = (RTAbstractModel) this.context.getBean(systemValue);
                
                if (systemValue.compareToIgnoreCase("secondorder") == 0) 
                {
                    this.initSecondOrderSystem((simulationserver.system.control.SecondOrderSystem) system);
                }
                else if (systemValue.compareToIgnoreCase("pid") == 0) 
                {
                    this.initController((simulationserver.system.control.Controlador) system);
                } else {
                    system.setStepSize(sampleTime);
                    this.server.setModel(system);
                }
            }
        }
    }
    
    private void initSecondOrderSystem(simulationserver.system.control.SecondOrderSystem system) {
        system.s_k = this.secOrderInit.getS_k();
        system.s_a = this.secOrderInit.getS_a();
        system.s_b = this.secOrderInit.getS_b();
        system.s_c = this.secOrderInit.getS_c();
        system.s_d = this.secOrderInit.getS_d();
        system.s_t = this.secOrderInit.getS_t();
        system.setDelayTime(this.secOrderInit.getS_t());
        system.setStepSize(this.secOrderInit.getStepSize());

        this.server.setModel(system);
    }
    
    private void initController(simulationserver.system.control.Controlador pid) {
        pid.Kp = this.pidControler.getKp();
        pid.Ti = this.pidControler.getKi();
        pid.Td = this.pidControler.getKd();
        pid.N = this.pidControler.getN();
        pid.Ts = this.pidControler.getTs();
        pid.b = this.pidControler.getB();
        pid.c = this.pidControler.getC();
        pid.bias_inicial = this.pidControler.getBias();
        pid.sat_min = this.pidControler.getOutputMin();
        pid.sat_max = this.pidControler.getOutputMax();
        pid.referencia = this.pidControler.getSetpoint();

        if (this.pidControler.isInverse()) {
            pid.setInverse();
        }

        pid.setStepSize(this.secOrderInit.getStepSize());

        this.server.setModel(pid);

        // show if setpoint gui must be shown
        if (this.pidControler.isShowSetpointGui()) {
            final simulationserver.system.gui.SetpointHandler setHandler = new simulationserver.system.gui.SetpointHandler();
            setHandler.setModel(pid);
            setHandler.setMaxValue(this.pidControler.getSetpointMax());
            setHandler.setMinValue(this.pidControler.getSetpointMin());
            setHandler.setValue(this.pidControler.getSetpoint());
            setHandler.start();
        }
    }
    
    /**
     * Setup the execution
     */
    @Override
    public void setupExecution() {
        System.out.println("Starting");
        System.out.println("Creating the server");
        System.out.println("Setting the server");
        
        try{
            this.server.setServer();
        }catch(Exception ex) {
            System.out.println("Exception in main: "+ex.getMessage());
        }
        
        System.out.println("Looping");
    }

    /**
     * Do the execution
     */
    @Override
    public void doExecution() {
        try{
            server.listen();
        }catch(Exception ex) {
            System.out.println("Exception in main: "+ex.getMessage());
        }
    }
    
    /**
     * Stop the loop
     */
    @Override
    public void stopLoop() {
        System.out.println("Closing the server");
        
        super.stopLoop();
        
        // close the server
        this.server.closeServer();
        System.out.println("Server closed");
    }
}