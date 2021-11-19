package com.mtu.ceit.hhk.traget.repos

import com.mtu.ceit.hhk.traget.data.NightPref
import com.mtu.ceit.hhk.traget.data.PricePref
import com.mtu.ceit.hhk.traget.data.localdb.ClientDAO
import com.mtu.ceit.hhk.traget.data.localdb.DieselDAO
import com.mtu.ceit.hhk.traget.data.localdb.MaintenanceDAO
import com.mtu.ceit.hhk.traget.data.model.Client
import com.mtu.ceit.hhk.traget.data.model.Diesel
import com.mtu.ceit.hhk.traget.data.model.Maintenance
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class SettingRepository @Inject constructor(
        private val pricePref: PricePref, private val nightPref: NightPref,
private val clientDAO: ClientDAO,private val maintainDAO:MaintenanceDAO,private val dieselDAO: DieselDAO) {


    suspend fun setHRPrice(price:Int) = pricePref.setHRPrice(price)

    suspend fun setRVPrice(price:Int) = pricePref.setRVPrice(price)

    suspend fun setNightMode(isNight:Boolean) = nightPref.setNightMode(isNight)

    fun getHRPrice() = pricePref.getHRPrice()

    fun getRVPrice() = pricePref.getRVPrice()

    fun isNightMode() = nightPref.isNightMode()

    fun getAllClients(): Flow<List<Client>> {
        Timber.tag("fafteo").e(Thread.currentThread().name)
        return clientDAO.getAllClients()
    }

    fun getAllMaintains() = maintainDAO.getMaintains()

    fun getAllDiesels() = dieselDAO.getAllDiesel()

    suspend fun insertClient(client:Client) {
        clientDAO.insertClient(client)
    }

    suspend fun insertDiesel(diesel:Diesel) {
        dieselDAO.insertDiesel(diesel)
    }

    suspend fun insertMaintain(maintain:Maintenance){
        maintainDAO.insertMaintain(maintain)
    }




}