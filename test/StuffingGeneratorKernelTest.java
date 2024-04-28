import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.StuffingGenerator.StuffingGenerator1;
import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.sequence.Sequence2L;

public class StuffingGeneratorKernelTest {
    @Test
    public void testGetOutputTypeSequence1L() {
        Sequence<String> seq = new Sequence1L<>();
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(seq);

        assertEquals(true, initialStuffing.getOutputType().getClass()
                .equals(seq.getClass()));
    }

    @Test
    public void testGetOutputTypeSequence2L() {
        Sequence<String> seq = new Sequence2L<>();
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(seq);

        assertEquals(true, initialStuffing.getOutputType().getClass()
                .equals(seq.getClass()));
    }

    @Test
    public void testGetOutputTypeBinaryTree1() {
        BinaryTree<Integer> tree = new BinaryTree1<>();
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(tree);

        assertEquals(true, initialStuffing.getOutputType().getClass()
                .equals(tree.getClass()));
    }

    @Test
    public void testSetOutputTypeWithSequence1LAndBinaryTreeGeneration() {

        Sequence<String> seqExpected = new Sequence2L<>();
        seqExpected.add(0, "1");
        seqExpected.add(1, "2");
        seqExpected.add(2, "3");
        seqExpected.add(3, "4");
        seqExpected.add(4, "5");

        BinaryTree<String> btExpected = new BinaryTree1<>();
        BinaryTree<String> btNode2 = new BinaryTree1<>();
        BinaryTree<String> btNode3 = new BinaryTree1<>();
        BinaryTree<String> btNode4 = new BinaryTree1<>();
        BinaryTree<String> btNode5 = new BinaryTree1<>();

        btNode3.assemble("3", new BinaryTree1<String>(),
                new BinaryTree1<String>());
        btNode4.assemble("4", new BinaryTree1<String>(),
                new BinaryTree1<String>());
        btNode5.assemble("5", new BinaryTree1<String>(),
                new BinaryTree1<String>());

        btNode2.assemble("2", btNode4, btNode5);

        btExpected.assemble("1", btNode2, btNode3);

        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateSimple(5);
        Sequence<String> seqGenerated = (Sequence<String>) initialStuffing
                .getRep();

        initialStuffing.setOutputType(btExpected);

        BinaryTree<String> btGenerated = (BinaryTree<String>) initialStuffing
                .getRep();

        assertEquals(true, seqExpected.equals(seqGenerated));
        assertEquals(true, btExpected.equals(btGenerated));
    }

    @Test
    public void testRemoveUsingSequence() {

        Sequence<String> seqExpected = new Sequence2L<>();
        seqExpected.add(0, "1");
        seqExpected.add(1, "2");
        seqExpected.add(2, "3");
        seqExpected.add(3, "4");
        seqExpected.add(4, "5");

        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateSimple(6);
        initialStuffing.remove(5);
        Sequence<String> seqGenerated = (Sequence<String>) initialStuffing
                .getRep();

        assertEquals(true, seqExpected.equals(seqGenerated));
    }

    @Test
    public void testAddUsingSequence() {

        Sequence<String> seqExpected = new Sequence2L<>();
        seqExpected.add(0, "1");
        seqExpected.add(1, "2");
        seqExpected.add(2, "3");
        seqExpected.add(3, "4");
        seqExpected.add(4, "5");

        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateSimple(4);
        initialStuffing.add(4, "5");
        Sequence<String> seqGenerated = (Sequence<String>) initialStuffing
                .getRep();

        assertEquals(true, seqExpected.equals(seqGenerated));
    }
}
