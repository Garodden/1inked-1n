package com.example.oneinkedoneproject.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class test {
    @Test
    public void failtest(){
        Assertions.assertThat(1).isEqualTo(2);
    }

}
