package com.example.train.view.fragment.first

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.train.R
import com.example.train.util.DensityUtil
import com.example.train.view.fragment.BaseFragment
import com.example.train.view.fragment.adapter.PageAdapter
import kotlinx.android.synthetic.main.fragment_add_personal.*
import kotlinx.android.synthetic.main.fragment_control_team.*
import kotlin.properties.Delegates

/**
 * Created by chenyang
 * on 20-2-27
 */
class ControlTeamFragment : BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_control_team
    private lateinit var pageAdapter: PageAdapter
    private var dialog by Delegates.notNull<AlertDialog>()
    private lateinit var btnDialogCancel: AppCompatButton
    private lateinit var btnDialogTrue: AppCompatButton


    override fun initial(view: View) {
        setChildFragment()
        creatDialog()
        val tabDivider = tl_control_team_fragment.getChildAt(0) as LinearLayout
        tabDivider.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        tabDivider.dividerDrawable =
            context?.getDrawable(R.drawable.shape_control_team_divider_vertical)
        tabDivider.dividerPadding = DensityUtil.dip2px(15)
        vp_control_team_fragmrnt.adapter = pageAdapter
        vp_control_team_fragmrnt.setPageTransformer(true, windmillTransformer())
        tl_control_team_fragment.setupWithViewPager(vp_control_team_fragmrnt)
    }

    private fun setChildFragment() {
        val addPersonalFragment =
            AddPersonalChildTeamFragment({
                it.setOnClickListener(
                    this@ControlTeamFragment
                )
            })
        val addTeamFragment =
            AddTeamChildTeamFragment({
                it.setOnClickListener(
                    this@ControlTeamFragment
                )
            })
        val hashMap: HashMap<Int, Fragment> =
            hashMapOf(0 to addPersonalFragment, 1 to addTeamFragment)
        pageAdapter = PageAdapter(childFragmentManager, hashMap.size, hashMap)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add_personal_fm_submit -> {
                dialog.show()
            }

            R.id.btn_add_team_submit -> {

            }

            R.id.btn_dialog_continue_add_true -> {
                if (dialog.isShowing) {
                    dialog.hide()
                }
                 et_add_personal_fm_name.setText("22222")
            }

            R.id.btn_dialog_continue_add_cancel -> {
                if (dialog.isShowing) {
                    dialog.hide()
                }
                activity?.onBackPressed()

            }
        }
    }

    private fun creatDialog() {
        val mContext = context
        if (mContext != null) {
            dialog = AlertDialog.Builder(mContext).create()
            val dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_contine_add, null)
            btnDialogCancel = dialogView.findViewById(R.id.btn_dialog_continue_add_cancel)
            btnDialogTrue = dialogView.findViewById(R.id.btn_dialog_continue_add_true)
            btnDialogCancel.setOnClickListener(this)
            btnDialogTrue.setOnClickListener(this)
            dialog.apply {
                setView(layoutInflater.inflate(R.layout.dialog_contine_add, null))
                show()  //这里有点蜜汁bug，貌似是因为我直接creat了以后再来设置view会有这个问题
                hide()
                setCanceledOnTouchOutside(true)
                window?.setContentView(dialogView)
            }
        }

    }

    companion object {
        private val TAG = "ControlTeamFragment"
        fun isInputAvailable(input: String): Boolean {
            return input.length != 0
        }

        fun isInputAvailable(vararg input: String): Boolean {
            for (str in input) {
                if (!isInputAvailable(
                        str
                    )
                ) {
                    return false
                }
            }
            return true
        }
    }


    private inner class windmillTransformer : ViewPager.PageTransformer {

        //前后两页是否露角, 暂时没有解决z轴高度问题，先不露角
        private val showAngle = false
        //初始旋转角度
        private val defaultRotation = -40

        override fun transformPage(page: View, position: Float) {

            if (position <= 0f) {//当前页
                page.apply {
                    //旋转
                    rotation = defaultRotation * Math.abs(position)
                    //Y轴位移，因为暂时露角有问题，暂时不设置这个值
//                    translationY = - page.height / 10f * position
//                    z轴阴影
                    // translationZ = - (page.width + defaultScale * 5 * position) / page.width
                }
            } else {  //前后页
                page.apply {
                    rotation = -(defaultRotation * Math.abs(position))
//                    translationY = page.height / 10 * position
//                    translationZ = (page.width - defaultScale * 5 * position) / page.width
                }
            }

            page.translationX =
                if (showAngle) -(page.width / 10 * position) else (page.width / 3 * position)

        }
    }

}