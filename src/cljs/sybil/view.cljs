(ns sybil.view
  (:require [petrol.core :refer [send! send-value!]]
            [sybil.messages :as m]))

;; text entry (example)
(defn text-display
  [ui-channel text]
  [:div.row
   [:div.large-12.columns
    [:div.callout text]
    [:div [:span.fa.fa-diamond]]]])

(defn text-editor
  [ui-channel app]
  [:div.row
   [:div.large-12.columns
    [:input {:type :text
             :defaultValue (:text app)
             :on-change (send-value! ui-channel m/->UpdateText)}]]])

;; Top bar
(defn address-input
  [ui-channel address]
  [:div#address-input
   [:div.expanded.row
    [:div.medium-12.columns
     [:div.input-group
      [:input.input-group-field
       {:type :text
        :defaultValue address
        :on-change (send-value! ui-channel m/->UpdateAddress)}]
      [:div.input-group-button
       [:button#go-button.button
        {:on-click (send! ui-channel (m/->LoadPage address))}
        [:span.fa.fa-arrow-right]]]]]]])

(defn navigation-bar
  [ui-channel {:keys [navigation] :as app}]
  [:div#navigation-bar.expanded.row
   [:div.medium-12.columns
    [address-input ui-channel (:address navigation)]]])

;; Root component
(defn root
  [ui-channel app]
  [:div#main.expanded.row
   [:div.medium-12.columns
    #_[:h1 "Text Editor"]
    [navigation-bar ui-channel app]
    #_[text-editor ui-channel app]
    #_[text-display ui-channel (:text app)]

    ]])
