package io.narayana.tracing.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.opentracing.Span;

/**
 * Storage for spans which need to be finished manually and are out of the scope
 * of the ScopeManager responsibility.
 *
 * Any implementation of this class must be thread safe.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public class SpanRegistry {

    private static final Map<RegistryType, ConcurrentMap<String, Span>> SPANS = spans();

    private static Map<RegistryType, ConcurrentMap<String, Span>> spans() {
        Map<RegistryType, ConcurrentMap<String, Span>> s = new HashMap<>();
        for (RegistryType type : RegistryType.values()) {
            s.put(type, new ConcurrentHashMap<>());
        }
        return s;
    }

    private SpanRegistry() {
    }

    /**
     * @throws IllegalArgumentException any parameter is null
     * @param id   id of the span (i.e. transaction ID), must not be null
     * @param type registry type, must not be null
     * @return {@code Span} from this registry
     */
    public static Optional<Span> get(RegistryType type, String id) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(id);
        Span span = SPANS.get(type).get(id);
        return span == null ? Optional.empty() : Optional.of(span);
    }

    /**
     * Same as {@link #get(RegistryType, String) get} but with the effect of
     * removing the span from the registry.
     *
     * @param id   id of the span (i.e. transaction ID), must not be null
     * @param type type of the registry, must not be null
     * @throws IllegalArgumentException {@code id} does not exist in the registry of
     *                                  type {@code type}
     * @return the deleted {@code Span}
     */
    public static Span remove(RegistryType type, String id) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(id);
        return SPANS.get(type).remove(id);
    }

    /**
     * Insert {@code span} into this registry. If an entry in this registry exists,
     * i.e. entry with id {@code id} is already present, this method does not
     * complete successfully. All parameters must be nonnull.
     *
     * @throws IllegalArgumentException any parameter is null or {@code span} with
     *                                  id {@code id} is already registered.
     */
    public static void insert(RegistryType type, String id, Span span) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(id);
        Objects.requireNonNull(span);
        if (SPANS.get(type).putIfAbsent(id, span) != null)
            throw new IllegalArgumentException(
                    String.format("There is already an entry with id %s in the registry %s.", id, type));
    }

}
