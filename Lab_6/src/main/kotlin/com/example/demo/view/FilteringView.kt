package com.example.demo.view

import com.example.demo.xml.XMLModifier
import org.dom4j.Element
import tornadofx.*

class FilteringView : View() {

    data class City(val name: String, val population: Int, val square: Double, val population_density: Int,
                    val country: String, val continent: String)

    private val reader = XMLModifier("./src/main/resources/lab.xml")

    init {
        with(reader.doc) {
            // фильтрация
            reader.filter_by_population(this)
        }
        find(MainView.MyFragment::class).openModal()
    }

    private val cities = reader.doc.selectNodes("/cities/city").map {
        City((it as Element).attributeValue("name"),
                it.attributeValue("population").toInt(),
                it.attributeValue("square").toDouble(),
                it.attributeValue("population_density").toInt(),
                it.attributeValue("country"),
                it.attributeValue("continent"))
    }.observable()

    override val root = tableview(cities) {
        column("Название", City::name)
        column("Население", City::population)
        column("Площадь", City::square)
        column("Плотность населения", City::population_density)
        column("Страна", City::country)
        column("Континент", City::continent)
    }
}