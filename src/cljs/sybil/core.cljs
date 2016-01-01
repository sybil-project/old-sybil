(ns sybil.core
  (:require [reagent.core :as reagent]
            [cljsjs.react]))

(defn greeting
  []
  [:div "Hello World"])

(defn main-page
  []
  [:div#main {:class "row"}
   [:div {:class "large-12 columns"}
    [greeting]]])

(defn mount-root
  []
  (reagent/render [main-page] (.getElementById js/document "app")))

(defn init!
  []
  (mount-root))
