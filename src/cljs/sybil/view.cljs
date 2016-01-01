(ns sybil.view
  (:require [petrol.core :refer [send!]]
            [sybil.messages :as m]))

(defn greeting
  []
  [:div [:h1 "Hello Sybil!"]])

(defn main-page
  []
  [:div#main {:class "row"}
   [:div {:class "large-12 columns"}
    [greeting]]])

(defn root
  [ui-channel app]
   [:div.expanded.row
    [:div.large-5.columns
     [:h1 "Interesting Counter!"]
     [:div.callout (:counter app)]
     [:div.button-group
      [:a.alert.button {:on-click (send! ui-channel (m/->ResetCounter))}
       "Reset"]
      (for [[label delta] [["Decrement" -1]
                           ["Increment" 1]
                           ["+ 5" 5]
                           ["+ 10" 10]]]
        [:a.button {:key delta
                    :on-click (send! ui-channel (m/->ModifyCounter delta))}
         label])]
      ]
     ])
