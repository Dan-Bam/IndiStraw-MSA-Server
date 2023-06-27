package com.project.indistraw.crowdfunding.adapter.output.feign.config

import com.project.indistraw.crowdfunding.adapter.output.feign.error.FeignClientErrorDecoder
import feign.codec.ErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.project.indistraw"])
@Configuration
class FeignConfig {

    @Bean
    @ConditionalOnMissingBean(value = [ErrorDecoder::class])
    fun commonFeignErrorDecoder(): FeignClientErrorDecoder {
        return FeignClientErrorDecoder()
    }


}