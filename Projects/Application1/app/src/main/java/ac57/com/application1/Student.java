package ac57.com.application1;

import android.support.annotation.NonNull;

import static android.R.attr.id;

/**
 * <br/>
 * Author： lzhs <br/>
 * DataTime： 2017/9/24  17:09<br/>
 * E-mail： 1050629507@qq.com
 */

public class Student implements  Comparable<Student> {
    String name;
    int id;
    int old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }


    @Override
    public int compareTo(@NonNull Student o) {
        if(o.id>id) return 1;
        else  if (o.id<id){return -1;}
        else {return  0;}
    }
}
