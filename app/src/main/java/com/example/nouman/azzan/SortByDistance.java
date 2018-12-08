package com.example.nouman.azzan;

import java.util.Comparator;


public class SortByDistance implements Comparator<MosqueNTime>
{
    public int compare(MosqueNTime a, MosqueNTime b)
    {
        return a.getDistance() - b.getDistance();
    }
}
