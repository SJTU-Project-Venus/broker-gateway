package macoredroid.brokergateway;

public interface EntityConvert<S, D> {
    D convertFrom(S s);
}
