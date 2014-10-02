public class MyErrorReporter implements org.mozilla.javascript.ErrorReporter {
    public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
        ProductionJS.logger.warn("WARNING line: "+line+" offset: "+lineOffset+" message: "+ message);
    }

    public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
        ProductionJS.logger.error("ERROR line: "+line+" offset: "+lineOffset+" message: "+ message);
    }

    public org.mozilla.javascript.EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
        error(message, sourceName, line, lineSource, lineOffset);
        return new org.mozilla.javascript.EvaluatorException(message);
    }
}
