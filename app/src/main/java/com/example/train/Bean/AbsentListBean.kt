package com.example.train.Bean

/**
 * Created by chenyang
 * on 19-10-11
 */
data class AbsentListBean(
    val `data`: List<Data>,
    val info: String,
    val status: Int
) {

    data class Data(
        val absent: Int,
        val check_count: Int,
        val id: Int,
        val name: String
    )
}
