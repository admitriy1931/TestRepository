package commands;

public class ParserOutput {
    public String stringOutput;
    public String recommendation;

    public ParserOutput(String _stringOutput, String _recommendation) {
        this.stringOutput = _stringOutput;
        this.recommendation = _recommendation;
    }

    public ParserOutput(String _stringOutput) {
        this.stringOutput = _stringOutput;
    }
}
