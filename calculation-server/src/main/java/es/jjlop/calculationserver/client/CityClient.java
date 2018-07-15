package es.jjlop.calculationserver.client;

import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import es.jjlop.calculationserver.util.JsonResourceLoader;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@FeignClient(name = "city-server", fallbackFactory  = CityClient.CityClientFallbackFactory.class)
public interface CityClient {
    @RequestMapping(method = RequestMethod.GET, value = "/routes/{origin}")
    ResponseVO<List<RouteVO>> getRoutesByOrigin(@PathVariable("origin") String origin);

    @Component
    static class CityClientFallbackFactory implements FallbackFactory<CityClient> {
        @Override
        public CityClient create(Throwable cause) {
            return new CityClient() {
                @Override
                public ResponseVO<List<RouteVO>> getRoutesByOrigin(String origin) {
                    try {
                        ResponseVO response = JsonResourceLoader.loadFromJSONResource("routes-backup.json", this.getClass());
                        List<RouteVO> originRoutes = ((List<RouteVO>) response.getResults()).stream()
                                .filter(r -> r.getOrigin().equalsIgnoreCase(origin))
                                .collect(Collectors.toList());

                        return new ResponseVO<>(originRoutes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }
    }
}