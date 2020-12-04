package com.br3ant.ancientpoems

import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.br3ant.ancientpoems.data.entities.SongCi
import com.br3ant.ancientpoems.util.SongCiInit
import com.br3ant.base.BaseActivity
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(R.layout.activity_main, keepScreenOn = true) {

    override fun initView() {

        SongCiInit.restoreDb()

        rv_list.linear().setup {
            addType<SongCi>(R.layout.item_song_ci)

            onBind {
                findView<TextView>(R.id.tv_paragraphs).text =
                        getModel<SongCi>().paragraphs.joinToString("\n")
            }

            onLongClick(R.id.item_view) {
                MainScope().launch {
                    ToastUtils.showLong(APP.db.authorDao().findByName(getModel<SongCi>().author)?.description)
                }
            }
        }

        APP.db.songCiDao().observeAll().observe(this) {
            rv_list.models = it
        }
    }

}