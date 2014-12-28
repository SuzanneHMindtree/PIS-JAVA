
package com.junfenglu.properties.manager;

import com.junfenglu.properties.connectionbean.ConnectionConfigBean;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the PropertiesManager
 * @author Junfeng Lu
 */
public class PropertiesManagerTest {

    private PropertiesManager pm;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        pm = new PropertiesManager();
    }

    /**
     * Test for writeText method of PropertiesManager class
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Test
    public void testWriteText() throws FileNotFoundException, IOException {
        ConnectionConfigBean connectionConfig1 = new ConnectionConfigBean("jdbc:mysql://localhost", "3306", "hospitaldb", "root", "lu614000");
        pm.writeTxtProperties("", "TestTextProps", connectionConfig1);

        ConnectionConfigBean connectionConfig2 = pm.loadTextProperties("", "TestTextProps");

        assertEquals("The two beans do not match", connectionConfig1, connectionConfig2);
        System.out.println("what came back:"+connectionConfig2.getDatabase());
    }

    /**
     * Test for the ReadJarText method of PropertiesManager class
     * @throws NullPointerException
     * @throws IOException 
     */
    @Test
    public void testReadJarText() throws NullPointerException, IOException {
        ConnectionConfigBean connectionConfig1 = new ConnectionConfigBean("jdbc:mysql://localhost", "3306", "hospitaldb", "root", "lu614000");

        ConnectionConfigBean connectionConfig2 = pm.loadJarTextProperties("jar.properties");

        assertEquals("The two beans do not match", connectionConfig1, connectionConfig2);
    }


}
