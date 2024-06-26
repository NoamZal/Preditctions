package Histogram.impl;

import Histogram.api.Histogram;
import execution.instance.enitty.EntityInstance;

import java.util.Map;

public class HistogramImpl implements Histogram {

    String guid;
    String date;
    Map<Integer, EntityInstance> entitiesInstances;
    Map<Object, Integer> histogramByProperty;

    public HistogramImpl(String guid, String date,  Map<Integer, EntityInstance> entitiesInstances){
        this.guid=guid;
        this.date =date;
        this.entitiesInstances=entitiesInstances;

    }

    @Override
    public String getGuid() {
        return guid;
    }

    @Override
    public String getSimulationTime() {
        return date;
    }
    @Override
    public void setSimulationTime(String simulationTime) {
        date = simulationTime;
    }
    @Override
    public void setGuid(String guid) {
        this.guid = guid;
    }
    @Override
    public Map<Integer, EntityInstance> getEntitiesInstances() {
        return entitiesInstances;
    }
    @Override
    public Map<Object, Integer> getHistogramByProperty() {
        return histogramByProperty;
    }

    @Override
    public void setHistogramByProperty(Map<Object, Integer> histogramByProperty) {
        this.histogramByProperty = histogramByProperty;
    }


}
