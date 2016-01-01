(ns sybil.core
  (:require [reagent.core :as reagent]
            [cljsjs.react]))

(defn greeting
  []
  [:div [:h1 "Hello Sybil!"]])

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
