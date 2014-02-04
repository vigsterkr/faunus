package com.thinkaurelius.faunus.formats.titan.cassandra;

import com.thinkaurelius.faunus.FaunusGraph;
import com.thinkaurelius.faunus.formats.TitanOutputFormatTest;
import com.thinkaurelius.faunus.tinkerpop.gremlin.Imports;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.diskstorage.configuration.backend.CommonsConfiguration;
import com.thinkaurelius.titan.graphdb.configuration.GraphDatabaseConfiguration;
import org.apache.commons.configuration.BaseConfiguration;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class TitanCassandraOutputFormatTest extends TitanOutputFormatTest {

    private static TitanGraph startUpCassandra() throws Exception {
        BaseConfiguration configuration = new BaseConfiguration();
        configuration.setProperty("storage.backend", "embeddedcassandra");
        configuration.setProperty("storage.hostname", "localhost");
        configuration.setProperty("storage.cassandra-config-dir", TitanCassandraOutputFormat.class.getResource("cassandra.yaml").toString());
        configuration.setProperty("storage.db-cache", "false");
        GraphDatabaseConfiguration graphconfig = new GraphDatabaseConfiguration(new CommonsConfiguration(configuration));
        graphconfig.getBackend().clearStorage();
        return TitanFactory.open(configuration);
    }

    private static BaseConfiguration getConfiguration() throws Exception {
        BaseConfiguration configuration = new BaseConfiguration();
        configuration.setProperty("storage.backend", "cassandrathrift");
        configuration.setProperty("storage.hostname", "localhost");
        configuration.setProperty("storage.db-cache", "false");
        return configuration;
    }


    public void testInGremlinImports() {
        assertTrue(Imports.getImports().contains(TitanCassandraOutputFormat.class.getPackage().getName() + ".*"));
    }

    public void testBulkLoading() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        super.testBulkLoading(getConfiguration(), f1);
    }

    public void testBulkElementDeletions() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testBulkElementDeletions(getConfiguration(), f1, f2);
    }

    public void testFewElementDeletions() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testFewElementDeletions(getConfiguration(), f1, f2);
    }

    public void testBulkVertexPropertyDeletions() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testBulkVertexPropertyDeletions(getConfiguration(), f1, f2);
    }

    public void testBulkVertexPropertyUpdates() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testBulkVertexPropertyUpdates(getConfiguration(), f1, f2);
    }

    public void testBulkEdgeDerivations() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testBulkEdgeDerivations(getConfiguration(), f1, f2);
    }

    public void testBulkEdgePropertyUpdates() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testBulkEdgePropertyUpdates(getConfiguration(), f1, f2);
    }

    public void testUnidirectionEdges() throws Exception {
        startUpCassandra();
        FaunusGraph f1 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("graphson-cassandra.properties"));
        FaunusGraph f2 = createFaunusGraph(TitanCassandraOutputFormat.class.getResourceAsStream("cassandra-cassandra.properties"));
        super.testUnidirectionEdges(getConfiguration(), f1, f2);
    }

}
