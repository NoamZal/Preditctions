package ThreadManager;

import simulation.api.SimulationManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    ExecutorService threadExecutor;

    public ExecutorService getThreadExecutor() {
        return threadExecutor;
    }
    public ThreadManager(Integer threadCount) {
        this.threadExecutor = Executors.newFixedThreadPool(threadCount);
    }
    public void executeSimulation(SimulationManager simulation){
        threadExecutor.execute(simulation);
    }
    public void shutDownThreads()
    {
        threadExecutor.shutdownNow();
    }
    public void setThreadPool(int threadNum)
    {
        threadExecutor.shutdown();
        threadExecutor = Executors.newFixedThreadPool(threadNum);
    }
}
