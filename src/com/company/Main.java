package com.company;

import java.util.*;

public class Main {

    public interface CountMap <T> {
        // добавляет элемент в этот контейнер.
        void add(T t);

        //Возвращает количество добавлений данного элемента
        int getCount(T t);

        //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
        int remove(T t);

        //количество разных элементов
        int size();

        //Добавить все элементы из source в текущий контейнер, при совпадении ключей,     суммировать значения
        void addAll(CountMap<T> source);

        //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
        Map toMap();

        //Тот же самый контракт как и toMap(), только всю информацию записать в destination
        void toMap(Map destination);
    }
    public static class CountMapIml <T> implements CountMap<T> {

        private HashMap<T, Integer> hashMap = new HashMap<>();;
        //отображение ключ - объект, значение - количество вхождений объекта
        // добавляет элемент в этот контейнер.
        @Override
        public void add(T t) {
            //если переданный объект не содержится в коллекции добавляем с 1
            //содержится, найдем и добавим  к количеству 1
            if (!hashMap.containsKey(t)) {
                hashMap.put(t, 1);
            } else {
                hashMap.replace(t, hashMap.get(t) + 1);
            }
        }
        @Override
        public int getCount(T t) {
            //если содежится то возвращаем кол-во вхождений
             if (hashMap.containsKey(t)) {
                 return(hashMap.get(t));
            }
            else {
                return (0);
            }
         }
        @Override
        public int remove(T t) {
            //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
            if (hashMap.containsKey(t)) {
                 return(hashMap.remove(t));
             }
            else {
                return (0);
            }
        }
        @Override
        public int size() {
            //количество разных элементов
            return(hashMap.size());
        }
        @Override
        public  void addAll(CountMap<T> source) {
            //Добавить все элементы из source в текущий контейнер, при совпадении ключей,     суммировать значения
            Iterator<T> it = source.toMap().keySet().iterator();
            while (it.hasNext()) {
                 this.add(it.next());
            }
        };
        @Override
        public  Map toMap() {
            //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
            Map map=new HashMap();
            map=(Map) hashMap.clone();
            return(map);
        }
        @Override
        public void toMap(Map destination) {
            //Тот же самый контракт как и toMap(), только всю информацию записать в destination
            destination=this.toMap();
        }

    }
    public static class CollectionUtils {
        public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
            destination.addAll(source);
        }

        public static<T> List newArrayList(List<? extends T> source) {
            List<? extends T> list=new ArrayList<>();
            return(list);
        }

        public static<T> int indexOf(List<? extends T> source, T t) {
            return (source.indexOf(t));
        }

        public static<T> List limit(List<? extends T> source, int size) {
            //возвращаем часть коллекции
            if (source.size()+1<=size) {
                return(source.subList(0,size-1));
            }
            return(source);
        }

        public static<T> void add(List<? super T> source, T t) {
            source.add(t);
        }

        public static<T> void removeAll(List<? super T> removeFrom, List<? super T> c2) {
            removeFrom.removeAll(c2);
        }

        public static<T> boolean containsAll(List<? super T> c1, List<? super T> c2) {
            //true если первый лист содержит все элементы второго
            return c1.containsAll(c2);
        }

        public static<T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
            //true если первый лист содержит хотя-бы 1 второго
            if (!c1.isEmpty() && !c2.isEmpty()) {
                for (T t : c1) {
                    if(c2.contains(t)){
                        return true;
                    }
                }
            }
            return false;
        }

        public static <T extends Comparable<T>> List range(List<T> list, T min, T max) {
            //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
            // Элементы сравнивать через Comparable.
            // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
            List<T> dest = list.subList(0,0);//Создаем пустой список
            for (T t : list){
                if (t.compareTo(min) >= 0 && t.compareTo(max) <= 0){
                    dest.add(t);
                }
            }
            return dest;
        }

        public static <T> List range(List<T> list, T min, T max, Comparator<Object> comparator) {
            //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
            // Элементы сравнивать через Comparable.
            // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
            List<T> dest = list.subList(0,0);//Создаем пустой список
            for(T t : list){
                if(comparator.compare(t,min) >= 0 && comparator.compare(t,max) <= 0){
                    dest.add(t);
                }
            }
            return dest;
        }

    }


    public static void main(String[] args) {
	// write your code here
        System.out.println("Is working, yet");
        CountMap<Integer> map = new CountMapIml<>() ;
        CountMap<Integer> map2 = new CountMapIml<>() ;
        map2.add(2);
        map2.add(3);
        map2.add(10);

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

//        int count = map.getCount(5); // 2
//        System.out.println(count);
//        count = map.getCount(6); // 1
//        System.out.println(count);
//        count = map.getCount(10); // 3
//        System.out.println(count);

        map.addAll(map2);

//        count = map.remove(10); // 1
//        System.out.println(count);
//        count = map.getCount(10); // 3
//        System.out.println(count);
        HashMap newMap = new HashMap();
        map.toMap(newMap);
        System.out.println(newMap.size());

        List<Integer> b1 = new LinkedList<>(Arrays.asList(8,1,3,5,6, 4));
        CollectionUtils.add(b1,8);
        System.out.println("Test add: "+b1);


        List<Integer> b2 = new LinkedList<>(Arrays.asList(1,2,5,7,9));
        List<Integer> b3 = new LinkedList<>(Arrays.asList(0,2,4,6,8,10));
        CollectionUtils.addAll(b2, b3);
        System.out.println("Test addAll: "+b3);
    }


}
