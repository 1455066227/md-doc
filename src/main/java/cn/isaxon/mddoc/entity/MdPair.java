package cn.isaxon.mddoc.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p></p>
 * Created at 2025/3/10 22:07
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@Getter
@Setter
public class MdPair<K, V> {
    private K k;

    private V v;
    public MdPair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public static <K, V> MdPair<K, V> of(K k, V v) {
        return new MdPair<>(k, v);
    }
}
