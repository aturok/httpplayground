(ns httpplayground.core
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.java.io :as io])
  (:import [java.util Date]))

(defn request-to [method & path-items]
  {:method method
   :url (apply str path-items)
   :throw-exceptions false})

(defn json-body [request body]
  (assoc request
         :body (json/encode body)
         :content-type :json))

(defn auth [request token]
  (assoc-in request [:headers "Authorization"] (str "Bearer " token)))

(defn response [resp]
  (assoc resp :body (let [body (:body resp)]
                      (try
                        (json/decode body true)
                        (catch Exception _
                          body)))))

(defn save-file [contents filename]
  (with-open [w (java.io.BufferedOutputStream. (java.io.FileOutputStream. filename))]
    (.write w contents)))

(defmacro timed-op [& body]
  `(let [start# (Date.)
         result# ~@body
         end# (Date.)
         diff# (- (.getTime end#) (.getTime start#))]
     {:time/start start#
      :time/end end#
      :time/diff diff#
      :time/diff-s (format "%.3f" (/ diff# 1000.0))
      :result result#}))


;;; EXAMPLES

;; just send a get
;; 
(-> (request-to :get "https://google.com")
    http/request
    response)

;; send a get and save results to a file
;; 
;; (-> (request-to :get "https://google.com")
;;     (assoc :as :byte-array)
;;     http/request
;;     :body
;;     (save-file "out.html"))

(def host "http://api.com")

;; a post with json body
;; 
;; (-> (request-to :post host "/api/something")
;;     (json-body {:data [1 2 3]})
;;     http/request
;;     response)

;; send document with Bearer auth
;; 
;; (-> (request-to :put host "/document")
;;     (auth "YOUR_JWT")
;;     (assoc :multipart [{:name "file" :content (io/file (io/resource "doc1.pdf")) :mime-type "application/pdf"}])
;;     http/request
;;     response
;;     :status)


;; send two requests in parallel, time them and capture results in a def
;; 
;; (def result
;;   (let [[r1 r2] [(future
;;                    (timed-op (-> (request-to :get "https://amazon.com")
;;                                  http/request
;;                                  response
;;                                  :status)))
;;                  (future
;;                    (timed-op (-> (request-to :get "https://apple.com")
;;                                  http/request
;;                                  response
;;                                  :status)))]]
;;     [@r1 @r2]))
;; result

