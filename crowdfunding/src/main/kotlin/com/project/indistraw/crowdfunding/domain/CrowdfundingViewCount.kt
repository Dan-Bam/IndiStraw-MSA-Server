package com.project.indistraw.crowdfunding.domain

data class CrowdfundingViewCount(
    val crowdfundingIdx: Long,
    val ips: Set<String>
) {

    fun addIp(ip: String): CrowdfundingViewCount =
        this.copy(ips = this.ips + ip)

}