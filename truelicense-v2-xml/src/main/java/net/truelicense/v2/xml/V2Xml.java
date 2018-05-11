/*
 * Copyright (C) 2005-2017 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package net.truelicense.v2.xml;

import net.truelicense.api.License;
import net.truelicense.api.LicenseManagementContextBuilder;
import net.truelicense.v2.core.V2;
import net.truelicense.v2.core.auth.V2RepositoryModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * This facade provides a static factory method for license management context builders for Version 2-with-XML (V2/XML)
 * format license keys.
 *
 * @author Christian Schlichtherle
 */
public final class V2Xml {

    /**
     * Returns a new license management context builder for managing Version 2-with-XML (V2/XML) format license keys.
     */
    public static LicenseManagementContextBuilder builder() { return builder(jaxbContext()); }

    /**
     * Returns a new license management context builder for managing Version 2-with-XML (V2/XML) format license keys
     * using the given JAXB context.
     */
    public static LicenseManagementContextBuilder builder(JAXBContext ctx) {
        return V2.builder().codec(new XMLCodec(ctx));
    }

    private static JAXBContext jaxbContext() {
        try {
            return JAXBContext.newInstance(License.class, V2RepositoryModel.class);
        } catch (final JAXBException ex) {
            throw new AssertionError(ex);
        }
    }

    private V2Xml() { }
}
