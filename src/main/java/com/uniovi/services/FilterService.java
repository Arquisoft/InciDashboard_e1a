package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Notification;
import com.uniovi.filter.Filter;

@Service
public class FilterService {

	private Map<String, List<Filter>> fieldFilters = new HashMap<>();

	@Autowired
	NotificationService notificationService;

	public void filterFields(Incidence incidence) {
		for (String key : incidence.getPropertyMap().keySet()) {
			compare(incidence, incidence.getPropertyMap().get(key), fieldFilters.get(key.trim()));
		}
	}
	

	public void addFieldFilter(Filter filter) {
		if (!fieldFilters.containsKey(filter.getProperty()))
			fieldFilters.put(filter.getProperty(), new ArrayList<>());
		fieldFilters.get(filter.getProperty()).add(filter);

	}

	private void compare(Incidence incidence, String value, List<Filter> filters) {
		if (filters != null) {
			Notification notification = null;
			for (Filter filter : filters) {
				if (filter.filter(value)) {
					if (notification == null)
						notification = new Notification(incidence);
					notification.addFilter(filter);
				}
			}

			if (notification != null)
				notificationService.addNotification(notification);
		}
	}
}
