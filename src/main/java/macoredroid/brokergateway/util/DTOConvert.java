package macoredroid.brokergateway.util;

public interface DTOConvert<S, D> {
    D convertFrom(S s);
}
