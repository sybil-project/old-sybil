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
(defn- load-page-action [ui-channel address]
  (send! ui-channel (m/->LoadPage address)))

(defn address-input
  [ui-channel address]
  [:div#address-input
   [:div.expanded.row
    [:div.medium-12.columns
     [:div.input-group
      [:input.input-group-field
       {:type :text
        :defaultValue address
        :on-change (send-value! ui-channel m/->UpdateAddress)
        :on-key-up (fn [e]
                     (if (= (.-keyCode e) 13)
                       ((load-page-action ui-channel address) e)))}]
      [:div.input-group-button
       [:button#go-button.button
        {:on-click (load-page-action ui-channel address)}
        [:span.fa.fa-arrow-right]]]]]]])

(defn navigation-buttons
  [ui-channel app]
  [:div#navigation-buttons
   [:a.hollow.button
    [:span.fa.fa-arrow-left]]
   [:a.hollow.button
    [:span.fa.fa-arrow-right]]
   [:a.hollow.button
    [:span.fa.fa-refresh]]
   ])

(defn navigation-bar
  [ui-channel {:keys [navigation] :as app}]
  [:div#navigation-bar.expanded.row
   [:div#navigation-buttons-wrapper.shrink.columns
    [navigation-buttons ui-channel app]]
   [:div.columns
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
