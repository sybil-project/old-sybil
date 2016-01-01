(ns sybil.processing
  (:require [petrol.core :refer [Message]]
            [sybil.messages :as m]))

(extend-protocol Message
  m/ResetCounter
  (process-message [_ app]
    (assoc app :counter 0))

  m/ModifyCounter
  (process-message [{n :n} app]
    (update app :counter #(+ % n))))
