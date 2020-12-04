package com.br3ant.ancientpoems.data.converters

import androidx.room.TypeConverter
import com.br3ant.utils.GsonUtil
import com.br3ant.utils.toJson
import com.google.gson.reflect.TypeToken
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
class StringConverter {

    @TypeConverter
    fun stringToParagraphs(data: String?): List<String>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return GsonUtil.getInstance().gson.fromJson(data, listType)
    }

    @TypeConverter
    fun paragraphsToString(list: List<String>?): String? {
        return list?.toJson()
    }
}