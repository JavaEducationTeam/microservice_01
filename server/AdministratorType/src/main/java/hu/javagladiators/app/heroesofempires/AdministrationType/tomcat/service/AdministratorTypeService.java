/*
 */
package hu.javagladiators.app.heroesofempires.AdministrationType.tomcat.service;

import hu.javagladiators.app.hero.model.AdministrationType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author krisztian
 */
@Component
public class AdministratorTypeService {
    private static List<AdministrationType> data = new ArrayList<>();

    static {
        for(byte i=0;i<Byte.MAX_VALUE;i++)
        data.add(new AdministrationType(i));
    }
    
    public int getSize(){return data.size();}
    
    public List<AdministrationType> getAdministratorTypes(int offset, int limit){
        return data.subList(offset, offset+limit);
    }
    
    public AdministrationType getAdministratorType(byte pValue) throws NullPointerException{
        for(AdministrationType a: data)
            if(a.getPrioritization() == pValue)
                return a;
        throw new NullPointerException("Not exists:"+pValue);
    }
    

}
