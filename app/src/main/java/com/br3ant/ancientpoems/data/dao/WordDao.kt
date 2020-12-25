package com.br3ant.ancientpoems.data.dao

import androidx.room.*
import com.br3ant.ancientpoems.data.entities.Word

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
interface WordDao {

    @Query("SELECT * FROM word")
    suspend fun findByName(name: String): Word?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg word: Word)

    @Update
    fun update(vararg word: Word)

    @Delete
    fun delete(vararg word: Word)

}