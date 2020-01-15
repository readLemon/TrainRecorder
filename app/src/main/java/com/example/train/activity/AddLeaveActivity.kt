package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train.model.TeamMemberBean
import com.example.train.R
import com.example.train.adapter.TeamMemberRecycleAdapter
import com.example.train.interfaces.OnInternetCallback
import com.example.train.interfaces.OnRecycleItemClickedListener
import com.example.train.retrofit.addAbsent
import com.example.train.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_add_leave.*
import retrofit2.Response

class AddLeaveActivity : AppCompatActivity() {


    private var memberList = ArrayList<TeamMemberBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_leave)

        initTeamMember()

        initView()

    }

    /**
     * 此方法用于初始化队内成员
     */
    private fun initTeamMember() {

        val names = arrayOf("勾建松","刘光炀","李昊川","李子龙","雒泰","潘立夫","傅译平","蒋正道","陈阳",
            "罗彦钦","乜云鹏","向元弟","王涛","蒋英杰","李俊","张纪强","卫卓凡","董瑞钊","周淋佳","王海龙","尹兴宸")
        var bean: TeamMemberBean

        for (name in names) {
            bean = TeamMemberBean(name)
            memberList.add(bean)
        }


    }

    private fun initView() {

        btn_add_absent.setOnClickListener {

            //按下发送以后，收起软键盘
            hideKeyboard(this)

            //输入的缺勤人员的名字
            val name = tv_add_absent_name.text.toString()
            //输入的缺勤的原因
            val reason = et_add_absent_reason.text.toString()
            //输入的缺勤的训练项目
            val project = et_add_absent_project.text.toString()

            //在人员名字不为空的情况下发送缺勤请求
            if (!(name == "")) {
                val map = HashMap<String,String>()
                map.put("name",name)
                map.put("reason",reason)
                map.put("project",project)

                addAbsent(map, object :OnInternetCallback<String>{
                    override fun onFailed() {
                        Toast.makeText(this@AddLeaveActivity, "增加${name}缺勤次数失败", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccessful(response: Response<String>) {
                        if (response.body()!!.contains("success")) {
                            Toast.makeText(this@AddLeaveActivity, "增加${name}缺勤次数成功", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@AddLeaveActivity, "增加${name}缺勤次数失败", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this@AddLeaveActivity, "请选择缺勤人员缺勤人员", Toast.LENGTH_SHORT).show()
            }
        }

        //以下为初始化弹窗显示姓名
        val baseDialog = AlertDialog.Builder(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_personal_absent_list, null)
        baseDialog.setView(dialogView)

        val personalRecycle = dialogView.findViewById<RecyclerView>(R.id.rv_dialog_personal_absent_list)
        personalRecycle.layoutManager = LinearLayoutManager(this)
        val dialog = baseDialog.create()

        //点击选择队员的名字
        tv_add_absent_name.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View) {

                val adapter = TeamMemberRecycleAdapter<TeamMemberBean>(memberList, this@AddLeaveActivity)
                adapter.setOnrecycleItemClikedListener(object :OnRecycleItemClickedListener<TeamMemberBean>{
                    override fun onItemCliked(bean: TeamMemberBean) {
                        tv_add_absent_name.text = bean.name
                        dialog.dismiss()
                    }
                })
                personalRecycle.adapter = adapter
                dialog.show()
            }
        })
    }
}
