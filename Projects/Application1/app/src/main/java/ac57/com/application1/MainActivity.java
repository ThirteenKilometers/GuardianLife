package ac57.com.application1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.attr.id;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       init();
    }

    private void init() {
        List<Student> list=new ArrayList<>();
        list.add(new Student("张萨安",1));
        list.add(new Student("李四",4));
        list.add(new Student("网安",7));
        list.add(new Student("撒村",3));
        list.add(new Student("丁俊晖",9));
        list.add(new Student("长发",19));
        Collections.sort(list);

        for(Student stu :list){
            System.out.print(stu);
        }
    }


}
