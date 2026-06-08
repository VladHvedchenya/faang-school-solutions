package LambdaExpressions.Task14;

import java.util.List;
import java.util.StringJoiner;

public class CVSJoiner {
    public <T> String toCSV(List<List<T>> matrix){
        VectorJoiner<T> vectorJoiner = (vector -> {
            if (vector.isEmpty())
                throw new IllegalArgumentException("Empty list!");

            StringJoiner joiner = new StringJoiner(",", "[", "]");

            for (T t : vector){
                joiner.add(t.toString());
            }

            return joiner.toString();
        });
        StringJoiner joiner = new StringJoiner(System.lineSeparator(), "{", "}");

        for (List<T> list : matrix){
            joiner.add(vectorJoiner.join(list));
        }

        return joiner.toString();
    }
}