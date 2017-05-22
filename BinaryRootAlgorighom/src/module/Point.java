package module;

/**
 * Created by Administrator on 2017/5/21.
 */
public class Point {
    private String name;

    public Point(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Point)obj).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }
}
