package components.StuffingGenerator;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.standard.Standard;

public class StuffingGenerator1<T> extends StuffingGeneratorSecondary {

    Standard currState;
    Sequence seq;

    public void createNewRep() {
        this.seq = new Sequence1L<T>();
        this.currState = (Standard) this.seq.newInstance();
    }

    public void createNewRep(Standard rep) {
        this.seq = new Sequence1L<T>();
        this.currState = (Standard) rep.newInstance();
    }

    public StuffingGenerator1() {
        this.createNewRep();
    }

    public StuffingGenerator1(Standard rep) {
        this.createNewRep(rep);
    }

    @Override
    public void add(int pos, Object x) {
        this.seq.add(pos, x);
    }

    @Override
    public Object remove(int pos) {
        return this.seq.remove(pos);
    }

    @Override
    public int length() {
        return this.seq.length();
    }

    @Override
    public void setOutputType(Standard type) {
        this.currState = (Standard) type.newInstance();

    }

    @Override
    public Standard getOutputType() {
        return (Standard) this.currState.newInstance();
    }

    @Override
    public Sequence currRep() {
        Sequence dummySeq = (Sequence) this.seq.newInstance();
        for (Object x : this.seq) {
            dummySeq.add(dummySeq.length(), x);
        }
        return dummySeq;
    }

    @Override
    public void clear() {
        this.createNewRep(this.currState);
    }

    @Override
    public Object newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void transferFrom(Object arg0) {
        StuffingGenerator1<T> localSource = (StuffingGenerator1) arg0;
        this.currState = localSource.currState;
        this.seq = localSource.seq;
        localSource.createNewRep();
    }

}
