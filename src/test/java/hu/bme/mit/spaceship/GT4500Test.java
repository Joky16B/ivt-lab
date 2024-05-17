package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore primary;
  private TorpedoStore secondary;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_All_Both_Failure(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Second_Empty(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_Both_Empty(){
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    
    
    // Act
    boolean first = ship.fireTorpedo(FiringMode.SINGLE);

    when(primary.isEmpty()).thenReturn(true);
    boolean second = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, first);
    assertEquals(false, second);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
    
  }

  @Test
  public void fireTorpedo_Single_Twice_Second_Empty(){
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    
    
    // Act
    boolean first = ship.fireTorpedo(FiringMode.SINGLE);
    boolean second = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, first);
    assertEquals(true, second);
    verify(primary, times(2)).fire(1);
    verify(secondary, times(0)).fire(1);
    
  }

  @Test
  public void fireTorpedo_Single_Twice_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    
    // Act
    boolean first = ship.fireTorpedo(FiringMode.SINGLE);
    boolean second = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, first);
    assertEquals(true, second);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Empty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_First_Empty(){
    // Arrange
    when(secondary.fire(1)).thenReturn(true);
    when(primary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
    
  }

  @Test
  public void fireTorpedo_Single_First_Empty(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

}
