package com.br3ant.ancientpoems

import com.br3ant.ancientpoems.data.APConstants
import com.br3ant.ancientpoems.util.PoemInit
import com.br3ant.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main, keepScreenOn = true) {

    override fun initView() {

        PoemInit.restoreDb()

        btn_tang_poem.setOnClickListener {
            PoemActivity.start(this, APConstants.DYNASTY_TANG_POEM)
        }

        btn_song_ci.setOnClickListener {
            PoemActivity.start(this, APConstants.DYNASTY_SONG_CI)
        }

    }

}