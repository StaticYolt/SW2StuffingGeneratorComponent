import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.StuffingGenerator.StuffingGenerator;
import components.StuffingGenerator.StuffingGenerator1;
import components.sequence.Sequence1L;
import components.sequence.Sequence2L;

//Assumes that seqTwo needs to be tested and that seqOne works as intended
public class UseCaseSequence2 {

    StuffingGenerator seqOne = new StuffingGenerator1(new Sequence1L<String>());
    StuffingGenerator seqTwo = new StuffingGenerator1(new Sequence2L<String>());

    @Test
    public void testAdd() {
        this.seqOne.generateSimple(5);
        Sequence1L<String> sequenceExpected = (Sequence1L) this.seqOne.getRep();
        this.seqTwo.generateSimple(4);
        Sequence2L<String> sequenceInitial = (Sequence2L) this.seqTwo.getRep();
        sequenceInitial.add(4, "5");

        assertEquals(sequenceExpected, sequenceInitial);

    }

    @Test
    public void testRemove() {
        this.seqOne.generateSimple(5);
        this.seqTwo.generateSimple(6);

        Sequence1L<String> sequenceExpected = (Sequence1L) this.seqOne.getRep();
        Sequence2L<String> sequenceInitial = (Sequence2L) this.seqTwo.getRep();

        String strExpected = sequenceInitial
                .remove(sequenceInitial.length() - 1);

        assertEquals(sequenceExpected, sequenceInitial);
        assertEquals("6", strExpected);

    }

    @Test
    public void testAppend() {
        this.seqOne.generateComplex("This", "wouldn't", "be", "a", "complete",
                "sentence", "without", "the", "latter", "half", "of", "the",
                "sequence");
        this.seqTwo.generateComplex("This", "wouldn't", "be", "a", "complete",
                "sentence", "without");

        Sequence1L<String> sequenceExpected = (Sequence1L) this.seqOne.getRep();
        Sequence2L<String> sequenceInitial = (Sequence2L) this.seqTwo.getRep();
        this.seqTwo.generateComplex("the", "latter", "half", "of", "the",
                "sequence");
        sequenceInitial.append((Sequence2L) this.seqTwo.getRep());

        assertEquals(sequenceExpected, sequenceInitial);

    }

}
