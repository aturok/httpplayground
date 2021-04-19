(defproject mozolin "0.1.0-SNAPSHOT"
  :description "HTTP playground"
  :url "https://github.com/aturok/httpplayground"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ch.qos.logback/logback-classic "1.1.8"]
                 [clj-http "3.10.0"]
                 [cheshire "5.10.0"]]

  :plugins []
  :source-paths ["src"]
  :resource-paths ["src/resources"]
  
  :profiles {})
