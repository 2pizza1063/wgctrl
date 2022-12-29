(ns wgctrl.cluster.transforms
  (:require [wgctrl.cluster.ssh :as ssh]
            [wgctrl.cluster.checks :as c]))

(defn peer->interface
  "Adds Peer to node's Interface,
   checks Peer is uniq by :peer field"
  [peer interface]
  (if (c/peer-exists? peer interface)
    interface
    (do (swap! (.peers interface) conj peer)
        interface)))

(defn node->cluster
  "Adds Node to Cluster, 
   checks node is uniq by UUID"
  [node cluster]
  (if (c/node-exists? node cluster)
    cluster
    (do (swap! (.nodes cluster) conj node)
        cluster)))

(defn interface->node
  "Adds Interface to Node"
  [interface node]
  (swap! (.interfaces node) conj interface)
  node)

