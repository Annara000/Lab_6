package com.example.demo.xml

import org.dom4j.Document
import org.dom4j.DocumentHelper
import org.dom4j.Element
import java.io.File
import org.dom4j.io.SAXReader

class XMLModifier(file_name: String) {

    val doc: Document

    init {
        //Прочтение xml-файла
        val file = File(file_name)
        doc = SAXReader().read(file)
    }

    // Фильтрация
    fun filter_by_population(doc: Document) {
        val cities_nodes = doc.selectNodes("/cities/city").map {
            if ((it as Element).attributeValue("population").toInt()>=20000000) {
                println((it).attributeValue("name"))
                println("Население: "+(it).attributeValue("population"))
                println(it.text)
            }
            else if ((it).attributeValue("population").toInt()<20000000) {
                it.detach()
            }
        }
    }
    // Преобразование
    fun calc_square_percent(doc: Document) {
        var all_square = 0.0
        var cities_nodes = doc.selectNodes("/cities/city").map {
            var current_square = (it as Element).attributeValue("square").toDouble()
            all_square+=current_square
        }
        cities_nodes = doc.selectNodes("/cities/city").map {
            var current_square = ((it as Element).attributeValue("square").toDouble())
            var current_square_percent = String.format("%.2f",((current_square/all_square)*100))
            var square_percent_adder = (it).addAttribute("square_percent", current_square_percent)
        }
    }
    // Группирование
    fun group_by_continent(doc: Document) {

        var continent = DocumentHelper.createElement("continent")
        doc.rootElement.add(continent)
        var cities_nodes = doc.selectNodes("/cities/city").map {
            if ((it as Element).attributeValue("continent")=="Азия") {
                val city = DocumentHelper.createElement("city")
                continent.add(city)
                var new = it
                val cities_nodes2 = doc.selectNodes("/cities/continent/city").map {
                    city.addAttribute("name", new.attributeValue("name"))
                    city.addAttribute("population", new.attributeValue("population"))
                    city.addAttribute("square", new.attributeValue("square"))
                    city.addAttribute("population_density", new.attributeValue("population_density"))
                    city.addAttribute("country", new.attributeValue("country"))
                    city.text = new.text
                }
            }
        }
        continent.addAttribute("name", "Азия")

        continent = DocumentHelper.createElement("continent")
        doc.rootElement.add(continent)
        cities_nodes = doc.selectNodes("/cities/city").map {
            if ((it as Element).attributeValue("continent")=="Африка") {
                val city = DocumentHelper.createElement("city")
                continent.add(city)
                var new = it
                val cities_nodes2 = doc.selectNodes("/cities/continent/city").map {
                    city.addAttribute("name", new.attributeValue("name"))
                    city.addAttribute("population", new.attributeValue("population"))
                    city.addAttribute("square", new.attributeValue("square"))
                    city.addAttribute("population_density", new.attributeValue("population_density"))
                    city.addAttribute("country", new.attributeValue("country"))
                    city.text = new.text
                }
            }
        }
        continent.addAttribute("name", "Африка")

        continent = DocumentHelper.createElement("continent")
        doc.rootElement.add(continent)
        cities_nodes = doc.selectNodes("/cities/city").map {
            if ((it as Element).attributeValue("continent")=="Европа") {
                val city = DocumentHelper.createElement("city")
                continent.add(city)
                var new = it
                val cities_nodes2 = doc.selectNodes("/cities/continent/city").map {
                    city.addAttribute("name", new.attributeValue("name"))
                    city.addAttribute("population", new.attributeValue("population"))
                    city.addAttribute("square", new.attributeValue("square"))
                    city.addAttribute("population_density", new.attributeValue("population_density"))
                    city.addAttribute("country", new.attributeValue("country"))
                    city.text = new.text
                }
            }
        }
        continent.addAttribute("name", "Европа")

        val cities_nodes3 = doc.selectNodes("/cities/city").map {
            (it as Element).detach()
        }
    }

}