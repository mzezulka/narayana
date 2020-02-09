package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.TRACING_ACTIVATED;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.opentracing.Span;

/**
 * Storage for spans which need to be finished manually and are not a responsibility
 * of a regular opentracing scope manager.
 *
 * This class is thread safe.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public class SpanRegistry {

    private static final ConcurrentMap<String, Span> ROOT_SPANS = new ConcurrentHashMap<>();

    private SpanRegistry() {
    }

    /**
     * @throws IllegalArgumentException id is null
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return {@code Span} from the registry of root spans
     */
    public static Optional<Span> get(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        Objects.requireNonNull(id);
        Span span = ROOT_SPANS.get(id);
        return span == null ? Optional.empty() : Optional.of(span);
    }

    /**
     * Same as {@link #get(String) get} but with the effect of
     * removing the span from the registry.
     *
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return the deleted {@code Span} if tracing is activated, empty otherwise
     */
    public static Optional<Span> remove(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        Objects.requireNonNull(id);
        Span rem = ROOT_SPANS.remove(id);
        return rem == null ? Optional.empty() : Optional.of(rem);
    }

    /**
     * Insert {@code span} into registry of root spans. If an entry in this registry exists,
     * i.e. entry with id {@code id} is already present, this method does not
     * complete successfully. All parameters must be nonnull.
     *
     * @throws IllegalArgumentException any parameter is null or {@code span} with
     *                                  id {@code id} is already registered.
     */
    public static void insert(String id, Span span) {
        if(!TRACING_ACTIVATED) return;
        Objects.requireNonNull(id);
        Objects.requireNonNull(span);
        if (ROOT_SPANS.put(id, span) != null)
            throw new IllegalArgumentException(
                    String.format("There is already an entry with id %s in the registry.", id));
    }

    // for testing purposes only
    static void reset() {
        ROOT_SPANS.clear();
    }

    static int rootSpanCount() {
        return ROOT_SPANS.size();
    }

}
