(ns sybil.processing
  (:require [petrol.core :refer [Message]]
            [sybil.messages :as m]
            [sybil.page :as page]))

(defonce static-pages
  {"about:welcome" "# Welcome to Sybil\nTake a look [here](http://example.com)."})

(extend-protocol Message
  m/UpdateAddress
  (process-message [{:keys [text]} app]
    (assoc-in app [:navigation :address] text))

  m/LoadPage
  (process-message [{:keys [address]} app]
    (println ">> " address)
    app)

  m/UpdateText
  (process-message [{:keys [t]} app]
    (assoc app :text t)))
