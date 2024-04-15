import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

public class Playground {

    public Playground() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Sequence oneL = new Sequence1L<String>();
        TescaGenerator0_1<Object> tescaTest = new TescaGenerator0_1<>(oneL);

//------------------------------------------------------------------------------

        tescaTest.generateSimple(10);
        Sequence initialSeq = (Sequence) tescaTest.getRep();
        tescaTest.remove(0);
        Sequence expectedSeq = (Sequence) tescaTest.getRep();

        System.out.println(initialSeq.toString());
        System.out.println(expectedSeq.toString());

//------------------------------------------------------------------------------

        System.out.println();
        tescaTest.generateComplex("hello", "your", "mom");
        Sequence intitialSeq2 = (Sequence) tescaTest.getRep();
        tescaTest.remove(0);
        Sequence expectedSeq2 = (Sequence) tescaTest.getRep();

        System.out.println(intitialSeq2.toString());
        System.out.println(expectedSeq2.toString());

//------------------------------------------------------------------------------

        System.out.println();
        tescaTest.setState(new NaturalNumber2());
        System.out.println(tescaTest.getState().toString());

        tescaTest.generateSimple(10);
        NaturalNumber initialNN = (NaturalNumber) tescaTest.getRep();
        tescaTest.remove(9);
        NaturalNumber expectedNN = (NaturalNumber) tescaTest.getRep();

        System.out.println(initialNN.toString());
        System.out.println(expectedNN.toString());

//------------------------------------------------------------------------------

        System.out.println();
//        Works for both String and int inputs into generateComplex
        tescaTest.generateComplex(2, 4, 6, 8, 1);
        NaturalNumber initialNN2 = (NaturalNumber) tescaTest.getRep();
        tescaTest.remove(4);
        NaturalNumber expectedNN2 = (NaturalNumber) tescaTest.getRep();

        System.out.println(initialNN2.toString());
        System.out.println(expectedNN2.toString());

//------------------------------------------------------------------------------
        System.out.println();
        tescaTest.setState(new BinaryTree1<String>());

        tescaTest.generateSimple(5);

        BinaryTree<String> initialBT = (BinaryTree<String>) tescaTest.getRep();
        tescaTest.remove(0);
        BinaryTree<String> expectedBT = (BinaryTree<String>) tescaTest.getRep();

        System.out.println(initialBT.toString());
        System.out.println(expectedBT.toString());

//------------------------------------------------------------------------------
        System.out.println();

        tescaTest.generateComplex("woah", "pee", "poo", "bop");

        BinaryTree<String> initialBT1 = (BinaryTree<String>) tescaTest.getRep();
        tescaTest.remove(0);
        BinaryTree<String> expectedBT1 = (BinaryTree<String>) tescaTest
                .getRep();

        System.out.println(initialBT1.toString());
        System.out.println(expectedBT1.toString());
    }
}
