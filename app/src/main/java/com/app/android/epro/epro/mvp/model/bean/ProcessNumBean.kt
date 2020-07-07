package com.app.android.epro.epro.mvp.model.bean

data class ProcessNumBean(
    val code: Int,
    val `data`: Data,
    val message: String,
    val status: String
) {
    data class Data(
        val askNum: Int,//询问信息数量
        val backNum: Int,//退回信息数量
        val statementsNum: Int,//待审信息数量
        val supervidsedNum: Int,//监督信息数量
        val waitNum: Int//待处理信息数量
    )
}