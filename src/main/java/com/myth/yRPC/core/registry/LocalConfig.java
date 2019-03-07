package com.myth.yRPC.core.registry;

import java.util.List;

class LocalConfig {
        private List<Node> nodes;
        private long time;
        public LocalConfig(List<Node> nodes, long time) {
            this.nodes = nodes;
            this.time = time;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void setNodes(List<Node> nodes) {
            this.nodes = nodes;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }