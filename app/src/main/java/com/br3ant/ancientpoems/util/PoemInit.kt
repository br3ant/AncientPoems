package com.br3ant.ancientpoems.util

import com.blankj.utilcode.util.*
import com.br3ant.ancientpoems.APP
import com.br3ant.ancientpoems.data.APConstants
import com.br3ant.ancientpoems.data.entities.Author
import com.br3ant.ancientpoems.data.entities.Poem
import com.br3ant.utils.coroutine.Coroutine
import com.br3ant.utils.toList
import java.io.File

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
                val dir = unzip()

                initSongCi(dir)

                initTangPoem(dir)

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

    private fun unzip(): String {
        val dir: String = Utils.getApp().filesDir.absolutePath + File.separator + "poem"
        val poemZip =
            Utils.getApp().filesDir.absolutePath + File.separator + "poem_temp" + File.separator + "poem_zip.zip"

        ResourceUtils.copyFileFromAssets("poem.zip", poemZip)
        ZipUtils.unzipFile(poemZip, dir)
        return dir
    }

    private fun initSongCi(dir: String) {
        val author =
            FileIOUtils.readFile2String("${dir}/ci/author.song.json").toList<Author>().onEach {
                it.dynasty = APConstants.DYNASTY_SONG_CI
            }

        APP.db.authorDao().insert(*author.toTypedArray())

        LogUtils.iTag("hqq", " initSongCi author success")

        val assets = (0..21000 step 1000).map { "${dir}/ci/ci.song.${it}.json" }

        assets.forEach { asset ->
            val result = FileIOUtils.readFile2String(asset).toList<Poem>().onEach {
                it.dynasty = APConstants.DYNASTY_SONG_CI
            }

            APP.db.poemDao().insert(*result.toTypedArray())
        }
        LogUtils.iTag("hqq", "song ci success")
    }

    private fun initTangPoem(dir: String) {

        val author =
            FileIOUtils.readFile2String("${dir}/json/authors.tang.json").toList<Author>().onEach {
                it.dynasty = APConstants.DYNASTY_TANG_POEM
            }

        APP.db.authorDao().insert(*author.toTypedArray())

        val assets = (0..57000 step 1000).map { "${dir}/json/poet.tang.${it}.json" }

        assets.forEach { asset ->
            val result = FileIOUtils.readFile2String(asset).toList<Poem>().onEach {
                it.dynasty = APConstants.DYNASTY_TANG_POEM
            }

            APP.db.poemDao().insert(*result.toTypedArray())
        }
        LogUtils.iTag("hqq", "tang poem success")
    }

    private fun initSongPoem(dir: String) {

        val author = FileIOUtils.readFile2String("${dir}/json/authors.song.json").toList<Author>()

        APP.db.authorDao().insert(*author.toTypedArray())

        LogUtils.iTag("hqq", " initSongPoem author success")

        val assets = (0..254000 step 1000).map { "${dir}/json/poet.song.${it}.json" }

        assets.forEach { asset ->
            val result = FileIOUtils.readFile2String(asset).toList<Poem>()
                .onEach {
                    it.dynasty = APConstants.DYNASTY_SONG_POEM
                }

            APP.db.poemDao().insert(*result.toTypedArray())
        }
        LogUtils.iTag("hqq", "song poem success")
    }
}