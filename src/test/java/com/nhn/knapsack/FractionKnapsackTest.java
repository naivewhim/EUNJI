/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nhn.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Greedy Algorithm 사용
 *
 * @author Eunji, Lim
 */
public class FractionKnapsackTest {
    private class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    @Test
    public void 가방의무게와_물건의가격과무게가주어졌을때_최대가치를구한다() {
        int capacity = 1000;

        int size = 10;
        List<Item> itemList = createRandomItemList(size);

        float maxValue = calcMaxValue(itemList, capacity);
        System.out.println("max value: " +  maxValue);
    }

    @Test
    public void 물건을_무게당가격순으로_내림차순한다() {
        int size = 10;
        List<Item> itemList = createRandomItemList(size);

        sortItemListOrderByDesc(itemList);

        for (int i = 0; i < size - 1; i++) {
            Assert.assertTrue(itemList.get(i).value >= itemList.get(i + 1).value);
        }
    }

    private float calcMaxValue(List<Item> itemList, int capacity) {
        float maxValue = 0;

        for (int i = 0; i < itemList.size(); i++) {
            Item currentItem = itemList.get(i);

            if (capacity > 0) {
                if (capacity >= currentItem.weight) {
                    capacity -= currentItem.weight;
                    maxValue += currentItem.value;

                } else {
                    capacity -= capacity;
                    maxValue += (currentItem.value / (float)currentItem.weight) * capacity;

                }
            } else {
                break;
            }
        }

        return maxValue;
    }

    private List<Item> sortItemListOrderByDesc(List<Item> itemList) {
        Collections.sort(itemList, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                if (item1.value < item2.value) {
                    return 1;
                }

                return -1;
            }
        });

        return itemList;
    }

    private List<Item> createRandomItemList(int size) {
        List<Item> itemList = new ArrayList<Item>();

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            itemList.add(new Item(random.nextInt(100), random.nextInt(100)));
        }

        return itemList;
    }

}
