package com.aditya.openinapp.Model

data class Data(
    val overall_url_chart: Map<String, Int>,
    val recent_links: MutableList<RecentLink>,
    val top_links: MutableList<TopLink>
)