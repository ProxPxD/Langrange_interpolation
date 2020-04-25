package com.company;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.function.DoubleBinaryOperator;

public class Vec {

    private Vector<Double> vec;

    Vec(){
        vec = new Vector<Double>();
    }

    Vec(int max){
        vec = new Vector<Double>(max);
        setSize(max);
    }

    Vec(Vec copy){
        vec = new Vector<Double>(copy.size());
        for(int i = 0; i < copy.size(); i++){
            this.set(i, copy.get(i));
        }
    }

    public void setSize(int size){
        vec.setSize(size);
    }

    public int size(){
        return vec.size();
    }

    public Double get(int i){
        return vec.get(i);
    }

    public void set(int i, Double elem){
        if(i < 0) {
            return;
        }

        if(i >= size()){
            setSize(i+1);
        }
        vec.set(i, elem);
    }

    public Vec performOperation(Vec vec2, DoubleBinaryOperator op){
        if(vec2.size() == size()){
            Vec result = new Vec(size());
            for(int i = 0; i< vec2.size(); i++ ){
                result.set(i, op.applyAsDouble(get(i), vec2.get(i)));
            }
            return result;
        } else {
            System.out.println("Zły rozmiar!");
            return new Vec(this);
        }
    }

    public void setAll(Double value){
        for(int i = 0; i < size(); i++){
            vec.set(i, value);
        }
    }


    public Vec eliminate(int index){
        Vec result = new Vec(size() -1);
        int waiter = 0;
        for(int i = 0; i < size(); i++){
            if( i == index){
                waiter++;
            } else {
                result.set(i - waiter, get(i));
            }
        }
        return result;
    }

    public Double product(Integer ... args){

        ArrayList<Integer> arguments = new ArrayList<Integer>(Arrays.asList(args));
        Double val = 1.0;
        for(int i = 0; i < size(); i++){
            if(!arguments.contains(i)){
                val *= get(i);
            }
        }
        return val;

    }

    public Vec add(Vec vec2){
        return performOperation(vec2, (v1, v2) -> v1 + v2);
    }

    public Vec subtract(Vec vec2){
        return performOperation(vec2, (v1, v2) -> v1 - v2);
    }

    public Vec multiply(Vec vec2){
        return performOperation(vec2, (v1, v2) -> v1 * v2);
    }

    public void show(){
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        String vecString = "[";
        for(int i = 0; i < this.size(); i++ ){
            vecString += this.get(i) + ", ";
        }
        return vecString + "]";

    }

}
