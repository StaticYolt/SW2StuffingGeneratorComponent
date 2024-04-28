package components.StuffingGenerator;
import components.binarytree.BinaryTree;
import components.map.Map;
import components.map.Map1L;
import components.naturalnumber.NaturalNumber;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.standard.Standard;

public abstract class StuffingGeneratorSecondary<T>
        implements StuffingGenerator<T> {
    //Only works with inner type thingy being a String
    @Override
    public void generateSimple(int size) {
        this.clear();
        int i = size;
        int j = 1;
        while (i > 0) {
            if (j % 10 == 0) {
                j += 1;
            }
            this.add(this.length(), (T) Integer.toString(j % 10));
            j++;
            i--;
        }
    }

    @Override
    public void generateComplex(T... ts) {
        this.clear();
        for (T element : ts) {
            this.add(this.length(), element);
        }
    }

    @Override
    public Standard<T> getRep() {
        Standard toRep = this.getOutputType();
        Sequence currSeq = this.currRep();
        if (toRep.getClass().toString().contains("class components.sequence")) {

            toRep.transferFrom(this.getSequence(currSeq));
        }
        if (toRep.getClass().toString()
                .contains("class components.naturalnumber")) {

            toRep.transferFrom(this.getNaturalNumber(currSeq));
        }
        if (toRep.getClass().toString()
                .contains("class components.binarytree")) {
            toRep.transferFrom(this.getBinaryTree(currSeq));
        }
        return toRep;
    }

    public Sequence getSequence(Sequence rep) {
        Sequence toRep = (Sequence) this.getOutputType();
        for (Object x : rep) {
            toRep.add(toRep.length(), x);
        }
        return toRep;
    }

    //ASSUIMNG REP LOOKS LIKE <"1", "2", "4", ...>
    public NaturalNumber getNaturalNumber(Sequence rep) {
        NaturalNumber toRep = (NaturalNumber) this.getOutputType()
                .newInstance();
        for (Object x : rep) {
            toRep.multiplyBy10(Integer.valueOf(x.toString()));
        }
        return toRep;
    }

    public BinaryTree getBinaryTree(Sequence rep) {
        T[] repArr = (T[]) new Object[rep.length()];
        for (int i = 0; i < rep.length(); i++) {
            repArr[i] = (T) rep.entry(i);
        }

        return this.fullAssembly(repArr);
    }

    public <T> BinaryTree<T> fullAssembly(T... args) {
        Map<Integer, Sequence<BinaryTree<T>>> binaryTreeRepMap = new Map1L<>();
        int logLength = (int) (Math.log(args.length) / Math.log(2)) + 1;
        for (int i = 0; i < logLength; i++) {
            binaryTreeRepMap.add(i, new Sequence1L<BinaryTree<T>>());
        }
        int currBinary = 1;
        int startPos = args.length - 1;
        int endPos = (int) Math.pow(2, logLength - currBinary) - 2;

        while (logLength - currBinary >= 0) {
            Sequence<BinaryTree<T>> currSequenceBT = binaryTreeRepMap
                    .value(logLength - currBinary);
            for (int i = startPos; i > endPos; i--) {
                BinaryTree<T> currBT = (BinaryTree<T>) this.getOutputType();
                if (args[i] != null) {
                    currBT.assemble(args[i],
                            (BinaryTree<T>) this.getOutputType(),
                            (BinaryTree<T>) this.getOutputType());
                }
                currSequenceBT.add(currSequenceBT.length(), currBT);

            }
            currBinary++;
            startPos = endPos;
            endPos = (int) Math.pow(2, logLength - currBinary) - 2;
        }
        for (int i = 0; i < logLength; i++) {
            Sequence<BinaryTree<T>> currBTM = binaryTreeRepMap.value(i);
            currBTM.flip();
        }
        for (int i = logLength - 1; i > 0; i--) {
            Sequence<BinaryTree<T>> currBTM = binaryTreeRepMap.value(i);
            Sequence<BinaryTree<T>> nextBTM = binaryTreeRepMap.value(i - 1);

            for (int j = 0; j < nextBTM.length(); j++) {
                BinaryTree<T> tempParentBT = nextBTM.remove(j);
                BinaryTree<T> leftBT = (BinaryTree<T>) this.getOutputType();
                BinaryTree<T> rightBT = (BinaryTree<T>) this.getOutputType();
                if (currBTM.length() > 0) {
                    leftBT = currBTM.remove(0);
                }
                if (currBTM.length() > 0) {
                    rightBT = currBTM.remove(0);

                }

                T currElement = tempParentBT.root();
                BinaryTree<T> parentBT = (BinaryTree<T>) this.getOutputType();
                if (currElement != null) {
                    parentBT.assemble(currElement, leftBT, rightBT);
                }
                nextBTM.add(j, parentBT);
            }
//            for (BinaryTree<T> x : nextBTM) {
//                System.out.println(x.toString());
//            }
//            System.out.println();
        }

        return binaryTreeRepMap.value(0).remove(0);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof StuffingGenerator<?>)) {
            return false;
        }
        StuffingGenerator<?> tempGen = (StuffingGenerator<?>) o;
        if (tempGen.length() != this.length()) {
            return false;
        }
        Sequence<?> tempGenSeq = tempGen.currRep();
        Sequence<T> currSeq = this.currRep();

        for (int i = 0; i < currSeq.length(); i++) {
            if (!tempGenSeq.entry(i).equals(currSeq.entry(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + this.currRep().toString() + " , "
                + this.getOutputType().toString() + ")";
    }

}
