package com.br3ant.ancientpoems.repository

import com.br3ant.ancientpoems.APP
import com.br3ant.ancientpoems.data.entities.Author
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rxhttp.RxHttp
import rxhttp.toResponse

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020 12 12/26/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object AuthorRepository {
    suspend fun findAuthorByName(name: String): Author? = withContext(Dispatchers.IO) {
        val db = APP.db.authorDao().findByName(name)

        if (db == null) {
            val net = RxHttp.get("/getPoemAuthor")
                .add("name", name)
                .toResponse<Author>().await()
            APP.db.authorDao().insert(net)
            APP.db.authorDao().findByName(name)
        } else {
            db
        }
    }
}