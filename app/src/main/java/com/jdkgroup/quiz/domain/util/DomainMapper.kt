package com.jdkgroup.quiz.domain.util

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

}