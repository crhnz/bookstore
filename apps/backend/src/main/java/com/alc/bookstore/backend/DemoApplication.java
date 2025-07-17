package com.alc.bookstore.backend;

import com.alc.bookstore.shared.domain.annotation.CommandBusComponent;
import com.alc.bookstore.shared.domain.annotation.CommandHandlerComponent;
import com.alc.bookstore.shared.domain.annotation.EventBusComponent;
import com.alc.bookstore.shared.domain.annotation.QueryBusComponent;
import com.alc.bookstore.shared.domain.annotation.QueryHandlerComponent;
import com.alc.bookstore.shared.domain.annotation.RepositoryComponent;
import com.alc.bookstore.shared.domain.annotation.SubscriberComponent;
import com.alc.bookstore.shared.domain.annotation.UseCaseComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(
        includeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {
                            UseCaseComponent.class,
                            RepositoryComponent.class,
                            CommandBusComponent.class,
                            EventBusComponent.class,
                            QueryHandlerComponent.class,
                            CommandHandlerComponent.class,
                            QueryBusComponent.class,
                            SubscriberComponent.class,
                            Component.class,
                            Configuration.class,
                            Bean.class,
                        }),
        value = {
            "com.alc.bookstore.shared",
            "com.alc.bookstore.bms",
            "com.alc.bookstore.backend",
            "com.alc.bookstore.springshared",
        })
@SuppressWarnings("PMD.UseUtilityClass")
public final class DemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
