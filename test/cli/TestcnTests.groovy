import grails.test.AbstractCliTestCase

class TestcnTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTestcn() {

        execute(["testcn"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
