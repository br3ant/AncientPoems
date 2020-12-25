package com.br3ant.ancientpoems

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.lifecycle.rxLifeScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.br3ant.ancientpoems.data.entities.Poem
import com.br3ant.ancientpoems.databinding.ActivityPoemBinding
import com.br3ant.ancientpoems.repository.PoemRepository
import com.br3ant.base.BaseActivity
import com.br3ant.utils.toList
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.hi.dhl.binding.viewbind

class PoemActivity : BaseActivity(R.layout.activity_poem, keepScreenOn = true) {

    private val binding: ActivityPoemBinding by viewbind()

    private val dynasty: String
        get() = intent.getStringExtra(KEY_DYNASTY) ?: ""

    override fun initView() {

        binding.rvList.linear().setup {
            addType<Poem>(R.layout.item_poem)

            onBind {
                findView<TextView>(R.id.tv_paragraphs).text =
                    getModel<Poem>().paragraphs.toList<String>().joinToString("\n")
            }

            onLongClick(R.id.item_view) {
                rxLifeScope.launch {
                    ToastUtils.showLong(
                        APP.db.authorDao().findByName(getModel<Poem>().author ?: "")?.displayDesc()
                    )
                }
            }
        }

        binding.page.onRefresh {
            rxLifeScope.launch({
                val data = PoemRepository.pageGetPoems(dynasty, index, LIMIT)

                LogUtils.iTag("hqq", "getData size ${data.size}")

                addData(data) {
                    data.size == LIMIT // 判断是否有更多页
                }
            }, onError = {
                binding.page.finish(false)
                ToastUtils.showShort(it.message)
            }, onFinally = {
            })

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