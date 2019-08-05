package com.arjuna.ats.arjuna.logging;

import java.lang.Integer;
import com.arjuna.ats.arjuna.common.Uid;
import java.io.Serializable;
import javax.annotation.Generated;
import java.lang.Throwable;
import java.lang.String;
import org.jboss.logging.Logger;

/**
 * Warning this class consists of generated code.
 */
@Generated(value = "org.jboss.logging.processor.generator.model.MessageLoggerImplementor", date = "2019-08-02T16:16:40+0200")
public class arjunaI18NLogger_$logger implements arjunaI18NLogger,Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FQCN = arjunaI18NLogger_$logger.class.getName();
    public arjunaI18NLogger_$logger(final Logger log) {
        this.log = log;
    }
    protected final Logger log;
    @Override
    public final void warn_ActivationRecord_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_ActivationRecord_1$str());
    }
    private static final String warn_ActivationRecord_1 = "ARJUNA012001: ActivationRecord::set_value() called illegally";
    protected String warn_ActivationRecord_1$str() {
        return warn_ActivationRecord_1;
    }
    @Override
    public final void warn_ActivationRecord_2(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_ActivationRecord_2$str(), arg0, arg1);
    }
    private static final String warn_ActivationRecord_2 = "ARJUNA012002: Invocation of ActivationRecord::restore_state for {0} inappropriate - ignored for {1}";
    protected String warn_ActivationRecord_2$str() {
        return warn_ActivationRecord_2;
    }
    @Override
    public final void warn_CadaverRecord_1(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_CadaverRecord_1$str(), arg0, arg1);
    }
    private static final String warn_CadaverRecord_1 = "ARJUNA012003: Attempted abort operation on deleted object id {0} of type {1} ignored";
    protected String warn_CadaverRecord_1$str() {
        return warn_CadaverRecord_1;
    }
    @Override
    public final void warn_DisposeRecord_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_DisposeRecord_2$str());
    }
    private static final String warn_DisposeRecord_2 = "ARJUNA012005: DisposeRecord::save_state - failed";
    protected String warn_DisposeRecord_2$str() {
        return warn_DisposeRecord_2;
    }
    @Override
    public final void warn_DisposeRecord_3() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_DisposeRecord_3$str());
    }
    private static final String warn_DisposeRecord_3 = "ARJUNA012006: DisposeRecord::save_state - no object store defined.";
    protected String warn_DisposeRecord_3$str() {
        return warn_DisposeRecord_3;
    }
    @Override
    public final void warn_DisposeRecord_5(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_DisposeRecord_5$str());
    }
    private static final String warn_DisposeRecord_5 = "ARJUNA012008: DisposeRecord::topLevelCommit - exception while deleting state";
    protected String warn_DisposeRecord_5$str() {
        return warn_DisposeRecord_5;
    }
    @Override
    public final void warn_PersistenceRecord_10() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_10$str());
    }
    private static final String warn_PersistenceRecord_10 = "ARJUNA012009: PersistenceRecord::restore_state: Failed to unpack object store type";
    protected String warn_PersistenceRecord_10$str() {
        return warn_PersistenceRecord_10;
    }
    @Override
    public final void warn_PersistenceRecord_14() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_14$str());
    }
    private static final String warn_PersistenceRecord_14 = "ARJUNA012011: PersistenceRecord::save_state - packing top level state failed";
    protected String warn_PersistenceRecord_14$str() {
        return warn_PersistenceRecord_14;
    }
    @Override
    public final void warn_PersistenceRecord_15() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_15$str());
    }
    private static final String warn_PersistenceRecord_15 = "ARJUNA012012: PersistenceRecord::save_state - failed";
    protected String warn_PersistenceRecord_15$str() {
        return warn_PersistenceRecord_15;
    }
    @Override
    public final void warn_PersistenceRecord_16() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_16$str());
    }
    private static final String warn_PersistenceRecord_16 = "ARJUNA012013: PersistenceRecord::save_state - no object store defined for object";
    protected String warn_PersistenceRecord_16$str() {
        return warn_PersistenceRecord_16;
    }
    @Override
    public final void warn_PersistenceRecord_19() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_19$str());
    }
    private static final String warn_PersistenceRecord_19 = "ARJUNA012015: PersistenceRecord::topLevelAbort() - Could not remove state from object store!";
    protected String warn_PersistenceRecord_19$str() {
        return warn_PersistenceRecord_19;
    }
    @Override
    public final void warn_PersistenceRecord_2(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_2$str(), arg0);
    }
    private static final String warn_PersistenceRecord_2 = "ARJUNA012016: PersistenceRecord::topLevelCommit - commit_state call failed for {0}";
    protected String warn_PersistenceRecord_2$str() {
        return warn_PersistenceRecord_2;
    }
    @Override
    public final void warn_PersistenceRecord_20(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_PersistenceRecord_20$str());
    }
    private static final String warn_PersistenceRecord_20 = "ARJUNA012017: PersistenceRecord::topLevelAbort() - Received ObjectStoreException";
    protected String warn_PersistenceRecord_20$str() {
        return warn_PersistenceRecord_20;
    }
    @Override
    public final void warn_PersistenceRecord_21(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_PersistenceRecord_21$str());
    }
    private static final String warn_PersistenceRecord_21 = "ARJUNA012018: PersistenceRecord.topLevelPrepare - write_uncommitted error";
    protected String warn_PersistenceRecord_21$str() {
        return warn_PersistenceRecord_21;
    }
    @Override
    public final void warn_PersistenceRecord_3() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_3$str());
    }
    private static final String warn_PersistenceRecord_3 = "ARJUNA012019: PersistenceRecord::topLevelCommit - no state to commit!";
    protected String warn_PersistenceRecord_3$str() {
        return warn_PersistenceRecord_3;
    }
    @Override
    public final void warn_PersistenceRecord_4(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_PersistenceRecord_4$str());
    }
    private static final String warn_PersistenceRecord_4 = "ARJUNA012020: PersistenceRecord::topLevelCommit - caught exception";
    protected String warn_PersistenceRecord_4$str() {
        return warn_PersistenceRecord_4;
    }
    @Override
    public final void warn_PersistenceRecord_5() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_5$str());
    }
    private static final String warn_PersistenceRecord_5 = "ARJUNA012021: PersistenceRecord::topLevelCommit - no object store specified!";
    protected String warn_PersistenceRecord_5$str() {
        return warn_PersistenceRecord_5;
    }
    @Override
    public final void warn_PersistenceRecord_6() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_6$str());
    }
    private static final String warn_PersistenceRecord_6 = "ARJUNA012022: PersistenceRecord::topLevelCommit - commit_state error";
    protected String warn_PersistenceRecord_6$str() {
        return warn_PersistenceRecord_6;
    }
    @Override
    public final void warn_PersistenceRecord_7() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_7$str());
    }
    private static final String warn_PersistenceRecord_7 = "ARJUNA012023: PersistenceRecord deactivate error, object probably already deactivated!";
    protected String warn_PersistenceRecord_7$str() {
        return warn_PersistenceRecord_7;
    }
    @Override
    public final void warn_PersistenceRecord_8() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_PersistenceRecord_8$str());
    }
    private static final String warn_PersistenceRecord_8 = "ARJUNA012024: PersistenceRecord.topLevelPrepare - setup error!";
    protected String warn_PersistenceRecord_8$str() {
        return warn_PersistenceRecord_8;
    }
    @Override
    public final void warn_RecoveryRecord_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_RecoveryRecord_1$str());
    }
    private static final String warn_RecoveryRecord_1 = "ARJUNA012025: RecoveryRecord::setValue not given OutputObjectState.";
    protected String warn_RecoveryRecord_1$str() {
        return warn_RecoveryRecord_1;
    }
    @Override
    public final void warn_RecoveryRecord_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_RecoveryRecord_2$str());
    }
    private static final String warn_RecoveryRecord_2 = "ARJUNA012026: RecoveryRecord::nestedAbort - restore_state on object failed!";
    protected String warn_RecoveryRecord_2$str() {
        return warn_RecoveryRecord_2;
    }
    @Override
    public final void warn_StateManager_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_1$str());
    }
    private static final String warn_StateManager_1 = "ARJUNA012027: LockManager::terminate() should be invoked in every destructor";
    protected String warn_StateManager_1$str() {
        return warn_StateManager_1;
    }
    @Override
    public final void warn_StateManager_10() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_10$str());
    }
    private static final String warn_StateManager_10 = "ARJUNA012028: StateManager::modified() invocation on an object whose state has not been restored - activating object";
    protected String warn_StateManager_10$str() {
        return warn_StateManager_10;
    }
    @Override
    public final void warn_StateManager_11(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_11$str(), arg0, arg1);
    }
    private static final String warn_StateManager_11 = "ARJUNA012029: Delete called on object with uid {0} and type {1} within atomic action.";
    protected String warn_StateManager_11$str() {
        return warn_StateManager_11;
    }
    @Override
    public final void warn_StateManager_12() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_12$str());
    }
    private static final String warn_StateManager_12 = "ARJUNA012030: StateManager.cleanup - could not save_state from terminate!";
    protected String warn_StateManager_12$str() {
        return warn_StateManager_12;
    }
    @Override
    public final void warn_StateManager_13() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_13$str());
    }
    private static final String warn_StateManager_13 = "ARJUNA012031: Attempt to use volatile store.";
    protected String warn_StateManager_13$str() {
        return warn_StateManager_13;
    }
    private static final String get_StateManager_14 = "ARJUNA012032: Volatile store not implemented!";
    protected String get_StateManager_14$str() {
        return get_StateManager_14;
    }
    @Override
    public final String get_StateManager_14() {
        return get_StateManager_14$str();
    }
    private static final String get_StateManager_15 = "ARJUNA012033: Invalid object state.";
    protected String get_StateManager_15$str() {
        return get_StateManager_15;
    }
    @Override
    public final String get_StateManager_15() {
        return get_StateManager_15$str();
    }
    @Override
    public final void warn_StateManager_2(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_2$str(), arg0, arg1);
    }
    private static final String warn_StateManager_2 = "ARJUNA012035: Activate of object with id = {0} and type {1} unexpectedly failed";
    protected String warn_StateManager_2$str() {
        return warn_StateManager_2;
    }
    @Override
    public final void warn_StateManager_3(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_StateManager_3$str());
    }
    private static final String warn_StateManager_3 = "ARJUNA012036: StateManager::deactivate - object store error";
    protected String warn_StateManager_3$str() {
        return warn_StateManager_3;
    }
    @Override
    public final void warn_StateManager_4() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_4$str());
    }
    private static final String warn_StateManager_4 = "ARJUNA012037: StateManager::deactivate - save_state error";
    protected String warn_StateManager_4$str() {
        return warn_StateManager_4;
    }
    @Override
    public final void warn_StateManager_6(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_6$str(), arg0);
    }
    private static final String warn_StateManager_6 = "ARJUNA012038: StateManager.destroy - failed to add abstract record to transaction {0}; check transaction status.";
    protected String warn_StateManager_6$str() {
        return warn_StateManager_6;
    }
    @Override
    public final void warn_StateManager_7(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_StateManager_7$str());
    }
    private static final String warn_StateManager_7 = "ARJUNA012039: StateManager.destroy - caught object store exception";
    protected String warn_StateManager_7$str() {
        return warn_StateManager_7;
    }
    @Override
    public final void warn_StateManager_8() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_8$str());
    }
    private static final String warn_StateManager_8 = "ARJUNA012040: StateManager.destroy - called on non-persistent or new object!";
    protected String warn_StateManager_8$str() {
        return warn_StateManager_8;
    }
    @Override
    public final void warn_StateManager_9() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_StateManager_9$str());
    }
    private static final String warn_StateManager_9 = "ARJUNA012041: StateManager.restore_state - could not find StateManager state in object state!";
    protected String warn_StateManager_9$str() {
        return warn_StateManager_9;
    }
    @Override
    public final void warn_common_Mutex_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_common_Mutex_2$str());
    }
    private static final String warn_common_Mutex_2 = "ARJUNA012043: Mutex.unlock - called by non-owning thread!";
    protected String warn_common_Mutex_2$str() {
        return warn_common_Mutex_2;
    }
    @Override
    public final void warn_common_Uid_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_common_Uid_1$str());
    }
    private static final String warn_common_Uid_1 = "ARJUNA012044: cannot get local host.";
    protected String warn_common_Uid_1$str() {
        return warn_common_Uid_1;
    }
    private static final String get_common_Uid_11 = "ARJUNA012046: Uid.Uid recreate constructor could not recreate Uid!";
    protected String get_common_Uid_11$str() {
        return get_common_Uid_11;
    }
    @Override
    public final String get_common_Uid_11() {
        return get_common_Uid_11$str();
    }
    private static final String get_common_Uid_2 = "ARJUNA012047: Uid.Uid string constructor could not create nullUid";
    protected String get_common_Uid_2$str() {
        return get_common_Uid_2;
    }
    @Override
    public final String get_common_Uid_2() {
        return get_common_Uid_2$str();
    }
    @Override
    public final void warn_common_Uid_3(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_common_Uid_3$str(), arg0);
    }
    private static final String warn_common_Uid_3 = "ARJUNA012048: Uid general parsing error: {0}";
    protected String warn_common_Uid_3$str() {
        return warn_common_Uid_3;
    }
    @Override
    public final void fatal_common_Uid_4(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, null, fatal_common_Uid_4$str(), arg0);
    }
    private static final String fatal_common_Uid_4 = "ARJUNA012049: Uid.Uid string constructor could not create nullUid for incorrect string: {0}";
    protected String fatal_common_Uid_4$str() {
        return fatal_common_Uid_4;
    }
    private static final String get_common_Uid_5 = "ARJUNA012050: Uid.Uid string constructor incorrect: {0}";
    protected String get_common_Uid_5$str() {
        return get_common_Uid_5;
    }
    @Override
    public final String get_common_Uid_5(final String arg0) {
        return java.text.MessageFormat.format(get_common_Uid_5$str(), arg0);
    }
    @Override
    public final void warn_common_Uid_6() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_common_Uid_6$str());
    }
    private static final String warn_common_Uid_6 = "ARJUNA012051: Uid.generateHash called for invalid Uid. Will ignore.";
    protected String warn_common_Uid_6$str() {
        return warn_common_Uid_6;
    }
    @Override
    public final void warn_common_Uid_bytes(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_common_Uid_bytes$str());
    }
    private static final String warn_common_Uid_bytes = "ARJUNA012055: Exception thrown creating Uid from bytes!";
    protected String warn_common_Uid_bytes$str() {
        return warn_common_Uid_bytes;
    }
    @Override
    public final void warn_common_Uid_getbytes(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_common_Uid_getbytes$str());
    }
    private static final String warn_common_Uid_getbytes = "ARJUNA012056: Exception thrown getting bytes!";
    protected String warn_common_Uid_getbytes$str() {
        return warn_common_Uid_getbytes;
    }
    @Override
    public final void warn_common_Uid_npe(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_common_Uid_npe$str(), arg0);
    }
    private static final String warn_common_Uid_npe = "ARJUNA012057: Uid.Uid string constructor {0} caught other throwable";
    protected String warn_common_Uid_npe$str() {
        return warn_common_Uid_npe;
    }
    @Override
    public final void warn_coordinator_AbstractRecord_npe(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_AbstractRecord_npe$str(), arg0);
    }
    private static final String warn_coordinator_AbstractRecord_npe = "ARJUNA012058: AbstractRecord.create {0} failed to find record.";
    protected String warn_coordinator_AbstractRecord_npe$str() {
        return warn_coordinator_AbstractRecord_npe;
    }
    private static final String get_coordinator_ActionHierarchy_1 = "ARJUNA012059: Memory exhausted.";
    protected String get_coordinator_ActionHierarchy_1$str() {
        return get_coordinator_ActionHierarchy_1;
    }
    @Override
    public final String get_coordinator_ActionHierarchy_1() {
        return get_coordinator_ActionHierarchy_1$str();
    }
    @Override
    public final void warn_coordinator_BasicAction_1(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_1$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_1 = "ARJUNA012060: Action nesting error - deletion of action id {0} invoked while child actions active";
    protected String warn_coordinator_BasicAction_1$str() {
        return warn_coordinator_BasicAction_1;
    }
    @Override
    public final void warn_coordinator_BasicAction_2(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_2$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_2 = "ARJUNA012061: Aborting child {0}";
    protected String warn_coordinator_BasicAction_2$str() {
        return warn_coordinator_BasicAction_2;
    }
    @Override
    public final void warn_coordinator_BasicAction_21(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_21$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_21 = "ARJUNA012062: BasicAction.restore_state - could not recover {0}";
    protected String warn_coordinator_BasicAction_21$str() {
        return warn_coordinator_BasicAction_21;
    }
    @Override
    public final void warn_coordinator_BasicAction_24() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_24$str());
    }
    private static final String warn_coordinator_BasicAction_24 = "ARJUNA012063: BasicAction.restore_state - error unpacking action status.";
    protected String warn_coordinator_BasicAction_24$str() {
        return warn_coordinator_BasicAction_24;
    }
    @Override
    public final void warn_coordinator_BasicAction_29(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_29$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_29 = "ARJUNA012065: BasicAction.Begin of action {0} ignored - incorrect invocation sequence {1}";
    protected String warn_coordinator_BasicAction_29$str() {
        return warn_coordinator_BasicAction_29;
    }
    @Override
    public final void warn_coordinator_BasicAction_3(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_3$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_3 = "ARJUNA012066: Destructor of still running action id {0} invoked - Aborting";
    protected String warn_coordinator_BasicAction_3$str() {
        return warn_coordinator_BasicAction_3;
    }
    @Override
    public final void warn_coordinator_BasicAction_30(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_30$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_30 = "ARJUNA012067: BasicAction.Begin of action {0} ignored - no parent and set as nested action!";
    protected String warn_coordinator_BasicAction_30$str() {
        return warn_coordinator_BasicAction_30;
    }
    @Override
    public final void warn_coordinator_BasicAction_31(final Uid arg0, final Uid arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_31$str(), arg0, arg1, arg2);
    }
    private static final String warn_coordinator_BasicAction_31 = "ARJUNA012068: BasicAction.Begin of action {0} ignored - parent action {1} is not running: {2}";
    protected String warn_coordinator_BasicAction_31$str() {
        return warn_coordinator_BasicAction_31;
    }
    @Override
    public final void warn_coordinator_BasicAction_33(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_33$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_33 = "ARJUNA012070: End called on non-running atomic action {0}";
    protected String warn_coordinator_BasicAction_33$str() {
        return warn_coordinator_BasicAction_33;
    }
    @Override
    public final void warn_coordinator_BasicAction_34(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_34$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_34 = "ARJUNA012071: End called on already committed atomic action {0}";
    protected String warn_coordinator_BasicAction_34$str() {
        return warn_coordinator_BasicAction_34;
    }
    @Override
    public final void warn_coordinator_BasicAction_35(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_35$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_35 = "ARJUNA012072: End called illegally on atomic action {0}";
    protected String warn_coordinator_BasicAction_35$str() {
        return warn_coordinator_BasicAction_35;
    }
    @Override
    public final void warn_coordinator_BasicAction_36(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_36$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_36 = "ARJUNA012073: BasicAction.End() - prepare phase of action-id {0} failed.";
    protected String warn_coordinator_BasicAction_36$str() {
        return warn_coordinator_BasicAction_36;
    }
    @Override
    public final void warn_coordinator_BasicAction_37(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_37$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_37 = "ARJUNA012074: Received heuristic: {0} .";
    protected String warn_coordinator_BasicAction_37$str() {
        return warn_coordinator_BasicAction_37;
    }
    @Override
    public final void warn_coordinator_BasicAction_38() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_38$str());
    }
    private static final String warn_coordinator_BasicAction_38 = "ARJUNA012075: Action Aborting";
    protected String warn_coordinator_BasicAction_38$str() {
        return warn_coordinator_BasicAction_38;
    }
    @Override
    public final void warn_coordinator_BasicAction_39(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_39$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_39 = "ARJUNA012076: Abort called on non-running atomic action {0}";
    protected String warn_coordinator_BasicAction_39$str() {
        return warn_coordinator_BasicAction_39;
    }
    @Override
    public final void warn_coordinator_BasicAction_40(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_40$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_40 = "ARJUNA012077: Abort called on already aborted atomic action {0}";
    protected String warn_coordinator_BasicAction_40$str() {
        return warn_coordinator_BasicAction_40;
    }
    @Override
    public final void warn_coordinator_BasicAction_41(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_41$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_41 = "ARJUNA012078: Abort called illegaly on atomic action {0}";
    protected String warn_coordinator_BasicAction_41$str() {
        return warn_coordinator_BasicAction_41;
    }
    @Override
    public final void warn_coordinator_BasicAction_42(final Uid arg0, final String arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_42$str(), arg0, arg1, arg2);
    }
    private static final String warn_coordinator_BasicAction_42 = "ARJUNA012079: BasicAction {0} - non-empty ( {1} ) pendingList {2}";
    protected String warn_coordinator_BasicAction_42$str() {
        return warn_coordinator_BasicAction_42;
    }
    @Override
    public final void warn_coordinator_BasicAction_43(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_43$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_43 = "ARJUNA012080: Transaction {0} marked as rollback only. Will abort.";
    protected String warn_coordinator_BasicAction_43$str() {
        return warn_coordinator_BasicAction_43;
    }
    @Override
    public final void warn_coordinator_BasicAction_44() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_44$str());
    }
    private static final String warn_coordinator_BasicAction_44 = "ARJUNA012081: Cannot force parent to rollback - no handle!";
    protected String warn_coordinator_BasicAction_44$str() {
        return warn_coordinator_BasicAction_44;
    }
    @Override
    public final void warn_coordinator_BasicAction_45(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_45$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_45 = "ARJUNA012082: BasicAction::prepare - creating intentions list failed for {0}";
    protected String warn_coordinator_BasicAction_45$str() {
        return warn_coordinator_BasicAction_45;
    }
    @Override
    public final void warn_coordinator_BasicAction_46(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_46$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_46 = "ARJUNA012083: BasicAction::prepare - intentions list write failed for {0}";
    protected String warn_coordinator_BasicAction_46$str() {
        return warn_coordinator_BasicAction_46;
    }
    @Override
    public final void warn_coordinator_BasicAction_47(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_47$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_47 = "ARJUNA012084: One-phase commit of action {0} received heuristic decision: {1}";
    protected String warn_coordinator_BasicAction_47$str() {
        return warn_coordinator_BasicAction_47;
    }
    @Override
    public final void fatal_coordinator_BasicAction_48() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, null, fatal_coordinator_BasicAction_48$str());
    }
    private static final String fatal_coordinator_BasicAction_48 = "ARJUNA012085: BasicAction.onePhaseCommit failed - no object store for atomic action state!";
    protected String fatal_coordinator_BasicAction_48$str() {
        return fatal_coordinator_BasicAction_48;
    }
    @Override
    public final void warn_coordinator_BasicAction_49(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_49$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_49 = "ARJUNA012086: Prepare phase of nested action {0} received inconsistent outcomes.";
    protected String warn_coordinator_BasicAction_49$str() {
        return warn_coordinator_BasicAction_49;
    }
    @Override
    public final void warn_coordinator_BasicAction_5(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_5$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_5 = "ARJUNA012087: Activate of atomic action with id {0} and type {1} unexpectedly failed, could not load state.";
    protected String warn_coordinator_BasicAction_5$str() {
        return warn_coordinator_BasicAction_5;
    }
    @Override
    public final void warn_coordinator_BasicAction_50(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_50$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_50 = "ARJUNA012088: Prepare phase of action {0} received heuristic decision: {1}";
    protected String warn_coordinator_BasicAction_50$str() {
        return warn_coordinator_BasicAction_50;
    }
    @Override
    public final void warn_coordinator_BasicAction_52(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_52$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_52 = "ARJUNA012089: Top-level abort of action {0} received heuristic decision: {1}";
    protected String warn_coordinator_BasicAction_52$str() {
        return warn_coordinator_BasicAction_52;
    }
    @Override
    public final void warn_coordinator_BasicAction_53(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_53$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_53 = "ARJUNA012090: Nested abort of action {0} received heuristic decision: {1}";
    protected String warn_coordinator_BasicAction_53$str() {
        return warn_coordinator_BasicAction_53;
    }
    @Override
    public final void warn_coordinator_BasicAction_54(final Uid arg0, final String arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_54$str(), arg0, arg1, arg2);
    }
    private static final String warn_coordinator_BasicAction_54 = "ARJUNA012091: Top-level abort of action {0} received {1} from {2}";
    protected String warn_coordinator_BasicAction_54$str() {
        return warn_coordinator_BasicAction_54;
    }
    @Override
    public final void warn_coordinator_BasicAction_55(final Uid arg0, final String arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_55$str(), arg0, arg1, arg2);
    }
    private static final String warn_coordinator_BasicAction_55 = "ARJUNA012092: Nested abort of action {0} received {1} from {2}";
    protected String warn_coordinator_BasicAction_55$str() {
        return warn_coordinator_BasicAction_55;
    }
    @Override
    public final void warn_coordinator_BasicAction_56(final Uid arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_56$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_56 = "ARJUNA012093: BasicAction.checkIsCurrent {0} - terminating non-current transaction: {1}";
    protected String warn_coordinator_BasicAction_56$str() {
        return warn_coordinator_BasicAction_56;
    }
    @Override
    public final void warn_coordinator_BasicAction_57(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_57$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_57 = "ARJUNA012094: Commit of action id {0} invoked while multiple threads active within it.";
    protected String warn_coordinator_BasicAction_57$str() {
        return warn_coordinator_BasicAction_57;
    }
    @Override
    public final void warn_coordinator_BasicAction_58(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_58$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_58 = "ARJUNA012095: Abort of action id {0} invoked while multiple threads active within it.";
    protected String warn_coordinator_BasicAction_58$str() {
        return warn_coordinator_BasicAction_58;
    }
    @Override
    public final void warn_coordinator_BasicAction_59(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_59$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_59 = "ARJUNA012096: Commit of action id {0} invoked while child actions active";
    protected String warn_coordinator_BasicAction_59$str() {
        return warn_coordinator_BasicAction_59;
    }
    @Override
    public final void warn_coordinator_BasicAction_5a(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_5a$str(), arg0, arg1);
    }
    private static final String warn_coordinator_BasicAction_5a = "ARJUNA012097: Deactivate of atomic action with id {0} and type {1} unexpectedly failed, could not save state.";
    protected String warn_coordinator_BasicAction_5a$str() {
        return warn_coordinator_BasicAction_5a;
    }
    @Override
    public final void warn_coordinator_BasicAction_60(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_60$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_60 = "ARJUNA012098: Abort of action id {0} invoked while child actions active";
    protected String warn_coordinator_BasicAction_60$str() {
        return warn_coordinator_BasicAction_60;
    }
    @Override
    public final void warn_coordinator_BasicAction_61(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_61$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_61 = "ARJUNA012099: Aborting child: {0}";
    protected String warn_coordinator_BasicAction_61$str() {
        return warn_coordinator_BasicAction_61;
    }
    @Override
    public final void warn_coordinator_BasicAction_62(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_62$str(), arg0);
    }
    private static final String warn_coordinator_BasicAction_62 = "ARJUNA012100: Now aborting self: {0}";
    protected String warn_coordinator_BasicAction_62$str() {
        return warn_coordinator_BasicAction_62;
    }
    @Override
    public final void warn_coordinator_BasicAction_64() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_64$str());
    }
    private static final String warn_coordinator_BasicAction_64 = "ARJUNA012101: BasicAction.updateState - Could not create ObjectState for failedList";
    protected String warn_coordinator_BasicAction_64$str() {
        return warn_coordinator_BasicAction_64;
    }
    @Override
    public final void warn_coordinator_BasicAction_65() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_65$str());
    }
    private static final String warn_coordinator_BasicAction_65 = "ARJUNA012102: BasicAction.End - Could not write failed list";
    protected String warn_coordinator_BasicAction_65$str() {
        return warn_coordinator_BasicAction_65;
    }
    @Override
    public final void warn_coordinator_BasicAction_68() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_BasicAction_68$str());
    }
    private static final String warn_coordinator_BasicAction_68 = "ARJUNA012103: (Internal) BasicAction.merge - record rejected";
    protected String warn_coordinator_BasicAction_68$str() {
        return warn_coordinator_BasicAction_68;
    }
    private static final String get_coordinator_BasicAction_69 = "ARJUNA012104: No object store for:";
    protected String get_coordinator_BasicAction_69$str() {
        return get_coordinator_BasicAction_69;
    }
    @Override
    public final String get_coordinator_BasicAction_69() {
        return get_coordinator_BasicAction_69$str();
    }
    @Override
    public final void warn_coordinator_BasicAction_70(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_coordinator_BasicAction_70$str());
    }
    private static final String warn_coordinator_BasicAction_70 = "ARJUNA012105: Could not remove intentions list:";
    protected String warn_coordinator_BasicAction_70$str() {
        return warn_coordinator_BasicAction_70;
    }
    @Override
    public final void warn_coordinator_CheckedAction_1(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_CheckedAction_1$str(), arg0, arg1);
    }
    private static final String warn_coordinator_CheckedAction_1 = "ARJUNA012107: CheckedAction::check - atomic action {0} commiting with {1} threads active!";
    protected String warn_coordinator_CheckedAction_1$str() {
        return warn_coordinator_CheckedAction_1;
    }
    @Override
    public final void warn_coordinator_CheckedAction_2(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_CheckedAction_2$str(), arg0, arg1);
    }
    private static final String warn_coordinator_CheckedAction_2 = "ARJUNA012108: CheckedAction::check - atomic action {0} aborting with {1} threads active!";
    protected String warn_coordinator_CheckedAction_2$str() {
        return warn_coordinator_CheckedAction_2;
    }
    private static final String get_coordinator_TransactionReaper_1 = "ARJUNA012109: TransactionReaper - attempting to insert an element that is already present.";
    protected String get_coordinator_TransactionReaper_1$str() {
        return get_coordinator_TransactionReaper_1;
    }
    @Override
    public final String get_coordinator_TransactionReaper_1() {
        return get_coordinator_TransactionReaper_1$str();
    }
    @Override
    public final void warn_coordinator_TransactionReaper_10(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_10$str(), arg0);
    }
    private static final String warn_coordinator_TransactionReaper_10 = "ARJUNA012110: TransactionReaper::check successfuly marked TX {0} as rollback only";
    protected String warn_coordinator_TransactionReaper_10$str() {
        return warn_coordinator_TransactionReaper_10;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_11(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_11$str(), arg0);
    }
    private static final String warn_coordinator_TransactionReaper_11 = "ARJUNA012111: TransactionReaper::check failed to mark TX {0}  as rollback only";
    protected String warn_coordinator_TransactionReaper_11$str() {
        return warn_coordinator_TransactionReaper_11;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_12(final Uid arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_coordinator_TransactionReaper_12$str(), arg0);
    }
    private static final String warn_coordinator_TransactionReaper_12 = "ARJUNA012112: TransactionReaper::check exception while marking TX {0} as rollback only";
    protected String warn_coordinator_TransactionReaper_12$str() {
        return warn_coordinator_TransactionReaper_12;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_13(final String arg0, final Uid arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_13$str(), arg0, arg1, arg2);
    }
    private static final String warn_coordinator_TransactionReaper_13 = "ARJUNA012113: TransactionReaper::doCancellations worker {0} missed interrupt when cancelling TX {1} -- exiting as zombie (zombie count decremented to {2})";
    protected String warn_coordinator_TransactionReaper_13$str() {
        return warn_coordinator_TransactionReaper_13;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_14(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_14$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_14 = "ARJUNA012114: TransactionReaper::doCancellations worker {0} successfuly marked TX {1} as rollback only";
    protected String warn_coordinator_TransactionReaper_14$str() {
        return warn_coordinator_TransactionReaper_14;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_15(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_15$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_15 = "ARJUNA012115: TransactionReaper::doCancellations worker {0} failed to mark TX {1}  as rollback only";
    protected String warn_coordinator_TransactionReaper_15$str() {
        return warn_coordinator_TransactionReaper_15;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_16(final String arg0, final Uid arg1, final Throwable arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg2, warn_coordinator_TransactionReaper_16$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_16 = "ARJUNA012116: TransactionReaper::doCancellations worker {0} exception while marking TX {1} as rollback only";
    protected String warn_coordinator_TransactionReaper_16$str() {
        return warn_coordinator_TransactionReaper_16;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_18(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_18$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_18 = "ARJUNA012117: TransactionReaper::check timeout for TX {0} in state  {1}";
    protected String warn_coordinator_TransactionReaper_18$str() {
        return warn_coordinator_TransactionReaper_18;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_19() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_19$str());
    }
    private static final String warn_coordinator_TransactionReaper_19 = "ARJUNA012118: TransactionReaper NORMAL mode is deprecated. Update config to use PERIODIC for equivalent behaviour.";
    protected String warn_coordinator_TransactionReaper_19$str() {
        return warn_coordinator_TransactionReaper_19;
    }
    @Override
    public final void error_coordinator_TransactionReaper_5(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.ERROR, null, error_coordinator_TransactionReaper_5$str(), arg0);
    }
    private static final String error_coordinator_TransactionReaper_5 = "ARJUNA012119: TransactionReaper::check worker zombie count {0} exceeds specified limit";
    protected String error_coordinator_TransactionReaper_5$str() {
        return error_coordinator_TransactionReaper_5;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_6(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_6$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_6 = "ARJUNA012120: TransactionReaper::check worker {0} not responding to interrupt when cancelling TX {1} -- worker marked as zombie and TX scheduled for mark-as-rollback";
    protected String warn_coordinator_TransactionReaper_6$str() {
        return warn_coordinator_TransactionReaper_6;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_7(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_7$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_7 = "ARJUNA012121: TransactionReaper::doCancellations worker {0} successfully canceled TX {1}";
    protected String warn_coordinator_TransactionReaper_7$str() {
        return warn_coordinator_TransactionReaper_7;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_8(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TransactionReaper_8$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_8 = "ARJUNA012122: TransactionReaper::doCancellations worker {0} failed to cancel TX {1} -- rescheduling for mark-as-rollback";
    protected String warn_coordinator_TransactionReaper_8$str() {
        return warn_coordinator_TransactionReaper_8;
    }
    @Override
    public final void warn_coordinator_TransactionReaper_9(final String arg0, final Uid arg1, final Throwable arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg2, warn_coordinator_TransactionReaper_9$str(), arg0, arg1);
    }
    private static final String warn_coordinator_TransactionReaper_9 = "ARJUNA012123: TransactionReaper::doCancellations worker {0} exception during cancel of TX {1} -- rescheduling for mark-as-rollback";
    protected String warn_coordinator_TransactionReaper_9$str() {
        return warn_coordinator_TransactionReaper_9;
    }
    @Override
    public final void warn_coordinator_TwoPhaseCoordinator_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TwoPhaseCoordinator_1$str());
    }
    private static final String warn_coordinator_TwoPhaseCoordinator_1 = "ARJUNA012124: TwoPhaseCoordinator.beforeCompletion - attempted rollback_only failed!";
    protected String warn_coordinator_TwoPhaseCoordinator_1$str() {
        return warn_coordinator_TwoPhaseCoordinator_1;
    }
    @Override
    public final void warn_coordinator_TwoPhaseCoordinator_2(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_coordinator_TwoPhaseCoordinator_2$str(), arg0);
    }
    private static final String warn_coordinator_TwoPhaseCoordinator_2 = "ARJUNA012125: TwoPhaseCoordinator.beforeCompletion - failed for {0}";
    protected String warn_coordinator_TwoPhaseCoordinator_2$str() {
        return warn_coordinator_TwoPhaseCoordinator_2;
    }
    @Override
    public final void warn_coordinator_TwoPhaseCoordinator_3() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TwoPhaseCoordinator_3$str());
    }
    private static final String warn_coordinator_TwoPhaseCoordinator_3 = "ARJUNA012126: TwoPhaseCoordinator.beforeCompletion TwoPhaseCoordinator.afterCompletion called on still running transaction!";
    protected String warn_coordinator_TwoPhaseCoordinator_3$str() {
        return warn_coordinator_TwoPhaseCoordinator_3;
    }
    @Override
    public final void warn_coordinator_TwoPhaseCoordinator_4(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TwoPhaseCoordinator_4$str(), arg0);
    }
    private static final String warn_coordinator_TwoPhaseCoordinator_4 = "ARJUNA012127: TwoPhaseCoordinator.afterCompletion - returned failure for {0}";
    protected String warn_coordinator_TwoPhaseCoordinator_4$str() {
        return warn_coordinator_TwoPhaseCoordinator_4;
    }
    @Override
    public final void warn_coordinator_TwoPhaseCoordinator_4a(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_coordinator_TwoPhaseCoordinator_4a$str(), arg0);
    }
    private static final String warn_coordinator_TwoPhaseCoordinator_4a = "ARJUNA012128: TwoPhaseCoordinator.afterCompletion - failed for {0} with exception";
    protected String warn_coordinator_TwoPhaseCoordinator_4a$str() {
        return warn_coordinator_TwoPhaseCoordinator_4a;
    }
    @Override
    public final void warn_coordinator_TwoPhaseCoordinator_4b(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_coordinator_TwoPhaseCoordinator_4b$str(), arg0);
    }
    private static final String warn_coordinator_TwoPhaseCoordinator_4b = "ARJUNA012129: TwoPhaseCoordinator.afterCompletion - failed for {0} with error";
    protected String warn_coordinator_TwoPhaseCoordinator_4b$str() {
        return warn_coordinator_TwoPhaseCoordinator_4b;
    }
    @Override
    public final void warn_coordinator_TxControl_1(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TxControl_1$str(), arg0);
    }
    private static final String warn_coordinator_TxControl_1 = "ARJUNA012130: Name of XA node not defined. Using {0}";
    protected String warn_coordinator_TxControl_1$str() {
        return warn_coordinator_TxControl_1;
    }
    @Override
    public final void warn_coordinator_TxControl_2(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TxControl_2$str(), arg0);
    }
    private static final String warn_coordinator_TxControl_2 = "ARJUNA012131: Supplied name of node is too long. Using {0}";
    protected String warn_coordinator_TxControl_2$str() {
        return warn_coordinator_TxControl_2;
    }
    @Override
    public final void warn_coordinator_TxControl_3(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_TxControl_3$str(), arg0);
    }
    private static final String warn_coordinator_TxControl_3 = "ARJUNA012132: Supplied name of node contains reserved character ''-''. Using {0}";
    protected String warn_coordinator_TxControl_3$str() {
        return warn_coordinator_TxControl_3;
    }
    private static final String get_StoreManager_invalidtype = "ARJUNA012135: Could not create Store type:";
    protected String get_StoreManager_invalidtype$str() {
        return get_StoreManager_invalidtype;
    }
    @Override
    public final String get_StoreManager_invalidtype() {
        return get_StoreManager_invalidtype$str();
    }
    @Override
    public final void warn_coordinator_norecordfound(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_norecordfound$str(), arg0);
    }
    private static final String warn_coordinator_norecordfound = "ARJUNA012136: Could not recreate abstract record {0}";
    protected String warn_coordinator_norecordfound$str() {
        return warn_coordinator_norecordfound;
    }
    @Override
    public final void warn_coordinator_notrunning() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_notrunning$str());
    }
    private static final String warn_coordinator_notrunning = "ARJUNA012137: Cannot begin new transaction as TM is disabled. Marking as rollback-only.";
    protected String warn_coordinator_notrunning$str() {
        return warn_coordinator_notrunning;
    }
    @Override
    public final void warn_coordinator_toolong(final Integer arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_coordinator_toolong$str(), arg0);
    }
    private static final String warn_coordinator_toolong = "ARJUNA012138: Node name cannot exceed {0} bytes!";
    protected String warn_coordinator_toolong$str() {
        return warn_coordinator_toolong;
    }
    @Override
    public final void warn_lastResource_disableWarning() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_lastResource_disableWarning$str());
    }
    private static final String warn_lastResource_disableWarning = "ARJUNA012139: You have chosen to disable the Multiple Last Resources warning. You will see it only once.";
    protected String warn_lastResource_disableWarning$str() {
        return warn_lastResource_disableWarning;
    }
    @Override
    public final void warn_lastResource_disallow(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_lastResource_disallow$str(), arg0, arg1);
    }
    private static final String warn_lastResource_disallow = "ARJUNA012140: Adding multiple last resources is disallowed. Trying to add {0}, but already have {1}";
    protected String warn_lastResource_disallow$str() {
        return warn_lastResource_disallow;
    }
    @Override
    public final void warn_lastResource_multipleWarning(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_lastResource_multipleWarning$str(), arg0);
    }
    private static final String warn_lastResource_multipleWarning = "ARJUNA012141: Multiple last resources have been added to the current transaction. This is transactionally unsafe and should not be relied upon. Current resource is {0}";
    protected String warn_lastResource_multipleWarning$str() {
        return warn_lastResource_multipleWarning;
    }
    @Override
    public final void warn_lastResource_startupWarning() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_lastResource_startupWarning$str());
    }
    private static final String warn_lastResource_startupWarning = "ARJUNA012142: You have chosen to enable multiple last resources in the transaction manager. This is transactionally unsafe and should not be relied upon.";
    protected String warn_lastResource_startupWarning$str() {
        return warn_lastResource_startupWarning;
    }
    @Override
    public final void warn_objectstore_ObjectStoreType_1(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ObjectStoreType_1$str(), arg0);
    }
    private static final String warn_objectstore_ObjectStoreType_1 = "ARJUNA012143: unknown store: {0}";
    protected String warn_objectstore_ObjectStoreType_1$str() {
        return warn_objectstore_ObjectStoreType_1;
    }
    private static final String get_objectstore_ObjectStoreType_2 = "ARJUNA012144: unknown store:";
    protected String get_objectstore_ObjectStoreType_2$str() {
        return get_objectstore_ObjectStoreType_2;
    }
    @Override
    public final String get_objectstore_ObjectStoreType_2() {
        return get_objectstore_ObjectStoreType_2$str();
    }
    @Override
    public final void warn_recovery_ActionStatusService_5(final Uid arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_recovery_ActionStatusService_5$str(), arg0);
    }
    private static final String warn_recovery_ActionStatusService_5 = "ARJUNA012146: ActionStatusService: searching for uid: {0}";
    protected String warn_recovery_ActionStatusService_5$str() {
        return warn_recovery_ActionStatusService_5;
    }
    @Override
    public final void info_recovery_ActionStatusService_1(final String arg0, final String arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_ActionStatusService_1$str(), arg0, arg1, arg2);
    }
    private static final String info_recovery_ActionStatusService_1 = "ARJUNA012147: transactionType: {0} uid: {1}   Status is {2}";
    protected String info_recovery_ActionStatusService_1$str() {
        return info_recovery_ActionStatusService_1;
    }
    @Override
    public final void warn_recovery_ActionStatusService_2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_ActionStatusService_2$str());
    }
    private static final String warn_recovery_ActionStatusService_2 = "ARJUNA012148: Other Exception";
    protected String warn_recovery_ActionStatusService_2$str() {
        return warn_recovery_ActionStatusService_2;
    }
    @Override
    public final void warn_recovery_ActionStatusService_3(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_ActionStatusService_3$str());
    }
    private static final String warn_recovery_ActionStatusService_3 = "ARJUNA012149: Exception retrieving action status";
    protected String warn_recovery_ActionStatusService_3$str() {
        return warn_recovery_ActionStatusService_3;
    }
    @Override
    public final void info_recovery_ActionStatusService_4(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_ActionStatusService_4$str(), arg0);
    }
    private static final String info_recovery_ActionStatusService_4 = "ARJUNA012150: matching Uid {0} found";
    protected String info_recovery_ActionStatusService_4$str() {
        return info_recovery_ActionStatusService_4;
    }
    @Override
    public final void warn_recovery_ActionStatusService_6(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_ActionStatusService_6$str());
    }
    private static final String warn_recovery_ActionStatusService_6 = "ARJUNA012151: Exception when accessing transaction store";
    protected String warn_recovery_ActionStatusService_6$str() {
        return warn_recovery_ActionStatusService_6;
    }
    @Override
    public final void warn_recovery_ActionStatusService_7() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_ActionStatusService_7$str());
    }
    private static final String warn_recovery_ActionStatusService_7 = "ARJUNA012152: Connection Lost to Recovery Manager";
    protected String warn_recovery_ActionStatusService_7$str() {
        return warn_recovery_ActionStatusService_7;
    }
    @Override
    public final void warn_recovery_RecoverAtomicAction_2(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_RecoverAtomicAction_2$str(), arg0);
    }
    private static final String warn_recovery_RecoverAtomicAction_2 = "ARJUNA012153: RecoverAtomicAction.replayPhase2: Unexpected status: {0}";
    protected String warn_recovery_RecoverAtomicAction_2$str() {
        return warn_recovery_RecoverAtomicAction_2;
    }
    @Override
    public final void warn_recovery_RecoverAtomicAction_4(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_RecoverAtomicAction_4$str(), arg0);
    }
    private static final String warn_recovery_RecoverAtomicAction_4 = "ARJUNA012154: RecoverAtomicAction: transaction {0} not activated, unable to replay phase 2 commit. Check state has not already been completed.";
    protected String warn_recovery_RecoverAtomicAction_4$str() {
        return warn_recovery_RecoverAtomicAction_4;
    }
    @Override
    public final void warn_recovery_RecoverAtomicAction_5(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_RecoverAtomicAction_5$str(), arg0);
    }
    private static final String warn_recovery_RecoverAtomicAction_5 = "ARJUNA012155: RecoverAtomicAction - tried to move failed activation log {0}";
    protected String warn_recovery_RecoverAtomicAction_5$str() {
        return warn_recovery_RecoverAtomicAction_5;
    }
    @Override
    public final void info_recovery_RecoveryManager_4(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_RecoveryManager_4$str(), arg0, arg1);
    }
    private static final String info_recovery_RecoveryManager_4 = "ARJUNA012159: Connected to recovery manager on {0}:{1}";
    protected String info_recovery_RecoveryManager_4$str() {
        return info_recovery_RecoveryManager_4;
    }
    @Override
    public final void warn_recovery_TransactionStatusConnectionManager_1(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_TransactionStatusConnectionManager_1$str());
    }
    private static final String warn_recovery_TransactionStatusConnectionManager_1 = "ARJUNA012161: Exception when accessing data store";
    protected String warn_recovery_TransactionStatusConnectionManager_1$str() {
        return warn_recovery_TransactionStatusConnectionManager_1;
    }
    @Override
    public final void warn_recovery_TransactionStatusConnectionManager_2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_TransactionStatusConnectionManager_2$str());
    }
    private static final String warn_recovery_TransactionStatusConnectionManager_2 = "ARJUNA012162: Object store exception";
    protected String warn_recovery_TransactionStatusConnectionManager_2$str() {
        return warn_recovery_TransactionStatusConnectionManager_2;
    }
    @Override
    public final void info_recovery_TransactionStatusManager_1(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_TransactionStatusManager_1$str(), arg0, arg1);
    }
    private static final String info_recovery_TransactionStatusManager_1 = "ARJUNA012163: Starting service {0} on port {1}";
    protected String info_recovery_TransactionStatusManager_1$str() {
        return info_recovery_TransactionStatusManager_1;
    }
    private static final String get_recovery_TransactionStatusManager_13 = "ARJUNA012167: Invalid host or port";
    protected String get_recovery_TransactionStatusManager_13$str() {
        return get_recovery_TransactionStatusManager_13;
    }
    @Override
    public final String get_recovery_TransactionStatusManager_13() {
        return get_recovery_TransactionStatusManager_13$str();
    }
    @Override
    public final void warn_recovery_TransactionStatusManager_14(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_TransactionStatusManager_14$str(), arg0, arg1);
    }
    private static final String warn_recovery_TransactionStatusManager_14 = "ARJUNA012168: Failed to create server socket on address {0} and port: {1}";
    protected String warn_recovery_TransactionStatusManager_14$str() {
        return warn_recovery_TransactionStatusManager_14;
    }
    @Override
    public final void warn_recovery_TransactionStatusManager_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_TransactionStatusManager_2$str());
    }
    private static final String warn_recovery_TransactionStatusManager_2 = "ARJUNA012169: Listener failed";
    protected String warn_recovery_TransactionStatusManager_2$str() {
        return warn_recovery_TransactionStatusManager_2;
    }
    @Override
    public final void info_recovery_TransactionStatusManager_3(final String arg0, final String arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_TransactionStatusManager_3$str(), arg0, arg1, arg2);
    }
    private static final String info_recovery_TransactionStatusManager_3 = "ARJUNA012170: TransactionStatusManager started on port {0} and host {1} with service {2}";
    protected String info_recovery_TransactionStatusManager_3$str() {
        return info_recovery_TransactionStatusManager_3;
    }
    @Override
    public final void warn_recovery_TransactionStatusManager_4(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_TransactionStatusManager_4$str(), arg0);
    }
    private static final String warn_recovery_TransactionStatusManager_4 = "ARJUNA012171: Failed to setup class for {0}";
    protected String warn_recovery_TransactionStatusManager_4$str() {
        return warn_recovery_TransactionStatusManager_4;
    }
    private static final String get_recovery_TransactionStatusManager_9 = "ARJUNA012176: Could not get unique port.";
    protected String get_recovery_TransactionStatusManager_9$str() {
        return get_recovery_TransactionStatusManager_9;
    }
    @Override
    public final String get_recovery_TransactionStatusManager_9() {
        return get_recovery_TransactionStatusManager_9$str();
    }
    private static final String get_state_InputBuffer_1 = "ARJUNA012177: com.arjuna.ats.arjuna.state.InputBuffer_1 - Invalid input buffer: byte.";
    protected String get_state_InputBuffer_1$str() {
        return get_state_InputBuffer_1;
    }
    @Override
    public final String get_state_InputBuffer_1() {
        return get_state_InputBuffer_1$str();
    }
    private static final String get_state_InputBuffer_10 = "ARJUNA012178: com.arjuna.ats.arjuna.state.InputBuffer_10 - Invalid input buffer: string.";
    protected String get_state_InputBuffer_10$str() {
        return get_state_InputBuffer_10;
    }
    @Override
    public final String get_state_InputBuffer_10() {
        return get_state_InputBuffer_10$str();
    }
    private static final String get_state_InputBuffer_11 = "ARJUNA012179: com.arjuna.ats.arjuna.state.InputBuffer_11 - Invalid from buffer";
    protected String get_state_InputBuffer_11$str() {
        return get_state_InputBuffer_11;
    }
    @Override
    public final String get_state_InputBuffer_11() {
        return get_state_InputBuffer_11$str();
    }
    private static final String get_state_InputBuffer_2 = "ARJUNA012180: com.arjuna.ats.arjuna.state.InputBuffer_2 - Invalid input buffer: bytes.";
    protected String get_state_InputBuffer_2$str() {
        return get_state_InputBuffer_2;
    }
    @Override
    public final String get_state_InputBuffer_2() {
        return get_state_InputBuffer_2$str();
    }
    private static final String get_state_InputBuffer_3 = "ARJUNA012181: com.arjuna.ats.arjuna.state.InputBuffer_3 - Invalid input buffer: boolean.";
    protected String get_state_InputBuffer_3$str() {
        return get_state_InputBuffer_3;
    }
    @Override
    public final String get_state_InputBuffer_3() {
        return get_state_InputBuffer_3$str();
    }
    private static final String get_state_InputBuffer_4 = "ARJUNA012182: com.arjuna.ats.arjuna.state.InputBuffer_4 - Invalid input buffer: char.";
    protected String get_state_InputBuffer_4$str() {
        return get_state_InputBuffer_4;
    }
    @Override
    public final String get_state_InputBuffer_4() {
        return get_state_InputBuffer_4$str();
    }
    private static final String get_state_InputBuffer_5 = "ARJUNA012183: com.arjuna.ats.arjuna.state.InputBuffer_5 - Invalid input buffer: short.";
    protected String get_state_InputBuffer_5$str() {
        return get_state_InputBuffer_5;
    }
    @Override
    public final String get_state_InputBuffer_5() {
        return get_state_InputBuffer_5$str();
    }
    private static final String get_state_InputBuffer_6 = "ARJUNA012184: com.arjuna.ats.arjuna.state.InputBuffer_6 - Invalid input buffer: int.";
    protected String get_state_InputBuffer_6$str() {
        return get_state_InputBuffer_6;
    }
    @Override
    public final String get_state_InputBuffer_6() {
        return get_state_InputBuffer_6$str();
    }
    private static final String get_state_InputBuffer_7 = "ARJUNA012185: com.arjuna.ats.arjuna.state.InputBuffer_7 - Invalid input buffer: long.";
    protected String get_state_InputBuffer_7$str() {
        return get_state_InputBuffer_7;
    }
    @Override
    public final String get_state_InputBuffer_7() {
        return get_state_InputBuffer_7$str();
    }
    private static final String get_state_InputBuffer_8 = "ARJUNA012186: com.arjuna.ats.arjuna.state.InputBuffer_8 - Invalid input buffer: float.";
    protected String get_state_InputBuffer_8$str() {
        return get_state_InputBuffer_8;
    }
    @Override
    public final String get_state_InputBuffer_8() {
        return get_state_InputBuffer_8$str();
    }
    private static final String get_state_InputBuffer_9 = "ARJUNA012187: com.arjuna.ats.arjuna.state.InputBuffer_9 - Invalid input buffer: double";
    protected String get_state_InputBuffer_9$str() {
        return get_state_InputBuffer_9;
    }
    @Override
    public final String get_state_InputBuffer_9() {
        return get_state_InputBuffer_9$str();
    }
    private static final String get_state_OutputBuffer_1 = "ARJUNA012188: com.arjuna.ats.arjuna.state.OutputBuffer_1 - Invalid input buffer: byte.";
    protected String get_state_OutputBuffer_1$str() {
        return get_state_OutputBuffer_1;
    }
    @Override
    public final String get_state_OutputBuffer_1() {
        return get_state_OutputBuffer_1$str();
    }
    private static final String get_state_OutputBuffer_10 = "ARJUNA012189: com.arjuna.ats.arjuna.state.OutputBuffer_10 - Invalid input buffer: string.";
    protected String get_state_OutputBuffer_10$str() {
        return get_state_OutputBuffer_10;
    }
    @Override
    public final String get_state_OutputBuffer_10() {
        return get_state_OutputBuffer_10$str();
    }
    private static final String get_state_OutputBuffer_11 = "ARJUNA012190: com.arjuna.ats.arjuna.state.OutputBuffer_11 - Invalid from buffer";
    protected String get_state_OutputBuffer_11$str() {
        return get_state_OutputBuffer_11;
    }
    @Override
    public final String get_state_OutputBuffer_11() {
        return get_state_OutputBuffer_11$str();
    }
    private static final String get_state_OutputBuffer_2 = "ARJUNA012191: com.arjuna.ats.arjuna.state.OutputBuffer_2 - Invalid input buffer: bytes.";
    protected String get_state_OutputBuffer_2$str() {
        return get_state_OutputBuffer_2;
    }
    @Override
    public final String get_state_OutputBuffer_2() {
        return get_state_OutputBuffer_2$str();
    }
    private static final String get_state_OutputBuffer_3 = "ARJUNA012192: com.arjuna.ats.arjuna.state.OutputBuffer_3 - Invalid input buffer: boolean.";
    protected String get_state_OutputBuffer_3$str() {
        return get_state_OutputBuffer_3;
    }
    @Override
    public final String get_state_OutputBuffer_3() {
        return get_state_OutputBuffer_3$str();
    }
    private static final String get_state_OutputBuffer_4 = "ARJUNA012193: com.arjuna.ats.arjuna.state.OutputBuffer_4 - Invalid input buffer: char.";
    protected String get_state_OutputBuffer_4$str() {
        return get_state_OutputBuffer_4;
    }
    @Override
    public final String get_state_OutputBuffer_4() {
        return get_state_OutputBuffer_4$str();
    }
    private static final String get_state_OutputBuffer_5 = "ARJUNA012194: com.arjuna.ats.arjuna.state.OutputBuffer_5 - Invalid input buffer: short.";
    protected String get_state_OutputBuffer_5$str() {
        return get_state_OutputBuffer_5;
    }
    @Override
    public final String get_state_OutputBuffer_5() {
        return get_state_OutputBuffer_5$str();
    }
    private static final String get_state_OutputBuffer_6 = "ARJUNA012195: com.arjuna.ats.arjuna.state.OutputBuffer_6 - Invalid input buffer: int.";
    protected String get_state_OutputBuffer_6$str() {
        return get_state_OutputBuffer_6;
    }
    @Override
    public final String get_state_OutputBuffer_6() {
        return get_state_OutputBuffer_6$str();
    }
    private static final String get_state_OutputBuffer_7 = "ARJUNA012196: com.arjuna.ats.arjuna.state.OutputBuffer_7 - Invalid input buffer: long.";
    protected String get_state_OutputBuffer_7$str() {
        return get_state_OutputBuffer_7;
    }
    @Override
    public final String get_state_OutputBuffer_7() {
        return get_state_OutputBuffer_7$str();
    }
    private static final String get_state_OutputBuffer_8 = "ARJUNA012197: com.arjuna.ats.arjuna.state.OutputBuffer_8 - Invalid input buffer: float.";
    protected String get_state_OutputBuffer_8$str() {
        return get_state_OutputBuffer_8;
    }
    @Override
    public final String get_state_OutputBuffer_8() {
        return get_state_OutputBuffer_8$str();
    }
    private static final String get_state_OutputBuffer_9 = "ARJUNA012198: com.arjuna.ats.arjuna.state.OutputBuffer_9 - Invalid input buffer: double";
    protected String get_state_OutputBuffer_9$str() {
        return get_state_OutputBuffer_9;
    }
    @Override
    public final String get_state_OutputBuffer_9() {
        return get_state_OutputBuffer_9$str();
    }
    @Override
    public final void info_tools_osb_util_JMXServer_m_1(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_tools_osb_util_JMXServer_m_1$str(), arg0);
    }
    private static final String info_tools_osb_util_JMXServer_m_1 = "ARJUNA012202: registering bean {0}.";
    protected String info_tools_osb_util_JMXServer_m_1$str() {
        return info_tools_osb_util_JMXServer_m_1;
    }
    @Override
    public final void info_tools_osb_util_JMXServer_m_2(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_tools_osb_util_JMXServer_m_2$str(), arg0);
    }
    private static final String info_tools_osb_util_JMXServer_m_2 = "ARJUNA012203: Instance already exists: {0}.";
    protected String info_tools_osb_util_JMXServer_m_2$str() {
        return info_tools_osb_util_JMXServer_m_2;
    }
    @Override
    public final void warn_tools_osb_util_JMXServer_m_3(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_tools_osb_util_JMXServer_m_3$str(), arg0);
    }
    private static final String warn_tools_osb_util_JMXServer_m_3 = "ARJUNA012204: Error registering {0}";
    protected String warn_tools_osb_util_JMXServer_m_3$str() {
        return warn_tools_osb_util_JMXServer_m_3;
    }
    @Override
    public final void warn_tools_osb_util_JMXServer_m_5(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_tools_osb_util_JMXServer_m_5$str(), arg0);
    }
    private static final String warn_tools_osb_util_JMXServer_m_5 = "ARJUNA012206: Unable to unregister bean {0}";
    protected String warn_tools_osb_util_JMXServer_m_5$str() {
        return warn_tools_osb_util_JMXServer_m_5;
    }
    @Override
    public final void warn_tools_osb_util_JMXServer_m_6(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_tools_osb_util_JMXServer_m_6$str(), arg0);
    }
    private static final String warn_tools_osb_util_JMXServer_m_6 = "ARJUNA012207: Unable to unregister bean {0}";
    protected String warn_tools_osb_util_JMXServer_m_6$str() {
        return warn_tools_osb_util_JMXServer_m_6;
    }
    @Override
    public final void warn_utils_FileLock_4(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_utils_FileLock_4$str(), arg0);
    }
    private static final String warn_utils_FileLock_4 = "ARJUNA012208: An error occurred while creating file {0}";
    protected String warn_utils_FileLock_4$str() {
        return warn_utils_FileLock_4;
    }
    @Override
    public final void warn_utils_Utility_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_utils_Utility_1$str());
    }
    private static final String warn_utils_Utility_1 = "ARJUNA012209: Utility.getDefaultProcess failed";
    protected String warn_utils_Utility_1$str() {
        return warn_utils_Utility_1;
    }
    @Override
    public final void warn_utils_Utility_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_utils_Utility_2$str());
    }
    private static final String warn_utils_Utility_2 = "ARJUNA012210: Unable to use InetAddress.getLocalHost() to resolve address.";
    protected String warn_utils_Utility_2$str() {
        return warn_utils_Utility_2;
    }
    @Override
    public final void warn_ats_atomicaction_1(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_ats_atomicaction_1$str(), arg0);
    }
    private static final String warn_ats_atomicaction_1 = "ARJUNA012211: Attempt to suspend a non-AtomicAction transaction. Type is {0}";
    protected String warn_ats_atomicaction_1$str() {
        return warn_ats_atomicaction_1;
    }
    @Override
    public final void warn_abstractrecords_smf1(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_abstractrecords_smf1$str());
    }
    private static final String warn_abstractrecords_smf1 = "ARJUNA012212: StateManagerFriend.forgetAction";
    protected String warn_abstractrecords_smf1$str() {
        return warn_abstractrecords_smf1;
    }
    @Override
    public final void warn_abstractrecords_smf2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_abstractrecords_smf2$str());
    }
    private static final String warn_abstractrecords_smf2 = "ARJUNA012213: StateManagerFriend.destroyed";
    protected String warn_abstractrecords_smf2$str() {
        return warn_abstractrecords_smf2;
    }
    @Override
    public final void warn_abstractrecords_smf3(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_abstractrecords_smf3$str());
    }
    private static final String warn_abstractrecords_smf3 = "ARJUNA012214: StateManagerFriend.rememberAction";
    protected String warn_abstractrecords_smf3$str() {
        return warn_abstractrecords_smf3;
    }
    @Override
    public final void warn_common_ClassloadingUtility_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_common_ClassloadingUtility_1$str());
    }
    private static final String warn_common_ClassloadingUtility_1 = "ARJUNA012215: className is null";
    protected String warn_common_ClassloadingUtility_1$str() {
        return warn_common_ClassloadingUtility_1;
    }
    @Override
    public final void warn_common_ClassloadingUtility_2(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_common_ClassloadingUtility_2$str(), arg0);
    }
    private static final String warn_common_ClassloadingUtility_2 = "ARJUNA012216: attempt to load {0} threw ClassNotFound. Wrong classloader?";
    protected String warn_common_ClassloadingUtility_2$str() {
        return warn_common_ClassloadingUtility_2;
    }
    @Override
    public final void warn_common_ClassloadingUtility_3(final String arg0, final String arg1, final Throwable arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg2, warn_common_ClassloadingUtility_3$str(), arg0, arg1);
    }
    private static final String warn_common_ClassloadingUtility_3 = "ARJUNA012217: class {0} does not implement {1}";
    protected String warn_common_ClassloadingUtility_3$str() {
        return warn_common_ClassloadingUtility_3;
    }
    @Override
    public final void warn_common_ClassloadingUtility_4(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_common_ClassloadingUtility_4$str(), arg0);
    }
    private static final String warn_common_ClassloadingUtility_4 = "ARJUNA012218: cannot create new instance of {0}";
    protected String warn_common_ClassloadingUtility_4$str() {
        return warn_common_ClassloadingUtility_4;
    }
    @Override
    public final void warn_common_ClassloadingUtility_5(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_common_ClassloadingUtility_5$str(), arg0);
    }
    private static final String warn_common_ClassloadingUtility_5 = "ARJUNA012219: cannot access {0}";
    protected String warn_common_ClassloadingUtility_5$str() {
        return warn_common_ClassloadingUtility_5;
    }
    @Override
    public final void warn_common_ClassloadingUtility_6(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_common_ClassloadingUtility_6$str(), arg0);
    }
    private static final String warn_common_ClassloadingUtility_6 = "ARJUNA012220: cannot initialize from string {0}";
    protected String warn_common_ClassloadingUtility_6$str() {
        return warn_common_ClassloadingUtility_6;
    }
    @Override
    public final void warn_objectstore_CacheStore_1(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_CacheStore_1$str(), arg0, arg1);
    }
    private static final String warn_objectstore_CacheStore_1 = "ARJUNA012221: Commit state failed for {0} and {1}";
    protected String warn_objectstore_CacheStore_1$str() {
        return warn_objectstore_CacheStore_1;
    }
    @Override
    public final void warn_objectstore_CacheStore_2(final Uid arg0, final String arg1, final String arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_CacheStore_2$str(), arg0, arg1, arg2);
    }
    private static final String warn_objectstore_CacheStore_2 = "ARJUNA012222: Remove state failed for {0} and {1} and {2}";
    protected String warn_objectstore_CacheStore_2$str() {
        return warn_objectstore_CacheStore_2;
    }
    @Override
    public final void warn_objectstore_CacheStore_3(final Uid arg0, final String arg1, final String arg2, final String arg3) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_CacheStore_3$str(), arg0, arg1, arg2, arg3);
    }
    private static final String warn_objectstore_CacheStore_3 = "ARJUNA012223: Write state failed for {0} and {1} and {2} and {3}";
    protected String warn_objectstore_CacheStore_3$str() {
        return warn_objectstore_CacheStore_3;
    }
    @Override
    public final void warn_objectstore_CacheStore_4(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_CacheStore_4$str(), arg0);
    }
    private static final String warn_objectstore_CacheStore_4 = "ARJUNA012224: Unknown work type {0}";
    protected String warn_objectstore_CacheStore_4$str() {
        return warn_objectstore_CacheStore_4;
    }
    private static final String get_objectstore_FileSystemStore_1 = "ARJUNA012225: FileSystemStore::setupStore - cannot access root of object store: {0}";
    protected String get_objectstore_FileSystemStore_1$str() {
        return get_objectstore_FileSystemStore_1;
    }
    @Override
    public final String get_objectstore_FileSystemStore_1(final String arg0) {
        return java.text.MessageFormat.format(get_objectstore_FileSystemStore_1$str(), arg0);
    }
    @Override
    public final void warn_objectstore_FileSystemStore_2(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_FileSystemStore_2$str(), arg0);
    }
    private static final String warn_objectstore_FileSystemStore_2 = "ARJUNA012226: FileSystemStore.removeFromCache - no entry for {0}";
    protected String warn_objectstore_FileSystemStore_2$str() {
        return warn_objectstore_FileSystemStore_2;
    }
    @Override
    public final void warn_objectstore_FileSystemStore_20(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_FileSystemStore_20$str(), arg0);
    }
    private static final String warn_objectstore_FileSystemStore_20 = "ARJUNA012227: FileSystemStore.renameFromTo - from {0} not present. Possibly renamed by crash recovery.";
    protected String warn_objectstore_FileSystemStore_20$str() {
        return warn_objectstore_FileSystemStore_20;
    }
    private static final String get_objectstore_FileSystemStore_2a = "ARJUNA012228: FileSystemStore::allObjUids - could not pack Uid.";
    protected String get_objectstore_FileSystemStore_2a$str() {
        return get_objectstore_FileSystemStore_2a;
    }
    @Override
    public final String get_objectstore_FileSystemStore_2a() {
        return get_objectstore_FileSystemStore_2a$str();
    }
    private static final String get_objectstore_FileSystemStore_3 = "ARJUNA012229: FileSystemStore::allObjUids - could not pack end of list Uid.";
    protected String get_objectstore_FileSystemStore_3$str() {
        return get_objectstore_FileSystemStore_3;
    }
    @Override
    public final String get_objectstore_FileSystemStore_3() {
        return get_objectstore_FileSystemStore_3$str();
    }
    private static final String get_objectstore_FileSystemStore_4 = "ARJUNA012230: FileSytemStore::allTypes - could not pack entry string.";
    protected String get_objectstore_FileSystemStore_4$str() {
        return get_objectstore_FileSystemStore_4;
    }
    @Override
    public final String get_objectstore_FileSystemStore_4() {
        return get_objectstore_FileSystemStore_4$str();
    }
    private static final String get_objectstore_FileSystemStore_5 = "ARJUNA012231: FileSystemStore::allTypes - could not pack end of list string.";
    protected String get_objectstore_FileSystemStore_5$str() {
        return get_objectstore_FileSystemStore_5;
    }
    @Override
    public final String get_objectstore_FileSystemStore_5() {
        return get_objectstore_FileSystemStore_5$str();
    }
    private static final String get_objectstore_FileSystemStore_6 = "ARJUNA012232: FileSystemStore::setupStore - error from unpack object store.";
    protected String get_objectstore_FileSystemStore_6$str() {
        return get_objectstore_FileSystemStore_6;
    }
    @Override
    public final String get_objectstore_FileSystemStore_6() {
        return get_objectstore_FileSystemStore_6$str();
    }
    private static final String get_objectstore_FileSystemStore_7 = "ARJUNA012233: FileSystemStore::allTypes - could not pack entry string.";
    protected String get_objectstore_FileSystemStore_7$str() {
        return get_objectstore_FileSystemStore_7;
    }
    @Override
    public final String get_objectstore_FileSystemStore_7() {
        return get_objectstore_FileSystemStore_7$str();
    }
    private static final String get_objectstore_FileSystemStore_8 = "ARJUNA012234: FileSystemStore::createHierarchy - null directory name.";
    protected String get_objectstore_FileSystemStore_8$str() {
        return get_objectstore_FileSystemStore_8;
    }
    @Override
    public final String get_objectstore_FileSystemStore_8() {
        return get_objectstore_FileSystemStore_8$str();
    }
    @Override
    public final void warn_objectstore_HashedStore_2(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_HashedStore_2$str(), arg0);
    }
    private static final String warn_objectstore_HashedStore_2 = "ARJUNA012236: invalid number of hash directories: {0}. Will use default.";
    protected String warn_objectstore_HashedStore_2$str() {
        return warn_objectstore_HashedStore_2;
    }
    private static final String get_objectstore_HashedStore_5 = "ARJUNA012237: HashedStore.allObjUids - could not pack Uid.";
    protected String get_objectstore_HashedStore_5$str() {
        return get_objectstore_HashedStore_5;
    }
    @Override
    public final String get_objectstore_HashedStore_5() {
        return get_objectstore_HashedStore_5$str();
    }
    private static final String get_objectstore_HashedStore_6 = "ARJUNA012238: HashedStore.allObjUids - could not pack end of list Uid.";
    protected String get_objectstore_HashedStore_6$str() {
        return get_objectstore_HashedStore_6;
    }
    @Override
    public final String get_objectstore_HashedStore_6() {
        return get_objectstore_HashedStore_6$str();
    }
    @Override
    public final void warn_objectstore_JDBCImple_1(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_1$str());
    }
    private static final String warn_objectstore_JDBCImple_1 = "ARJUNA012239: hide_state caught exception";
    protected String warn_objectstore_JDBCImple_1$str() {
        return warn_objectstore_JDBCImple_1;
    }
    @Override
    public final void warn_objectstore_JDBCImple_10(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_10$str(), arg0);
    }
    private static final String warn_objectstore_JDBCImple_10 = "ARJUNA012240: remove_state - type() operation of object with uid {0} returns NULL";
    protected String warn_objectstore_JDBCImple_10$str() {
        return warn_objectstore_JDBCImple_10;
    }
    @Override
    public final void warn_objectstore_JDBCImple_11(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_11$str(), arg0);
    }
    private static final String warn_objectstore_JDBCImple_11 = "ARJUNA012241: invalid initial pool size: {0}";
    protected String warn_objectstore_JDBCImple_11$str() {
        return warn_objectstore_JDBCImple_11;
    }
    @Override
    public final void warn_objectstore_JDBCImple_12(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_12$str(), arg0);
    }
    private static final String warn_objectstore_JDBCImple_12 = "ARJUNA012242: invalid maximum pool size: {0}";
    protected String warn_objectstore_JDBCImple_12$str() {
        return warn_objectstore_JDBCImple_12;
    }
    @Override
    public final void warn_objectstore_JDBCImple_13(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_13$str());
    }
    private static final String warn_objectstore_JDBCImple_13 = "ARJUNA012243: initialise caught exceptionatorLoader_3";
    protected String warn_objectstore_JDBCImple_13$str() {
        return warn_objectstore_JDBCImple_13;
    }
    @Override
    public final void warn_objectstore_JDBCImple_14(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_14$str());
    }
    private static final String warn_objectstore_JDBCImple_14 = "ARJUNA012244: getState caught exception";
    protected String warn_objectstore_JDBCImple_14$str() {
        return warn_objectstore_JDBCImple_14;
    }
    @Override
    public final void warn_objectstore_JDBCImple_15(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_15$str(), arg0);
    }
    private static final String warn_objectstore_JDBCImple_15 = "ARJUNA012245: removeFromCache - no entry for {0}";
    protected String warn_objectstore_JDBCImple_15$str() {
        return warn_objectstore_JDBCImple_15;
    }
    @Override
    public final void warn_objectstore_JDBCImple_16(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_16$str());
    }
    private static final String warn_objectstore_JDBCImple_16 = "ARJUNA012246: getPool caught exception";
    protected String warn_objectstore_JDBCImple_16$str() {
        return warn_objectstore_JDBCImple_16;
    }
    @Override
    public final void info_objectstore_JDBCImple_17() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_objectstore_JDBCImple_17$str());
    }
    private static final String info_objectstore_JDBCImple_17 = "ARJUNA012247: getPool - interrupted while waiting for a free connection";
    protected String info_objectstore_JDBCImple_17$str() {
        return info_objectstore_JDBCImple_17;
    }
    @Override
    public final void warn_objectstore_JDBCImple_18() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_18$str());
    }
    private static final String warn_objectstore_JDBCImple_18 = "ARJUNA012248: freePool - freeing a connection which is already free!";
    protected String warn_objectstore_JDBCImple_18$str() {
        return warn_objectstore_JDBCImple_18;
    }
    @Override
    public final void warn_objectstore_JDBCImple_2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_2$str());
    }
    private static final String warn_objectstore_JDBCImple_2 = "ARJUNA012249: reveal_state caught exception";
    protected String warn_objectstore_JDBCImple_2$str() {
        return warn_objectstore_JDBCImple_2;
    }
    @Override
    public final void warn_objectstore_JDBCImple_3(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_3$str());
    }
    private static final String warn_objectstore_JDBCImple_3 = "ARJUNA012250: currentState caught exception";
    protected String warn_objectstore_JDBCImple_3$str() {
        return warn_objectstore_JDBCImple_3;
    }
    @Override
    public final void warn_objectstore_JDBCImple_4(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_4$str());
    }
    private static final String warn_objectstore_JDBCImple_4 = "ARJUNA012251: allObjUids caught exception";
    protected String warn_objectstore_JDBCImple_4$str() {
        return warn_objectstore_JDBCImple_4;
    }
    @Override
    public final void warn_objectstore_JDBCImple_5(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_5$str());
    }
    private static final String warn_objectstore_JDBCImple_5 = "ARJUNA012252: allObjUids - pack of Uid failed";
    protected String warn_objectstore_JDBCImple_5$str() {
        return warn_objectstore_JDBCImple_5;
    }
    @Override
    public final void warn_objectstore_JDBCImple_6(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_6$str());
    }
    private static final String warn_objectstore_JDBCImple_6 = "ARJUNA012253: allTypes caught exception";
    protected String warn_objectstore_JDBCImple_6$str() {
        return warn_objectstore_JDBCImple_6;
    }
    @Override
    public final void warn_objectstore_JDBCImple_7(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_7$str());
    }
    private static final String warn_objectstore_JDBCImple_7 = "ARJUNA012254: allTypes - pack of Uid failed";
    protected String warn_objectstore_JDBCImple_7$str() {
        return warn_objectstore_JDBCImple_7;
    }
    @Override
    public final void warn_objectstore_JDBCImple_8(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_8$str());
    }
    private static final String warn_objectstore_JDBCImple_8 = "ARJUNA012255: remove_state caught exception";
    protected String warn_objectstore_JDBCImple_8$str() {
        return warn_objectstore_JDBCImple_8;
    }
    @Override
    public final void warn_objectstore_JDBCImple_9(final String arg0, final Uid arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_9$str(), arg0, arg1);
    }
    private static final String warn_objectstore_JDBCImple_9 = "ARJUNA012256: remove_state() attempted removal of {0} state for object with uid {1}";
    protected String warn_objectstore_JDBCImple_9$str() {
        return warn_objectstore_JDBCImple_9;
    }
    @Override
    public final void warn_objectstore_JDBCImple_readfailed() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_readfailed$str());
    }
    private static final String warn_objectstore_JDBCImple_readfailed = "ARJUNA012257: JDBCImple:read_state failed";
    protected String warn_objectstore_JDBCImple_readfailed$str() {
        return warn_objectstore_JDBCImple_readfailed;
    }
    @Override
    public final void warn_objectstore_JDBCImple_writefailed(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_JDBCImple_writefailed$str());
    }
    private static final String warn_objectstore_JDBCImple_writefailed = "ARJUNA012258: JDBCImple:write_state caught exception";
    protected String warn_objectstore_JDBCImple_writefailed$str() {
        return warn_objectstore_JDBCImple_writefailed;
    }
    @Override
    public final void fatal_objectstore_JDBCStore_1(final String arg0, final String arg1, final Throwable arg2) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, arg2, fatal_objectstore_JDBCStore_1$str(), arg0, arg1);
    }
    private static final String fatal_objectstore_JDBCStore_1 = "ARJUNA012259: JDBCStore could not setup store < {0} , {1} >";
    protected String fatal_objectstore_JDBCStore_1$str() {
        return fatal_objectstore_JDBCStore_1;
    }
    @Override
    public final void fatal_objectstore_JDBCStore_2(final String arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, arg1, fatal_objectstore_JDBCStore_2$str(), arg0);
    }
    private static final String fatal_objectstore_JDBCStore_2 = "ARJUNA012260: Received exception for {0}";
    protected String fatal_objectstore_JDBCStore_2$str() {
        return fatal_objectstore_JDBCStore_2;
    }
    @Override
    public final void warn_objectstore_JDBCStore_3() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCStore_3$str());
    }
    private static final String warn_objectstore_JDBCStore_3 = "ARJUNA012261: JDBCStore.setupStore failed to initialise!";
    protected String warn_objectstore_JDBCStore_3$str() {
        return warn_objectstore_JDBCStore_3;
    }
    private static final String get_objectstore_JDBCStore_5 = "ARJUNA012263: No JDBCAccess implementation provided!";
    protected String get_objectstore_JDBCStore_5$str() {
        return get_objectstore_JDBCStore_5;
    }
    @Override
    public final String get_objectstore_JDBCStore_5() {
        return get_objectstore_JDBCStore_5$str();
    }
    @Override
    public final void warn_objectstore_ShadowingStore_10(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_10$str(), arg0, arg1);
    }
    private static final String warn_objectstore_ShadowingStore_10 = "ARJUNA012265: ShadowingStore::remove_state() - state {0} does not exist for type {1}";
    protected String warn_objectstore_ShadowingStore_10$str() {
        return warn_objectstore_ShadowingStore_10;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_11(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_11$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_11 = "ARJUNA012266: ShadowingStore::remove_state() - unlink failed on {0}";
    protected String warn_objectstore_ShadowingStore_11$str() {
        return warn_objectstore_ShadowingStore_11;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_12(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_12$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_12 = "ARJUNA012267: ShadowingStore.remove_state() - fd error for {0}";
    protected String warn_objectstore_ShadowingStore_12$str() {
        return warn_objectstore_ShadowingStore_12;
    }
    @Override
    public final void info_objectstore_ShadowingStore_14(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_objectstore_ShadowingStore_14$str(), arg0, arg1);
    }
    private static final String info_objectstore_ShadowingStore_14 = "ARJUNA012269: UNKNOWN state for object with uid {0} , type {1}";
    protected String info_objectstore_ShadowingStore_14$str() {
        return info_objectstore_ShadowingStore_14;
    }
    @Override
    public final void info_objectstore_ShadowingStore_15(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_objectstore_ShadowingStore_15$str(), arg0, arg1);
    }
    private static final String info_objectstore_ShadowingStore_15 = "ARJUNA012270: HIDDEN state for object with uid {0} , type {1}";
    protected String info_objectstore_ShadowingStore_15$str() {
        return info_objectstore_ShadowingStore_15;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_17(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_17$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_17 = "ARJUNA012272: ShadowingStore.remove_state - type() operation of object with uid {0} returns NULL";
    protected String warn_objectstore_ShadowingStore_17$str() {
        return warn_objectstore_ShadowingStore_17;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_18(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_18$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_18 = "ARJUNA012273: ShadowingStore::write_state() - openAndLock failed for {0}";
    protected String warn_objectstore_ShadowingStore_18$str() {
        return warn_objectstore_ShadowingStore_18;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_19(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_19$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_19 = "ARJUNA012274: ShadowingStore::write_state - unlock or close of {0} failed.";
    protected String warn_objectstore_ShadowingStore_19$str() {
        return warn_objectstore_ShadowingStore_19;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_2(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_2$str(), arg0, arg1);
    }
    private static final String warn_objectstore_ShadowingStore_2 = "ARJUNA012275: ShadowStore::commit_state - failed to rename {0} to {1}";
    protected String warn_objectstore_ShadowingStore_2$str() {
        return warn_objectstore_ShadowingStore_2;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_3(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_3$str(), arg0, arg1);
    }
    private static final String warn_objectstore_ShadowingStore_3 = "ARJUNA012278: ShadowStore::hide_state - failed to rename {0} to {1}";
    protected String warn_objectstore_ShadowingStore_3$str() {
        return warn_objectstore_ShadowingStore_3;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_4(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_4$str(), arg0, arg1);
    }
    private static final String warn_objectstore_ShadowingStore_4 = "ARJUNA012279: ShadowStore::reveal_state - failed to rename {0} to {1}";
    protected String warn_objectstore_ShadowingStore_4$str() {
        return warn_objectstore_ShadowingStore_4;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_5(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_5$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_5 = "ARJUNA012280: ShadowingStore::read_state() - openAndLock failed for {0}";
    protected String warn_objectstore_ShadowingStore_5$str() {
        return warn_objectstore_ShadowingStore_5;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_7() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_7$str());
    }
    private static final String warn_objectstore_ShadowingStore_7 = "ARJUNA012282: ShadowingStore::read_state() failed";
    protected String warn_objectstore_ShadowingStore_7$str() {
        return warn_objectstore_ShadowingStore_7;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_8(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_8$str(), arg0);
    }
    private static final String warn_objectstore_ShadowingStore_8 = "ARJUNA012283: ShadowingStore::read_state - unlock or close of {0} failed";
    protected String warn_objectstore_ShadowingStore_8$str() {
        return warn_objectstore_ShadowingStore_8;
    }
    @Override
    public final void warn_objectstore_ShadowingStore_9(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_ShadowingStore_9$str(), arg0, arg1);
    }
    private static final String warn_objectstore_ShadowingStore_9 = "ARJUNA012284: ShadowingStore::remove_state() - access problems on {0} and {1}";
    protected String warn_objectstore_ShadowingStore_9$str() {
        return warn_objectstore_ShadowingStore_9;
    }
    @Override
    public final void warn_objectstore_jdbc_oracle_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_jdbc_oracle_1$str());
    }
    private static final String warn_objectstore_jdbc_oracle_1 = "ARJUNA012285: oracle:read_state failed";
    protected String warn_objectstore_jdbc_oracle_1$str() {
        return warn_objectstore_jdbc_oracle_1;
    }
    @Override
    public final void warn_objectstore_jdbc_oracle_2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_jdbc_oracle_2$str());
    }
    private static final String warn_objectstore_jdbc_oracle_2 = "ARJUNA012286: oracle:write_state caught exception";
    protected String warn_objectstore_jdbc_oracle_2$str() {
        return warn_objectstore_jdbc_oracle_2;
    }
    private static final String get_objectstore_notypenameuid = "ARJUNA012287: No typename for object:";
    protected String get_objectstore_notypenameuid$str() {
        return get_objectstore_notypenameuid;
    }
    @Override
    public final String get_objectstore_notypenameuid() {
        return get_objectstore_notypenameuid$str();
    }
    private static final String get_objectstore_packProblem = "ARJUNA012288: allTypes - could not pack end of list string.";
    protected String get_objectstore_packProblem$str() {
        return get_objectstore_packProblem;
    }
    @Override
    public final String get_objectstore_packProblem() {
        return get_objectstore_packProblem$str();
    }
    @Override
    public final void warn_recovery_AtomicActionRecoveryModule_1(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_AtomicActionRecoveryModule_1$str());
    }
    private static final String warn_recovery_AtomicActionRecoveryModule_1 = "ARJUNA012289: RecoveryManagerStatusModule: Object store exception";
    protected String warn_recovery_AtomicActionRecoveryModule_1$str() {
        return warn_recovery_AtomicActionRecoveryModule_1;
    }
    @Override
    public final void warn_recovery_AtomicActionRecoveryModule_2(final Uid arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_recovery_AtomicActionRecoveryModule_2$str(), arg0);
    }
    private static final String warn_recovery_AtomicActionRecoveryModule_2 = "ARJUNA012290: failed to recover Transaction {0}";
    protected String warn_recovery_AtomicActionRecoveryModule_2$str() {
        return warn_recovery_AtomicActionRecoveryModule_2;
    }
    @Override
    public final void warn_recovery_AtomicActionRecoveryModule_3(final Uid arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_recovery_AtomicActionRecoveryModule_3$str(), arg0);
    }
    private static final String warn_recovery_AtomicActionRecoveryModule_3 = "ARJUNA012291: failed to access transaction store {0}";
    protected String warn_recovery_AtomicActionRecoveryModule_3$str() {
        return warn_recovery_AtomicActionRecoveryModule_3;
    }
    @Override
    public final void warn_recovery_Connection_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_Connection_1$str());
    }
    private static final String warn_recovery_Connection_1 = "ARJUNA012292: Connection - IOException";
    protected String warn_recovery_Connection_1$str() {
        return warn_recovery_Connection_1;
    }
    @Override
    public final void warn_recovery_Connection_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_Connection_2$str());
    }
    private static final String warn_recovery_Connection_2 = "ARJUNA012293: Setting timeout exception.";
    protected String warn_recovery_Connection_2$str() {
        return warn_recovery_Connection_2;
    }
    @Override
    public final void info_recovery_ExpiredEntryMonitor_5() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_ExpiredEntryMonitor_5$str());
    }
    private static final String info_recovery_ExpiredEntryMonitor_5 = "ARJUNA012297: ExpiredEntryMonitor - no scans on first iteration";
    protected String info_recovery_ExpiredEntryMonitor_5$str() {
        return info_recovery_ExpiredEntryMonitor_5;
    }
    @Override
    public final void warn_recovery_ExpiredTransactionScanner_2(final Uid arg0, final Throwable arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg1, warn_recovery_ExpiredTransactionScanner_2$str(), arg0);
    }
    private static final String warn_recovery_ExpiredTransactionScanner_2 = "ARJUNA012301: ExpiredTransactionScanner - exception during attempted move {0}";
    protected String warn_recovery_ExpiredTransactionScanner_2$str() {
        return warn_recovery_ExpiredTransactionScanner_2;
    }
    @Override
    public final void info_recovery_ExpiredTransactionScanner_4(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_ExpiredTransactionScanner_4$str(), arg0);
    }
    private static final String info_recovery_ExpiredTransactionScanner_4 = "ARJUNA012303: ExpiredTransactionScanner - log {0} is assumed complete and will be moved.";
    protected String info_recovery_ExpiredTransactionScanner_4$str() {
        return info_recovery_ExpiredTransactionScanner_4;
    }
    @Override
    public final void info_recovery_PeriodicRecovery_13(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_PeriodicRecovery_13$str(), arg0, arg1);
    }
    private static final String info_recovery_PeriodicRecovery_13 = "ARJUNA012310: Recovery manager listening on endpoint {0}:{1}";
    protected String info_recovery_PeriodicRecovery_13$str() {
        return info_recovery_PeriodicRecovery_13;
    }
    @Override
    public final void warn_recovery_PeriodicRecovery_9(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_PeriodicRecovery_9$str());
    }
    private static final String warn_recovery_PeriodicRecovery_9 = "ARJUNA012318: Could not create recovery listener";
    protected String warn_recovery_PeriodicRecovery_9$str() {
        return warn_recovery_PeriodicRecovery_9;
    }
    @Override
    public final void warn_recovery_RecoveryManagerImple_2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_RecoveryManagerImple_2$str());
    }
    private static final String warn_recovery_RecoveryManagerImple_2 = "ARJUNA012326: socket I/O exception";
    protected String warn_recovery_RecoveryManagerImple_2$str() {
        return warn_recovery_RecoveryManagerImple_2;
    }
    @Override
    public final void warn_recovery_TransactionStatusConnector_1() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_TransactionStatusConnector_1$str());
    }
    private static final String warn_recovery_TransactionStatusConnector_1 = "ARJUNA012327: TransactionStatusConnector.delete called erroneously";
    protected String warn_recovery_TransactionStatusConnector_1$str() {
        return warn_recovery_TransactionStatusConnector_1;
    }
    @Override
    public final void warn_recovery_TransactionStatusConnector_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_TransactionStatusConnector_2$str());
    }
    private static final String warn_recovery_TransactionStatusConnector_2 = "ARJUNA012328: Connection lost to TransactionStatusManagers'' process";
    protected String warn_recovery_TransactionStatusConnector_2$str() {
        return warn_recovery_TransactionStatusConnector_2;
    }
    @Override
    public final void warn_recovery_TransactionStatusConnector_3() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_TransactionStatusConnector_3$str());
    }
    private static final String warn_recovery_TransactionStatusConnector_3 = "ARJUNA012329: Connection lost to TransactionStatusManagers'' process";
    protected String warn_recovery_TransactionStatusConnector_3$str() {
        return warn_recovery_TransactionStatusConnector_3;
    }
    @Override
    public final void info_recovery_TransactionStatusConnector_4(final String arg0, final String arg1, final String arg2, final String arg3) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_TransactionStatusConnector_4$str(), arg0, arg1, arg2, arg3);
    }
    private static final String info_recovery_TransactionStatusConnector_4 = "ARJUNA012330: TransactionStatusManager process for uid {0} is ALIVE. connected to host: {1}, port: {2} on socket: {3}";
    protected String info_recovery_TransactionStatusConnector_4$str() {
        return info_recovery_TransactionStatusConnector_4;
    }
    @Override
    public final void info_recovery_TransactionStatusConnector_5(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_TransactionStatusConnector_5$str(), arg0);
    }
    private static final String info_recovery_TransactionStatusConnector_5 = "ARJUNA012331: TransactionStatusManager process for uid {0} is DEAD.";
    protected String info_recovery_TransactionStatusConnector_5$str() {
        return info_recovery_TransactionStatusConnector_5;
    }
    @Override
    public final void info_recovery_TransactionStatusConnector_6() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_TransactionStatusConnector_6$str());
    }
    private static final String info_recovery_TransactionStatusConnector_6 = "ARJUNA012332: Failed to establish connection to server";
    protected String info_recovery_TransactionStatusConnector_6$str() {
        return info_recovery_TransactionStatusConnector_6;
    }
    @Override
    public final void warn_recovery_TransactionStatusManagerItem_1(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_TransactionStatusManagerItem_1$str());
    }
    private static final String warn_recovery_TransactionStatusManagerItem_1 = "ARJUNA012333: Problem with removing host/port item";
    protected String warn_recovery_TransactionStatusManagerItem_1$str() {
        return warn_recovery_TransactionStatusManagerItem_1;
    }
    @Override
    public final void warn_recovery_TransactionStatusManagerItem_2(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_TransactionStatusManagerItem_2$str());
    }
    private static final String warn_recovery_TransactionStatusManagerItem_2 = "ARJUNA012334: Problem with storing host/port";
    protected String warn_recovery_TransactionStatusManagerItem_2$str() {
        return warn_recovery_TransactionStatusManagerItem_2;
    }
    @Override
    public final void warn_recovery_TransactionStatusManagerItem_3(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_TransactionStatusManagerItem_3$str());
    }
    private static final String warn_recovery_TransactionStatusManagerItem_3 = "ARJUNA012335: Problem retrieving host/port";
    protected String warn_recovery_TransactionStatusManagerItem_3$str() {
        return warn_recovery_TransactionStatusManagerItem_3;
    }
    @Override
    public final void warn_recovery_TransactionStatusManagerItem_4(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_TransactionStatusManagerItem_4$str());
    }
    private static final String warn_recovery_TransactionStatusManagerItem_4 = "ARJUNA012336: Failed to obtain host";
    protected String warn_recovery_TransactionStatusManagerItem_4$str() {
        return warn_recovery_TransactionStatusManagerItem_4;
    }
    @Override
    public final void warn_recovery_WorkerService_1(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_recovery_WorkerService_1$str());
    }
    private static final String warn_recovery_WorkerService_1 = "ARJUNA012338: Other Exception:";
    protected String warn_recovery_WorkerService_1$str() {
        return warn_recovery_WorkerService_1;
    }
    @Override
    public final void warn_recovery_WorkerService_2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_WorkerService_2$str());
    }
    private static final String warn_recovery_WorkerService_2 = "ARJUNA012339: IOException";
    protected String warn_recovery_WorkerService_2$str() {
        return warn_recovery_WorkerService_2;
    }
    @Override
    public final void info_recovery_WorkerService_3() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_WorkerService_3$str());
    }
    private static final String info_recovery_WorkerService_3 = "ARJUNA012340: RecoveryManager scan scheduled to begin.";
    protected String info_recovery_WorkerService_3$str() {
        return info_recovery_WorkerService_3;
    }
    @Override
    public final void info_recovery_WorkerService_4() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_WorkerService_4$str());
    }
    private static final String info_recovery_WorkerService_4 = "ARJUNA012341: RecoveryManager scan completed.";
    protected String info_recovery_WorkerService_4$str() {
        return info_recovery_WorkerService_4;
    }
    @Override
    public final void fatal_recovery_fail(final String arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, null, fatal_recovery_fail$str(), arg0, arg1);
    }
    private static final String fatal_recovery_fail = "ARJUNA012342: RecoveryManagerImple: cannot bind to socket on address {0} and port {1}";
    protected String fatal_recovery_fail$str() {
        return fatal_recovery_fail;
    }
    @Override
    public final void info_recovery_socketready(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_recovery_socketready$str(), arg0);
    }
    private static final String info_recovery_socketready = "ARJUNA012344: RecoveryManagerImple is ready on port {0}";
    protected String info_recovery_socketready$str() {
        return info_recovery_socketready;
    }
    @Override
    public final void warn_tools_log_eaa1(final Uid arg0, final String arg1) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_tools_log_eaa1$str(), arg0, arg1);
    }
    private static final String warn_tools_log_eaa1 = "ARJUNA012345: Transaction {0} and {1} not activate.";
    protected String warn_tools_log_eaa1$str() {
        return warn_tools_log_eaa1;
    }
    @Override
    public final void warn_tools_log_eaa2() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_tools_log_eaa2$str());
    }
    private static final String warn_tools_log_eaa2 = "ARJUNA012346: Error - could not get resource to forget heuristic. Left on Heuristic List.";
    protected String warn_tools_log_eaa2$str() {
        return warn_tools_log_eaa2;
    }
    private static final String get_utils_ExecProcessId_1 = "ARJUNA012347: Could not get back a valid pid.";
    protected String get_utils_ExecProcessId_1$str() {
        return get_utils_ExecProcessId_1;
    }
    @Override
    public final String get_utils_ExecProcessId_1() {
        return get_utils_ExecProcessId_1$str();
    }
    private static final String get_utils_ExecProcessId_2 = "ARJUNA012348: Problem executing getpids utility:";
    protected String get_utils_ExecProcessId_2$str() {
        return get_utils_ExecProcessId_2;
    }
    @Override
    public final String get_utils_ExecProcessId_2() {
        return get_utils_ExecProcessId_2$str();
    }
    private static final String get_utils_ExecProcessId_3 = "ARJUNA012349: Problem executing command:";
    protected String get_utils_ExecProcessId_3$str() {
        return get_utils_ExecProcessId_3;
    }
    @Override
    public final String get_utils_ExecProcessId_3() {
        return get_utils_ExecProcessId_3$str();
    }
    private static final String get_utils_ExecProcessId_4 = "ARJUNA012350: Problem getting pid information from stream:";
    protected String get_utils_ExecProcessId_4$str() {
        return get_utils_ExecProcessId_4;
    }
    @Override
    public final String get_utils_ExecProcessId_4() {
        return get_utils_ExecProcessId_4$str();
    }
    @Override
    public final void warn_utils_ExecProcessId_5(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_utils_ExecProcessId_5$str());
    }
    private static final String warn_utils_ExecProcessId_5 = "ARJUNA012351: Encountered a problem when closing the data stream";
    protected String warn_utils_ExecProcessId_5$str() {
        return warn_utils_ExecProcessId_5;
    }
    private static final String get_utils_FileProcessId_1 = "ARJUNA012352: FileProcessId.getpid - could not locate temporary directory.";
    protected String get_utils_FileProcessId_1$str() {
        return get_utils_FileProcessId_1;
    }
    @Override
    public final String get_utils_FileProcessId_1() {
        return get_utils_FileProcessId_1$str();
    }
    private static final String get_utils_FileProcessId_2 = "ARJUNA012353: FileProcessId.getpid could not create unique file.";
    protected String get_utils_FileProcessId_2$str() {
        return get_utils_FileProcessId_2;
    }
    @Override
    public final String get_utils_FileProcessId_2() {
        return get_utils_FileProcessId_2$str();
    }
    private static final String get_utils_MBeanProcessId_1 = "ARJUNA012354: Could not get back a valid pid.";
    protected String get_utils_MBeanProcessId_1$str() {
        return get_utils_MBeanProcessId_1;
    }
    @Override
    public final String get_utils_MBeanProcessId_1() {
        return get_utils_MBeanProcessId_1$str();
    }
    private static final String get_utils_MBeanProcessId_2 = "ARJUNA012355: getName returned unrecognized format:";
    protected String get_utils_MBeanProcessId_2$str() {
        return get_utils_MBeanProcessId_2;
    }
    @Override
    public final String get_utils_MBeanProcessId_2() {
        return get_utils_MBeanProcessId_2$str();
    }
    private static final String get_utils_ManualProcessId_1 = "ARJUNA012356: Could not get back a valid pid.";
    protected String get_utils_ManualProcessId_1$str() {
        return get_utils_ManualProcessId_1;
    }
    @Override
    public final String get_utils_ManualProcessId_1() {
        return get_utils_ManualProcessId_1$str();
    }
    private static final String get_utils_SocketProcessId_2 = "ARJUNA012359: SocketProcessId.getpid could not get unique port.";
    protected String get_utils_SocketProcessId_2$str() {
        return get_utils_SocketProcessId_2;
    }
    @Override
    public final String get_utils_SocketProcessId_2() {
        return get_utils_SocketProcessId_2$str();
    }
    @Override
    public final void info_osb_MBeanCtorFail(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, arg0, info_osb_MBeanCtorFail$str());
    }
    private static final String info_osb_MBeanCtorFail = "ARJUNA012361: Error constructing mbean";
    protected String info_osb_MBeanCtorFail$str() {
        return info_osb_MBeanCtorFail;
    }
    @Override
    public final void info_osb_StateManagerWrapperFail(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, arg0, info_osb_StateManagerWrapperFail$str());
    }
    private static final String info_osb_StateManagerWrapperFail = "ARJUNA012362: Failed to create StateManagerWrapper";
    protected String info_osb_StateManagerWrapperFail$str() {
        return info_osb_StateManagerWrapperFail;
    }
    private static final String get_StoreManager_invalidroot = "ARJUNA012363: Invalid rootName. Expected {0} but was {1}";
    protected String get_StoreManager_invalidroot$str() {
        return get_StoreManager_invalidroot;
    }
    @Override
    public final String get_StoreManager_invalidroot(final String arg0, final String arg1) {
        return java.text.MessageFormat.format(get_StoreManager_invalidroot$str(), arg0, arg1);
    }
    private static final String get_recovery_RecActivatorLoader_initfailed = "ARJUNA012364: RecoveryActivator init failed for {0}";
    protected String get_recovery_RecActivatorLoader_initfailed$str() {
        return get_recovery_RecActivatorLoader_initfailed;
    }
    @Override
    public final String get_recovery_RecActivatorLoader_initfailed(final String arg0) {
        return java.text.MessageFormat.format(get_recovery_RecActivatorLoader_initfailed$str(), arg0);
    }
    private static final String get_method_not_implemented = "ARJUNA012365: Method not implemented";
    protected String get_method_not_implemented$str() {
        return get_method_not_implemented;
    }
    @Override
    public final String get_method_not_implemented() {
        return get_method_not_implemented$str();
    }
    @Override
    public final void warn_journal_load_error() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_journal_load_error$str());
    }
    private static final String warn_journal_load_error = "ARJUNA012366: Unexpected data read from journal - file may be corrupt.";
    protected String warn_journal_load_error$str() {
        return warn_journal_load_error;
    }
    private static final String get_dir_create_failed = "ARJUNA012367: Failed to create store dir {0}";
    protected String get_dir_create_failed$str() {
        return get_dir_create_failed;
    }
    @Override
    public final String get_dir_create_failed(final String arg0) {
        return java.text.MessageFormat.format(get_dir_create_failed$str(), arg0);
    }
    private static final String get_node_identifier_invalid = "ARJUNA012368: Node identifiers must be an integer and must be 1 or greater: {0}";
    protected String get_node_identifier_invalid$str() {
        return get_node_identifier_invalid;
    }
    @Override
    public final String get_node_identifier_invalid(final int nodeIdentifier) {
        return java.text.MessageFormat.format(get_node_identifier_invalid$str(), nodeIdentifier);
    }
    private static final String get_node_identifier_reset_attempt = "ARJUNA012369: The node identifier was already set";
    protected String get_node_identifier_reset_attempt$str() {
        return get_node_identifier_reset_attempt;
    }
    @Override
    public final String get_node_identifier_reset_attempt() {
        return get_node_identifier_reset_attempt$str();
    }
    @Override
    public final void trace_JDBCImple_previouslycommitteddeleted(final int rowcount) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.TRACE, null, trace_JDBCImple_previouslycommitteddeleted$str(), rowcount);
    }
    private static final String trace_JDBCImple_previouslycommitteddeleted = "ARJUNA012370: Previously committed row(s) deleted {0}";
    protected String trace_JDBCImple_previouslycommitteddeleted$str() {
        return trace_JDBCImple_previouslycommitteddeleted;
    }
    @Override
    public final void warn_objectstore_JDBCImple_over_max_image_size(final int imageSize, final int maxStateSize) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_over_max_image_size$str(), imageSize, maxStateSize);
    }
    private static final String warn_objectstore_JDBCImple_over_max_image_size = "ARJUNA012371: Image size {0} is greater than max allowed {1}";
    protected String warn_objectstore_JDBCImple_over_max_image_size$str() {
        return warn_objectstore_JDBCImple_over_max_image_size;
    }
    @Override
    public final void fatal_nodename_too_long(final String xaNodeName, final Integer nameLength) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, null, fatal_nodename_too_long$str(), xaNodeName, nameLength);
    }
    private static final String fatal_nodename_too_long = "ARJUNA012372: The node identifier {0} was too long {1}, aborting initialization";
    protected String fatal_nodename_too_long$str() {
        return fatal_nodename_too_long;
    }
    private static final String get_fatal_nodename_too_long = "ARJUNA012373: The node identifier {0} was too long {1}, aborting initialization";
    protected String get_fatal_nodename_too_long$str() {
        return get_fatal_nodename_too_long;
    }
    @Override
    public final String get_fatal_nodename_too_long(final String xaNodeName, final Integer nameLength) {
        return java.text.MessageFormat.format(get_fatal_nodename_too_long$str(), xaNodeName, nameLength);
    }
    @Override
    public final void fatal_nodename_null() {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, null, fatal_nodename_null$str());
    }
    private static final String fatal_nodename_null = "ARJUNA012374: The node identifier cannot be null, aborting initialization";
    protected String fatal_nodename_null$str() {
        return fatal_nodename_null;
    }
    private static final String get_fatal_nodename_null = "ARJUNA012375: The node identifier cannot be null, aborting initialization";
    protected String get_fatal_nodename_null$str() {
        return get_fatal_nodename_null;
    }
    @Override
    public final String get_fatal_nodename_null() {
        return get_fatal_nodename_null$str();
    }
    @Override
    public final void warn_objectstore_remove_state_exception(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_objectstore_remove_state_exception$str());
    }
    private static final String warn_objectstore_remove_state_exception = "ARJUNA012376: ObjectStore remove_state caught exception:";
    protected String warn_objectstore_remove_state_exception$str() {
        return warn_objectstore_remove_state_exception;
    }
    @Override
    public final void warn_hornetqobjectstore_remove_state_exception(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_hornetqobjectstore_remove_state_exception$str());
    }
    private static final String warn_hornetqobjectstore_remove_state_exception = "ARJUNA012377: HornetqObjectStore remove_state caught exception:";
    protected String warn_hornetqobjectstore_remove_state_exception$str() {
        return warn_hornetqobjectstore_remove_state_exception;
    }
    @Override
    public final void wedged_reaperelement(final String arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, wedged_reaperelement$str(), arg0);
    }
    private static final String wedged_reaperelement = "ARJUNA012378: ReaperElement appears to be wedged: {0}";
    protected String wedged_reaperelement$str() {
        return wedged_reaperelement;
    }
    @Override
    public final void warn_recovery_ExpiredTransactionStatusManagerScanner_6(final Uid arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_recovery_ExpiredTransactionStatusManagerScanner_6$str(), arg0);
    }
    private static final String warn_recovery_ExpiredTransactionStatusManagerScanner_6 = "ARJUNA012379: ExpiredTransactionScanner - {0} is assumed complete and will be moved.";
    protected String warn_recovery_ExpiredTransactionStatusManagerScanner_6$str() {
        return warn_recovery_ExpiredTransactionStatusManagerScanner_6;
    }
    @Override
    public final void info_osb_HeaderStateCtorFail(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, arg0, info_osb_HeaderStateCtorFail$str());
    }
    private static final String info_osb_HeaderStateCtorFail = "ARJUNA012380: OSB: Error constructing record header reader";
    protected String info_osb_HeaderStateCtorFail$str() {
        return info_osb_HeaderStateCtorFail;
    }
    @Override
    public final void warn_multiple_threads(final Uid objectUid, final String key, final String string) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_multiple_threads$str(), objectUid, key, string);
    }
    private static final String warn_multiple_threads = "ARJUNA012381: Action id {0} completed with multiple threads - thread {1} was in progress with {2}";
    protected String warn_multiple_threads$str() {
        return warn_multiple_threads;
    }
    @Override
    public final void warn_objectstore_JDBCImple_nothingtocommit(final String string) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_nothingtocommit$str(), string);
    }
    private static final String warn_objectstore_JDBCImple_nothingtocommit = "ARJUNA012382: Action id {0} could not be transitioned to committed";
    protected String warn_objectstore_JDBCImple_nothingtocommit$str() {
        return warn_objectstore_JDBCImple_nothingtocommit;
    }
    @Override
    public final void warn_objectstore_JDBCImple_nothingtoupdate(final String string) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_nothingtoupdate$str(), string);
    }
    private static final String warn_objectstore_JDBCImple_nothingtoupdate = "ARJUNA012383: Action id {0} could not be updated during write_state";
    protected String warn_objectstore_JDBCImple_nothingtoupdate$str() {
        return warn_objectstore_JDBCImple_nothingtoupdate;
    }
    @Override
    public final void warn_objectstore_JDBCImple_nothingtoinsert(final String string) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, null, warn_objectstore_JDBCImple_nothingtoinsert$str(), string);
    }
    private static final String warn_objectstore_JDBCImple_nothingtoinsert = "ARJUNA012384: Action id {0} could not be inserted during write_state";
    protected String warn_objectstore_JDBCImple_nothingtoinsert$str() {
        return warn_objectstore_JDBCImple_nothingtoinsert;
    }
    private static final String warn_objectstore_JDBCImple_readfailed_message = "ARJUNA012385: Could not read from object store";
    protected String warn_objectstore_JDBCImple_readfailed_message$str() {
        return warn_objectstore_JDBCImple_readfailed_message;
    }
    @Override
    public final String warn_objectstore_JDBCImple_readfailed_message() {
        return warn_objectstore_JDBCImple_readfailed_message$str();
    }
    private static final String unexpected_state_type = "ARJUNA012386: Unexpected state type {0}";
    protected String unexpected_state_type$str() {
        return unexpected_state_type;
    }
    @Override
    public final String unexpected_state_type(final int stateType) {
        return java.text.MessageFormat.format(unexpected_state_type$str(), stateType);
    }
    @Override
    public final void fatal_encoding_not_supported(final String encodingName) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.FATAL, null, fatal_encoding_not_supported$str(), encodingName);
    }
    private static final String fatal_encoding_not_supported = "ARJUNA012387: Encoding {0} is not supported";
    protected String fatal_encoding_not_supported$str() {
        return fatal_encoding_not_supported;
    }
    private static final String get_encoding_not_supported = "ARJUNA012388: Encoding {0} is not supported";
    protected String get_encoding_not_supported$str() {
        return get_encoding_not_supported;
    }
    @Override
    public final String get_encoding_not_supported(final String encodingName) {
        return java.text.MessageFormat.format(get_encoding_not_supported$str(), encodingName);
    }
    @Override
    public final void info_osb_HeaderStateCtorInfo(final String reason) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.INFO, null, info_osb_HeaderStateCtorInfo$str(), reason);
    }
    private static final String info_osb_HeaderStateCtorInfo = "ARJUNA012389: OSB: Error constructing record header reader: {0}";
    protected String info_osb_HeaderStateCtorInfo$str() {
        return info_osb_HeaderStateCtorInfo;
    }
    @Override
    public final void warn_osb_MBeanCtorFail(final Throwable arg0) {
        log.logv(FQCN, org.jboss.logging.Logger.Level.WARN, arg0, warn_osb_MBeanCtorFail$str());
    }
    private static final String warn_osb_MBeanCtorFail = "ARJUNA012390: Error constructing mbean";
    protected String warn_osb_MBeanCtorFail$str() {
        return warn_osb_MBeanCtorFail;
    }
    private static final String init_StoreManager_instantiate_class_failure = "ARJUNA012391: Could not initialize object store '{0}' of type '{1}'";
    protected String init_StoreManager_instantiate_class_failure$str() {
        return init_StoreManager_instantiate_class_failure;
    }
    @Override
    public final String init_StoreManager_instantiate_class_failure(final String name, final String type) {
        return java.text.MessageFormat.format(init_StoreManager_instantiate_class_failure$str(), name, type);
    }
}
