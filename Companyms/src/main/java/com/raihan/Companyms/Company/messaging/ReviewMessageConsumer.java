package com.raihan.Companyms.Company.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.raihan.Companyms.Company.CompanyService;
import com.raihan.Companyms.Company.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }


    @RabbitListener(queues = "companyRating")
    public void consumedMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
