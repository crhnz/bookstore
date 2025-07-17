package com.alc.bookstore.backend.fake;

import com.alc.bookstore.backend.fake.service.RetryDemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake")
@AllArgsConstructor
public class FakeApiController {

    private final RetryDemoService retryDemoService;

    /* TODO por si quieres hacer el servicio de reintentos en Use cases en background
    @GetMapping("/unstable")
    public String sometimesFails() {
        if (Math.random() < 0.7) {
            throw new RuntimeException("Simulated failure");
        }
        return "OK";
    }*/

    @GetMapping("/retry")
    public String demoRetry() {
        return retryDemoService.callUnstableApi();
    }
}
