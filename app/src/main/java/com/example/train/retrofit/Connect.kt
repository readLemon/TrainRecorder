package com.example.train.retrofit

import android.util.Log
import com.example.train.Bean.AbsentListBean
import com.example.train.Bean.CountBean
import com.example.train.interfaces.AddUserService
import com.example.train.interfaces.GetAllService
import com.example.train.interfaces.GetCountService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chenyang
 * on 19-10-10
 */

private val baseRetrofit = Retrofit.Builder()
    .baseUrl("http://47.95.210.105:9999")

//加缺勤人员名字
public fun addAbsent(name: String) {
    val retrofit = baseRetrofit
        .addConverterFactory(StringConverterFactory())
        .build()

    val addService = retrofit.create(AddUserService::class.java)
    val request = addService.addUser(name)

    request.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {

            Log.d("addAbsent请求成功", "***" + response.body())

        }

        override fun onFailure(call: Call<String>, t: Throwable) {

            //回调在UI线程
        }
    })
}

//得到缺勤的具体信息
public fun getCount(name: String) {
    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getCountService = retrofit.create(GetCountService::class.java)
    val request = getCountService.getCount(name)

    request.enqueue(object : Callback<CountBean> {
        override fun onFailure(call: Call<CountBean>, t: Throwable) {

        }

        override fun onResponse(call: Call<CountBean>, response: Response<CountBean>) {
            Log.d("getCount请求成功", "***" + response.body()!!.name)
        }

    })
}


//得到所有的缺勤人员信息
public fun getAll(name: String) {

    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getAllService = retrofit.create(GetAllService::class.java)
    val request = getAllService.getAll(name)

    request.enqueue(object :Callback<AbsentListBean>{
        override fun onFailure(call: Call<AbsentListBean>, t: Throwable) {

        }

        override fun onResponse(call: Call<AbsentListBean>, response: Response<AbsentListBean>) {
            val bean = response
        }
    })

}







