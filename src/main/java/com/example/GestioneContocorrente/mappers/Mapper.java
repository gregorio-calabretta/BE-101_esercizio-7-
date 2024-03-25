package com.example.GestioneContocorrente.mappers;

import java.util.LinkedList;
import java.util.List;


public interface Mapper<T,S> {
     S  map(T t);
     default List<S> mapAll(List<T> objs) {
          var result = new LinkedList<S>();

          for (T entity : objs) {
               result.add(map(entity));
          }

          return result;
     }
}
