package com.sdu.share.expense.validation.cases

interface ValidationBaseCase<In, Out> {
    fun execute(input: In): Out
}