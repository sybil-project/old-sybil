(ns sybil.core
  (:require [reagent.core :as reagent]
            [cljsjs.react]
            [petrol.core :as petrol]
            [sybil.processing :as processing]
            [sybil.view :as view]))

(def initial-state
  {:counter 0
   :text "Hello World!"
   :navigation {:address "http://example.com"}})

(defonce !app
  (reagent/atom initial-state))

;; figwheel reload-hook
(defn reload-hook
  []
  (swap! !app identity))

(defn render-fn
  [ui-channel app]
  (reagent/render-component [view/root ui-channel app]
                            (.getElementById js/document "app")))

(defn mount-root
  []
  (enable-console-print!)
  (petrol/start-message-loop! !app render-fn))

(defn init!
  []
  (mount-root))
