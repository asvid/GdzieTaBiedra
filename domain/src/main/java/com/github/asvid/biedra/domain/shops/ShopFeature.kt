package com.github.asvid.biedra.domain.shops

enum class ShopFeature {
    Bakery, Relax, Atm, CardPayment, TaxFreeShopping, EuroAccepted, NewShop
}

/**
 * DLS method for [OpenHours]
 * */
fun features(block: FeaturesBuilder.() -> Unit): Set<ShopFeature> = FeaturesBuilder().apply(
        block).build()


@ShopDsl
class FeaturesBuilder {
    var bakery: Boolean = false
    var relax: Boolean = false
    var atm: Boolean = false
    var cardPayment: Boolean = false
    var isTaxFree: Boolean = false
    var isEuro: Boolean = false
    var isNew: Boolean = false

    fun build(): Set<ShopFeature> {
        return mutableListOf<ShopFeature>().apply {
            if (bakery) add(ShopFeature.Bakery)
            if (relax) add(ShopFeature.Relax)
            if (atm) add(ShopFeature.Atm)
            if (cardPayment) add(ShopFeature.CardPayment)
            if (isTaxFree) add(ShopFeature.TaxFreeShopping)
            if (isEuro) add(ShopFeature.EuroAccepted)
            if (isNew) add(ShopFeature.NewShop)
        }.toSet()
    }
}