package at.fhooe.mc.vis.task_2_1;

import java.io.Serializable;
import java.sql.Timestamp;

public class EnvData implements Serializable {
    private String mSensorName;
    private Timestamp mTimestamp;
    private Integer[] mSensorValue;
}
