package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train.Bean.AbsentListBean
import com.example.train.R
import com.example.train.adapter.AbsentRecycleAdapter
import com.example.train.interfaces.OnInternetCallback
import com.example.train.interfaces.OnRecycleItemClickedListener
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

        rv_absent_list.layoutManager = LinearLayoutManager(this) as LinearLayoutManager
        rv_absent_list.adapter = adapter

        adapter.setOnrecycleItemClikedListener(object :OnRecycleItemClickedListener{
            override fun onItemCliked() {
                Log.d("********","adapter")

                //当recycleiew的Item被点击以后，弹出弹窗显示这个人的所有的缺勤消息记录
                setPersonalAbsentDialog()
            }

        })

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


    private fun setPersonalAbsentDialog() {
        val baseDialog = AlertDialog.Builder(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_personal_absent_list, null)
        baseDialog.setView(dialogView)
        baseDialog.apply {
            create()
            show()
        }

        val personalRecycle = dialogView.findViewById<RecyclerView>(R.id.rv_dialog_personal_absent_list)



    }
}
