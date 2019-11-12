package io.narayana.tracing;

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
        return get(ROOT_SPANS, id);
    }

    /**
     * @throws IllegalArgumentException id is null
     * @param id id of the span (i.e. transaction ID), must not be null
     * @return {@code Span} from the registry of wrapper pre-2PC spans
     */
    public static Optional<Span> getPre2pc(String id) {
        return get(PRE2PC_SPANS, id);
    }

    private static Span remove(ConcurrentMap<String, Span> map, String id) {
        Objects.requireNonNull(id);
        return map.remove(id);
    }

    /**
     * Same as {@link #getRoot(String) get} but with the effect of
     * removing the span from the registry.
     *
     * @param id id of the span (i.e. transaction ID), must not be null
     * @throws NullPointerException {@code id} does not exist in the registry of
     *                                  type {@code type}
     * @return the deleted {@code Span}
     */
    public static Span removeRoot(String id) {
        return remove(ROOT_SPANS, id);
    }

    /**
     * Same as {@link #getPre2pc(String) get} but with the effect of
     * removing the span from the registry.
     *
     * @param id id of the span (i.e. transaction ID), must not be null
     * @throws NullPointerException {@code id} does not exist in the registry of
     *                                  type {@code type}
     * @return the deleted {@code Span}
     */
    public static Span removePre2pc(String id) {
        return remove(PRE2PC_SPANS, id);
    }

    private static void insert(ConcurrentMap<String, Span> map, String id, Span span) {
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
        insert(PRE2PC_SPANS, id, span);
    }

}
