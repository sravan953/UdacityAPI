package com.biryanistudio.udacityapi.Generics;

import java.util.List;

/**
 * Created by Sravan on 20-Jul-16.
 */
public interface IGenericUpdateUI<T> {
    void newData(List<T> data);
}
