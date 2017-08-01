package net.tf.web.api;

import net.tf.model.Unit;
import net.tf.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;

    @RequestMapping(
            value = "/api/units/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> getUnit(@PathVariable("id") Long id) {

        Unit unit = unitService.getUnitInfo(id);
        if (unit == null) {
            return new ResponseEntity<Unit>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Unit>(unit, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/units",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {

        if (unitService.produce(unit) == null) {
            return new ResponseEntity<Unit>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Unit>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/units/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> moveUnit(@PathVariable("id") Long id, @RequestBody Unit unit) {

        if (unitService.move(id, unit) == null) {
            return new ResponseEntity<Unit>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Unit>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/units/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> deleteUnit(@PathVariable("id") Long id, @RequestBody Unit unit) {

        if (unitService.sell(id, unit) == null) {
            return new ResponseEntity<Unit>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Unit>(HttpStatus.NO_CONTENT);
    }
}
