package com.waether.app.core

@Retention (AnnotationRetention.RUNTIME)
@Target (AnnotationTarget.CLASS)
annotation class ViewById(val resID : Int)