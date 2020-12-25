package com.br3ant.ancientpoems

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.br3ant.ancientpoems.data.AppDatabase
import com.br3ant.ancientpoems.util.rxhttp.RxHttpUtils
import com.drake.brv.utils.BRV
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

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
class APP : Application() {

    override fun onCreate() {
        super.onCreate()

        Utils.init(this)
        RxHttpUtils.init()
        LogUtils.getConfig().isLog2FileSwitch = true

        BRV.modelId = BR.m

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ -> MaterialHeader(this) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ -> ClassicsFooter(this) }
    }

    companion object {
        val db: AppDatabase by lazy {
            AppDatabase.createDatabase(Utils.getApp())
        }
    }
}