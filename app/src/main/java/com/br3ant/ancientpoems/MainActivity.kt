package com.br3ant.ancientpoems

import com.br3ant.ancientpoems.data.APConstants
import com.br3ant.ancientpoems.databinding.ActivityMainBinding
import com.br3ant.base.BaseActivity
import com.hi.dhl.binding.viewbind


class MainActivity : BaseActivity(R.layout.activity_main, keepScreenOn = true) {

    private val binding: ActivityMainBinding by viewbind()

    override fun initView() {

        binding.btnTangPoem.setOnClickListener {
            PoemActivity.start(this, APConstants.DYNASTY_TANG_POEM)
        }

        binding.btnSongCi.setOnClickListener {
            PoemActivity.start(this, APConstants.DYNASTY_SONG_CI)
        }

        binding.btnYuanQu.setOnClickListener {
            PoemActivity.start(this, APConstants.DYNASTY_YUAN_QU)
        }

        binding.btnShiJing.setOnClickListener {
            PoemActivity.start(this, APConstants.DYNASTY_SHI_JING)
        }

    }

}