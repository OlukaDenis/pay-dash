package com.dennnytech.paydash.mappers

interface BaseUiMapper<UI, DOMAIN> {
    fun toUI(entity: DOMAIN): UI
}