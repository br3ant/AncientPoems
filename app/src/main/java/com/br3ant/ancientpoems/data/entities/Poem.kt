package com.br3ant.ancientpoems.data.entities

import androidx.room.Entity
import com.br3ant.ancientpoems.data.APConstants
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
@Entity(
    tableName = "poem",
    primaryKeys = ["id"]
)

class Poem {
    var id: Long = 0
    var author: String? = ""
    var paragraphs: String? = "[]"
        get() = if (dynasty == APConstants.DYNASTY_SHI_JING) content else field
    var rhythmic: String? = ""
    var title: String? = ""
    var chapter: String? = ""
    var section: String? = ""
    var content: String? = ""
    var dynasty: String = APConstants.DYNASTY_NONE

    fun displayTitle(): String = when (dynasty) {
        APConstants.DYNASTY_TANG_POEM, APConstants.DYNASTY_YUAN_QU -> title ?: ""
        APConstants.DYNASTY_SONG_CI -> rhythmic ?: ""
        APConstants.DYNASTY_SHI_JING -> "$section $chapter $title"
        else -> "未知"
    }

    fun ttsTitle(): String = displayTitle() + "作者 $author" + paragraphs.toList<String>().joinToString("\n")

}