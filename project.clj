(defproject sybil "0.1.0-alpha1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/cljs"]

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [cljsjs/react "0.13.3-1"]
                 [cljsjs/nodejs-externs "1.0.4-1"]
                 [cljsjs/commonmark "0.22.0-0"]
                 [reagent "0.5.1"]
                 [petrol "0.1.2"]
                 ]

  :plugins [[lein-cljsbuild "1.1.1"]]

  :min-lein-version "2.5.3"

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "app/js/p/app.js"
                                        :output-dir    "app/js/p/out"
                                        :asset-path    "js/p/out"
                                        :optimizations :none
                                        :pretty-print  true
                                        :cache-analysis true}}}}

  :clean-targets ^{:protect false} [:target-path "out" "app/js/p"]

  :figwheel {:css-dirs ["app/css"]}

  :profiles {:dev {:cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]
                                              :compiler {:source-map true
                                                         :main       "sybil.dev"
                                                         :verbose true}
                                              :figwheel {:on-jsload "sybil.core/mount-root"}}}}
                   :source-paths ["env/dev/cljs"]

                   :dependencies [[figwheel-sidecar "0.5.0-2"]]

                   :plugins [[lein-ancient "0.6.8"]
                             [lein-kibit "0.1.2"]
                             [lein-cljfmt "0.3.0"]
                             [lein-figwheel "0.5.0-2"]]}

             :production {:cljsbuild {:builds {:app {:compiler {:optimizations :advanced
                                                                :main          "sybil.prod"
                                                                :cache-analysis false
                                                                :closure-defines {"goog.DEBUG" false}
                                                                :externs ["externs/misc.js"]
                                                                :pretty-print false}
                                                     :source-paths ["env/prod/cljs"]}}}}}
  )
