package com.example.xinwen.bean

class Rubbish {
    var name: String? = null
    var classify: String? = null
    var list: List<String> = ArrayList()

    companion object {
        private var rubbish: Rubbish? = null

        //同步控制,避免多线程的状况多创建了实例对象
        @get:Synchronized
        val instance: Rubbish
            get() {               //同步控制,避免多线程的状况多创建了实例对象
                if (rubbish == null) {
                    rubbish = Rubbish()
                }
                return rubbish!!
            }
    }
}