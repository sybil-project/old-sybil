(ns sybil.processing
  (:require [petrol.core :refer [Message]]
            [sybil.messages :as m]))

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
