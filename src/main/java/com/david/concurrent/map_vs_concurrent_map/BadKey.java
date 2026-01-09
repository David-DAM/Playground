package com.david.concurrent.map_vs_concurrent_map;

class BadKey {
    private final int id;

    BadKey(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return 1; // TODAS colisionan
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BadKey && ((BadKey) o).id == id;
    }
}

