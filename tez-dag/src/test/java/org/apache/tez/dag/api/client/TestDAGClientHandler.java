package org.apache.tez.dag.api.client;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.event.Dispatcher;
import org.apache.hadoop.yarn.event.Event;
import org.apache.hadoop.yarn.event.EventHandler;
import org.apache.tez.client.PreWarmContext;
import org.apache.tez.client.TezSessionStatus;
import org.apache.tez.dag.api.TezException;
import org.apache.tez.dag.api.records.DAGProtos.DAGPlan;
import org.apache.tez.dag.app.AppContext;
import org.apache.tez.dag.app.DAGAppMaster;
import org.apache.tez.dag.app.DAGAppMasterState;
import org.apache.tez.dag.app.dag.DAG;
import org.apache.tez.dag.app.dag.event.DAGEvent;
import org.apache.tez.dag.app.dag.event.DAGEventType;
import org.apache.tez.dag.records.TezDAGID;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.util.collections.Sets;


public class TestDAGClientHandler {
  
  @Test(timeout = 1000)
  @SuppressWarnings("unchecked")
  public void testDAGClientHandler() throws TezException {

    TezDAGID mockTezDAGId = mock(TezDAGID.class);
    when(mockTezDAGId.getId()).thenReturn(1);
    when(mockTezDAGId.toString()).thenReturn("dag_9999_0001_1");

    DAG mockDAG = mock(DAG.class);
    when(mockDAG.getID()).thenReturn(mockTezDAGId);
    DAGStatusBuilder mockDagStatusBuilder = mock(DAGStatusBuilder.class);
    when(mockDAG.getDAGStatus(anySetOf(StatusGetOpts.class))).thenReturn(
        mockDagStatusBuilder);
    VertexStatusBuilder mockVertexStatusBuilder =
        mock(VertexStatusBuilder.class);
    when(mockDAG.getVertexStatus(anyString(), anySetOf(StatusGetOpts.class)))
        .thenReturn(mockVertexStatusBuilder);

    DAGAppMaster mockDagAM = mock(DAGAppMaster.class);
    AppContext mockAppContext = mock(AppContext.class);
    when(mockDagAM.getContext()).thenReturn(mockAppContext);
    when(mockDagAM.getContext().getCurrentDAG()).thenReturn(mockDAG);

    DAGClientHandler dagClientHandler = new DAGClientHandler(mockDagAM);

    // getAllDAGs()
    assertEquals(1, dagClientHandler.getAllDAGs().size());
    assertEquals("dag_9999_0001_1", dagClientHandler.getAllDAGs().get(0));

    // getDAGStatus
    try {
      dagClientHandler.getDAGStatus("dag_9999_0001_2", Sets.newSet(StatusGetOpts.GET_COUNTERS));
      fail("should not come here");
    } catch (TezException e) {
      assertTrue(e.getMessage().contains("Unknown dagId"));
    }
    DAGStatus dagStatus = dagClientHandler.getDAGStatus("dag_9999_0001_1", 
        Sets.newSet(StatusGetOpts.GET_COUNTERS));
    assertEquals(mockDagStatusBuilder, dagStatus);

    // getVertexStatus
    try {
      dagClientHandler.getVertexStatus("dag_9999_0001_2", "v1", Sets.newSet(StatusGetOpts.GET_COUNTERS));
      fail("should not come here");
    } catch (TezException e) {
      assertTrue(e.getMessage().contains("Unknown dagId"));
    }
    VertexStatus vertexStatus = dagClientHandler.getVertexStatus("dag_9999_0001_1", "v1",
        Sets.newSet(StatusGetOpts.GET_COUNTERS));
    assertEquals(mockVertexStatusBuilder, vertexStatus);
    
    
    // getSessionStatus
    when(mockDagAM.isSession()).thenReturn(false);
    try{
      dagClientHandler.getSessionStatus();
      fail("should not come here");
    }catch(TezException e){
      assertEquals("Unsupported operation as AM not running in session mode", e.getMessage());
    }
    when(mockDagAM.isSession()).thenReturn(true);
    when(mockDagAM.getState()).thenReturn(DAGAppMasterState.INITED);
    assertEquals(TezSessionStatus.INITIALIZING, dagClientHandler.getSessionStatus());
    when(mockDagAM.getState()).thenReturn(DAGAppMasterState.ERROR);
    assertEquals(TezSessionStatus.SHUTDOWN, dagClientHandler.getSessionStatus());
    
    
    // startPreWarmContainers
    PreWarmContext mockPreWarnContext = mock(PreWarmContext.class);
    dagClientHandler.preWarmContainers(mockPreWarnContext);
    verify(mockDagAM).startPreWarmContainers(mockPreWarnContext);
    
    // tryKillDAG
    try{
      dagClientHandler.tryKillDAG("dag_9999_0001_2");
      fail("should not come here");
    }catch(TezException e){
      assertTrue(e.getMessage().contains("Unknown dagId"));
    }
    dagClientHandler.tryKillDAG("dag_9999_0001_1");
    ArgumentCaptor<DAG> eventCaptor = ArgumentCaptor.forClass(DAG.class);
    verify(mockDagAM, times(1)).tryKillDAG(eventCaptor.capture());
    assertEquals(1, eventCaptor.getAllValues().size());
    assertTrue(eventCaptor.getAllValues().get(0) instanceof DAG);
    assertEquals("dag_9999_0001_1",  ((DAG)eventCaptor.getAllValues().get(0)).getID().toString());

    // submitDAG
    DAGPlan dagPlan = DAGPlan.getDefaultInstance();
    Map<String,LocalResource> localResources = new HashMap<String, LocalResource>();
    dagClientHandler.submitDAG(dagPlan, localResources);
    verify(mockDagAM).submitDAGToAppMaster(dagPlan, localResources);
    
    // shutdown
    dagClientHandler.shutdownAM();
    verify(mockDagAM).shutdownTezAM();
  }
  
}