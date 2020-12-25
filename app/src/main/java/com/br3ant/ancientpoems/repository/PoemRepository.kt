package com.br3ant.ancientpoems.repository

import com.br3ant.ancientpoems.APP
import com.br3ant.ancientpoems.data.entities.Poem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rxhttp.RxHttp
import rxhttp.toResponse

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020 12 12/25/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object PoemRepository {
    suspend fun pageGetPoems(dynasty: String, page: Int, limit: Int): List<Poem> = withContext(Dispatchers.IO) {
        val db = APP.db.poemDao().pageGetPoem(dynasty, page, limit)

        if (db.isEmpty()) {
            val net = RxHttp.get("/getPoems")
                .add("dynasty", dynasty)
                .add("page", page)
                .add("limit", limit).toResponse<List<Poem>>().await()

            APP.db.poemDao().insert(*net.toTypedArray())

            APP.db.poemDao().pageGetPoem(dynasty, page, limit)
        } else {
            db
        }
    }
}