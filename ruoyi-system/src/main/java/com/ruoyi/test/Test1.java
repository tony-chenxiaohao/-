package com.ruoyi.test;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test1 {

    @Builder
    @Data
    @ToString
    public static class Stu {
        private Long id;
        private String name; // 注意这里的拼写错误，应该是 "name" 而不是 "anme"
    }


    public static void main(String[] args) {
        //新建一个方法里的内部类Stu,有id和name字段

        Stu stu = Stu.builder().id(1L).name(null).build();

        Optional.ofNullable(stu).ifPresent(v -> {
            v.setName(Optional.ofNullable(v.getName()).orElse("李四"));
            System.out.println(v.getName());
        });



//        Stu stu1= Stu.builder().id(2L).name("李四").build();
//       //将stu和stu1放入List<Stu>
//        List<Stu> list = new ArrayList<Stu>();
//        list.add(stu);
//        list.add(stu1);
//
//        // 将list转换成Map<Long, Object>  {1=Test1.Stu(id=1, name=张三), 2=Test1.Stu(id=2, name=李四)}
//        Map<Long, Object> map1 = list.stream()
//                .collect(Collectors.toMap(
//                        Stu::getId,
//                        Function.identity(), // 使用identity()来保持原始对象
//                        (existing, replacement) -> existing // 解决键冲突的情况
//                ));
//        System.out.println(map1);
//         //提取map1中所有的key成为一个List<Long>
//        List<Long> keyList = new ArrayList<>(map1.keySet());
//        System.out.println(keyList);
//        //提取map1中所有Object的id成为一个Set<Long>
////        List<Long> idList = map1.values().stream()
////                .map(obj -> ((Stu) obj).getId())
////                .collect(Collectors.toList());
//        // 如果您想打印一个新的Map，其键为id，值为name
//        Map<Long, String> newNameMap = map1.values().stream().collect(Collectors.toMap(v->((Stu) v).getId(), v->((Stu) v).getName()));
//        System.out.println(newNameMap);
////        System.out.println(idList);





    }
}
