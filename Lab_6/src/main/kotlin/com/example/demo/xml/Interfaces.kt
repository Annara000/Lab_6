package com.example.demo.xml

import org.dom4j.Document

/**
 * Отфильтровать города (cities) по количеству населения и убрать несоответсующие города из документа
 */
interface FilterByPopulation {
    fun filter_by_population(doc: Document)
}

/**
 * Отфильтровать города (cities) по принадлежности к стране и убрать несоответсующие города из документа
 */
interface FilterByCountry {
    fun filter_by_country(doc: Document)
}

/**
 * Отфильтровать города(cities) по плотности населения и убрать несоответсующие города из документа
 */
interface FilterByPopulationDensity {
    fun filter_by_population_density(doc: Document)
}

/**
* Отфильтровать города(cities) по площади города и убрать несоответсующие города из документа
*/
interface FilterBySquare {
    fun filter_by_square(doc: Document)
}

/**
 * Рассчитать процентную долю населения (среди всех) для городов и сохранить в документе значения в виде атрибутов
 */
interface CalcPopulationPercent {
    fun calc_population_percent(doc: Document)
}

/**
 * Рассчитать процентную долю площади (среди всех) для городов и сохранить в документе значения в виде атрибутов
 */
interface CalcSquarePercent {
    fun calc_square_percent(doc: Document)
}

/**
 * Отсортировать города по плотности населения и сохранить новый порядок в документе
 */
interface SortByPopulationDensity {
    fun sort_by_population_density(doc: Document)
}

/**
 * Отсортировать города по площади города и сохранить новый порядок в документе
 */
interface SortBySquare {
    fun sort_by_square(doc: Document)
}

/**
 * Сгруппировать все города по странам и собрать в документе города в отдельные группы, создав элементы country
 */
interface GroupByCountry {
    fun group_by_country(doc: Document)
}

/**
 * Сгруппировать все города по континентам и собрать в документе города в отдельные группы, создав элементы continent
 */
interface GroupByContinent {
    fun group_by_continent(doc: Document)
}