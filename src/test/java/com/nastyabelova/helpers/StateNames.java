package com.nastyabelova.helpers;

import java.util.Arrays;
import java.util.List;

public enum StateNames {

    UTTAR_PRADESH("Uttar Pradesh") {
        public List<String> namesCities() {
            return Arrays.asList("Agra", "Lucknow", "Merrut");
        }
    }, HARYANA("Haryana") {
        public List<String> namesCities() {
            return Arrays.asList("Karnal", "Panipat");
        }
    }, RAJASTHAN("Rajasthan") {
        public List<String> namesCities() {
            return Arrays.asList("Jaipur", "Jaiselmer");
        }
    },

    NCR_City("NCR") {
        public List<String> namesCities() {
            return Arrays.asList("Delhi", "Gurgaon", "Noida");
        }
    };

    private final String desc;

    StateNames(String desc) {
        this.desc = desc;
    }

    public abstract List namesCities();

    public String getDesc() {
        return desc;
    }
}
