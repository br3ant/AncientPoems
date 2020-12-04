package com.br3ant.ancientpoems.data.entities

import androidx.room.Entity
import androidx.room.TypeConverters
import com.br3ant.ancientpoems.data.converters.StringConverter
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
    tableName = "songci",
    primaryKeys = ["id"]
)
@TypeConverters(StringConverter::class)
class SongCi {
    var id: String = UUID.randomUUID().toString()
    var author: String = ""
    var paragraphs: List<String> = emptyList()
    var rhythmic: String = ""
}