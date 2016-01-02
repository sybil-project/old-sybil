(ns sybil.view
  (:require [petrol.core :refer [send! send-value!]]
            [sybil.messages :as m]))

;; text entry
(defn text-display
  [ui-channel app]
  [:div.row
   [:div.large-12.columns
    [:div.callout (:text app)]]])

(defn text-editor
  [ui-channel app]
  [:div.row
   [:div.large-12.columns
    [:input {:type :text
             :defaultValue (:text app)
             :on-change (send-value! ui-channel m/->UpdateText)}]]])

(defn root
  [ui-channel app]
  [:div.expanded.row
   [:div.large-6.columns
    [:h1 "Text Editor"]
    [text-editor ui-channel app]
    [text-display ui-channel app]]])
