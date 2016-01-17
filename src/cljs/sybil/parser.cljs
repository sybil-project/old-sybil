(ns sybil.parser
  (:require cljsjs.commonmark))

(defn md->html [md-text]
  (let [parser (new js/commonmark.Parser)
        renderer (new js/commonmark.HtmlRenderer)]
    (.render renderer (.parse parser md-text))))

(defn render-page [page]
  (assoc page :html-content (md->html (:md-content page))))
