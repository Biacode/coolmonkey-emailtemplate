package com.sfl.coolmonkey.emailtemplate.service.helper.conversion;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/1/15
 * Time: 6:48 PM
 */
@Component
@SuppressWarnings({
        "pmd:NcssMethodCount",
        "squid:S138",
})
@Qualifier("serviceMapperFacade")
public class ServiceMapperFacadeFactory extends AbstractFactoryBean<MapperFacade> {

    //region Constants
    //endregion

    //region Dependencies
    //endregion

    //region Constructors
    public ServiceMapperFacadeFactory() {
    }
    //endregion

    //region Public methods
    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    public MapperFacade createInstance() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return mapperFactory.getMapperFacade();
    }
    //endregion

    //region Utility methods
    //endregion
}
