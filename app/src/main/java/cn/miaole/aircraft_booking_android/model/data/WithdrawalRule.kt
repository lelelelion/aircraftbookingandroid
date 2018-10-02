package cn.miaole.aircraft_booking_android.model.data

import com.bin.david.form.annotation.SmartColumn
import com.bin.david.form.annotation.SmartTable

@SmartTable(name = "退改签规则", count = false)
class WithdrawalRule(@SmartColumn(name = "舱位") val key: String,
                     @SmartColumn(name = "经济舱") val value: String)