package com.raihan.Companyms.Company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="REVIEWMS", url="${reviewms.url}")
public interface ReviewClient {
    @GetMapping("/reviews/average_rating")
    Double getAverageRating(@RequestParam("companyId") Long companyId);
}
