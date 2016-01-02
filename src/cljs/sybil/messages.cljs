(ns sybil.messages)

(defrecord UpdateText [t])

(defrecord UpdateAddress [text])
(defrecord LoadPage [address])
