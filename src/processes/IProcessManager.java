package processes;

public interface IProcessManager {
	public void addProcess(Process p);
	public void update(long nanoSeconds);
	public void clearProcesses(boolean executeExitMethod);
}
