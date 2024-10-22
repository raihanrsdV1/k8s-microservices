package com.raihan.Jobs.Job.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.raihan.Jobs.Job.external.Review;

@FeignClient(name = "REVIEWMS", url="${reviewms.url}")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long id);
}
