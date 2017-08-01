package net.tf.repository;

import net.tf.model.Unit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Transactional
@Repository
public class UnitRepositoryImpl implements UnitRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Unit getInitialState(Long initialStateHash) {
        String parameterName = "stateHash";

        try {
            Unit unit = (Unit) entityManager.createQuery("select u from " + Unit.class.getSimpleName() + " u where " + parameterName + " = " + (Long)initialStateHash).getSingleResult();
            return unit;
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public Collection<Unit> getUnitHistory(Long serialNumber) {
        String parameterName = "serialNumber";

        List<Unit> unitHistory = (List<Unit>) entityManager.createQuery("select e from " + Unit.class.getSimpleName() + " e where " + parameterName + " = " + (Long)serialNumber)
                .getResultList();

        return unitHistory;
    }

    @Override
    public Unit insertNewEntry(Unit unit) {

        Unit persistedUnit = entityManager.merge(unit);
        return persistedUnit;

    }

}
