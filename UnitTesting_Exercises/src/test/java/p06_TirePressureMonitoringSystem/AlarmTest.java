package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTest {
    private static final double LOW_PRESSURE_VALUE=16.0;
    private static final double NORMAL_PRESSURE_VALUE=19.0;
    private static final double HIGH_PRESSURE_VALUE=23.0;
    private Alarm alarm;
    private Sensor sensor;
    @Before
    public  void prepare() {
        sensor=Mockito.mock(Sensor.class);
        alarm=new Alarm(sensor);
    }
    @Test
    public void testAlarmIsOffWhenPressureIsCorrect() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE_VALUE);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }
    @Test
    public void testAlarmIsOnWhenPressureUnderNeeded() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_VALUE);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }
    @Test
    public void testAlarmIsOnWhenPressureOverNeeded() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_VALUE);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

}