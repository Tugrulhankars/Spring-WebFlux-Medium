package org.example.reactiveuserapp.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig extends AbstractR2dbcConfiguration {


    @Override
    public ConnectionFactory connectionFactory() {

        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(ConnectionFactoryOptions.DRIVER,"postgresql")
                        .option(ConnectionFactoryOptions.HOST,"localhost")
                        .option(ConnectionFactoryOptions.PORT,5432)
                        .option(ConnectionFactoryOptions.USER,"metropol")
                        .option(ConnectionFactoryOptions.PASSWORD,"20002002")
                        .option(ConnectionFactoryOptions.DATABASE,"ReactiveUserApp")
                        .build()
        );
    }
}
