package commands;

public class ParserOutput {
    public String stringOutput;
    public String recommendation;
    public JsonParserResult parserResult;

    public ParserOutput(String _stringOutput, String _recommendation, JsonParserResult _parserResult) {
        this.stringOutput = _stringOutput;
        this.recommendation = _recommendation;
        this.parserResult = _parserResult;
    }

    public ParserOutput(String _stringOutput) {
        this.stringOutput = _stringOutput;
    }
}
