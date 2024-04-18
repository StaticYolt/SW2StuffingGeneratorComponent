import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.sequence.Sequence2L;

public class StuffingGeneratorTest {

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
    public void testGetRepSimpleLen3Sequence1L() {
        /**
         * Creating and filling a sequence with len 3 regular
         */
        Sequence<String> seqExpected = new Sequence1L<>();
        seqExpected.add(0, "1");
        seqExpected.add(1, "2");
        seqExpected.add(2, "3");

        /**
         * Creating and filling a sequence of len 3 with simple values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateSimple(3);
        Sequence<String> seqGenerated = (Sequence<String>) initialStuffing
                .getRep();

        assertEquals(true, seqGenerated.equals(seqExpected));
    }

    @Test
    public void testGetRepSimpleLen10Sequence2L() {
        /**
         * Creating and filling a sequence with len 3 regular
         */
        Sequence<String> seqExpected = new Sequence2L<>();
        seqExpected.add(0, "1");
        seqExpected.add(1, "2");
        seqExpected.add(2, "3");
        seqExpected.add(3, "4");
        seqExpected.add(4, "5");
        seqExpected.add(5, "6");
        seqExpected.add(6, "7");
        seqExpected.add(7, "8");
        seqExpected.add(8, "9");
        seqExpected.add(9, "1");

        /**
         * Creating and filling a sequence of len 3 with simple values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateSimple(10);
        Sequence<String> seqGenerated = (Sequence<String>) initialStuffing
                .getRep();
//        System.out.println(seqExpected.toString());
//        System.out.println(seqGenerated.toString());

        assertEquals(true, seqGenerated.equals(seqExpected));
    }

    @Test
    public void testGetRepComplexLen5Sequence1LString() {
        /**
         * Creating and filling a sequence with len 3 regular
         */
        Sequence<String> seqExpected = new Sequence1L<>();
        seqExpected.add(0, "John");
        seqExpected.add(1, "Caden");
        seqExpected.add(2, "Traina");
        seqExpected.add(3, "Keith");
        seqExpected.add(4, "Connor");
        seqExpected.add(5, "Aladin");
        seqExpected.add(6, "Theodore");
        seqExpected.add(7, "Graven");
        seqExpected.add(8, "Sally");
        seqExpected.add(9, "Soapstone");

        /**
         * Creating and filling a sequence of len 3 with client values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateComplex("John", "Caden", "Traina", "Keith",
                "Connor", "Aladin", "Theodore", "Graven", "Sally", "Soapstone");
        Sequence<String> seqGenerated = (Sequence<String>) initialStuffing
                .getRep();
//        System.out.println(seqExpected.toString());
//        System.out.println(seqGenerated.toString());

        assertEquals(true, seqGenerated.equals(seqExpected));
    }

    @Test
    public void testGetRepComplexLen10Sequence1LInteger() {
        /**
         * Creating and filling a sequence with len 10 regular. Someting to
         * mention is that duplicates are ok.
         */
        Sequence<Integer> seqExpected = new Sequence1L<>();
        seqExpected.add(0, 123);
        seqExpected.add(1, 234);
        seqExpected.add(2, 125);
        seqExpected.add(3, 123);
        seqExpected.add(4, 888);
        seqExpected.add(5, 343);
        seqExpected.add(6, 44);
        seqExpected.add(7, 123144);
        seqExpected.add(8, 66666);
        seqExpected.add(9, 3);

        /**
         * Creating and filling a sequence of len 10 with client values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(
                seqExpected);
        initialStuffing.generateComplex(123, 234, 125, 123, 888, 343, 44,
                123144, 66666, 3);
        Sequence<Integer> seqGenerated = (Sequence<Integer>) initialStuffing
                .getRep();

        assertEquals(true, seqGenerated.equals(seqExpected));
    }

    @Test
    public void testGetRepSimpleLen5BinaryTree1() {

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

        /**
         * Creating and filling a binary tree of len 5 with simple values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(btExpected);
        initialStuffing.generateSimple(5);
        BinaryTree<Integer> btGenerated = (BinaryTree<Integer>) initialStuffing
                .getRep();

        assertEquals(true, btExpected.equals(btGenerated));
    }

    @Test
    public void testGetRepComplexLen5BinaryTree1() {

        BinaryTree<String> btExpected = new BinaryTree1<>();
        BinaryTree<String> btNode2 = new BinaryTree1<>();
        BinaryTree<String> btNode3 = new BinaryTree1<>();
        BinaryTree<String> btNode4 = new BinaryTree1<>();
        BinaryTree<String> btNode5 = new BinaryTree1<>();

        btNode3.assemble("3WOWOWOWO", new BinaryTree1<String>(),
                new BinaryTree1<String>());
        btNode4.assemble("4notsure", new BinaryTree1<String>(),
                new BinaryTree1<String>());
        btNode5.assemble("5HI", new BinaryTree1<String>(),
                new BinaryTree1<String>());

        btNode2.assemble("2JERMEY", btNode4, btNode5);

        btExpected.assemble("1BABY", btNode2, btNode3);

        /**
         * Creating and filling a binary tree of len 5 with user values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(btExpected);
        initialStuffing.generateComplex("1BABY", "2JERMEY", "3WOWOWOWO",
                "4notsure", "5HI");
        BinaryTree<Integer> btGenerated = (BinaryTree<Integer>) initialStuffing
                .getRep();

        assertEquals(true, btExpected.equals(btGenerated));
    }

    @Test
    public void testGetRepSimpleLen5NaturalNumber1L() {

        NaturalNumber nnExpected = new NaturalNumber1L("123");

        /**
         * Creating and filling a binary tree of len 5 with simple values
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(nnExpected);
        initialStuffing.generateSimple(3);
        NaturalNumber nnGenerated = (NaturalNumber) initialStuffing.getRep();

        assertEquals(true, nnExpected.equals(nnGenerated));
    }

    @Test
    public void testGetRepComplexLen5NaturalNumber1L() {

        NaturalNumber nnExpected = new NaturalNumber1L("998");

        /**
         * Creating and filling a binary tree of len 5 with complex values As
         * you can probably see, its probably easier to just create a NN in this
         * case without the generator.
         */
        StuffingGenerator1 initialStuffing = new StuffingGenerator1(nnExpected);
        initialStuffing.generateComplex(9, 9, 8);
        NaturalNumber nnGenerated = (NaturalNumber) initialStuffing.getRep();

        assertEquals(true, nnExpected.equals(nnGenerated));
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
