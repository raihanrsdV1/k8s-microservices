package com.raihan.Jobs.Job.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.raihan.Jobs.Job.external.Company;

@FeignClient(name = "COMPANYMS", url="${companyms.url}")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable Long id);
}
