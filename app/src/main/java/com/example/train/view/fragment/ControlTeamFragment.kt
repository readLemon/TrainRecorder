package com.example.train.view.fragment

import android.view.View
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.train.R
import com.example.train.util.DensityUtil
import com.example.train.view.fragment.adapter.PageAdapter
import kotlinx.android.synthetic.main.fragment_control_team.*
/**
 * Created by chenyang
 * on 20-2-27
 */
class ControlTeamFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_control_team

    override fun initial(view: View) {
        val tabDivider = tl_control_team_fragment.getChildAt(0) as LinearLayout
        tabDivider.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        tabDivider.dividerDrawable = context?.getDrawable(R.drawable.shape_control_team_divider_vertical)
        tabDivider.dividerPadding = DensityUtil.dip2px(15)
        vp_control_team_fragmrnt.adapter = PageAdapter(childFragmentManager, 2)
        vp_control_team_fragmrnt.setPageTransformer(true, windmillTransformer())
        tl_control_team_fragment.setupWithViewPager(vp_control_team_fragmrnt)
    }

    companion object {
        fun isInputAvailable(input: String): Boolean {
            return input.length != 0
        }

        fun isInputAvailable(vararg input: String): Boolean {
            for (str in input) {
                if (!isInputAvailable(str)) {
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