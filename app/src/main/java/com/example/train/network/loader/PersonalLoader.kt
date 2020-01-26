package com.example.train.network.loader

import com.example.train.model.BaseResponse
import com.example.train.model.PersonalAbsentModel
import com.example.train.network.interfaces.PersonalService
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-1-17
 */
class PersonalLoader() : BaseLoader<PersonalService>() {
    override val clazz: Class<PersonalService>
        get() = PersonalService::class.java


    /**
     * 得到个人的所有缺勤的数据
     * @param null
     * @return
     */
    fun getAbsentList(name: String): Observable<PersonalAbsentModel> {
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