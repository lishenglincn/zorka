/**
 * Copyright 2012-2013 Rafal Lewczuk <rafal.lewczuk@jitlogic.com>
 * <p/>
 * This is free software. You can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p/>
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jitlogic.zorka.common;

import java.util.Map;
import java.util.Set;

public class TimedDeltaMetric extends RawDeltaMetric {

    private long lastClock;

    public TimedDeltaMetric(int id, String name, Map<String, Object> attrs) {
        super(id, name, attrs);
    }

    public TimedDeltaMetric(MetricTemplate template, Set<Map.Entry<String, Object>> attrSet) {
        super(template, attrSet);
    }

    @Override
    public Number getValue(long clock, Number value) {
        Number val = super.getValue(clock, value);

        long dclock = clock - lastClock;

        lastClock = clock;

        return 1000.0 * val.doubleValue() / dclock;
    }
}
