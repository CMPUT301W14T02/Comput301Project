package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.view.SView;

/**
 * https://github.com/abramhindle/FillerCreepForAndroid/blob/master/src/es/softwareprocess/fillercreep/FModel.java 6 April 2014
 *
 * @param <V>
 */

@SuppressWarnings("rawtypes")
public class SModel<V extends SView> {
    private ArrayList<V> views;

    public SModel() {
        views = new ArrayList<V>();
    }

    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    public void deleteView(V view) {
        views.remove(view);
    }

    @SuppressWarnings("unchecked")
	public void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}