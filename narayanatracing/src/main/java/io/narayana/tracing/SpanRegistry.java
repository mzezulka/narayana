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
    private static final ConcurrentMap<String, Span> PRE2PC_SPANS = new ConcurrentHashMap<>();

    private SpanRegistry() {
    }

    private static Optional<Span> get(ConcurrentMap<String, Span> map, String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        Objects.requireNonNull(id);
        Span span = map.get(id);
        return span == null ? Optional.empty() : Optional.of(span);
    }

    /**
     * @throws IllegalArgumentException id is null
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return {@code Span} from the registry of root spans
     */
    public static Optional<Span> getRoot(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        return get(ROOT_SPANS, id);
    }

    /**
     * @throws IllegalArgumentException id is null
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return {@code Span} from the registry of wrapper pre-2PC spans
     */
    public static Optional<Span> getPre2pc(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        return get(PRE2PC_SPANS, id);
    }

    private static Optional<Span> remove(ConcurrentMap<String, Span> map, String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        Objects.requireNonNull(id);
        Span rem = map.remove(id);
        return rem == null ? Optional.empty() : Optional.of(rem);
    }

    /**
     * Same as {@link #getRoot(String) get} but with the effect of
     * removing the span from the registry.
     *
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return the deleted {@code Span} if tracing is activated, empty otherwise
     */
    public static Optional<Span> removeRoot(String id) {
        if(!TRACING_ACTIVATED) return null;
        return remove(ROOT_SPANS, id);
    }

    /**
     * Same as {@link #getPre2pc(String) get} but with the effect of
     * removing the span from the registry if there is span with the id {@code}.
     *
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return the deleted {@code Span} wrapped in {@code Optional} if it exists, empty otherwise
     */
    public static Optional<Span> removePre2pc(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        return remove(PRE2PC_SPANS, id);
    }

    private static void insert(ConcurrentMap<String, Span> map, String id, Span span) {
        if(!TRACING_ACTIVATED) return;
        Objects.requireNonNull(id);
        Objects.requireNonNull(span);
        if (map.putIfAbsent(id, span) != null)
            throw new IllegalArgumentException(
                    String.format("There is already an entry with id %s in the registry.", id));
    }

    /**
     * Insert {@code span} into registry of root spans. If an entry in this registry exists,
     * i.e. entry with id {@code id} is already present, this method does not
     * complete successfully. All parameters must be nonnull.
     *
     * @throws IllegalArgumentException any parameter is null or {@code span} with
     *                                  id {@code id} is already registered.
     */
    public static void insertRoot(String id, Span span) {
        if(!TRACING_ACTIVATED) return;
        insert(ROOT_SPANS, id, span);
    }

    /**
     * Insert {@code span} into registry of wrapper pre-2PC spans. If an entry in this registry exists,
     * i.e. entry with id {@code id} is already present, this method does not
     * complete successfully. All parameters must be nonnull.
     *
     * @throws IllegalArgumentException any parameter is null or {@code span} with
     *                                  id {@code id} is already registered.
     */
    public static void insertPre2pc(String id, Span span) {
        if(!TRACING_ACTIVATED) return;
        insert(PRE2PC_SPANS, id, span);
    }

}
