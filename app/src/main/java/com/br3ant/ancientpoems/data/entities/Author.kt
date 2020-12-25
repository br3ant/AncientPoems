package com.br3ant.ancientpoems.data.entities

import androidx.room.Entity
import com.br3ant.ancientpoems.data.APConstants

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
@Entity(
        tableName = "author",
        primaryKeys = ["id"]
)

class Author {
    var id: Long = 0
    var name: String? = ""
    var description: String? = null
    var short_description: String? = null
    var desc: String? = null
    var dynasty: String = APConstants.DYNASTY_NONE

    fun displayDesc(): String? = when (dynasty) {
        APConstants.DYNASTY_TANG_POEM -> desc
        APConstants.DYNASTY_SONG_CI -> description
        else -> "未知"
    }

}