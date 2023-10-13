package mem.mrponeryea.bank.model.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {
    T getId();
}
