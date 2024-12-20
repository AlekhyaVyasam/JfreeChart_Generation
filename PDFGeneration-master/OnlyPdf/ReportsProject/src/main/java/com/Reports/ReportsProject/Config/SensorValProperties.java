package com.Reports.ReportsProject.Config;



import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "temperature.chart")
public class SensorValProperties {

    private String title;
    private int width;
    private int height;
    private String seriesColor;
    private String backgroundColor;
    private String xaxisDateFormat;

    // Correct boolean property for shapes visibility
    private boolean seriesShapesVisible;  // This will generate isSeriesShapesVisible()

    public String getXAxisDateFormat() {
        return xaxisDateFormat;
    }

    public void setXAxisDateFormat(String xaxisDateFormat) {
        this.xaxisDateFormat = xaxisDateFormat;
    }
}
