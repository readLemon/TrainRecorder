package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train.Bean.PersonalAbsentBean
import com.example.train.Bean.TeamAbsentBean
import com.example.train.Bean.TeamCountBean
import com.example.train.R
import com.example.train.adapter.LeaveRecycleAdapter
import com.example.train.adapter.PersonalRecycleAdapter
import com.example.train.interfaces.OnInternetCallback
import com.example.train.interfaces.OnRecycleItemClickedListener
import com.example.train.retrofit.getAll
import com.example.train.retrofit.getCount
import com.example.train.retrofit.getTeamCount
import kotlinx.android.synthetic.main.activity_check_absent.*
import retrofit2.Response

class CheckAbsentActivity : AppCompatActivity() {

    private lateinit var currentBean: TeamAbsentBean.Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_absent)

        initView()

    }

    private fun initView() {

        tb_sign_in.setNavigationOnClickListener { finish() }

        val absentList = ArrayList<TeamAbsentBean.Data>()
        absentList.sortBy { it.absent }

        val adapter = LeaveRecycleAdapter<TeamAbsentBean.Data>(absentList, this)

        rv_absent_list.layoutManager = LinearLayoutManager(this) as LinearLayoutManager
        rv_absent_list.adapter = adapter

        adapter.setOnrecycleItemClikedListener(object :OnRecycleItemClickedListener<TeamAbsentBean.Data>{
            override fun onItemCliked(bean: TeamAbsentBean.Data) {

                currentBean = bean
                setPersonalAbsentDialog()

            }
        })
        //得到初始化数据
        getAll(object :OnInternetCallback<TeamAbsentBean>{
            override fun onFailed() {
                Toast.makeText(this@CheckAbsentActivity, "网络请求失败", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccessful(response: Response<TeamAbsentBean>) {
                if (response.body()!!.info=="success") {
                    absentList.clear()
                    absentList.addAll(response.body()!!.data.filter { it.absent != 0 })
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@CheckAbsentActivity, "网络无数据", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //团队的训练次数
        getTeamCount(object :OnInternetCallback<TeamCountBean> {
            override fun onFailed() {

            }

            override fun onSuccessful(response: Response<TeamCountBean>) {
                tv_team_count.text = response.body()!!.data.get(0).check_count.toString()
            }

        })
    }

    private fun setPersonalAbsentDialog() {

        val baseDialog = AlertDialog.Builder(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_personal_absent_list, null)
        baseDialog.setView(dialogView)
        val personalRecycle = dialogView.findViewById<RecyclerView>(R.id.rv_dialog_personal_absent_list)
        personalRecycle.layoutManager = LinearLayoutManager(this)
        baseDialog.create()


        //用于展示个人的缺勤情况的弹窗
        getCount(currentBean.name, object :OnInternetCallback<PersonalAbsentBean>{

            override fun onSuccessful(response: Response<PersonalAbsentBean>) {

                val dataList = response.body()!!.leave as ArrayList<PersonalAbsentBean.Leave>

                val adapter = PersonalRecycleAdapter(dataList, this@CheckAbsentActivity)
                personalRecycle.adapter = adapter
                //在有数据的情况下，展示弹窗
                baseDialog.show()
            }

            override fun onFailed() {
                Toast.makeText(this@CheckAbsentActivity,"对不起，请求个人数据失败", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
