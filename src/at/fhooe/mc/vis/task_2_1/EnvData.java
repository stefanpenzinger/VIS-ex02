package at.fhooe.mc.vis.task_2_1;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Random;
import java.util.Vector;

/**
 * EnvData class representing our sensors
 */
public class EnvData implements Serializable {

    /**
     * Enum of availabel sensor types
     */
    enum EnvDataTypes{ light, noise, air }

    /** Represents Sensor data */
    Vector<Integer> mData;
    /** Timestamp of the Sensor data */
    Timestamp mTimestamp;
    /** The Sensor type */
    EnvDataTypes mType;

    /**
     * initializes a new sensor
     *
     * @param _type (type of sensor)
     */
    EnvData(EnvDataTypes _type){
        mType = _type;
        mData = new Vector<>();
        mTimestamp = new Timestamp(System.currentTimeMillis());
        seedSensorData();
    }

    /**
     * generates random values to simulate the sensor data and stores them in mData Vector.
     */
    public void seedSensorData(){
        Random rand = new Random();
        mTimestamp = new Timestamp(System.currentTimeMillis());
        int values = (mType == EnvDataTypes.air) ? 3 : 1;
        for(int i = 0; i < values; i++){
            mData.add(rand.nextInt(100));
        }
    }

    /**
     * overrides toString() method
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder rv = new StringBuilder();
        rv.append("Timestamp: ").append(mTimestamp.toString()).append(" | ");
        rv.append("Type: ").append(mType.name()).append(" | ");
        rv.append("Data: ");
        for (int i : mData) {
            rv.append((i)).append(" ");
        }
        return rv.toString();
    }

}
