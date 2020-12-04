package com.br3ant.ancientpoems

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.br3ant.ancientpoems.data.entities.Poem
import com.br3ant.base.BaseActivity
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import kotlinx.android.synthetic.main.activity_poem.*

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PoemActivity : BaseActivity(R.layout.activity_poem, keepScreenOn = true) {

    override fun initView() {

        rv_list.linear().setup {
            addType<Poem>(R.layout.item_poem)

            onBind {
                findView<TextView>(R.id.tv_paragraphs).text =
                        getModel<Poem>().paragraphs.joinToString("\n")
            }

            onLongClick(R.id.item_view) {
                MainScope().launch {
                    ToastUtils.showLong(APP.db.authorDao().findByName(getModel<Poem>().author)?.displayDesc())
                }
            }
        }

        APP.db.poemDao().observeByDynasty(intent.getStringExtra(KEY_DYNASTY) ?: "").observe(this) {
            rv_list.models = it
        }
    }

    companion object {
        const val KEY_DYNASTY = "Dynasty"

        fun start(context: Context, dynasty: String) {
            context.startActivity(Intent(context, PoemActivity::class.java).apply {
                putExtra(KEY_DYNASTY, dynasty)
            })
        }
    }

}