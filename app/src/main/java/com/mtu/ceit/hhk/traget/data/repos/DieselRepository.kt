package com.mtu.ceit.hhk.traget.data.repos

import com.mtu.ceit.hhk.traget.data.local.dao.DieselDAO
import com.mtu.ceit.hhk.traget.data.model.Diesel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class DieselRepository @Inject constructor(private val dao: DieselDAO) {

    suspend fun insertBarrel(diesel:Diesel) {
        dao.insertDiesel(diesel)
    }

    suspend fun getActiveId() = flowOf(dao.getActive())



    suspend fun deleteDiesel(diesel: Diesel) {
        dao.deleteDiesel(diesel)
    }


    fun getAllBarrels() = dao.getAllDiesel()

    fun getBarrelsWithClients() =
        dao.getDieselWithClients()


}