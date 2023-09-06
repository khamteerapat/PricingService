package com.axonstech.PricingService.api;

import com.axonstech.PricingService.payload.CalculatePayload;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestRestExamApi {
    @PostMapping("/calculate")
    public Double responseTestMessage(@RequestBody CalculatePayload calculatePayload){
        return calculatePayload.getA() + calculatePayload.getB();
    }
}
