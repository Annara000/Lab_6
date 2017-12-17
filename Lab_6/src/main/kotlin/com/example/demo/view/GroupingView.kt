package com.example.demo.view

import com.example.demo.xml.XMLModifier
import javafx.collections.ObservableList
import org.dom4j.Element
import tornadofx.*

class GroupingView : View() {
    data class Continent( val name: String,
                          val cities: ObservableList<City>)
    data class City(val name: String, val population: Int,
                    val square: Double,
                    val population_density: Int,
                    val country: String)

    private val reader = XMLModifier("./src/main/resources/lab.xml")

    init {
        with(reader.doc) {
            //группирование
            reader.group_by_continent(this)
        }
        find(MainView.MyFragment::class).openModal()
    }

    private val continents = listOf(
            Continent(reader.doc.selectSingleNode("/cities/continent[1]/@name")
                    .valueOf("/cities/continent[1]/@name").toString(),
                    cities = reader.doc.selectNodes("/cities/continent[1]/city").map {
        City((it as Element).attributeValue("name"),
                it.attributeValue("population").toInt(),
                it.attributeValue("square").toDouble(),
                it.attributeValue("population_density").toInt(),
                it.attributeValue("country"))
    }.observable()),
            Continent(reader.doc.selectSingleNode("/cities/continent[2]/@name").valueOf("/cities/continent[2]/@name").toString(),
                    cities = reader.doc.selectNodes("/cities/continent[2]/city").map {
                        City((it as Element).attributeValue("name"),
                                it.attributeValue("population").toInt(),
                                it.attributeValue("square").toDouble(),
                                it.attributeValue("population_density").toInt(),
                                it.attributeValue("country"))
                    }.observable()),
            Continent(reader.doc.selectSingleNode("/cities/continent[3]/@name").valueOf("/cities/continent[3]/@name").toString(),
                    cities = reader.doc.selectNodes("/cities/continent[3]/city").map {
                        City((it as Element).attributeValue("name"),
                                it.attributeValue("population").toInt(),
                                it.attributeValue("square").toDouble(),
                                it.attributeValue("population_density").toInt(),
                                it.attributeValue("country"))
                    }.observable())
    ).observable()
    override val root = tableview(continents) {
        column("Континент", Continent::name)
        rowExpander(expandOnDoubleClick = true) {
            paddingLeft = expanderColumn.width
            tableview(it.cities) {
                column("Название", GroupingView.City::name)
                column("Население", GroupingView.City::population)
                column("Площадь", GroupingView.City::square)
                column("Плотность населения", GroupingView.City::population_density)
                column("Страна", GroupingView.City::country)
            }
        }
    }


}