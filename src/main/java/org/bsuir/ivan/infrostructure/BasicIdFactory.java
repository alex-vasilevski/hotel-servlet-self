package org.bsuir.ivan.infrostructure;

import java.util.List;

public class BasicIdFactory implements IdFactory{

    private final UriTokenizer tokenizer = (UriTokenizer) WebApplicationContext.getBean(BasicUriTokenizer.class);

    @Override
    public Long createId(String stringId) {
        List<String> tokens = tokenizer.tokenize(stringId);
        String s = tokens.get(tokens.size() - 1);
        return Long.parseLong(s);
    }
}
