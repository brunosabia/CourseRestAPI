package br.com.erudio.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static<O, D> D parseObject(O origin, Class<D> destination) { //recebe um VO da origem e do destino 
		return mapper.map(origin, destination); //conversão do VO de origem para o OBJ destino
	}
	
	public static<O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) { //recebe uma lista de VOs
		List<D> destinationObjects = new ArrayList<D>();  //criando a lista de objetos convertidos
		for(Object o : origin) {
			destinationObjects.add(mapper.map(o, destination)); //populando a lista de objetos
		}
		return destinationObjects;
	}
}
