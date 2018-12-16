package gui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Region;

public class ViewFactory {
	private static ViewFactory instance;

	private Map<String, Region> regionMap;

	public static ViewFactory getInstance () {
		if (ViewFactory.instance == null) {
			ViewFactory.instance = new ViewFactory ();
		}
		return ViewFactory.instance;
	}

	private ViewFactory() {
		this.regionMap = new HashMap<String, Region>();
	}

	public Region getRegion(String viewName) {
		Region region;
		region = this.regionMap.getOrDefault(viewName, null);
		if (region == null) {
			String msg = String.format("No view found with name '%s'", viewName);
			System.out.println(msg);
		}		
		return region;
	}

	public void addRegion(String name, Region region) {
		this.regionMap.put(name, region);
	}
}
