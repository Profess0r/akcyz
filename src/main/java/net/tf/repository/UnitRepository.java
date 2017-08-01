package net.tf.repository;

import net.tf.model.Unit;

import java.util.Collection;

public interface UnitRepository {

    Unit getInitialState(Long initialStateHash);

    Collection<Unit> getUnitHistory(Long serialNumber);

    Unit insertNewEntry(Unit unit);

}
