import components.binarytree.BinaryTree;
import components.map.Map;
import components.map.Map1L;
import components.naturalnumber.NaturalNumber;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.standard.Standard;

/**
 *
 *
 * @author Leo Cheng
 */
public final class TescaGenerator0_1<T> {

    Standard currState;
    Sequence seq;

    public TescaGenerator0_1() {

    }

    public TescaGenerator0_1(Standard rep) {

//        this.curState = mp.valueOf(rep);
        this.currState = (Standard) rep.newInstance();
        this.seq = new Sequence1L<T>();
    }

    public Class<? extends Standard> getState() {
        return this.currState.getClass();
    }

    public void setState(Standard rep) {
        this.currState = (Standard) rep.newInstance();
    }

    public void clear() {
        this.seq.clear();
    }

    public void add(int pos, T x) {
        this.seq.add(pos, x);
    }

    public T remove(int pos) {
        return (T) this.seq.remove(pos);
    }

    public void generateSimple(int size) {
        this.seq.clear();
        int i = size;
        int j = 1;
        while (i > 0) {
            if (j % 10 == 0) {
                j += 1;
            }
            this.seq.add(this.seq.length(), Integer.toString(j % 10));
            j++;
            i--;
        }
    }

    public void generateComplex(T... ts) {
        this.seq.clear();
        for (T element : ts) {
            this.seq.add(this.seq.length(), element);
        }
    }

//    public Standard getRep(Standard rep) {
//        Standard toRep = (Standard) rep.newInstance();
//        if (toRep.getClass().toString().contains("class components.sequence")) {
//            toRep.transferFrom(this.getSequence((Sequence) rep));
//        }
//        if (toRep.getClass().toString()
//                .contains("class components.naturalnumber")) {
//            toRep.transferFrom(this.getNaturalNumber((Sequence) rep));
//        }
//        return toRep;
//
//    }

    public Standard getRep() {
        Standard toRep = (Standard) this.currState.newInstance();
        if (toRep.getClass().toString().contains("class components.sequence")) {

            toRep.transferFrom(this.getSequence(this.seq));
        }
        if (toRep.getClass().toString()
                .contains("class components.naturalnumber")) {

            toRep.transferFrom(this.getNaturalNumber(this.seq));
        }
        if (toRep.getClass().toString()
                .contains("class components.binarytree")) {
            toRep.transferFrom(this.getBinaryTree(this.seq));
        }
        return toRep;
    }

    public Sequence getSequence(Sequence rep) {
        Sequence toRep = (Sequence) rep.newInstance();
        for (Object x : this.seq) {
            toRep.add(toRep.length(), x);
        }
        return toRep;
    }

    //ASSUIMNG REP LOOKS LIKE <"1", "2", "4", ...>
    public NaturalNumber getNaturalNumber(Sequence rep) {
        NaturalNumber toRep = (NaturalNumber) this.currState.newInstance();
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
                BinaryTree<T> currBT = (BinaryTree<T>) this.currState
                        .newInstance();
                if (args[i] != null) {
                    currBT.assemble(args[i],
                            (BinaryTree<T>) this.currState.newInstance(),
                            (BinaryTree<T>) this.currState.newInstance());
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
                BinaryTree<T> leftBT = (BinaryTree<T>) this.currState
                        .newInstance();
                BinaryTree<T> rightBT = (BinaryTree<T>) this.currState
                        .newInstance();
                if (currBTM.length() > 0) {
                    leftBT = currBTM.remove(0);
                }
                if (currBTM.length() > 0) {
                    rightBT = currBTM.remove(0);

                }

                T currElement = tempParentBT.root();
                BinaryTree<T> parentBT = (BinaryTree<T>) this.currState
                        .newInstance();
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
    public String toString() {
        return this.seq.toString();
    }
}

/**
 * Initial thought
 *
 * Generally, clients would only need one instance of Tesca inside their code.
 *
 * Construtor would have user be inputting in String value which would represent
 * a different OSU kernel implementation. Use enum to determine how the string
 * value would translate different kernels
 *
 * There would be static variables: cur, seq, mapping cur -> handles what OSU
 * kernel component it is seq -> The sequence represenation of what will be
 * returned to client mapping -> shows which OSU kernel components are supported
 *
 * x Would have a built in map which shows which kernels are supported by this
 * component.
 *
 * I want the component to initially support
 */