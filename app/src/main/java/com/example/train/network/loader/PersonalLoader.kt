package com.example.train.network.loader

import com.example.train.bean.BaseResponse
import com.example.train.bean.PersonalAbsentBean
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.PersonalService
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-1-17
 */
class PersonalLoader() : BaseLoader() {
    override val clazz: Class<PersonalService>
        get() = PersonalService::class.java

    val service: PersonalService

    init {
        service = RetrofitServiceManager.getInstance().creat(clazz)

    }

    /**
     * 得到个人的所有缺勤的数据
     * @param null
     * @return
     */
    fun getAbsentList(name: String): Observable<PersonalAbsentBean> {
        return observe(service.getCount(name))
    }

    /** 
     * 增加个人的缺勤
     * @param null
     * @return 
     */
    fun requestAddAbsent(map: Map<String, String>): Observable<BaseResponse> {
        return observe(service.requestAddAbsent(map))
    }

}