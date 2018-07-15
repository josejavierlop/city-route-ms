package es.jjlop.calculationserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonResourceLoaderTest {

    private JsonResourceLoader component;

    @Test
    public void loadFromJSONResource() throws IOException {
        ResponseVO<List<RouteVO>> response = JsonResourceLoader.loadFromJSONResource("routes-backup.json", JsonResourceLoaderTest.class);
        Assert.assertThat(response.getResults().size(), is(4));
    }
}