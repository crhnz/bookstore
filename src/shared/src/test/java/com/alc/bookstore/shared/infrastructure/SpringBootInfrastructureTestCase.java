package com.alc.bookstore.shared.infrastructure;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = DemoTestApplication.class)
public abstract class SpringBootInfrastructureTestCase extends IntegrationTestCase {}
