package com.example.train.retrofit

import android.util.Log
import com.example.train.model.TeamAbsentModel
import com.example.train.model.PersonalAbsentModel
import com.example.train.model.TeamCountModel
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
public fun addAUser(name: String, callback: OnInternetCallback<String>) {
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
 * 得到个人缺勤的具体信息
 * POST
 */
public fun getCount(name: String, callback: OnInternetCallback<PersonalAbsentModel>) {
    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getCountService = retrofit.create(GetCountService::class.java)
    val request = getCountService.getCount(name)

    request.enqueue(object : Callback<PersonalAbsentModel> {
        override fun onFailure(call: Call<PersonalAbsentModel>, t: Throwable) {

            callback.onFailed()
        }

        override fun onResponse(call: Call<PersonalAbsentModel>, response: Response<PersonalAbsentModel>) {
            Log.d("getCount请求成功", "***" + response.body()!!.name)
            callback.onSuccessful(response)
        }

    })
}



/**
 * 得到所有的缺勤人员信息
 * Get
 */
 fun getAll(callback: OnInternetCallback<TeamAbsentModel>) {

    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getAllService = retrofit.create(GetAllService::class.java)
    val request = getAllService.getAll()

    request.enqueue(object :Callback<TeamAbsentModel>{
        override fun onFailure(call: Call<TeamAbsentModel>, t: Throwable) {
            callback.onFailed()
        }

        override fun onResponse(call: Call<TeamAbsentModel>, response: Response<TeamAbsentModel>) {
            val bean = response.body() as TeamAbsentModel
            Log.d("getAll请求成功","***"+bean.info)
            callback.onSuccessful(response)
        }
    })
}

/**
 * 增加个人的训练次数
 * map里面应该包含三个参数及值，如果后两个的参数值为空则表示缺席（即人未到，也未请假）
 *
 * name 请假或者缺席的人员的名字
 * project 请假的项目
 * reason 请假的原因
 *
 * POST
 */
public fun addAbsent(postMap: Map<String, String>, callback: OnInternetCallback<String>) {

    val retrofit = baseRetrofit
        .addConverterFactory(StringConverterFactory())
        .build()

    val addUserService = retrofit.create<AddAbsentService>(AddAbsentService::class.java)
    val request = addUserService.addAbsent(postMap)

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
    public fun addTeamCount(teamName: String, callback: OnInternetCallback<String>) {

        val retrofit = baseRetrofit
            .addConverterFactory(StringConverterFactory())
            .build()

        val addUserService = retrofit.create<AddTeamCountService>(AddTeamCountService::class.java)
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
public fun getTeamCount(callback: OnInternetCallback<TeamCountModel>) {

    val retrofit = baseRetrofit
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getTeamCountService = retrofit.create<GetTeamCountService>(GetTeamCountService::class.java)
    val request = getTeamCountService.getTeamCount()

    request.enqueue(object : Callback<TeamCountModel> {
        override fun onFailure(call: Call<TeamCountModel>, t: Throwable) {
            Log.e("getTeamTraincount请求失败", "***" + t)
            callback.onFailed()
        }

        override fun onResponse(call: Call<TeamCountModel>, response: Response<TeamCountModel>) {
            Log.d("getTeamTraincount请求成功", "***" + response.body())
            callback.onSuccessful(response)
        }

    })
}






