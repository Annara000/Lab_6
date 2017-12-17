package com.example.demo.view

import javafx.scene.control.Label
import javafx.scene.layout.VBox
import tornadofx.*

class MainView : View("XML parsing with dom4j & TornadoFX") {

    class MyFragment: Fragment() {
        override val root = VBox()

        init {
            with(root) {
                prefWidth = 600.0
                prefHeight = 400.0
                this += button("To Main menu"){
                    action{
                        replaceWith(MainView::class)
                    }
                }
            }
        }
    }

    override val root = VBox()

    init {
        with(root) {
            prefWidth = 600.0
            prefHeight = 400.0

            this += Label("XML parsing")
            this += button("Filtering"){
                action{
                    replaceWith(FilteringView::class)
                }
            }
            this += button("Convert"){
                action{
                    replaceWith(ConvertView::class)
                }
            }
            this += button("Grouping"){
                action{
                    replaceWith(GroupingView::class)
                }
            }
        }
    }
}