package macoredroid.brokergateway;

public interface DTOConvert<S, D> {
    D convertFrom(S s);
}
