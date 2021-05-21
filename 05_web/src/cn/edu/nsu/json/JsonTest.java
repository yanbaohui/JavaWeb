package cn.edu.nsu.json;

import cn.edu.nsu.pojo.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    @Test
    public void test1(){
        Person person = new Person(1,"我很帅");

        Gson gson = new Gson();

        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);

        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);
    }

    @Test
    public void test2(){
        List<Person> list = new ArrayList<>();
        list.add(new Person(1,"你"));
        list.add(new Person(2,"好"));
        list.add(new Person(3,"帅"));

        Gson gson = new Gson();

        System.out.println(gson.toJson(list));
        gson.fromJson(gson.toJson(list),new PersonListType().getType());
    }

    //map转为json字符串
    @Test
    public void test3(){
        Map<Integer,Person> map = new HashMap<>();
        map.put(1,new Person(1,"你"));
        map.put(2,new Person(1,"ni"));

        Gson gson = new Gson();
        String personJsonMap = gson.toJson(map);
        System.out.println(personJsonMap);

//        Map<Integer,Person> map1 = gson.fromJson(personJsonMap, new PersonMapType().getType());
        Map<Integer,Person> map1 = gson.fromJson(personJsonMap, new TypeToken<HashMap<Integer,Person>>(){}.getType());
        System.out.println(map1);
        Person p = map1.get(1);
        System.out.println(p);
    }


}
