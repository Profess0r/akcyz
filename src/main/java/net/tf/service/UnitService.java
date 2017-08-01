package net.tf.service;

import net.tf.model.Unit;

import java.util.Collection;

public interface UnitService {

    Unit getUnitInfo(Long initialStateHash);

    Unit produce(Unit unit);

    Unit move(Long initialStateHash, Unit unit);

    Unit sell(Long initialStateHash, Unit unit);
}
