package org.bsuir.ivan.infrostructure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BasicUriTokenizer implements UriTokenizer{

    private UriTokenizer tokenizer = (UriTokenizer) WebApplicationContext.getBean(BasicUriTokenizer.class);

    @Override
    public List<String> tokenize(String target) {
        StringTokenizer tokenizer = new StringTokenizer(target, "/", false);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()){
            tokens.add(tokenizer.nextToken());
        }

        return tokens;
    }
}
