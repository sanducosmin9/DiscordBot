package Trivia;

import java.util.ArrayList;
import java.util.List;

public class Result {
    int response_code = 0;
    List<TriviaQuestion> results;

    public Result() {
        this.results = new ArrayList<>();
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<TriviaQuestion> getResults() {
        return results;
    }

    public void setResults(List<TriviaQuestion> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Result{" +
                "response_code=" + response_code +
                ", results=" + results +
                '}';
    }
}
