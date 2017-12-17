package com.example.demo.view

import com.example.demo.xml.XMLModifier
import org.dom4j.Element
import tornadofx.*

class ConvertView : View() {
    data class City(val name: String, val population: Int, val square: Double, val square_percent: String,
                    val population_density: Int, val country: String, val continent: String)

    private val reader = XMLModifier("./src/main/resources/lab.xml")

    init {
        with(reader.doc) {
            //Применение одной (только одной!) из функций (фильтрация, преобразование или группирование) согласно своему варианту
            reader.calc_square_percent(this)
        }
        find(MainView.MyFragment::class).openModal()
    }

    private val cities = reader.doc.selectNodes("/cities/city").map {
        City((it as Element).attributeValue("name"),
                it.attributeValue("population").toInt(),
                it.attributeValue("square").toDouble(),
                it.attributeValue("square_percent"),
                it.attributeValue("population_density").toInt(),
                it.attributeValue("country"),
                it.attributeValue("continent"))
    }.observable()

     override val root = tableview(cities) {
        column("Название", City::name)
        column("Население", City::population)
        column("Площадь", City::square)
        column("Процентная доля площади", City::square_percent)
        column("Плотность населения", City::population_density)
        column("Страна", City::country)
        column("Континент", City::continent)
    }


}