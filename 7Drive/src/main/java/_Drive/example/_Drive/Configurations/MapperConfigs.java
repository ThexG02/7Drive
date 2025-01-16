package _Drive.example._Drive.Configurations;
import org.locationtech.jts.geom.GeometryFactory;

import _Drive.example._Drive.Dto.PointDto;
import _Drive.example._Drive.Utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MapperConfigs {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(PointDto.class, Point.class).setConverter(mappingContext -> {
            PointDto pointDto = mappingContext.getSource();
            return GeometryUtil.CreatePoint(pointDto);
        });

        mapper.typeMap(Point.class, PointDto.class).setConverter(mappingContext -> {
            Point point = mappingContext.getSource();
            double[] coordinates ={
                    point.getX(),
                    point.getY()
            };
            return  new PointDto(coordinates);
        });
        return  mapper;
}

}