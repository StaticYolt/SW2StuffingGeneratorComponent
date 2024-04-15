import components.sequence.Sequence;
import components.standard.Standard;

/**
 * Stuffing generator component with primary methods
 *
 * @param <T>
 *            type of {@code StuffingGeneratorKernel} entries
 *
 * @mathsubtypes <pre> STUFFING_GENERATOR_MODEL is ( contents : finite multiset
 *               of T outputType: T that extends Standard )
 */
public interface StuffingGeneratorKernel<T> extends Standard {

    /**
     * Adds the entry {@code x} at position {@code pos} of {@code this}.
     *
     * @param pos
     *            the position at which to add an entry
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @requires {@code 0 <= pos and pos <= |this|}
     * @ensures {@code this = #this[0, pos) * <x> * #this[pos, |#this|)}
     */
    void add(int pos, T x);

    /**
     * Removes and returns the entry at position {@code pos} of {@code this} .
     *
     * @param pos
     *            the position at which to remove an entry
     * @return the entry removed
     * @updates this
     * @requires {@code 0 <= pos and pos < |this|}
     * @ensures {@code
     * this = #this[0, pos) * #this[pos+1, |#this|)  and
     * <remove> = #this[pos, pos+1)
     * }
     */
    T remove(int pos);

    /**
     * Reports the length of {@code this}.
     *
     * @return the length
     * @ensures length = |this|
     */
    int length();

    /**
     * Sets this.output type to {@code type}.
     *
     * @param type
     *            what the output type will be.
     * @ensures this.outputType = {@code type}
     */
    void setOutputType(Standard type);

    /**
     * gets this.outputType
     *
     * @return Standard the outputType of the generator
     */
    Standard getOutputType();

    /**
     * See how the current representation looks like without ailas
     *
     * @return current rep as sequence with elements identical to the curr rep
     */
    Sequence<T> currRep();

}
