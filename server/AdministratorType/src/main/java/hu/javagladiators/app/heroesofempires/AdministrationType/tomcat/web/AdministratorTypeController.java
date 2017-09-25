/*
 */
package hu.javagladiators.app.heroesofempires.AdministrationType.tomcat.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import hu.javagladiators.app.hero.model.AdministrationType;
import hu.javagladiators.app.hero.model.Items;
import hu.javagladiators.app.heroesofempires.AdministrationType.tomcat.service.AdministratorTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author krisztian
 */
@RestController
@RequestMapping("/adminlevel/")
public class AdministratorTypeController {
 
    @Autowired
    private AdministratorTypeService service;
        
    @RequestMapping(value = "{offset}/{limit}", method=RequestMethod.GET)
    public HttpEntity<Items<AdministrationType>> get(
            @PathVariable(value = "offset") int offset,
            @PathVariable(value = "limit") int limit
    ){
        List<AdministrationType> resData = service.getAdministratorTypes(offset,limit);
        int aPage = (offset/limit) +1;
        Items res = new Items(offset, limit, aPage, service.getSize(), resData);

        if(aPage > 1)
            res.add(
                linkTo(
                    methodOn(AdministratorTypeController.class).get((offset-limit>0)?offset-limit:0,limit)
                )
                .withRel("prev")        
            );
        
        if(aPage < service.getSize()/limit)
            res.add(
                linkTo(
                    methodOn(AdministratorTypeController.class).get(offset+limit,limit)
                )
                .withRel("next")        
            );
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{num}",method=RequestMethod.GET)
    @ResponseBody
    public AdministrationType get(@PathVariable(value = "num") byte pValue){
        return service.getAdministratorType(pValue);
    }
    
    
}
