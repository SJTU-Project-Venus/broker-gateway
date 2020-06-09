package macoredroid.brokergateway.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    Exchange exchange(){
        return new DirectExchange("marketDepth");
    }

    @Bean
    Exchange exchange1(){
        return new DirectExchange("finishedOrder");
    }
}
