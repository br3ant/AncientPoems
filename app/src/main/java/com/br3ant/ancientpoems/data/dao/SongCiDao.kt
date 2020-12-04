package com.br3ant.ancientpoems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br3ant.ancientpoems.data.entities.SongCi

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
interface SongCiDao {

    @Query("SELECT * FROM songci")
    fun findAll(): List<SongCi>

    @Query("SELECT * FROM songci")
    fun observeAll(): LiveData<List<SongCi>>

    @Query("DELETE FROM songci")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg songCi: SongCi)

    @Update
    fun update(vararg songCi: SongCi)

    @Delete
    fun delete(vararg songCi: SongCi)

}