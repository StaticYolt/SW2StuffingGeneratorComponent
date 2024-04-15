import components.standard.Standard;

/**
 * Stuffing generator component with enhanced methods
 *
 * @param <T>
 *            type of {@code StuffingGenerator} entries
 */
public interface StuffingGenerator<T> extends StuffingGeneratorKernel<T> {
    /**
     * Sets {@code this} to a string of length {@code size} containing repeated
     * digits from 1 to 9.
     *
     * @param size
     *            size of string after call.
     * @ensures this.contents = string of length {@code size} containg repeated
     *          digits from 1 to 9.
     */
    void generateSimple(int size);

    /**
     * Sets {@code this} to what is in {@code ts}.
     *
     * @param ts
     *            Array of type T.
     * @ensures this.contents = ts
     */
    void generateComplex(T... ts);

    /**
     * @return class which contains this.contents of type this.outputType
     * @requires this.outputType is supported by the component
     */
    Standard<T> getRep();

}
