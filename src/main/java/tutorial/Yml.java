package tutorial;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


public class Yml{

	public Map<String, Object> getYml() {
		Yaml yaml = new Yaml();
    	InputStream inputStream = PlanSpecs.class.getResourceAsStream("demo.yaml");
    	
		Map<String, Object> obj = yaml.load(inputStream);
    	return obj;
	}
}