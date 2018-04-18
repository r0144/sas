package com.sas.interview.map;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;


@RestController
@RequestMapping("/map")
public class MapController {

    private Map<String, String> map = new HashMap();


    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Resource> create(@RequestBody Entry entry) throws Exception {
        validateKeyRequired(entry.getKey());
        String val = map.put(entry.getKey(), entry.getVal());
        ValResource resource = new ValResource(val);
        addCreateLinks(resource, entry);
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    @RequestMapping(value="{key}")
    public ResponseEntity<Resource> read(@PathVariable String key) throws Exception {
        validateKeyExists(key);
        String val = map.get(key);
        ValResource resource = new ValResource(val);
        addReadLinks(resource, new Entry(key, val));
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<Resource> readAll() throws Exception {
        MapResource resource = new MapResource(map);
        addReadAllLinks(resource, new Entry(null, null));
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Resource> update(@RequestBody Entry entry) throws Exception {
        validateKeyExists(entry.getKey());
        String val = map.replace(entry.getKey(), entry.getVal());
        ValResource resource = new ValResource(val);
        addUpdateLinks(resource, entry);
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="{key}")
    public ResponseEntity<Resource> delete(@PathVariable String key) throws Exception {
        validateKeyExists(key);
        String val = map.remove(key);
        ValResource resource = new ValResource(val);
        addDeleteLinks(resource, new Entry(key, null));
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    private void addCreateLinks(Resource resource, Entry entry) throws Exception {
        resource.add(linkTo(methodOn(MapController.class).create(entry)).withSelfRel());
        resource.add(linkTo(methodOn(MapController.class).read(entry.getKey())).withRel("read"));
        resource.add(linkTo(methodOn(MapController.class).readAll()).withRel("readAll"));
        resource.add(linkTo(methodOn(MapController.class).update(entry)).withRel("update"));
        resource.add(linkTo(methodOn(MapController.class).delete(entry.getKey())).withRel("delete"));
    }

    private void addReadLinks(Resource resource, Entry entry) throws Exception {
        resource.add(linkTo(methodOn(MapController.class).create(entry)).withRel("create"));
        resource.add(linkTo(methodOn(MapController.class).read(entry.getKey())).withSelfRel());
        resource.add(linkTo(methodOn(MapController.class).readAll()).withRel("readAll"));
        resource.add(linkTo(methodOn(MapController.class).update(entry)).withRel("update"));
        resource.add(linkTo(methodOn(MapController.class).delete(entry.getKey())).withRel("delete"));
   }

    private void addReadAllLinks(Resource resource, Entry entry) throws Exception {
        resource.add(linkTo(methodOn(MapController.class).create(entry)).withRel("create"));
        resource.add(linkTo(methodOn(MapController.class).readAll()).withSelfRel());
        resource.add(linkTo(methodOn(MapController.class).update(entry)).withRel("update"));
    }

    private void addUpdateLinks(Resource resource, Entry entry) throws Exception {
        resource.add(linkTo(methodOn(MapController.class).create(entry)).withRel("create"));
        resource.add(linkTo(methodOn(MapController.class).read(entry.getKey())).withRel("read"));
        resource.add(linkTo(methodOn(MapController.class).readAll()).withRel("readAll"));
        resource.add(linkTo(methodOn(MapController.class).update(entry)).withSelfRel());
        resource.add(linkTo(methodOn(MapController.class).delete(entry.getKey())).withRel("delete"));
    }

    private void addDeleteLinks(Resource resource, Entry entry) throws Exception {
        resource.add(linkTo(methodOn(MapController.class).create(entry)).withRel("create"));
        resource.add(linkTo(methodOn(MapController.class).readAll()).withRel("readAll"));
        resource.add(linkTo(methodOn(MapController.class).update(entry)).withRel("update"));
        resource.add(linkTo(methodOn(MapController.class).delete(entry.getKey())).withSelfRel());
    }

    private void validateKeyRequired(String key) throws Exception {
        if (key == null) {
            throw new Exception("key is required");
        }
    }

    private void validateKeyExists(String key) throws Exception {
        if (map.get(key) == null) {
            throw new Exception("key " + key + " not found in map");
        }
    }

}