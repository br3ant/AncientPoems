package com.br3ant.ancientpoems.util

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ResourceUtils
import com.br3ant.ancientpoems.APP
import com.br3ant.ancientpoems.data.entities.Author
import com.br3ant.ancientpoems.data.entities.SongCi
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
object SongCiInit {
    private var songCiRestored: Boolean by PreferenceUtil("songCiRestored", false)

    fun restoreDb() {
        if (!songCiRestored) {
            Coroutine.async {

                val author = ResourceUtils.readAssets2String("ci/author.song.json").toList<Author>()

                LogUtils.iTag("hqq", "author " + author.size)

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
                    val result = ResourceUtils.readAssets2String(it).toList<SongCi>()
                    LogUtils.iTag("hqq", result.size)
                    APP.db.songCiDao().insert(*result.toTypedArray())
                }

            }.onSuccess {
                songCiRestored = true
            }.onError {
                LogUtils.eTag("hqq", it.message)
//                APP.db.songCiDao().deleteAll()
            }
        }
    }
}