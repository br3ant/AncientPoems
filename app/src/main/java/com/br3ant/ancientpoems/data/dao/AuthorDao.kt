package com.br3ant.ancientpoems.data.dao

import androidx.room.*
import com.br3ant.ancientpoems.data.entities.Author

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
@Dao
interface AuthorDao {

    @Query("SELECT * FROM author where name = :name ")
    suspend fun findByName(name: String): Author?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg author: Author)

    @Update
    fun update(vararg author: Author)

    @Delete
    fun delete(vararg author: Author)

}