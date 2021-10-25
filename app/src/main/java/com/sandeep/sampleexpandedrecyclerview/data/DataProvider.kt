package com.sandeep.sampleexpandedrecyclerview.data

object DataProvider {

    fun getRandomItemsGroup(countItems: Int, suffix: String): ItemsGroup {

        val subItemList: ArrayList<SubItem> = arrayListOf()
        val subList: ArrayList<PlanDetail> = arrayListOf()
        val planDetail = PlanDetail("Plan $suffix", "Amount $suffix")
        subList.add(planDetail)
        subList.add(planDetail)
        subList.add(planDetail)
        subList.add(planDetail)
        subList.add(planDetail)

        val subItem = SubItem("SubHeader $suffix", subList)
        val subItem1 = SubItem("SubHeader $suffix", subList)
        val subItem2 = SubItem("SubHeader $suffix", subList)
        val subItem3 = SubItem("SubHeader $suffix", subList)

        subItemList.add(subItem)
        subItemList.add(subItem1)
        subItemList.add(subItem2)
        subItemList.add(subItem3)

        return ItemsGroup("Header $suffix", subItemList)

    }

    fun getRandomItemsGroupList(countGroups: Int): List<ItemsGroup> =
        (1..countGroups)
            .map { i ->
                getRandomItemsGroup(i, i.toString())
            }
}