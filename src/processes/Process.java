package processes;

public abstract class Process {
	private ProcessState state;
	private Process childProcess;

	protected Process() {
		this.state = ProcessState.INIT;
		this.childProcess = null;
	}
	
	public abstract void update(long nanoSeconds);

	public void initialize() {
		if (state == ProcessState.INIT) {
			state = ProcessState.RUNNING;
			onInit();
		}
	}

	public void pause() {
		if (state == ProcessState.RUNNING) {
			state = ProcessState.PAUSED;
		}
	}

	public void unpause() {
		if (state == ProcessState.PAUSED) {
			state = ProcessState.RUNNING;
		}
	}

	public void finish() {
		if (state == ProcessState.RUNNING || state == ProcessState.FINISHED) {
			state = ProcessState.FINISHED;
			onFinish();
		}
	}

	public void abort() {
		if (state == ProcessState.RUNNING || state == ProcessState.FINISHED) {
			state = ProcessState.ABORTED;
			onAbort();
		}
	}

	public void fail() {
		if (state == ProcessState.RUNNING || state == ProcessState.FINISHED) {
			state = ProcessState.FAILED;
			onFail();
		}
	}

	public void appendChild(Process child) {
		if (this.childProcess != null) {
			this.childProcess.appendChild(child);
		} else {
			childProcess = child;
		}
	}

	public Process removeChild() {
		if (childProcess != null) {
			Process child = childProcess;
			childProcess = null;
			return child;
		} else {
			return null;
		}
	}

	public ProcessState getState() {
		return state;
	}

	boolean isRunning() {
		return state == ProcessState.RUNNING;
	}

	boolean isActive() {
		return state == ProcessState.RUNNING || state == ProcessState.PAUSED;
	}

	boolean isDead() {
		return state == ProcessState.FINISHED || state == ProcessState.ABORTED
				|| state == ProcessState.FAILED;
	}

	boolean isFinished() {
		return state == ProcessState.FINISHED;
	}

	boolean isInitialized() {
		return state != ProcessState.INIT;
	}

	protected abstract void onInit();

	protected abstract void onAbort();

	protected abstract void onFail();

	protected abstract void onFinish();

	public Process getChild() {
		return childProcess;
	}
}
