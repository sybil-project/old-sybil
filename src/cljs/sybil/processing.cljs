(ns sybil.processing
  (:require [petrol.core :refer [Message]]
            [sybil.messages :as m]))

(extend-protocol Message
  m/UpdateText
  (process-message [{:keys [t]} app]
    (assoc app :text t)))
