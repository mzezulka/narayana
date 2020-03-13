package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.TRACING_ACTIVATED;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Storage for objects which need to be finished manually.
 *
 * This class is thread safe.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public abstract class Registry<T> {
    protected final ConcurrentMap<String, T> store = new ConcurrentHashMap<>();

    /**
     * @throws IllegalArgumentException id is null
     * @param id id of {@code T}, must not be null
     * @return {@code T} from the registry
     */
    public Optional<T> get(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        Objects.requireNonNull(id);
        T t = store.get(id);
        return t == null ? Optional.empty() : Optional.of(t);
    }

    /**
     * Same as {@link #get(String) get} but with the effect of
     * removing {@code T} from the registry.
     *
     * @param id id of {@code T}, must not be null
     * @return the deleted {@code T} wrapped in optional, Optional.empty otherwise
     */
    public Optional<T> remove(String id) {
        if(!TRACING_ACTIVATED) return Optional.empty();
        Objects.requireNonNull(id);
        T t = store.remove(id);
        return t == null ? Optional.empty() : Optional.of(t);
    }

    /**
     * Insert {@code T} into this registry. If an entry in this registry exists,
     * i.e. entry with id {@code id} is already present, this throws an exception.
     *
     * All parameters must be nonnull.
     *
     * @throws IllegalArgumentException any input parameter is null or
     *                                  {@code T} with id {@code id} is already registered.
     */
    public void insert(String id, T t) {
        if(!TRACING_ACTIVATED) return;
        Objects.requireNonNull(id);
        Objects.requireNonNull(t);
        if (store.put(id, t) != null)
            throw new IllegalArgumentException(
                    String.format("There is already an entry with id %s of type %s in the registry.", id, t.getClass()));
    }

    // for testing purposes only
    void reset() {
        store.clear();
    }

    // for testing purposes only
    boolean isEmpty() {
        return store.size() == 0;
    }

    // for testing purposes only
    int elementsCount() {
        return store.size();
    }
}
