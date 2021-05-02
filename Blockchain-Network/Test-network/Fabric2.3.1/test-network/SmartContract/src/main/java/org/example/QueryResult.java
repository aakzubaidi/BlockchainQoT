package org.example;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;
import com.owlike.genson.annotation.JsonProperty;

@DataType()
public class QueryResult {
    @Property()
    private final String key;
    @Property()
    private final Deltas record;


    public QueryResult(@JsonProperty("Key") final String key, @JsonProperty("Record") final Deltas record) {
        this.key = key;
        this.record = record;
    }

    public String getKey() {
        return key;
    }

    public Deltas getRecord() {
        return record;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        QueryResult other = (QueryResult) obj;

        Boolean recordsAreEquals = this.getRecord().equals(other.getRecord());
        Boolean keysAreEquals = this.getKey().equals(other.getKey());

        return recordsAreEquals && keysAreEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getKey(), this.getRecord());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [key=" + key + ", record="
                + record + "]";
    }


    }




