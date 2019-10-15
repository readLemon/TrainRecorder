package com.example.train.retrofit

import android.util.Log
import com.example.train.Bean.AbsentListBean
import com.example.train.Bean.CountBean
import com.example.train.interfaces.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chenyang
 * on 19-10-10
 */

private val baseRetrofit = Retrofit.Builder()
    .baseUrl("http://47.95.210.105:9999")

/**
 * 加缺勤人员名字
 * POST
 */
public fun addAbsent(name: String, callback: OnInternetCallback<String>) {
    val retrofit = baseRetrofit
        .addConverterFactory(StringConverterFactory())
        .build()

    val addService = retrofit.create(AddUserService::class.java)
    val request = addService.addUser(name)

    request.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {

            Log.d("addAbsent请求成功", "***" + response.body())
            callback.onSuccessful(response)

        }

        override fun onFailure(call: Call<String>, t: Throwable) {

            callback.onFailed()
            //回调在UI线程
        }
    })
}

/**
 * 得到缺勤的具体信息
 * POST
 */
public fun getCount(name: String, callback: OnInternetCallback<CountBean>) {
    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getCountService = retrofit.create(GetCountService::class.java)
    val request = getCountService.getCount(name)

    request.enqueue(object : Callback<CountBean> {
        override fun onFailure(call: Call<CountBean>, t: Throwable) {

            callback.onFailed()
        }

        override fun onResponse(call: Call<CountBean>, response: Response<CountBean>) {
            Log.d("getCount请求成功", "***" + response.body()!!.name)
            callback.onSuccessful(response)
        }

    })
}


///**
// * 得到所有的缺勤人员信息
// * Get
// */
//public fun getAll(callback: OnInternetCallback<AbsentListBean>) {
//
//    val retrofit = baseRetrofit
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val getAllService = retrofit.create(GetAllService::class.java)
//    val request = getAllService.getAll()
//
//    request.enqueue(object :Callback<AbsentListBean>{
//        override fun onFailure(call: Call<AbsentListBean>, t: Throwable) {
//            callback.onFailed()
//        }
//
//        override fun onResponse(call: Call<AbsentListBean>, response: Response<AbsentListBean>) {
//            Log.d("getAll请求成功","***"+response.body()!!.info)
//            val bean = response.body()
//            callback.onSuccessful(response)
//        }
//    })
//}

/**
 * 得到所有的缺勤人员信息
 * Get
 */
public fun getAll(callback: OnInternetCallback<AbsentListBean>) {

    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getAllService = retrofit.create(GetAllService::class.java)
    val request = getAllService.getAll()

    request.enqueue(object :Callback<AbsentListBean>{
        override fun onFailure(call: Call<AbsentListBean>, t: Throwable) {
            callback.onFailed()
        }

        override fun onResponse(call: Call<AbsentListBean>, response: Response<AbsentListBean>) {
            val bean = response.body()
            Log.d("getAll请求成功","***"+bean!!.info)
            callback.onSuccessful(response)
        }
    })
}

/**
 * 增加个人的训练次数
 * POST
 */
public fun addCount(name: String, callback: OnInternetCallback<String>) {

    val retrofit = baseRetrofit
        .addConverterFactory(StringConverterFactory())
        .build()

    val addUserService = retrofit.create<AddCountService>(AddCountService::class.java)
    val request = addUserService.addCount(name)

    request.enqueue(object :Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.e("addCount请求失败","***"+t)
            callback.onFailed()
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            Log.d("addCount请求成功", "***" + response.body())
            callback.onSuccessful(response)
        }

    })


}


    /**
     * 增加全队的训练次数
     * POST
     */
    public fun addTeamTraincount(teamName: String, callback: OnInternetCallback<String>) {

        val retrofit = baseRetrofit
            .addConverterFactory(StringConverterFactory())
            .build()

        val addUserService = retrofit.create<AddTeamTrainCountService>(AddTeamTrainCountService::class.java)
        val request = addUserService.addTrainCount(teamName)

        request.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("addTeamTraincount请求失败", "***" + t)
                callback.onFailed()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("addTeamTraincount请求成功", "***" + response.body())
                callback.onSuccessful(response)
            }

        })
    }

/**
 * 得到全队的训练次数
 * POST
 */
public fun getTeamTraincount(teamName: String, callback: OnInternetCallback<String>) {

    val retrofit = baseRetrofit
        .addConverterFactory(StringConverterFactory())
        .build()

    val addUserService = retrofit.create<GetTeamTrainCountService>(GetTeamTrainCountService::class.java)
    val request = addUserService.getTrainCount(teamName)

    request.enqueue(object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.e("getTeamTraincount请求失败", "***" + t)
            callback.onFailed()
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            Log.d("getTeamTraincount请求成功", "***" + response.body())
            callback.onSuccessful(response)
        }

    })
}






