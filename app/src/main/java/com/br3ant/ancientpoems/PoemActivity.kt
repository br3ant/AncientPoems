package com.br3ant.ancientpoems

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.br3ant.ancientpoems.data.entities.Poem
import com.br3ant.base.BaseActivity
import com.drake.brv.utils.linear
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
                    ToastUtils.showLong(
                        APP.db.authorDao().findByName(getModel<Poem>().author)?.displayDesc()
                    )
                }
            }
        }

        page.onRefresh {
            MainScope().launch {
                val data = APP.db.poemDao().pageGetPoem(intent.getStringExtra(KEY_DYNASTY) ?: "", index, LIMIT)
                LogUtils.iTag("hqq", "getData size ${data.size}")

                addData(data) {
                    data.size == LIMIT // 判断是否有更多页
                }
            }

        }.autoRefresh()
    }

    companion object {
        const val KEY_DYNASTY = "Dynasty"
        private const val LIMIT = 100

        fun start(context: Context, dynasty: String) {
            context.startActivity(Intent(context, PoemActivity::class.java).apply {
                putExtra(KEY_DYNASTY, dynasty)
            })
        }
    }

}