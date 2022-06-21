package com.zpc.operations.pop.domain;

import com.zpc.operations.pop.paging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class ReportComparators {
    static Map<Key, Comparator<OutwardItemsDetailsReport>> map = new HashMap<>();

    static {
        map.put(new Key("tDate", Direction.asc), Comparator.comparing(OutwardItemsDetailsReport::gettDate));
        map.put(new Key("tDate", Direction.desc), Comparator.comparing(OutwardItemsDetailsReport::gettDate)
                .reversed());

        map.put(new Key("draweeBank", Direction.asc), Comparator.comparing(OutwardItemsDetailsReport::getDraweeBank));
        map.put(new Key("draweeBank", Direction.desc), Comparator.comparing(OutwardItemsDetailsReport::getDraweeBank)
                .reversed());

        map.put(new Key("receivingName", Direction.asc), Comparator.comparing(OutwardItemsDetailsReport::getReceivingName));
        map.put(new Key("receivingName", Direction.desc), Comparator.comparing(OutwardItemsDetailsReport::getReceivingName)
                .reversed());

        map.put(new Key("receivingAccountNo", Direction.asc), Comparator.comparing(OutwardItemsDetailsReport::getReceivingAccountNo));
        map.put(new Key("receivingAccountNo", Direction.desc), Comparator.comparing(OutwardItemsDetailsReport::getReceivingAccountNo)
                .reversed());

        map.put(new Key("seqNo", Direction.asc), Comparator.comparing(OutwardItemsDetailsReport::getSeqNo));
        map.put(new Key("seqNo", Direction.desc), Comparator.comparing(OutwardItemsDetailsReport::getSeqNo)
                .reversed());


    }

    private ReportComparators() {
    }

    public static Comparator<OutwardItemsDetailsReport> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }
}
