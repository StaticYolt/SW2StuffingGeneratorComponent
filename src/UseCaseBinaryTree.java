import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.StuffingGenerator.StuffingGenerator;
import components.StuffingGenerator.StuffingGenerator1;
import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

public class UseCaseBinaryTree {

    private StuffingGenerator<String> btOne = new StuffingGenerator1<String>(
            new BinaryTree1<String>());

    @Test
    public void testBTHeight() {
        this.btOne.generateSimple(5);
        /**
         * Generated Tree would look like compose(1, compose(2, 4, 5),
         * compose(3, EMPTY, EMPTY))
         */
        BinaryTree<String> expectedBT = (BinaryTree) this.btOne.getRep();

        int expectedHeight = expectedBT.height();

        assertEquals(3, expectedHeight);
    }

    @Test
    public void testBTClear() {
        this.btOne.generateSimple(4);
        /**
         * Generated Tree would look like compose(1, compose(2, 4, EMPTY),
         * compose(3, EMPTY, EMPTY))
         */
        BinaryTree<String> initialBT = (BinaryTree) this.btOne.getRep();

        BinaryTree<String> expectedBT = new BinaryTree1<>();

        initialBT.clear();
        assertEquals(initialBT, expectedBT);
    }

    @Test
    public void testBTSize() {
        this.btOne.generateComplex("Hello", "What", "is", "the", "size", "of",
                "tree");

        BinaryTree<String> initialBT = (BinaryTree) this.btOne.getRep();
        //If you want to see what tree looks like
        //System.out.println(initialBT.toString());

        int expectedSize = initialBT.size();
        assertEquals(7, expectedSize);
    }
}
