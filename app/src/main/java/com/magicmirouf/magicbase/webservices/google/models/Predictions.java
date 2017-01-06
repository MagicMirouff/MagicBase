package com.magicmirouf.magicbase.webservices.google.models;

import java.util.List;

/**
 * Created by sylva on 11/08/2016.
 */
public class Predictions {

    private List<Prediction> predictions;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public class Prediction {
        public String id;
        public String place_id;
        public String description;
        public List<Term> terms;
        public String[] types;

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public List<Term> getTerms() {
            return terms;
        }
        public String getCity(){
            return terms.get(0).getValue();
        }
    }

    public class Term {
        private int offset;
        private String value;

        public int getOffset() {
            return offset;
        }

        public String getValue() {
            return value;
        }

    }

}
