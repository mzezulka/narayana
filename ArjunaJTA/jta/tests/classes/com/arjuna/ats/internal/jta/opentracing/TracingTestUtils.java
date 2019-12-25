package com.arjuna.ats.internal.jta.opentracing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.RollbackException;
import javax.transaction.TransactionManager;

import org.assertj.core.api.ListAssert;

import com.hp.mwtests.ts.jta.common.FailureXAResource;
import com.hp.mwtests.ts.jta.common.XACreator;

import io.narayana.tracing.names.SpanName;
import io.opentracing.mock.MockSpan;

public class TracingTestUtils {


    /**
     *  Basic commit intermediated by JTA which makes use of two XAResources.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        10       3         "Enlistment"
     *        1        11       3         "Enlistment"
     *        2         3       2         "XAResource Enlistments"
     *        3         5       4         "Branch Prepare"
     *        4         6       4         "Branch Prepare"
     *        5         4       2         "Global Prepare"
     *        6         8       7         "Branch Commit"
     *        7         9       7         "Branch Commit"
     *        8         7       2         "Global Commit"
     *        9         2       0         "Transaction"
     *  Please note that specific ids of spans may vary based on the mock implementation
     *  of the tracer, the important bit is the relations between spans. This holds true
     *  for any other test.
     */
    static void jtaTwoPhaseCommit(TransactionManager tm) throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.commit();
    }

    /**
     *  User initiated JTA abort, making use of two XAResources.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        6        5         "Enlistment"
     *        1        7        5         "Enlistment"
     *        2        5        4         "XAResource Enlistments"
     *        3        9        8         "Branch Rollback"
     *        4       10        8         "Branch Rollback"
     *        5        8        4         "Global Abort - User Initiated"
     *        6        4        0         "Transaction"
     */
    static void jtaUserRollback(TransactionManager tm) throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.rollback();
    }

    /*
     * Make use of existing failing XAResources and force the JTA transaction to fail in the prepare phase.
     *
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        10       3         "Enlistment"
     *        1        11       3         "Enlistment"
     *        2         3       2         "XAResource Enlistments"
     *        3         5       4         "Branch Prepare"
     *        4         6       4         "Branch Prepare"
     *        5         4       2         "Global Prepare"
     *        6         8       7         "Branch Rollback"
     *        7         9       7         "Branch Rollback"
     *        8         7       2         "Global Abort"
     *        9         2       0         "Transaction"
     */
    static void jtaPrepareResFail(TransactionManager tm) throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(new FailureXAResource(FailureXAResource.FailLocation.prepare));
        try {
            tm.commit();
        } catch (RollbackException re) {
            Class<?> cl = javax.transaction.xa.XAException.class;
            if(re.getSuppressed().length < 1) {
                throw new RuntimeException("Expected suppressed exceptions (especially XAException) but got none.");
            }
            for(Throwable t : re.getSuppressed()) {
                if(t.getClass().equals(cl)) {
                    // ok, we've found the suppressed exception corresponding to XAResource prepare fail
                    return;
                }
            }
            throw new RuntimeException("Did not find expected suppressed exception of type " + cl, re);
        }
    }

    static List<String> operationEnumsToStrings(SpanName... ops) {
        return Arrays.asList(ops).stream().map(s -> s.toString()).collect(Collectors.toList());
    }

    static List<String> spansToOperationStrings(List<MockSpan> spans) {
        return spans.stream().map(s -> s.operationName()).collect(Collectors.toList());
    }
    /*
     * Retrieve the root span which must always sit at the very end of the collection (because
     * spans are reported in a postorder fashion.
     */
    static MockSpan getRootSpanFrom(List<MockSpan> spans) {
        return spans.get(spans.size()-1);
    }

    // AssertJ extension for easier manipulation with opentracing Spans
    static class SpanListAssert extends ListAssert<Long> {
        public SpanListAssert(List<? extends Long> actual) {
            super(actual);
        }

        public ListAssert<Long> haveParent(MockSpan span) {
            return super.containsOnly(span.context().spanId());
        }
    }

    static SpanListAssert assertThatSpans(MockSpan... spans) {
        return new SpanListAssert(Arrays.asList(spans).stream().map(s -> s.parentId()).collect(Collectors.toList()));
    }
}
