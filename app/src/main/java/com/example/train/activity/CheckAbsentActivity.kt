package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.Bean.AbsentListBean
import com.example.train.R
import com.example.train.adapter.AbsentRecycleAdapter
import com.example.train.interfaces.OnInternetCallback
import com.example.train.retrofit.getAll
import kotlinx.android.synthetic.main.activity_check_absent.*
import retrofit2.Response

class CheckAbsentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_absent)

        initView()

    }

    private fun initView() {

        val absentList = ArrayList<AbsentListBean.Data>()
        val adapter = AbsentRecycleAdapter(absentList, this)

        rv_absent_list.layoutManager = LinearLayoutManager(this)
        rv_absent_list.adapter = adapter

        getAll(object :OnInternetCallback<AbsentListBean>{
            override fun onFailed() {

                Toast.makeText(this@CheckAbsentActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccessful(response: Response<AbsentListBean>) {

                if (response.body()!!.info=="success") {
                    absentList.clear()
                    absentList.addAll(response.body()!!.data)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@CheckAbsentActivity, "网络无数据", Toast.LENGTH_SHORT).show()
                }
            }

        })




    }
}
