package com.br3ant.ancientpoems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br3ant.ancientpoems.data.entities.Poem

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
interface PoemDao {

    @Query("SELECT * FROM poem")
    fun findAll(): List<Poem>

    @Query("SELECT * FROM poem")
    fun observeAll(): LiveData<List<Poem>>

    @Query("SELECT * FROM poem where dynasty = :dynasty LIMIT 100")
    fun observeByDynasty(dynasty: String): LiveData<List<Poem>>

    @Query("SELECT * FROM poem where dynasty = :dynasty LIMIT :limit offset ((:page - 1) * :limit)")
    suspend fun pageGetPoem(dynasty: String, page: Int, limit: Int): List<Poem>

    @Query("DELETE FROM poem")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg poem: Poem)

    @Update
    fun update(vararg poem: Poem)

    @Delete
    fun delete(vararg poem: Poem)

}