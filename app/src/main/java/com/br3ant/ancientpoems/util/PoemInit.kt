package com.br3ant.ancientpoems.util

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.ToastUtils
import com.br3ant.ancientpoems.APP
import com.br3ant.ancientpoems.data.APConstants
import com.br3ant.ancientpoems.data.entities.Author
import com.br3ant.ancientpoems.data.entities.Poem
import com.br3ant.utils.coroutine.Coroutine
import com.br3ant.utils.toList

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 12/4/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object PoemInit {
    private var poemRestored: Boolean by PreferenceUtil("poemRestored", false)

    fun restoreDb() {
        if (!poemRestored) {
            Coroutine.async {

                initSongCi()

                initTangPoem()

            }.onSuccess {
                poemRestored = true

                ToastUtils.showLong("数据初始化完成")
            }.onError {
                LogUtils.eTag("hqq", it.message)

                ToastUtils.showLong("数据初始化失败")
            }.onStart {
                ToastUtils.showLong("初始化数据...")
            }
        }
    }

    private fun initSongCi() {
        val author = ResourceUtils.readAssets2String("ci/author.song.json").toList<Author>().onEach {
            it.dynasty = APConstants.DYNASTY_SONG_CI
        }

        APP.db.authorDao().insert(*author.toTypedArray())

        LogUtils.iTag("hqq", "author success")

        val assets = listOf(
                "ci/ci.song.0.json", "ci/ci.song.1000.json", "ci/ci.song.2000.json", "ci/ci.song.3000.json", "ci/ci.song.4000.json", "ci/ci.song.5000.json", "ci/ci.song.6000.json", "ci/ci.song.7000.json", "ci/ci.song.8000.json", "ci/ci.song.9000.json", "ci/ci.song.10000.json", "ci/ci.song.11000.json", "ci/ci.song.12000.json", "ci/ci.song.13000.json", "ci/ci.song.14000.json", "ci/ci.song.15000.json", "ci/ci.song.16000.json", "ci/ci.song.17000.json", "ci/ci.song.18000.json", "ci/ci.song.18000.json", "ci/ci.song.19000.json", "ci/ci.song.20000.json", "ci/ci.song.21000.json"
        )
        assets.forEach { asset ->
            val result = ResourceUtils.readAssets2String(asset).toList<Poem>().onEach {
                it.dynasty = APConstants.DYNASTY_SONG_CI
            }

            APP.db.poemDao().insert(*result.toTypedArray())
        }
        LogUtils.iTag("hqq", "song ci success")
    }

    private fun initTangPoem() {

        val author = ResourceUtils.readAssets2String("json/authors.tang.json").toList<Author>().onEach {
            it.dynasty = APConstants.DYNASTY_TANG_POEM
        }

        APP.db.authorDao().insert(*author.toTypedArray())

        val assets = listOf(
                "json/poet.tang.0.json", "json/poet.tang.1000.json", "json/poet.tang.2000.json", "json/poet.tang.3000.json", "json/poet.tang.4000.json", "json/poet.tang.5000.json", "json/poet.tang.6000.json", "json/poet.tang.7000.json", "json/poet.tang.8000.json", "json/poet.tang.9000.json", "json/poet.tang.10000.json", "json/poet.tang.11000.json", "json/poet.tang.12000.json", "json/poet.tang.13000.json", "json/poet.tang.14000.json", "json/poet.tang.15000.json", "json/poet.tang.16000.json", "json/poet.tang.17000.json", "json/poet.tang.18000.json", "json/poet.tang.19000.json", "json/poet.tang.20000.json", "json/poet.tang.21000.json", "json/poet.tang.22000.json", "json/poet.tang.23000.json", "json/poet.tang.24000.json", "json/poet.tang.25000.json", "json/poet.tang.26000.json", "json/poet.tang.27000.json", "json/poet.tang.28000.json", "json/poet.tang.29000.json", "json/poet.tang.30000.json", "json/poet.tang.31000.json", "json/poet.tang.32000.json", "json/poet.tang.33000.json", "json/poet.tang.34000.json", "json/poet.tang.35000.json", "json/poet.tang.36000.json", "json/poet.tang.37000.json", "json/poet.tang.38000.json", "json/poet.tang.39000.json", "json/poet.tang.40000.json", "json/poet.tang.41000.json", "json/poet.tang.42000.json", "json/poet.tang.43000.json", "json/poet.tang.44000.json", "json/poet.tang.45000.json", "json/poet.tang.46000.json", "json/poet.tang.47000.json", "json/poet.tang.48000.json", "json/poet.tang.49000.json", "json/poet.tang.50000.json", "json/poet.tang.51000.json", "json/poet.tang.52000.json", "json/poet.tang.53000.json", "json/poet.tang.54000.json", "json/poet.tang.55000.json", "json/poet.tang.56000.json", "json/poet.tang.57000.json"
        )
        assets.forEach { asset ->
            val result = ResourceUtils.readAssets2String(asset).toList<Poem>().onEach {
                it.dynasty = APConstants.DYNASTY_TANG_POEM
            }

            APP.db.poemDao().insert(*result.toTypedArray())
        }
        LogUtils.iTag("hqq", "tang poem success")
    }

    private fun initSongPoem() {

        val author = ResourceUtils.readAssets2String("ci/author.song.json").toList<Author>()

        APP.db.authorDao().insert(*author.toTypedArray())

        LogUtils.iTag("hqq", "author success")

        val assets = listOf(
                "ci/ci.song.0.json",
                "ci/ci.song.1000.json",
                "ci/ci.song.2000.json",
                "ci/ci.song.3000.json",
                "ci/ci.song.4000.json",
                "ci/ci.song.5000.json",
                "ci/ci.song.6000.json",
                "ci/ci.song.7000.json",
                "ci/ci.song.8000.json",
                "ci/ci.song.9000.json",
                "ci/ci.song.10000.json",
                "ci/ci.song.11000.json",
                "ci/ci.song.12000.json",
                "ci/ci.song.13000.json",
                "ci/ci.song.14000.json",
                "ci/ci.song.15000.json",
                "ci/ci.song.16000.json",
                "ci/ci.song.17000.json",
                "ci/ci.song.18000.json",
                "ci/ci.song.18000.json",
                "ci/ci.song.19000.json",
                "ci/ci.song.20000.json",
                "ci/ci.song.21000.json"
        )
        assets.forEach {
            val result = ResourceUtils.readAssets2String(it).toList<Poem>()

            APP.db.poemDao().insert(*result.toTypedArray())
        }
        LogUtils.iTag("hqq", "song ci success")
    }
}