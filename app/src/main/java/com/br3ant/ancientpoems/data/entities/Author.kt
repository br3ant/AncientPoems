package com.br3ant.ancientpoems.data.entities

import androidx.room.Entity

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
        primaryKeys = ["name"]
)

class Author {
    var name: String = ""
    var description: String = ""
    var short_description: String? = null
}