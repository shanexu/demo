package org.xusheng.demo.metrics;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Meter.Id;
import io.micrometer.core.instrument.Meter.Type;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.TimeGauge;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.pause.PauseDetector;
import io.micrometer.core.instrument.search.RequiredSearch;
import io.micrometer.core.instrument.search.Search;

public class MeterRegistries {

    private final ConcurrentMap<String, MeterRegistry> children;

    private final MeterRegistry meterRegistry;

    public MeterRegistries(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.children = new ConcurrentHashMap<>();
    }

    public MeterRegistry get(String namespace) {
        return children.computeIfAbsent(namespace, n -> new PrefixedMeterRegistry(n, Clock.SYSTEM));
    }

    class PrefixedMeterRegistry extends MeterRegistry {

        private final String namespace;
        private final String prefix;

        protected PrefixedMeterRegistry(String namespace, Clock clock) {
            super(clock);
            this.namespace = namespace;
            this.prefix = namespace + "_";
        }

		@Override
		protected DistributionStatisticConfig defaultHistogramConfig() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected TimeUnit getBaseTimeUnit() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Counter newCounter(Id arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected DistributionSummary newDistributionSummary(Id arg0, DistributionStatisticConfig arg1, double arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected <T> FunctionCounter newFunctionCounter(Id arg0, T arg1, ToDoubleFunction<T> arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected <T> FunctionTimer newFunctionTimer(Id arg0, T arg1, ToLongFunction<T> arg2, ToDoubleFunction<T> arg3,
				TimeUnit arg4) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected <T> Gauge newGauge(Id arg0, T arg1, ToDoubleFunction<T> arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected LongTaskTimer newLongTaskTimer(Id arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Meter newMeter(Id arg0, Type arg1, Iterable<Measurement> arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Timer newTimer(Id arg0, DistributionStatisticConfig arg1, PauseDetector arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Meter> getMeters() {
            throw new UnsupportedOperationException();
		}

		@Override
		public void forEachMeter(Consumer<? super Meter> consumer) {
            throw new UnsupportedOperationException();
		}

		@Override
		public Config config() {
            return MeterRegistries.this.meterRegistry.config();
		}

		@Override
		public Search find(String name) {
            throw new UnsupportedOperationException();
		}

		@Override
		public RequiredSearch get(String name) {
            throw new UnsupportedOperationException();
		}

		@Override
		public Counter counter(String name, Iterable<Tag> tags) {
			return MeterRegistries.this.meterRegistry.counter(prefix + name, tags);
		}

		@Override
		public Counter counter(String name, String... tags) {
            return MeterRegistries.this.meterRegistry.counter(prefix + name, tags);
		}

		@Override
		public DistributionSummary summary(String name, Iterable<Tag> tags) {
            return MeterRegistries.this.meterRegistry.summary(prefix + name, tags);
		}

		@Override
		public DistributionSummary summary(String name, String... tags) {
            return MeterRegistries.this.meterRegistry.summary(prefix + name, tags);
		}

		@Override
		public Timer timer(String name, Iterable<Tag> tags) {
            return MeterRegistries.this.meterRegistry.timer(prefix + name, tags);
		}

		@Override
		public Timer timer(String name, String... tags) {
            return MeterRegistries.this.meterRegistry.timer(prefix + name, tags);
		}

		@Override
		public More more() {
            throw new UnsupportedOperationException();
		}

		@Override
		public <T extends Number> T gauge(String name, T number) {
			return MeterRegistries.this.meterRegistry.gauge(prefix + name, number);
		}

		@Override
		public <T extends Number> T gauge(String name, Iterable<Tag> tags, T number) {
			return MeterRegistries.this.meterRegistry.gauge(prefix + name, tags, number);
		}

		@Override
		public <T> T gauge(String name, T obj, ToDoubleFunction<T> valueFunction) {
			return MeterRegistries.this.meterRegistry.gauge(prefix + name, obj, valueFunction);
		}

		@Override
		public <T> T gauge(String name, Iterable<Tag> tags, T obj, ToDoubleFunction<T> valueFunction) {
			return MeterRegistries.this.meterRegistry.gauge(prefix + name, tags, obj, valueFunction);
		}

		@Override
		public <T extends Collection<?>> T gaugeCollectionSize(String name, Iterable<Tag> tags, T collection) {
			return MeterRegistries.this.meterRegistry.gaugeCollectionSize(prefix + name, tags, collection);
		}

		@Override
		public <T extends Map<?, ?>> T gaugeMapSize(String name, Iterable<Tag> tags, T map) {
			return MeterRegistries.this.meterRegistry.gaugeMapSize(prefix + name, tags, map);
		}

		@Override
		public Meter remove(Meter meter) {
            if (!meter.getId().getName().startsWith(prefix)) {
                return null;
            }
			return MeterRegistries.this.meterRegistry.remove(meter);
		}

		@Override
		public Meter remove(Id id) {
            if (!id.getName().startsWith(prefix)) {
                return null;
            }
			return MeterRegistries.this.meterRegistry.remove(id);
		}
    }
}
