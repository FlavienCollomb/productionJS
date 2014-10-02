public class StackTrace {
    private StackTraceElement[] stackTraceElements;

    public StackTrace(StackTraceElement[] stackTraceElements){
        this.stackTraceElements = stackTraceElements;
    }
    public String toString(){
        String trace = "";
        if (this.stackTraceElements.length > 0) {
            for (StackTraceElement element: this.stackTraceElements)
                trace.concat("[StackTraceElement] [CLASS= " +element.getClassName()+ "] [METHOD= " +element.getMethodName()+ "] [LINE= " +element.getLineNumber()+ "]\n");
        }
        return trace;
    }
}
