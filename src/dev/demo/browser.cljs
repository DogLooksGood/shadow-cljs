(ns demo.browser
  (:require-macros [demo.browser :refer (test-macro)])
  (:require
    #_ ["babel-test" :as babel-test :default Shape]
    [clojure.pprint :refer (pprint)]
    [clojure.spec.alpha :as s]
    [clojure.spec.gen.alpha :as gen]
    [shadow.api :refer (ns-ready)]
    [cljs.core.async :as async :refer (go)]
    ["./es6.js" :as es6]
    ["./foo" :as foo]
    #_ ["circular-test" :as circ]
    #_ ["/demo/myComponent" :refer (myComponent)]
    [cljs.test :refer (deftest)]
    [demo.never-load]
    [demo.always-load]
    ))

(deftest yo
  (= 1 2))

(js/console.log "or" (or nil js/document.body))

(go (<! (async/timeout 500))
    (js/console.log "go!"))

(es6/someAsyncFn (js/fetch "/index.html"))

#_ (js/console.log "JSX" (myComponent))

(pprint [1 2 3])

(assoc nil :foo 1)

(js/console.log "es6" (es6/foo "es6"))

;; (defn x 1)

(js/console.log :foo)
(prn :foo1 :bar)





#_ (js/console.log "babel-test" babel-test (Shape. 1 1))

(js/console.log "foo" foo)

#_ (js/console.log "circular - not yet" (circ/test))
#_ (js/console.log "circular - actual" (circ/foo))

(s/def ::foo string?)

(s/fdef foo
  :args (s/cat :foo ::foo))

(defn ^:dev/after-load start []
  (js/console.log "browser-start"))

(defn start-from-config []
  (js/console.log "browser-start-from-config"))

(defn ^:export init []
  (js/console.log "browser-init")
  (start))

(defn ^:dev/before-load stop-sync []
  (js/console.log "browser-stop-sync"))

(defn ^:dev/before-load-async stop [done]
  (js/console.log "browser-stop async")
  (js/setTimeout
    (fn []
      (js/console.log "browser-stop async complete")
      (done))
    250))

(defn stop-from-config [done]
  (js/console.log "browser-stop-from-config async")
  (js/setTimeout
    (fn []
      (js/console.log "browser-stop-from-config async complete")
      (done))
    250))

(defrecord Foo [a b])

(js/console.log (pr-str Foo) (pr-str (Foo. 1 2)) (Foo. 1 2))

(js/console.log (test-macro 1 2 3))

(js/console.log {:something [:nested #{1 2 3}]})

(defn tokenize []
  (js/console.log "REMOVE-CHECK"))

(ns-ready)

(goog-define DEBUG false)

(when DEBUG
  (js/console.log "foo2"))
