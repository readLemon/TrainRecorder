package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.train.R
import com.example.train.interfaces.OnInternetCallback
import com.example.train.retrofit.addAbsent
import kotlinx.android.synthetic.main.activity_add_absent.*
import retrofit2.Response

class AddAbsentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_absent)


        initView()


    }

    private fun initView() {

        btn_add_absent.setOnClickListener {
            //输入的缺勤人员的名字
            val name = et_add_absent_name.text.toString()
            //输入的缺勤的原因
            val reason = et_add_absent_reason.text.toString()
            if (!name.equals("") && !name.equals(null)) {

                addAbsent(name, object :OnInternetCallback<String>{
                    override fun onFailed() {
                        Toast.makeText(this@AddAbsentActivity, "增加${name}缺勤次数失败", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccessful(response: Response<String>) {
                        if (response.body()!!.contains("success")) {
                            Toast.makeText(this@AddAbsentActivity, "增加${name}缺勤次数成功", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@AddAbsentActivity, "增加${name}缺勤次数失败", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }

        }

    }
}
