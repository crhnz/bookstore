package com.alc.bookstore.shared.infrastructure;

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
        })
@SuppressWarnings("PMD.UseUtilityClass")
public class DemoTestApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoTestApplication.class, args);
    }
}
