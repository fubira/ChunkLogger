package net.ironingot.chunklogger;

import java.lang.Integer;
import java.lang.Math;
import java.util.HashMap;
import java.util.Iterator;

public class RecordStore {
    private class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(Location location) {
            return getDistance(location.x, location.y);
        }

        public int getDistance(int px, int py) {
            return (int)Math.sqrt(getDistanceSq(px, py));
        }

        public int getDistanceSq(Location location) {
            return getDistanceSq(location.x, location.y);
        }

        public int getDistanceSq(int px, int py) {
            return (x * x + y * y) - (px * px + py * py);
        }
    }

    private static long expireMillis = 1000 * 60 * 1;
    private HashMap<Location, Long> recordMap;

    public RecordStore() {
        recordMap = new HashMap<Location, Long>();
    }

    public void record(int x, int y, int skipDist) {
        Long ms = System.currentTimeMillis();

        if (test(new Location(x, y), skipDist))
            return;

        recordMap.put(new Location(x, y), ms);
    }

    public void clear() {
        recordMap.clear();

    }

    private boolean test(Location location, int range) {
        Iterator it = recordMap.keySet().iterator();
        while (it.hasNext()) {
            Location l = (Location)it.next();
            if (isExpire(recordMap.get(l)))
            {
                it.remove();
                continue;
            }

            if (location.getDistanceSq(l) < range)
                return true;
        }
        return false;
    }

    private boolean isExpire(Long time) {
        Long current = System.currentTimeMillis();
        return (time + expireMillis < current);
    }

}
