package com.br3ant.ancientpoems.data.entities

import androidx.room.Entity
import com.br3ant.ancientpoems.data.APConstants
import java.util.*

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
    tableName = "word",
    primaryKeys = ["id"]
)

class Word {
    var id: String = UUID.randomUUID().toString()
    var ci: String? = null
    var explanation: String? = null
    var example: String? = null
    var pinyin: String? = null
    var word: String? = null
    var abbreviation: String? = null
    var word_type: String = APConstants.WORD_NONE

}