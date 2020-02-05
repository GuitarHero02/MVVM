package com.tistory.deque.mvvm.model.enum

enum class Item {
    ITEM_TOTAL_GRAPH, ITEM_ACCOUNT, ITEM_ISSAC_VIEW, ITEM_SNS, ITEM_GLOBAL_COMMENT
}

enum class MultiType(val type:Int) {
    TEXT_TYPE(type = 0), IMAGE_TYPE(type = 1), IMAGE_TYPE_2(type = 2)
}