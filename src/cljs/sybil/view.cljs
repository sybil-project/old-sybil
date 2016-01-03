(ns sybil.view
  (:require [petrol.core :refer [send! send-value!]]
            [sybil.messages :as m]))

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

(defn new-tab-button
  [ui-channel app]
   [:a#new-tab-button.hollow.button
    [:span.fa.fa-plus]])

(defn settings-button
  [ui-channel app]
   [:a#settings-button.hollow.button
    [:span.fa.fa-cog]])

(defn top-bar
  [ui-channel {:keys [navigation] :as app}]
  [:div#top-bar.expanded.row
   [:div#top-bar-left.shrink.columns
    [navigation-buttons ui-channel app]]
   [:div#top-bar-center.columns
    [address-input ui-channel (:address navigation)]]
   [:div#top-bar-right.shrink.columns
    [new-tab-button ui-channel app]
    [settings-button ui-channel app]]])


;; Page body
(defn page-container
  [ui-channel app]
  [:div#page-container.expanded.row
   [:div.medium-12.columns "hi"]])

;; Root component
(defn root
  [ui-channel app]
  [:div#main.expanded.row
   [:div.medium-12.columns
    [top-bar ui-channel app]
    [page-container ui-channel app]]])
