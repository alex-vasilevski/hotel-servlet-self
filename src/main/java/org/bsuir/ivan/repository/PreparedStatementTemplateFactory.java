package org.bsuir.ivan.repository;

import java.sql.PreparedStatement;
import java.util.Map;

public class PreparedStatementTemplateFactory {

    //TODO
    private Map<PreparedStatementTemplate, String> templateStringMap ;

    public String getPreparedStatement(PreparedStatementTemplate template){
         return templateStringMap.get(template);
    }
}
