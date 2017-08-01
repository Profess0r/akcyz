package net.tf.service;

import net.tf.model.Unit;
import net.tf.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@Service
public class UnitServiceBean implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Unit getUnitInfo(Long initialStateHash) {

        Unit initialStateUnit = unitRepository.getInitialState(initialStateHash);

        Collection<Unit> unitHistory = unitRepository.getUnitHistory(initialStateUnit.getSerialNumber());

        Unit lastEntry = Collections.max(unitHistory, new Comparator<Unit>() {
            @Override
            public int compare(Unit unit1, Unit unit2) {
                return unit1.getOperationDate().compareTo(unit2.getOperationDate());
            }
        });

        lastEntry.setProduceDate(initialStateUnit.getOperationDate());
        lastEntry.setProducePlace(initialStateUnit.getOperationPlace());
        return lastEntry;

    }

    @Override
    public Unit produce(Unit unit) {

        if (unitRepository.getUnitHistory(unit.getSerialNumber()).size() > 0) {
            return null;
        }

        unit.setOperationDate(LocalDateTime.now());
        unit.setOperationName("produce");

        unit.setStateHash(calculateHash(unit.getSerialNumber(), unit.getOperationName(), unit.getOperationPlace(), unit.getOperationDate()));

        System.out.println(unit);

        return unitRepository.insertNewEntry(unit);
    }

    @Override
    public Unit move(Long initialStateHash, Unit unit) {
        Unit storedUnit = getUnitInfo(initialStateHash);
        if (storedUnit == null) {
            return null;
        }

        if (storedUnit.getOperationPlace().equals(unit.getOperationPlace())) {
            return null;
        }

        unit.setOperationDate(LocalDateTime.now());
        unit.setOperationName("move");

        unit.setStateHash(calculateHash(unit.getSerialNumber(), unit.getOperationName(), unit.getOperationPlace(), unit.getOperationDate()));

        return unitRepository.insertNewEntry(unit);
    }

    @Override
    public Unit sell(Long initialStateHash, Unit unit) {
        Unit storedUnit = getUnitInfo(initialStateHash);

        if (storedUnit.getOperationName().equals("sell")) {
            return null;
        }

        unit.setOperationDate(LocalDateTime.now());
        unit.setOperationName("sell");

        unit.setStateHash(calculateHash(unit.getSerialNumber(), unit.getOperationName(), unit.getOperationPlace(), unit.getOperationDate()));

        return unitRepository.insertNewEntry(unit);
    }




    private Long calculateHash(Long serialNumber, String operationName, String operationPlace, LocalDateTime operationDate) {

        Long result = Long.valueOf(serialNumber.hashCode());
        result = 31 * result + operationDate.hashCode();
        result = 31 * result + operationPlace.hashCode();
        result = 31 * result + operationName.hashCode();
        return result;
    }

}
