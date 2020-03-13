/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc. and/or its affiliates,
 * and individual contributors as indicated by the @author tags.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * (C) 2010,
 * @author JBoss, by Red Hat.
 */
package io.narayana.tracing.logging;

import static org.jboss.logging.Logger.Level.WARN;
import static org.jboss.logging.annotations.Message.Format.MESSAGE_FORMAT;

import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

import io.narayana.tracing.names.SpanName;

/**
 * i18n log messages for the narayanatracing module.
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
@MessageLogger(projectCode = "ARJUNA")
public interface TracingI18NLogger {

    @Message(id = 10001, value = "No txn root span found for txn id {0} when building span '{1}'", format = MESSAGE_FORMAT)
    @LogMessage(level = WARN)
    public void warnNoRootSpan(String id, SpanName name);

}
